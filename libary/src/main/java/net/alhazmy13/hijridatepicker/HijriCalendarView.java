package net.alhazmy13.hijridatepicker;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * HijriDatePicker
 */
public class HijriCalendarView extends Dialog implements MonthDialog.OnMonthChanged, View.OnClickListener, YearDialog.OnYearChanged, DefaultValue {
    private Context mContext;
    private String[] days;
    private TextView dayTextView, monthTextView, yearTextView, lastSelectedDay,dateTextView;
    private TableLayout tableLayout;
    private CalendarInstance calendarInstance;
    private List<TextView> textViewList;
    private Button doneButton, cancelButton;
    private TableRow daysHeader;
    private ImageView next,previous;
    private boolean isLandArabic=false;
    /**
     * @param mContext
     */
    HijriCalendarView(final Context mContext) {
        super(mContext);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (GeneralAttribute.uiView == HijriCalendarDialog.UiView.Land)
        {
            if (GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue())
            {
                this.setContentView(R.layout.temp);
                initLand();
                isLandArabic=true;
            }else
            {
                this.setContentView(R.layout.dialog_hijri_calendar_land);
                initLand();
            }

        }

        else
        {
            this.setContentView(R.layout.dialog_hijri_calendar);
            initButtons();
        }

        this.mContext = mContext;

        flowFunctions();

    }

    /**
     * @param langCode
     */
    private void callSwitchLang(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        mContext.getResources().updateConfiguration(config,
                mContext.getResources().getDisplayMetrics());
        onCreate(null);
    }

    private void flowFunctions() {
        initViews();
        initHeaderOfCalender();
        initDays();
        initListener();
    }

    /**
     * @param month
     */
    @Override
    public void onMonthChanged(int month) {
        calendarInstance.setMonth(month + 1);
        initDays();
    }

