package net.alhazmy13.hijridatepicker.calendar;

/**
 * Created by Alhazmy13 on 2/3/16.
 */
interface CustomCalendarView {
    void plusMonth();
    void minusMonth();
    boolean isCurrentMonth();
    void setMonth(int month);
    void setDay(int day);
    void setYear(int year);
    int getWeekStartFrom();
    int getLastDayOfMonth();
    int getDayOfMonth();
    int getMonth();
    String getMonthName();
    int getYear();
    int lengthOfMonth();
    int getCurrentMonth();
    int getOffsetMonthCount();
    String[] getMonths();
}
