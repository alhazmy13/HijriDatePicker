/*
 * Copyright (c) 2016.
 * Created by Alhazmy13 6/1/2016.
 * http://Alhazmy13.net
 */

package net.alhazmy13.hijridatepicker;

import android.content.Context;

/**
 * Created by Alhazmy13 on 1/6/16.
 */
public class HijriCalendarDialog {

    public HijriCalendarDialog(){

    }
    public static class  Builder{
        public Builder(Context context) {
            GeneralAttribute.mContext=context;
            GeneralAttribute.title="";
            GeneralAttribute.max = 1450;
            GeneralAttribute.min = 1437;
        }
        public HijriCalendarDialog.Builder setMaxYear(int maxYear) {
            GeneralAttribute.max=maxYear;
            return this;
        }

        public HijriCalendarDialog.Builder setMinYear(int minYear) {
            GeneralAttribute.min=minYear;
            return this;
        }

        public HijriCalendarDialog.Builder setMinMaxYear(int min,int max){
            GeneralAttribute.max = max;
            GeneralAttribute.min = min;
            return this;
        }

        public HijriCalendarDialog.Builder setUILanguage(int language){
            GeneralAttribute.language = language;
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



    }
}
