package net.alhazmy13.hijridatepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import net.alhazmy13.hijridatepicker.adapter.HijriCalendarAdapter;
import net.alhazmy13.hijridatepicker.adapter.HijriCalenderItem;
import net.alhazmy13.hijridatepicker.adapter.OnDaySelected;
import net.alhazmy13.hijridatepicker.calendar.CalendarInstance;
import net.alhazmy13.hijridatepicker.month.MonthDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alhazmy13 on 10/15/15.
 * HijriDatePicker
 */
class HijriCalendarView extends Dialog implements MonthDialog.OnMonthChanged, View.OnClickListener, YearDialog.OnYearChanged, OnDaySelected {
    private Context mContext;
    private String[] days;
    private TextView dayTextView, monthTextView, yearTextView, dateTextView;
    private CalendarInstance calendarInstance;
    private RecyclerView recyclerView;
    private List<HijriCalenderItem> hijriItems;
    private HijriCalendarAdapter mAdapter;


    HijriCalendarView(final Context mContext) {
        super(mContext);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.mContext = mContext;
        if (GeneralAttribute.uiView == HijriCalendarDialog.UIView.Landscape)
            setContentView(R.layout.dialog_hijri_calendar_land);
        else
            setContentView(R.layout.dialog_hijri_calendar);

        init();

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

    private void init() {
        initViews();
        initRecycleView();
        initDays();
        initListener();
    }

    private void initRecycleView() {
        hijriItems = new ArrayList<>();
        mAdapter = new HijriCalendarAdapter(mContext, this, hijriItems);
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
            calendarInstance.minusMonth();
            slideRightToLeft();
            initDays();
        } else if (id == R.id.leftArrowImageView) {
            calendarInstance.plusMonth();
            slideLeftToRight();
            initDays();
        }

    }


    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.calendarTableLayout);
        dayTextView = (TextView) findViewById(R.id.dayTextView);
        monthTextView = (TextView) findViewById(R.id.monthTextView);
        yearTextView = (TextView) findViewById(R.id.yearTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        days = mContext.getResources().getStringArray(R.array.hijri_date_picker_days);
        findViewById(R.id.leftArrowImageView).setOnClickListener(this);
        findViewById(R.id.rightArrowImageView).setOnClickListener(this);

        if (GeneralAttribute.language == HijriCalendarDialog.Language.Arabic)
            callSwitchLang("ar");
        else if (GeneralAttribute.language == HijriCalendarDialog.Language.English)
            callSwitchLang("en");
        calendarInstance = new CalendarInstance(mContext, GeneralAttribute.mode);
        if (GeneralAttribute.setDefaultDate) {
            calendarInstance.setDay(GeneralAttribute.defaultDay);
            calendarInstance.setMonth(GeneralAttribute.defaultMonth);
            calendarInstance.setYear(GeneralAttribute.defaultYear);
        }
    }


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

        if (GeneralAttribute.language == HijriCalendarDialog.Language.Arabic) {
            List<HijriCalenderItem> temp = Utility.reverseItems(hijriItems.toArray(new HijriCalenderItem[hijriItems.size()]), 7);
            hijriItems.clear();
            hijriItems.addAll(temp);
        }

        mAdapter.notifyDataSetChanged();

    }

    private void initListener() {
        monthTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MonthDialog monthDialog = new MonthDialog(mContext, HijriCalendarView.this);
                monthDialog.setMonthNames(calendarInstance.getMonths(), calendarInstance.getCurrentMonth());
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
        String year = GeneralAttribute.language == HijriCalendarDialog.Language.Arabic ? Utility.toArabicNumbers(calendarInstance.getYear() + "") : calendarInstance.getYear() + "";
        dayTextView.setText(GeneralAttribute.language == HijriCalendarDialog.Language.Arabic ? Utility.toArabicNumbers(calendarInstance.getDayOfMonth() + "") : calendarInstance.getDayOfMonth() + "");
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


    @Override
    public void daySelected(int position) {
        calendarInstance.setDay(Integer.parseInt(hijriItems.get(position).getText()));
        GeneralAttribute.onDateSetListener.onDateSet(calendarInstance.getYear(), calendarInstance.getMonth(), calendarInstance.getDayOfMonth());
        dismiss();
    }
}
