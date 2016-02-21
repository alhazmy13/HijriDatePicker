/*
 * Copyright (c) 2016.
 * Created by Alhazmy13 6/1/2016.
 * http://Alhazmy13.net
 */

package net.alhazmy13.hijridatepicker;

import android.content.Context;

/**
 * Created by Alhazmy13 on 1/6/16.
 *  HijriDatePicker
 */
public class HijriCalendarDialog {

    public HijriCalendarDialog(){

    }
   public enum Mode{
        Hijri(1),
        Gregorian(2);
        private int mode;
        Mode(int mode) {
            this.mode = mode;
        }
        public int getModeValue() {
            return mode;
        }

    }

    public enum Language{
        Arabic(1),
        English(2);
        private int language;
        Language(int language) {
            this.language = language;
        }
        public int getLanguageValue() {
            return language;
        }

    }
    public static class  Builder{
        public Builder(Context context) {
            GeneralAttribute.mContext=context;
            GeneralAttribute.title="";
            GeneralAttribute.hijri_max = 1450;
            GeneralAttribute.hijri_min = 1437;
            GeneralAttribute.gregorian_max = 2050;
            GeneralAttribute.gregorian_min = 2013;
            GeneralAttribute.language = Language.English.getLanguageValue();
        }
        public HijriCalendarDialog.Builder setMaxHijriYear(int maxYear) {
            GeneralAttribute.hijri_max=maxYear;
            return this;
        }

        public HijriCalendarDialog.Builder setMinHijriYear(int minYear) {
            GeneralAttribute.hijri_min=minYear;
            return this;
        }

        public HijriCalendarDialog.Builder setMinMaxHijriYear(int min,int max){
            GeneralAttribute.hijri_max = max;
            GeneralAttribute.hijri_min = min;
            return this;
        }


        public HijriCalendarDialog.Builder setMaxGregorianYear(int maxYear) {
            GeneralAttribute.gregorian_max=maxYear;
            return this;
        }

        public HijriCalendarDialog.Builder setMinGregorianYear(int minYear) {
            GeneralAttribute.gregorian_min=minYear;
            return this;
        }

        public HijriCalendarDialog.Builder setMinMaxGregorianYear(int min,int max){
            GeneralAttribute.gregorian_max = max;
            GeneralAttribute.gregorian_min = min;
            return this;
        }

        public HijriCalendarDialog.Builder setUILanguage(Language language){
            GeneralAttribute.language = language.getLanguageValue();
            return this;
        }
        public  HijriCalendarDialog.Builder setOnDateSetListener(HijriCalendarView.OnDateSetListener onDateSetListener){
            GeneralAttribute.onDateSetListener = onDateSetListener;
            return this;
        }
        public HijriCalendarDialog.Builder show(){
            new HijriCalendarView(GeneralAttribute.mContext).show();
            return this;
        }
        public HijriCalendarDialog.Builder setMode(Mode mode){
            GeneralAttribute.mode = mode;
            return this;
        }
        public HijriCalendarDialog.Builder setDefaultHijriDate(int day, int month, int year){
            if (month>11 || month<0)
                throw new RuntimeException("Month must be between 0-11");
            GeneralAttribute.setDefaultDate = true;
            GeneralAttribute.defaultDay = day;
            GeneralAttribute.defaultMonth = month>11?0:month;
            GeneralAttribute.defaultYear = year;
            return this;
        }


    }
}
