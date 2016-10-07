package net.alhazmy13.hijridatepicker.calendar;

import android.content.Context;
import android.util.Log;

import net.alhazmy13.hijridatepicker.R;

import java.util.Calendar;

/**
 * Created by Alhazmy13 on 10/14/15.
 * HijriDatePicker
 */
class GregorianCalendar implements CustomCalendarView{
    private static final String TAG = "GregorianCalendar";
    private Calendar calendar;
    private String[] monthNames;
    private int countMonth,countYear,currentMonth,currentYear;

    GregorianCalendar(Context context){
        calendar=Calendar.getInstance();
        monthNames=new String[]{
                context.getResources().getString(R.string.January),
                context.getResources().getString(R.string.February),
                context.getResources().getString(R.string.March),
                context.getResources().getString(R.string.April),
                context.getResources().getString(R.string.May),
                context.getResources().getString(R.string.June),
                context.getResources().getString(R.string.July),
                context.getResources().getString(R.string.August),
                context.getResources().getString(R.string.September),
                context.getResources().getString(R.string.October),
                context.getResources().getString(R.string.November),
                context.getResources().getString(R.string.December)};
        countMonth=calendar.get(Calendar.MONTH);
        countYear=calendar.get(Calendar.YEAR);
        currentMonth=countMonth;
        Log.d(TAG, "HijriCalendar: "+countMonth);
        currentYear=countYear;
    }




    public void plusMonth(){
        countMonth++;
        if(countMonth==12) {
            countMonth = 0;
            countYear++;
        }
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,countYear);
        calendar.set(Calendar.MONTH,countMonth);

    }


    public void minusMonth(){
        countMonth--;
        if(countMonth==-1) {
            countMonth = 11;
            countYear--;
        }
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,countYear);
        calendar.set(Calendar.MONTH,countMonth);
    }


    public boolean isCurrentMonth(){
        return (countMonth==currentMonth && currentYear==countYear);
    }
    public void setMonth(int month){
        Log.d(TAG, "setMonth() called with: " + "month = [" + month + "]");
        this.countMonth=month-1;
        calendar.set(Calendar.MONTH,month);
    }
    public void setDay(int day){
        calendar.set(Calendar.DAY_OF_MONTH,day);
    }

    public void setYear(int year) {
        this.countYear = year;
        calendar.set(Calendar.YEAR,year);
    }
    public int getWeekStartFrom(){
        Calendar temp=Calendar.getInstance();
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
        return countMonth+1;
    }

    public String getMonthName() {

        return monthNames[getOffsetMonthCount()];
    }
    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }

    public int lengthOfMonth() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }




    public int getCurrentMonth() {
        return getOffsetMonthCount();
    }

    public int getOffsetMonthCount() {
        if(countMonth < 0)
            return 11;
        else if(countMonth > 11)
            return 0;
        return countMonth;
    }

    @Override
    public String[] getMonths() {
        return monthNames;
    }

    @Override
    public int getCurrentYear() {
        return countYear;
    }
}
