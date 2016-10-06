package net.alhazmy13.hijricalenderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;

import net.alhazmy13.hijridatepicker.HijriCalendarDialog;
import net.alhazmy13.hijridatepicker.HijriCalendarView;
import net.alhazmy13.hijridatepicker.calendar.CalendarInstance;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements HijriCalendarView.OnDateSetListener {

    private int day,month,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarInstance calendarInstance = new CalendarInstance(this,HijriCalendarDialog.Mode.Hijri.getModeValue());
        day=calendarInstance.getDayOfMonth();
        month=calendarInstance.getCurrentMonth();
        year=calendarInstance.getCurrentYear();
        Log.i("HijriPicker",year + "/" + (month + 1) + "/" + day);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HijriCalendarDialog.Builder(MainActivity.this)
                        .setOnDateSetListener(MainActivity.this)
                        .setMode(HijriCalendarDialog.Mode.Hijri)
                        .setEnableScrolling(false)
                        .setUILanguage(HijriCalendarDialog.Language.Arabic)
                        .setUIView(HijriCalendarDialog.UiView.Land)
                        .setDefaultHijriDate(day,month,year)
                        .show();

            }
        });

    }

    @Override
    public void onDateSet(int mYear, int mMonth, int mDay) {
        day = mDay;
        month = mMonth;
        year = mYear;
        ((TextView) findViewById(R.id.textView)).setText(year + "/" + (month + 1) + "/" + day);
    }


}