    /**
     * @param view
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rightArrowImageView) {

        }else if(id == R.id.leftArrowImageView){

        }else {
            TextView temp = (TextView) view;
            if (!temp.getText().toString().trim().isEmpty()) {
                lastSelectedDay.setTextColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
                lastSelectedDay.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.transparent));
                temp.setBackground(ContextCompat.getDrawable(mContext, R.drawable.hijri_date_picker_card_selected));
                temp.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
                lastSelectedDay = temp;
                dayTextView.setText(temp.getText().toString());
                calendarInstance.setDay(Integer.parseInt(temp.getText().toString()));
                //TODO in case of landscape Arabic
                if (isLandArabic) {
                    GeneralAttribute.onDateSetListener.onDateSet(calendarInstance.getYear(), calendarInstance.getMonth(), calendarInstance.getDayOfMonth());
                    dismiss();
                }

            }
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
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        days = mContext.getResources().getStringArray(R.array.hijri_date_picker_days);
        textViewList = new ArrayList<>();
        if (GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue())
            callSwitchLang("ar");
        else if (GeneralAttribute.language == HijriCalendarDialog.Language.English.getLanguageValue())
            callSwitchLang("en");
        calendarInstance = new CalendarInstance(mContext, GeneralAttribute.mode.getModeValue());
        if (GeneralAttribute.setDefaultDate) {
            calendarInstance.setDay(GeneralAttribute.defaultDay);
            calendarInstance.setMonth(GeneralAttribute.defaultMonth);
            calendarInstance.setYear(GeneralAttribute.defaultYear);
        }
    }

    //region init for land
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initLand ()
    {
        next = (ImageView) findViewById(R.id.leftArrowImageView);
        previous = (ImageView) findViewById(R.id.rightArrowImageView);

        next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calendarInstance.plusMonth();
            slideLeftToRight();
            initDays();
        }
    });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarInstance.minusMonth();
                slideLeftToRight();
                initDays();
            }
        });

    }
    //endregion

    //region init Buttons
    private void initButtons()
    {
        doneButton = (Button) findViewById(R.id.doneButton);
        cancelButton = (Button) findViewById(R.id.closeButton);
        setButtonTint(doneButton);
        setButtonTint(cancelButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GeneralAttribute.onDateSetListener != null) {
                    GeneralAttribute.onDateSetListener.onDateSet(calendarInstance.getYear(), calendarInstance.getMonth(), calendarInstance.getDayOfMonth());


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
    //endregion
    public void setButtonTint(Button button) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP && button instanceof AppCompatButton) {
            ((AppCompatButton) button).setSupportBackgroundTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.hijri_date_picker_accent_color)));
        } else {
            ViewCompat.setBackgroundTintList(button, ColorStateList.valueOf(mContext.getResources().getColor(R.color.hijri_date_picker_accent_color)));
        }
    }

    private void initHeaderOfCalender() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
//        params.setMargins(8, 8, 8, 8);

        daysHeader = new TableRow(mContext);
        daysHeader.setGravity(Gravity.CENTER);
        for (int i = 0; i < 7; i++) {
            TextView textView = new TextView(mContext);
            textView.setLayoutParams(params);
            textView.setTextSize(11);
            textView.setPadding(4,4,4,4);
            textView.setSingleLine();
            textView.setTextColor(mContext.getResources().getColor(R.color.hijri_date_picker_accent_color));
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
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1f);
//        params.setMargins(8, 8, 8, 8);
        for (int i = 0; i < 5; i++) {
            TableRow row = new TableRow(mContext);
            row.setGravity(Gravity.CENTER);
            for (int j = 1; j <= 7; j++) {
                TextView textView = new TextView(mContext);
                textView.setLayoutParams(params);
                textView.setGravity(Gravity.CENTER);
                textView.setSingleLine();
                textView.setPadding(4,4,4,4);
                textView.setOnClickListener(this);
                textView.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
                if (count <= calendarInstance.lengthOfMonth()) {
                    if (firstTime && j == calendarInstance.getWeekStartFrom()) {
                        textView.setText(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue() ? Utility.toArabicNumbers(count + "") : count + "");
                        firstTime = false;
                        count++;
                    } else if (!firstTime) {
                        textView.setText(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue() ? Utility.toArabicNumbers(count + "") : count + "");
                        count++;
                    } else {
                        textView.setText(" ");

                    }
                } else {
                    textView.setText(" ");

                }
                if ((calendarInstance.isCurrentMonth() || (calendarInstance.getCurrentMonth() == GeneralAttribute.defaultMonth && calendarInstance.getCurrentYear() == GeneralAttribute.defaultYear)) && count - 1 == calendarInstance.getDayOfMonth()) {
                    textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.hijri_date_picker_card_selected));
                    //      textView.setBackgroundColor(mContext.getResources().getColor(R.color.hijri_date_picker_accent_color));
                    textView.setTextColor(mContext.getResources().getColor(android.R.color.white));
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
                MonthDialog monthDialog = new MonthDialog(mContext);
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
                YearDialog yearDialog = new YearDialog(mContext);
                yearDialog.setOnYearChanged(HijriCalendarView.this);
                yearDialog.setYear(Integer.parseInt(yearTextView.getText().toString()));
                yearDialog.show();
                return false;
            }
        });
    }

    private void updateCalenderInformation() {
        String year = GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue() ? Utility.toArabicNumbers(calendarInstance.getYear() + "") : calendarInstance.getYear() + "";
        dayTextView.setText(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue() ? Utility.toArabicNumbers(calendarInstance.getDayOfMonth() + "") : calendarInstance.getDayOfMonth() + "");
        monthTextView.setText(calendarInstance.getMonthName());
        yearTextView.setText(year);
        dateTextView.setText(String.valueOf(calendarInstance.getMonthName() +" "+ year));
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
        yearTextView.setText(year + "");
        calendarInstance.setYear(year);
        initDays();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (GeneralAttribute.scrolling) {
            float x1 = 0, x2;
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
        }
        return super.onTouchEvent(event);
    }

    public interface OnDateSetListener {
        @Deprecated
        void onDateSet(int year, int month, int day);
    }


}
