package net.alhazmy13.hijridatepickerexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import com.google.android.material.button.MaterialButton;

import net.alhazmy13.hijridatepicker.date.hijri.HijriDatePickerDialog;

import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class HijriDatePickerFragment extends Fragment implements HijriDatePickerDialog.OnDateSetListener {

    private TextView dateTextView;
    private CheckBox modeDarkDate;
    private CheckBox modeCustomAccentDate;
    private CheckBox vibrateDate;
    private CheckBox dismissDate;
    private CheckBox titleDate;
    private CheckBox showYearFirst;
    private CheckBox showVersion2;
    private CheckBox limitSelectableDays;
    private CheckBox highlightDays;

    public HijriDatePickerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.datepicker_layout, container, false);

        // Find our View instances
        dateTextView = view.findViewById(R.id.date_textview);
        MaterialButton dateButton = view.findViewById(R.id.date_button);
        modeDarkDate = view.findViewById(R.id.mode_dark_date);
        modeCustomAccentDate = view.findViewById(R.id.mode_custom_accent_date);
        vibrateDate = view.findViewById(R.id.vibrate_date);
        dismissDate = view.findViewById(R.id.dismiss_date);
        titleDate = view.findViewById(R.id.title_date);
        showYearFirst = view.findViewById(R.id.show_year_first);
        showVersion2 = view.findViewById(R.id.show_version_2);
        limitSelectableDays = view.findViewById(R.id.limit_dates);
        highlightDays = view.findViewById(R.id.highlight_dates);

        // Show a datepicker when the dateButton is clicked
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UmmalquraCalendar now = new UmmalquraCalendar();
                HijriDatePickerDialog dpd = HijriDatePickerDialog.newInstance(
                        HijriDatePickerFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setMinDate(new UmmalquraCalendar(1300,0,1));
                dpd.setThemeDark(modeDarkDate.isChecked());
                dpd.vibrate(vibrateDate.isChecked());
                dpd.dismissOnPause(dismissDate.isChecked());
                dpd.showYearPickerFirst(showYearFirst.isChecked());
                dpd.setVersion(showVersion2.isChecked() ? HijriDatePickerDialog.Version.VERSION_2 : HijriDatePickerDialog.Version.VERSION_1);
                if (modeCustomAccentDate.isChecked()) {
                    dpd.setAccentColor(Color.parseColor("#009688"));
                }
                if (titleDate.isChecked()) {
                    dpd.setTitle("DatePicker Title");
                }
                if (highlightDays.isChecked()) {
                    UmmalquraCalendar date1 = new UmmalquraCalendar();
                    UmmalquraCalendar date2 = new UmmalquraCalendar();
                    date2.add(Calendar.WEEK_OF_MONTH, -1);
                    UmmalquraCalendar date3 = new UmmalquraCalendar();
                    date3.add(Calendar.WEEK_OF_MONTH, 1);
                    UmmalquraCalendar[] days = {date1, date2, date3};
                    dpd.setHighlightedDays(days);
                }
                if (limitSelectableDays.isChecked()) {
                    UmmalquraCalendar[] days = new UmmalquraCalendar[13];
                    for (int i = -6; i < 7; i++) {
                        UmmalquraCalendar day = new UmmalquraCalendar();
                        day.add(Calendar.DAY_OF_MONTH, i * 2);
                        days[i + 6] = day;
                    }
//                    dpd.setSelectableDays(days);
                }
                //Change the language to any of supported language
                dpd.setLocale(Locale.getDefault());
                assert getFragmentManager() != null;
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        assert getFragmentManager() != null;
        HijriDatePickerDialog dpd = (HijriDatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }


    @Override
    public void onDateSet(HijriDatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        dateTextView.setText(date);
    }
}
