/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.alhazmy13.hijridatepicker.date.hijri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;

import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
import net.alhazmy13.hijridatepicker.date.hijri.MonthView.OnDayClickListener;

import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * An adapter for a list of {@link MonthView} items.
 */
public abstract class MonthAdapter extends BaseAdapter implements OnDayClickListener {

    private static final String TAG = "SimpleMonthAdapter";

    private final Context mContext;
    protected final DatePickerController mController;

    private CalendarDay mSelectedDay;

    protected static int WEEK_7_OVERHANG_HEIGHT = 7;
    protected static final int MONTHS_IN_YEAR = 12;

    /**
     * A convenience class to represent a specific date.
     */
    public static class CalendarDay {
        private UmmalquraCalendar calendar;
        int year;
        int month;
        int day;
        TimeZone mTimeZone;

        public CalendarDay(TimeZone timeZone) {
            mTimeZone = timeZone;
            setTime(System.currentTimeMillis());
        }

        public CalendarDay(long timeInMillis, TimeZone timeZone) {
            mTimeZone = timeZone;
            setTime(timeInMillis);
        }

        public CalendarDay(UmmalquraCalendar calendar, TimeZone timeZone) {
            mTimeZone = timeZone;
            year = calendar.get(UmmalquraCalendar.YEAR);
            month = calendar.get(UmmalquraCalendar.MONTH);
            day = calendar.get(UmmalquraCalendar.DAY_OF_MONTH);
        }

        public CalendarDay(int year, int month, int day) {
            setDay(year, month, day);
        }

        public void set(CalendarDay date) {
            year = date.year;
            month = date.month;
            day = date.day;
        }

        public void setDay(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        private void setTime(long timeInMillis) {
            if (calendar == null) {
                calendar = new UmmalquraCalendar(mTimeZone, Locale.getDefault());
            }
            calendar.setTimeInMillis(timeInMillis);
            month = calendar.get(UmmalquraCalendar.MONTH);
            year = calendar.get(UmmalquraCalendar.YEAR);
            day = calendar.get(UmmalquraCalendar.DAY_OF_MONTH);
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }
    }

    public MonthAdapter(Context context,
            DatePickerController controller) {
        mContext = context;
        mController = controller;
        init();
        setSelectedDay(mController.getSelectedDay());
    }

    /**
     * Updates the selected day and related parameters.
     *
     * @param day The day to highlight
     */
    public void setSelectedDay(CalendarDay day) {
        mSelectedDay = day;
        notifyDataSetChanged();
    }

    @SuppressWarnings("unused")
    public CalendarDay getSelectedDay() {
        return mSelectedDay;
    }

    /**
     * Set up the gesture detector and selected time
     */
    protected void init() {
        mSelectedDay = new CalendarDay(System.currentTimeMillis(), mController.getTimeZone());
    }

    @Override
    public int getCount() {
        UmmalquraCalendar endDate = mController.getEndDate();
        UmmalquraCalendar startDate = mController.getStartDate();
        int endMonth = endDate.get(UmmalquraCalendar.YEAR) * MONTHS_IN_YEAR + endDate.get(UmmalquraCalendar.MONTH);
        int startMonth = startDate.get(UmmalquraCalendar.YEAR) * MONTHS_IN_YEAR + startDate.get(UmmalquraCalendar.MONTH);
        return endMonth - startMonth + 1;
        //return ((mController.getMaxYear() - mController.getMinYear()) + 1) * MONTHS_IN_YEAR;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonthView v;
        HashMap<String, Integer> drawingParams = null;
        if (convertView != null) {
            v = (MonthView) convertView;
            // We store the drawing parameters in the view so it can be recycled
            drawingParams = (HashMap<String, Integer>) v.getTag();
        } else {
            v = createMonthView(mContext);
            // Set up the new view
            LayoutParams params = new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            v.setLayoutParams(params);
            v.setClickable(true);
            v.setOnDayClickListener(this);
        }
        if (drawingParams == null) {
            drawingParams = new HashMap<>();
        }
        drawingParams.clear();

        final int month = (position + mController.getStartDate().get(UmmalquraCalendar.MONTH)) % MONTHS_IN_YEAR;
        final int year = (position + mController.getStartDate().get(UmmalquraCalendar.MONTH)) / MONTHS_IN_YEAR + mController.getMinYear();

        int selectedDay = -1;
        if (isSelectedDayInMonth(year, month)) {
            selectedDay = mSelectedDay.day;
        }

        // Invokes requestLayout() to ensure that the recycled view is set with the appropriate
        // height/number of weeks before being displayed.
        v.reuse();

        drawingParams.put(MonthView.VIEW_PARAMS_SELECTED_DAY, selectedDay);
        drawingParams.put(MonthView.VIEW_PARAMS_YEAR, year);
        drawingParams.put(MonthView.VIEW_PARAMS_MONTH, month);
        drawingParams.put(MonthView.VIEW_PARAMS_WEEK_START, mController.getFirstDayOfWeek());
        v.setMonthParams(drawingParams);
        v.invalidate();
        return v;
    }

    public abstract MonthView createMonthView(Context context);

    private boolean isSelectedDayInMonth(int year, int month) {
        return mSelectedDay.year == year && mSelectedDay.month == month;
    }


    @Override
    public void onDayClick(MonthView view, CalendarDay day) {
        if (day != null) {
            onDayTapped(day);
        }
    }

    /**
     * Maintains the same hour/min/sec but moves the day to the tapped day.
     *
     * @param day The day that was tapped
     */
    protected void onDayTapped(CalendarDay day) {
        mController.tryVibrate();
        mController.onDayOfMonthSelected(day.year, day.month, day.day);
        setSelectedDay(day);
    }
}
