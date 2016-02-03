package net.alhazmy13.hijridatepicker.calendar;

import android.content.Context;

import net.alhazmy13.hijridatepicker.EnumConfig;

import java.util.Calendar;

/**
 * Created by Alhazmy13 on 2/3/16.
 */
public class CalendarInstance implements CustomCalendarView{
    private HijriCalendar hijri;
    private GregorianCalendar gerogian;
    private Context mContext;
    private int mMode;
    public CalendarInstance(Context context, int mode){
        this.hijri = new HijriCalendar(context);
        this.gerogian = new GregorianCalendar(context);
        this.mMode = mode;
    }
    @Override
    public void plusMonth() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue()) {
            hijri.plusMonth();
            return;
        }
        gerogian.plusMonth();
    }

    @Override
    public void minusMonth() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue()) {
            hijri.minusMonth();
            return;
        }
        gerogian.minusMonth();
    }

    @Override
    public boolean isCurrentMonth() {
        if (mMode == EnumConfig.Mode.Hijri.getModeValue())
        return hijri.isCurrentMonth();
        return gerogian.isCurrentMonth();
    }

    @Override
    public void setMonth(int month) {
        if (mMode == EnumConfig.Mode.Hijri.getModeValue()){
            hijri.setMonth(month);
            return;
        }
        gerogian.setMonth(month);
    }

    @Override
    public void setDay(int day) {
        if (mMode == EnumConfig.Mode.Hijri.getModeValue()){
            hijri.setDay(day);
            return;
        }
        gerogian.setDay(day);
    }

    @Override
    public void setYear(int year) {
        if (mMode == EnumConfig.Mode.Hijri.getModeValue()){
            hijri.setYear(year);
            return;
        }
        gerogian.setYear(year);
    }

    @Override
    public int getWeekStartFrom() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getWeekStartFrom();
        return gerogian.getWeekStartFrom();
    }

    @Override
    public int getLastDayOfMonth() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getLastDayOfMonth();
        return gerogian.getLastDayOfMonth();
    }

    @Override
    public int getDayOfMonth() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getDayOfMonth();
        return gerogian.getDayOfMonth();
    }

    @Override
    public int getMonth() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getMonth();
        return gerogian.getMonth();
    }

    @Override
    public String getMonthName() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getMonthName();
        return gerogian.getMonthName();
    }

    @Override
    public int getYear() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getYear();
        return gerogian.getYear();
    }

    @Override
    public int lengthOfMonth() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.lengthOfMonth();
        return gerogian.lengthOfMonth();
    }

    @Override
    public int getCurrentMonth() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getCurrentMonth();
        return gerogian.getCurrentMonth();
    }

    @Override
    public int getOffsetMonthCount() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getOffsetMonthCount();
        return gerogian.getOffsetMonthCount();
    }

    public String[] getMonths() {
        if(mMode == EnumConfig.Mode.Hijri.getModeValue())
            return hijri.getMonths();
        return gerogian.getMonths();
    }
}
