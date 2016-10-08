package net.alhazmy13.hijridatepicker;

import net.alhazmy13.hijridatepicker.adapter.HijriCalenderItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alhazmy13 on 10/19/15.
 * HijriDatePicker
 */
class Utility {

    static String toEnglishNumbers(String day) {
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

    static List<HijriCalenderItem> reverseItems(HijriCalenderItem[] array, int max) {
        List<HijriCalenderItem[]> items = splitArray(array, max);
        List<HijriCalenderItem> newItems = new ArrayList<>();
        for (HijriCalenderItem[] item : items) {
            for (int i = 0; i < item.length / 2; i++) {
                HijriCalenderItem tempString = item[i];
                item[i] = item[item.length - i - 1];
                item[item.length - i - 1] = tempString;
            }
            Collections.addAll(newItems, item);
        }


        return newItems;

    }

    private static List<HijriCalenderItem[]> splitArray(HijriCalenderItem[] array, int max) {
        int x = array.length / max;
        int r = (array.length % max); // remainder
        int lower = 0;
        int upper = 0;
        List<HijriCalenderItem[]> list = new ArrayList<>();
        int i = 0;
        for (i = 0; i < x; i++) {
            upper += max;
            list.add(Arrays.copyOfRange(array, lower, upper));
            lower = upper;
        }
        if (r > 0) {
            list.add(Arrays.copyOfRange(array, lower, (lower + r)));
        }
        return list;
    }


}
