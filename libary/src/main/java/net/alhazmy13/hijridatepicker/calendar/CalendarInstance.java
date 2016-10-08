package net.alhazmy13.hijridatepicker.calendar;

import android.content.Context;

import net.alhazmy13.hijridatepicker.HijriCalendarDialog;

/**
 * Created by Alhazmy13 on 2/3/16.
 * HijriDatePicker
 */
public class CalendarInstance implements CustomCalendarView{
    private HijriCalendar hijri;
    private GregorianCalendar georgian;
    private HijriCalendarDialog.Mode mMode;

    public CalendarInstance(Context context, HijriCalendarDialog.Mode mode){
        this.hijri = new HijriCalendar(context);
        this.georgian = new GregorianCalendar(context);
        this.mMode = mode;
    }
    @Override
    public void plusMonth() {
        if(mMode == HijriCalendarDialog.Mode.Hijri) {
            hijri.plusMonth();
            return;
        }
        georgian.plusMonth();
    }

    @Override
    public void minusMonth() {
        if(mMode == HijriCalendarDialog.Mode.Hijri) {
            hijri.minusMonth();
            return;
        }
        georgian.minusMonth();
    }

    @Override
    public boolean isCurrentMonth() {
        if (mMode == HijriCalendarDialog.Mode.Hijri)
        return hijri.isCurrentMonth();
        return georgian.isCurrentMonth();
    }

    @Override
    public void setMonth(int month) {
        if (mMode == HijriCalendarDialog.Mode.Hijri){
            hijri.setMonth(month);
            return;
        }
        georgian.setMonth(month);
    }

    @Override
    public void setDay(int day) {
        if (mMode == HijriCalendarDialog.Mode.Hijri){
            hijri.setDay(day);
            return;
        }
        georgian.setDay(day);
    }

    @Override
    public void setYear(int year) {
        if (mMode == HijriCalendarDialog.Mode.Hijri){
            hijri.setYear(year);
            return;
        }
        georgian.setYear(year);
    }

    @Override
    public int getWeekStartFrom() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getWeekStartFrom();
        return georgian.getWeekStartFrom();
    }

    @Override
    public int getLastDayOfMonth() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getLastDayOfMonth();
        return georgian.getLastDayOfMonth();
    }

    @Override
    public int getDayOfMonth() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getDayOfMonth();
        return georgian.getDayOfMonth();
    }

    @Override
    public int getMonth() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getMonth();
        return georgian.getMonth();
    }

    @Override
    public String getMonthName() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getMonthName();
        return georgian.getMonthName();
    }

    @Override
    public int getYear() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getYear();
        return georgian.getYear();
    }

    @Override
    public int lengthOfMonth() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.lengthOfMonth();
        return georgian.lengthOfMonth();
    }

    @Override
    public int getCurrentMonth() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getCurrentMonth();
        return georgian.getCurrentMonth();
    }

    @Override
    public int getOffsetMonthCount() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getOffsetMonthCount();
        return georgian.getOffsetMonthCount();
    }

    public String[] getMonths() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getMonths();
        return georgian.getMonths();
    }

    public int getCurrentYear() {
        if(mMode == HijriCalendarDialog.Mode.Hijri)
            return hijri.getCurrentYear();
        return georgian.getCurrentYear();
    }
}
