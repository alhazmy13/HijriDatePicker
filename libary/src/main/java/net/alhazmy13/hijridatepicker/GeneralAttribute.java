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
    static Context mContext;
    static String title;
    static int language;
    static HijriCalendarView.OnDateSetListener onDateSetListener;
    static HijriCalendarDialog.Mode mode = HijriCalendarDialog.Mode.Hijri;
    static int hijri_min;
    static int hijri_max;
    static int gregorian_min;
    static int gregorian_max;
    static boolean setDefaultDate = false;
    static int defaultDay;
    static int defaultMonth;
    static int defaultYear;
    static boolean scrolling;
    static HijriCalendarDialog.UiView uiView;
}
