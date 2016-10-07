package net.alhazmy13.hijridatepicker.adapter;

/**
 * Created by Alhazmy13 on 10/7/16.
 * HijriDatePicker
 */
public class HijriCalenderItem {
    private String text;
    private boolean isCurrentDay;
    private Type itemType;

    public HijriCalenderItem(String text, boolean isCurrentDay, Type itemType) {
        this.text = text;
        this.isCurrentDay = isCurrentDay;
        this.itemType = itemType;
    }

    public String getText() {
        return text;
    }

    boolean isCurrentDay() {
        return isCurrentDay;
    }

    Type getItemType() {
        return itemType;
    }

    public static enum Type{
        HEADER, SPACE, DAY
    }
}
