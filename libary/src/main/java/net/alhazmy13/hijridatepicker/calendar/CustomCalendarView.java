package net.alhazmy13.hijridatepicker.calendar;

/**
 * Created by Alhazmy13 on 2/3/16.
 * HijriDatePicker
 */
interface CustomCalendarView {
    void plusMonth();

    void minusMonth();

    void setDay(int day);

    int getWeekStartFrom();

    int getLastDayOfMonth();

    int getDayOfMonth();

    int getMonth();

    void setMonth(int month);

    int getYear();

    void setYear(int year);

    int lengthOfMonth();

    int getCurrentMonth();

    int getOffsetMonthCount();

    int getCurrentYear();

    boolean isCurrentMonth();

    String[] getMonths();

    String getMonthName();

}
