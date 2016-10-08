/*
 * Copyright (c) 2016.
 * Created by Alhazmy13 6/1/2016.
 * http://Alhazmy13.net
 */

package net.alhazmy13.hijridatepicker;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by Alhazmy13 on 1/6/16.
 * HijriDatePicker
 */
public class HijriCalendarDialog {

    public HijriCalendarDialog() {

    }

    public enum Mode {
        Hijri, Gregorian

    }

    public enum Language {
        Arabic, English, Default

    }

    public enum UIView {
        Portrait, Landscape,Auto
    }

    public interface OnDateSetListener {
        void onDateSet(int year, int month, int day);
    }

    public static class Builder {
        public Builder(Context context) {
            GeneralAttribute.context = new WeakReference<>(context);
            GeneralAttribute.title = "";
            GeneralAttribute.hijri_max = 1450;
            GeneralAttribute.hijri_min = 1437;
            GeneralAttribute.gregorian_max = 2050;
            GeneralAttribute.gregorian_min = 2013;
            GeneralAttribute.language = Language.Default;
            GeneralAttribute.scrolling = true;
            GeneralAttribute.uiView = UIView.Auto;
        }

        public HijriCalendarDialog.Builder MaxHijriYear(int maxYear) {
            GeneralAttribute.hijri_max = maxYear;
            return this;
        }

        public HijriCalendarDialog.Builder MinHijriYear(int minYear) {
            GeneralAttribute.hijri_min = minYear;
            return this;
        }

        public HijriCalendarDialog.Builder MinMaxHijriYear(int min, int max) {
            GeneralAttribute.hijri_max = max;
            GeneralAttribute.hijri_min = min;
            return this;
        }


        public HijriCalendarDialog.Builder MaxGregorianYear(int maxYear) {
            GeneralAttribute.gregorian_max = maxYear;
            return this;
        }

        public HijriCalendarDialog.Builder MinGregorianYear(int minYear) {
            GeneralAttribute.gregorian_min = minYear;
            return this;
        }

        public HijriCalendarDialog.Builder MinMaxGregorianYear(int min, int max) {
            GeneralAttribute.gregorian_max = max;
            GeneralAttribute.gregorian_min = min;
            return this;
        }

        public HijriCalendarDialog.Builder setUILanguage(Language language) {
            GeneralAttribute.language = language;
            return this;
        }

        /**
         * Sets UIView.
         * @param uiView Portrait :  view for mobile devices<br>
         * Landscape : view for tablet devices<br>
         * Auto : auto detect user device
         * @return the ui view
         */
        public HijriCalendarDialog.Builder setUIView(UIView uiView) {
            GeneralAttribute.uiView = uiView;
            return this;
        }

        public HijriCalendarDialog.Builder listener(OnDateSetListener onDateSetListener) {
            GeneralAttribute.onDateSetListener = onDateSetListener;
            return this;
        }

        public HijriCalendarDialog.Builder show() {
            new HijriCalendarView(GeneralAttribute.context.get()).show();
            return this;
        }

        public HijriCalendarDialog.Builder mode(Mode mode) {
            GeneralAttribute.mode = mode;
            return this;
        }

        public HijriCalendarDialog.Builder defaultHijriDate(int day, int month, int year) {
            if (month > 11 || month < 0)
                throw new RuntimeException("Month must be between 0-11");
            GeneralAttribute.setDefaultDate = true;
            GeneralAttribute.defaultDay = day;
            GeneralAttribute.defaultMonth = month > 11 ? 0 : month;
            GeneralAttribute.defaultYear = year;
            return this;
        }


    }
}
