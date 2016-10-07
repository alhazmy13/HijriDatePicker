package net.alhazmy13.hijridatepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.widget.Button;

/**
 * Created by Alhazmy13 on 10/19/15.
 * HijriDatePicker
 */
class Utility {

    public static String toEnglishNumbers(String day) {
        return day.replace("١", "1")
                .replace("٢", "2")
                .replace("٣", "3")
                .replace("٤", "4")
                .replace("٥", "5")
                .replace("٦", "6")
                .replace("٧", "7")
                .replace("٨", "8")
                .replace("٩", "9")
                .replace("٠", "0");
    }

    static String toArabicNumbers(String day) {
        return day.replace("1", "١")
                .replace("2", "٢")
                .replace("3", "٣")
                .replace("4", "٤")
                .replace("5", "٥")
                .replace("6", "٦")
                .replace("7", "٧")
                .replace("8", "٨")
                .replace("9", "٩")
                .replace("0", "٠");
    }

    static void setButtonTint(Context context, Button button) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP && button instanceof AppCompatButton) {
            ((AppCompatButton) button).setSupportBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.hijri_date_picker_accent_color)));
        } else {
            ViewCompat.setBackgroundTintList(button, ColorStateList.valueOf(ContextCompat.getColor(context,R.color.hijri_date_picker_accent_color)));
        }
    }
}
