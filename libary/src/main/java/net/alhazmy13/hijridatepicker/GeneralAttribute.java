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
class GeneralAttribute {
    public static Context mContext;
    public static String title;
    public static int language;
    public static HijriCalendarView.OnDateSetListener onDateSetListener;
    public static HijriCalendarDialog.Mode mode = HijriCalendarDialog.Mode.Hijri;
    public static int hijri_min;
    public static int hijri_max;
    public static int gregorian_min;
    public static int gregorian_max;
    public static boolean setDefaultDate = false;
    public static int defaultDay;
    public static int defaultMonth;
    public static int defaultYear;
    public static boolean scrolling;
}
