package net.alhazmy13.hijridatepicker;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import net.alhazmy13.hijridatepicker.adapter.HijriCalendarAdapter;
import net.alhazmy13.hijridatepicker.adapter.HijriCalenderItem;
import net.alhazmy13.hijridatepicker.calendar.CalendarInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alhazmy13 on 10/15/15.
 * HijriDatePicker
 */
public class HijriCalendarView extends Dialog implements MonthDialog.OnMonthChanged, View.OnClickListener, YearDialog.OnYearChanged {
    private Context mContext;
    private String[] days;
    private TextView dayTextView, monthTextView, yearTextView, lastSelectedDay, dateTextView;
    private CalendarInstance calendarInstance;
    private RecyclerView recyclerView;
    private boolean isLandArabic = false;
    private List<HijriCalenderItem> hijriItems;
    private HijriCalendarAdapter mAdapter;

    /**
     * @param mContext
     */
    HijriCalendarView(final Context mContext) {
        super(mContext);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (GeneralAttribute.uiView == HijriCalendarDialog.UiView.Land) {
            if (GeneralAttribute.language == HijriCalendarDialog.Language.Arabic.getLanguageValue()) {
                this.setContentView(R.layout.temp);
                initLand();
                isLandArabic = true;
            } else {
                this.setContentView(R.layout.dialog_hijri_calendar_land);
                initLand();
            }

        } else {
            this.setContentView(R.layout.dialog_hijri_calendar);
            initButtons();
        }

        this.mContext = mContext;
        flowFunctions();

    }

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
        initRecycleView();
        initDays();
        initListener();
    }

    private void initRecycleView() {
        hijriItems = new ArrayList<>();
        mAdapter = new HijriCalendarAdapter(mContext, hijriItems);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 7));
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onMonthChanged(int month) {
        calendarInstance.setMonth(month + 1);
        initDays();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rightArrowImageView) {
            calendarInstance.plusMonth();
            slideLeftToRight();
            initDays();
        } else if (id == R.id.leftArrowImageView) {
            calendarInstance.minusMonth();
            slideRightToLeft();
            initDays();
        } else {
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


    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.calendarTableLayout);
        dayTextView = (TextView) findViewById(R.id.dayTextView);
        monthTextView = (TextView) findViewById(R.id.monthTextView);
        yearTextView = (TextView) findViewById(R.id.yearTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        days = mContext.getResources().getStringArray(R.array.hijri_date_picker_days);
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
    private void initLand() {
        ImageView next = (ImageView) findViewById(R.id.leftArrowImageView);
        ImageView previous = (ImageView) findViewById(R.id.rightArrowImageView);

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
    private void initButtons() {
        Button doneButton = (Button) findViewById(R.id.doneButton);
        Button cancelButton = (Button) findViewById(R.id.closeButton);
        Utility.setButtonTint(mContext, doneButton);
        Utility.setButtonTint(mContext, cancelButton);

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

    private void initHeaderOfCalender() {
        for (String day : days) {
            hijriItems.add(new HijriCalenderItem(day, false, HijriCalenderItem.Type.HEADER));
        }
        mAdapter.notifyDataSetChanged();

    }

    private void initDays() {
        hijriItems.clear();
        updateCalenderInformation();
        initHeaderOfCalender();
        boolean firstTime = true;
        for (int i = 1; i <= calendarInstance.lengthOfMonth(); i++) {
            if (firstTime && i != calendarInstance.getWeekStartFrom()) {
                hijriItems.add(new HijriCalenderItem("", false, HijriCalenderItem.Type.SPACE));
                continue;
            }
            firstTime = false;
            hijriItems.add(new HijriCalenderItem(String.valueOf(i), i == calendarInstance.getDayOfMonth(), HijriCalenderItem.Type.DAY));
        }


        mAdapter.notifyDataSetChanged();

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
        dateTextView.setText(String.valueOf(calendarInstance.getMonthName() + " " + year));
    }

    private void slideLeftToRight() {
        YoYo.with(Techniques.SlideInLeft)
                .duration(700)
                .playOn(recyclerView);
    }

    private void slideRightToLeft() {
        YoYo.with(Techniques.SlideInRight)
                .duration(700)
                .playOn(recyclerView);
    }

    @Override
    public void onYearChanged(int year) {
        yearTextView.setText(String.valueOf(year + ""));
        calendarInstance.setYear(year);
        initDays();
    }


    public interface OnDateSetListener {
        @Deprecated
        void onDateSet(int year, int month, int day);
    }


}
