# Hijri Date Picker
This library offers a hijri (Islam Calendar) Date Picker  designed on [Google's Material Design Principals For Pickers](http://www.google.com/design/spec/components/pickers.html) for Android 4.1 (API 16) +.

Date Picker 
---- |
![Date Picker](https://cloud.githubusercontent.com/assets/4659608/10555929/b4700b68-747f-11e5-933b-734992188acd.png) 

You can report any issue on issues page. **Note: If you speak Arabic, you can submit issues with Arabic language and I will check them. :)**



# Usage
The library follows the same API as other pickers in the Android framework.
After adding the library, for using a picker in your project you need to:

1. Implement an `OnDateSetListener`
2. Create a `HijriCalendarDialog` using the supplied factory
3. Theme the pickers

### Implement an `OnDateSetListener`
In order to receive the date  set in the picker, you will need to implement the `OnDateSetListener`  interfaces. Typically this will be the `Dialog`  that creates the Pickers. The callbacks use the same API as the standard Android pickers.
```java
  @Override
    public void onDateSet(int year, int month, int day) {
        Toast.makeText(getApplicationContext(),year+"/"+month+"/"+day+"/",Toast.LENGTH_SHORT).show();

    }
```

### Create a `HijriCalendarDialog`
You will need to create a new instance of `HijriCalendarDialog`. Once the dialogs are configured, you can call `show()`.
```java
 HijriCalendarDialog dialog = new HijriCalendarDialog(this);
        dialog.setOnDateSetListener(this);
        dialog.show();
```

### Theme the pickers
You can theme the pickers by overwriting the color resources `mdtp_accent_color` and `mdtp_accent_color_dark` in your project.
```xml
<color name="mdtp_accent_color">#009688</color>
```

### Credits 
Thanks to  [ummalqura-calendar Library](https://github.com/msarhan/ummalqura-calendar) and [AndroidViewAnimations](https://github.com/daimajia/AndroidViewAnimations).
