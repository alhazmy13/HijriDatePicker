package net.alhazmy13.hijridatepicker;

/**
 * Created by Alhazmy13 on 10/19/15.
 */
class Utility {

    public static String toEnglishNumbers(String day){
        String englishNumbers=
                day.replace("١","1")
                        .replace("٢", "2")
                        .replace("٣","3")
                        .replace("٤","4")
                        .replace("٥","5")
                        .replace("٦","6")
                        .replace("٧","7")
                        .replace("٨","8")
                        .replace("٩","9")
                        .replace("٠", "0");
        return englishNumbers;
    }

    public static String toArabicNumbers(String day){
        String arabicNumbers=
                day.replace("1","١")
                        .replace("2", "٢")
                        .replace("3","٣")
                        .replace("4","٤")
                        .replace("5","٥")
                        .replace("6","٦")
                        .replace("7","٧")
                        .replace("8","٨")
                        .replace("9","٩")
                        .replace("0","٠");
        return arabicNumbers;
    }
}
