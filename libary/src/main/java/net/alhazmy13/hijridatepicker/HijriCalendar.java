package net.alhazmy13.hijridatepicker;

import android.content.Context;


import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;

import java.util.Calendar;

/**
 * Created by Alhazmy13 on 10/14/15.
 */
public class HijriCalendar {

    private UmmalquraCalendar calendar;
    private Context context;
    private String[] monthNames;
    private int countMonth,countYear,currentMonth,currentYear;
    public HijriCalendar(Context context){
        this.context=context;
        calendar=new UmmalquraCalendar();
        monthNames=new String[]{
                context.getResources().getString(R.string.muḥarram),
                context.getResources().getString(R.string.safar),
                context.getResources().getString(R.string.rabi_i),
                context.getResources().getString(R.string.rabi_ii),
                context.getResources().getString(R.string.jamada_i),
                context.getResources().getString(R.string.jamada_ii),
                context.getResources().getString(R.string.rajab),
                context.getResources().getString(R.string.shaban),
                context.getResources().getString(R.string.ramadan),
                context.getResources().getString(R.string.shawal),
                context.getResources().getString(R.string.dhu_alqadah),
                context.getResources().getString(R.string.dhu_alhijjah)};
        countMonth=calendar.get(Calendar.MONTH);
        countYear=calendar.get(Calendar.YEAR);
        currentMonth=countMonth;
        currentYear=countYear;
    }


    //Get Methodes

    public int getWeekStartFrom(){
        UmmalquraCalendar temp=new UmmalquraCalendar();
        temp.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
        return temp.get(Calendar.DAY_OF_WEEK);
    }
    public int getLastDayOfMonth(){
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public int getDayOfMonth(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public int getMonth(){
        return calendar.get(Calendar.MONTH)+1;
    }
    public String getMonthName(){
        return monthNames[calendar.get(Calendar.MONTH)];
    }
    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }

    public void plusMonth(){
        countMonth++;
        if(countMonth==12) {
            countMonth = 0;
            countYear++;
        }
        calendar=new UmmalquraCalendar(countYear,countMonth,calendar.get(Calendar.DAY_OF_MONTH));

    }


    public void minusMonth(){
        countMonth--;
        if(countMonth==-1) {
            countMonth = 11;
            countYear--;
        }
        calendar=new UmmalquraCalendar(countYear,countMonth,calendar.get(Calendar.DAY_OF_MONTH));

    }


    public boolean isCurrentMonth(){
        return (countMonth==currentMonth && currentYear==countYear);
    }
    public void setMonth(int month){
        countMonth=month;
        calendar.set(Calendar.MONTH,month);
    }
    public void setDay(int day){
        calendar.set(Calendar.DAY_OF_MONTH,day);
    }
}
