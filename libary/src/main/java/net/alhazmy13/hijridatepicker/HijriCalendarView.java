package net.alhazmy13.hijridatepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import net.alhazmy13.hijridatepicker.calendar.CalendarInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alhazmy13 on 10/15/15.
 */
public class HijriCalendarView extends Dialog implements MonthDialog.OnMonthChanged, View.OnClickListener,YearDialog.OnYearChanged,DefaultValue {
    private Context context;
    private String[] days ;
    private TextView dayTextView, monthTextView, yearTextView,lastSelectedDay;
    private TableLayout tableLayout;
    private CalendarInstance calendarInstance;
    private List<TextView> textViewList;
    private Button doneButton, cancelButton;
    private TableRow daysHeader;


    public interface OnDateSetListener{
        void onDateSet(int year, int month, int day);
    }

    /**
     *
     * @param langCode
     */
    private void callSwitchLang(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
        onCreate(null);
    }

    /**
     *
     * @param context
     */
    public HijriCalendarView(final Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_hijri_calendar);
        this.context=context;

        flowFunctions();

    }


    private void flowFunctions(){
        initViews();
        initHeaderOfCalender();
        initDays();
        initListener();
    }


    /**
     *
     * @param month
     */
    @Override
    public void onMonthChanged(int month) {
        calendarInstance.setMonth(month+1);
        initDays();
    }

    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        TextView temp = (TextView) view;
        if (!temp.getText().toString().isEmpty()) {
            lastSelectedDay.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
            lastSelectedDay.setBackground(null);


            temp.setBackgroundColor(context.getResources().getColor(R.color.hijri_date_picker_accent_color));
            temp.setTextColor(context.getResources().getColor(android.R.color.white));
            lastSelectedDay = temp;
            dayTextView.setText(temp.getText().toString());
            calendarInstance.setDay(Integer.parseInt(temp.getText().toString()));

        }
    }

    /**
     *
     */
    private void initViews() {
        tableLayout = (TableLayout) findViewById(R.id.calendarTableLayout);
        dayTextView = (TextView) findViewById(R.id.dayTextView);
        monthTextView = (TextView) findViewById(R.id.monthTextView);
        yearTextView = (TextView) findViewById(R.id.yearTextView);
        doneButton=(Button)findViewById(R.id.doneButton);
        cancelButton =(Button)findViewById(R.id.closeButton);
        days=context.getResources().getStringArray(R.array.hijri_date_picker_days);
        textViewList = new ArrayList<>();
        if(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue())callSwitchLang("ar"); else callSwitchLang("en");
        calendarInstance = new CalendarInstance(context,GeneralAttribute.mode);
        if (GeneralAttribute.setDefaultDate)
        {
            calendarInstance.setDay(GeneralAttribute.defaultDay);
            calendarInstance.setMonth(GeneralAttribute.defaultMonth);
            calendarInstance.setYear(GeneralAttribute.defaultYear);
        }
    }

    private void initHeaderOfCalender() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(0, 8, 0, 8);
        daysHeader = new TableRow(context);
        daysHeader.setGravity(Gravity.CENTER);
        for (int i = 0; i < 7; i++) {
            TextView textView = new TextView(context);
            textView.setLayoutParams(params);
            textView.setTextSize(11);
            textView.setTextColor(context.getResources().getColor(R.color.hijri_date_picker_accent_color));
            textView.setGravity(Gravity.CENTER);
            textView.setText(days[i]);
            daysHeader.addView(textView);
        }


    }

    private void initDays() {
        tableLayout.removeAllViews();
        tableLayout.addView(daysHeader);
        updateCalenderInformation();

        int count = 1;
        boolean firstTime = true;
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(0, 8, 0, 8);
        for (int i = 0; i < 5; i++) {
            TableRow row = new TableRow(context);
            row.setGravity(Gravity.CENTER);
            for (int j = 1; j <= 7; j++) {
                TextView textView = new TextView(context);
                textView.setLayoutParams(params);
                textView.setGravity(Gravity.CENTER);
                textView.setOnClickListener(this);
                textView.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
                if (count <= calendarInstance.lengthOfMonth()) {
                    if (firstTime && j == calendarInstance.getWeekStartFrom()) {
                        textView.setText(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue()?Utility.toArabicNumbers(count+""):count+"");
                        firstTime = false;
                        count++;
                    } else if (!firstTime) {
                        textView.setText(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue()?Utility.toArabicNumbers(count+""):count+"");
                        count++;
                    } else {
                        textView.setText("");

                    }
                } else {
                    textView.setText("");

                }
                if (count - 1 == calendarInstance.getDayOfMonth()) {
                    textView.setBackgroundColor(context.getResources().getColor(R.color.hijri_date_picker_accent_color));
                    textView.setTextColor(context.getResources().getColor(android.R.color.white));
                    lastSelectedDay = textView;
                }
                textViewList.add(textView);
                row.addView(textView);
            }
            tableLayout.addView(row);
        }
    }


    private void initListener() {
        monthTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MonthDialog monthDialog = new MonthDialog(context);
                monthDialog.setOnDateChanged(HijriCalendarView.this);
                monthDialog.setCurrentMonth(calendarInstance.getCurrentMonth());
                monthDialog.setMonthNames(calendarInstance.getMonths());
                monthDialog.show();

                return false;
            }
        });
        yearTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                YearDialog yearDialog = new YearDialog(context);
                yearDialog.setOnYearChanged(HijriCalendarView.this);
                yearDialog.setYear(Integer.parseInt(yearTextView.getText().toString()));
                yearDialog.show();
                return false;
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GeneralAttribute.onDateSetListener != null) {
                        GeneralAttribute.onDateSetListener.onDateSet(calendarInstance.getYear(), calendarInstance.getMonth()+1, calendarInstance.getDayOfMonth());

                }
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void updateCalenderInformation(){
        dayTextView.setText(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue() ? Utility.toArabicNumbers(calendarInstance.getDayOfMonth() + "") : calendarInstance.getDayOfMonth() + "");
        monthTextView.setText(calendarInstance.getMonthName());
        yearTextView.setText(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue()  ? Utility.toArabicNumbers(calendarInstance.getYear() + "") : calendarInstance.getYear() + "");
    }


    private void slideLeftToRight() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(700)
                .playOn(tableLayout);
    }
    private void slideRightToLeft() {
        YoYo.with(Techniques.SlideInRight)
                .duration(700)
                .playOn(tableLayout);
    }
    @Override
    public void onYearChanged(int year) {
        yearTextView.setText(year+"");
        calendarInstance.setYear(year);
        initDays();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x1 = 0,x2;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 > x1) {
                        calendarInstance.plusMonth();
                        slideLeftToRight();
                        initDays();
                    }

                    // Right to left swipe action
                    else {
                        calendarInstance.minusMonth();
                        slideRightToLeft();
                        initDays();
                    }

                }
                break;
        }
        return super.onTouchEvent(event);
    }



}
