package net.alhazmy13.hijridatepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alhazmy13 on 10/15/15.
 */
public class HijriCalendarDialog extends Dialog implements MonthDialog.OnMonthChanged, View.OnClickListener {
    private Context context;
    private String[] days ;
    private TextView dayTextView, monthTextView, yearTextView,lastSelectedDay;
    private TableLayout tableLayout;
    private HijriCalendar calendar;
    private List<TextView> textViewList;
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private Button doneButton, cancelButton;
    private TableRow daysHeader;
    public static final int ARABIC=1;
    public static final int ENGLISH=2;
    public static boolean isArabicUI=false;

    //Interfaces
    public interface OnDateSetListener{
        void onDateSet(int year, int month, int day);
    }

    private OnDateSetListener onDateSetListener;
    public void setOnDateSetListener(OnDateSetListener listen) {
        onDateSetListener = listen;
    }
    private void callSwitchLang(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
        onCreate(null);
    }

    public HijriCalendarDialog(final Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.hijri_calendar_dialog);
        this.context=context;

        flowFunctions();

    }



    private void flowFunctions(){
        initViews();
        initHeaderOfCalender();
        initDays();
        initListner();
    }



    @Override
    public void onMonthChanged(int month) {
        calendar.setMonth(month);
        initDays();
    }

    @Override
    public void onClick(View view) {
        TextView temp = (TextView) view;
        if (!temp.getText().toString().isEmpty()) {
            lastSelectedDay.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
            lastSelectedDay.setBackground(null);


            temp.setBackgroundColor(context.getResources().getColor(R.color.mdtp_accent_color));
            temp.setTextColor(context.getResources().getColor(android.R.color.white));
            lastSelectedDay = temp;
            dayTextView.setText(temp.getText().toString());
            calendar.setDay(Integer.parseInt(temp.getText().toString()));

        }
    }
    private void initViews() {
        tableLayout = (TableLayout) findViewById(R.id.calendarTableLayout);
        dayTextView = (TextView) findViewById(R.id.dayTextView);
        monthTextView = (TextView) findViewById(R.id.monthTextView);
        yearTextView = (TextView) findViewById(R.id.yearTextView);
        doneButton=(Button)findViewById(R.id.doneButton);
        cancelButton =(Button)findViewById(R.id.closeButton);
        days=context.getResources().getStringArray(R.array.days);
        textViewList = new ArrayList<>();
        calendar = new HijriCalendar(context);
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
            textView.setTextColor(context.getResources().getColor(R.color.mdtp_accent_color));
            textView.setGravity(Gravity.CENTER);
            textView.setText(days[i]);
            daysHeader.addView(textView);
        }


    }

    private void initDays() {
        tableLayout.removeAllViews();
        tableLayout.addView(daysHeader);
        updateCalenderInforamation();

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
                if (count < 30) {
                    if (firstTime && j == calendar.getWeekStartFrom()) {
                        textView.setText(isArabicUI?Utility.toArabicNumbers(count+""):count+"");
                        firstTime = false;
                        count++;
                    } else if (!firstTime) {
                        textView.setText(isArabicUI?Utility.toArabicNumbers(count+""):count+"");
                        count++;
                    } else {
                        textView.setText("");

                    }
                } else {
                    textView.setText("");

                }
                if (calendar.isCurrentMonth() && count - 1 == calendar.getDayOfMonth()) {
                    textView.setBackgroundColor(context.getResources().getColor(R.color.mdtp_accent_color));
                    textView.setTextColor(context.getResources().getColor(android.R.color.white));
                    lastSelectedDay = textView;
                }
                textViewList.add(textView);
                row.addView(textView);
            }
            tableLayout.addView(row);
        }
    }


    private void initListner() {
        monthTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MonthDialog monthDialog = new MonthDialog(context);
                monthDialog.setOnDateChanged(HijriCalendarDialog.this);
                monthDialog.show();

                return false;
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDateSetListener != null) {
                    onDateSetListener.onDateSet(calendar.getYear(), calendar.getMonth(), calendar.getDayOfMonth());
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

    private void updateCalenderInforamation(){
        dayTextView.setText(isArabicUI?Utility.toArabicNumbers(calendar.getDayOfMonth()+""):calendar.getDayOfMonth()+"");
        monthTextView.setText(calendar.getMonthName() + "");
        yearTextView.setText(isArabicUI ? Utility.toArabicNumbers(calendar.getYear() + "") : calendar.getYear() + "");
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
    public boolean onTouchEvent(MotionEvent event) {

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
                        calendar.plusMonth();
                        slideLeftToRight();
                        initDays();
                    }

                    // Right to left swipe action
                    else {
                        calendar.minusMonth();
                        slideRightToLeft();
                        initDays();
                    }

                } else {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setUILanguage(int language){
        switch (language){
            case ARABIC:
                isArabicUI=true;
                callSwitchLang("ar");
                break;
            case ENGLISH:
                callSwitchLang("en");
                break;
        }
        flowFunctions();

    }



}
