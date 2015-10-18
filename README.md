# Hijri Date Picker
This library offers a hijri (Islamic Calendar) Date Picker  designed on [Google's Material Design Principals For Pickers](http://www.google.com/design/spec/components/pickers.html) for Android 4.1 (API 16) +.

عربي | English
---- | ----
![Arabic](https://camo.githubusercontent.com/e1367ae8bab8db71f9275f69eff1105645f4bc4b/68747470733a2f2f6769746875622d636c6f75642e73332e616d617a6f6e6177732e636f6d2f6173736574732f343635393630382f31303536353334362f30316662646337652d373564372d313165352d393364302d3765623035353664313966312e706e67) | ![English](https://camo.githubusercontent.com/da0ea333d8133cabc0acc5ac6667ed845e1c4f66/68747470733a2f2f6769746875622d636c6f75642e73332e616d617a6f6e6177732e636f6d2f6173736574732f343635393630382f31303536353334372f30323036303864652d373564372d313165352d386438312d3931363332386564376235622e706e67) 

You can report any issue on issues page. **Note: If you speak Arabic, you can submit issues with Arabic language and I will check them. :)**

##Installation
**Maven**
```xml
<dependency>
<groupId>net.alhazmy13.hijridatepicker</groupId>
<artifactId>libary</artifactId>
<version>1.0.0</version>
</dependency>
```
**Gradle**
```gradle
repositories {
	url 'https://dl.bintray.com/alhazmy13/maven/'
}
dependencies {
	compile(group: 'net.alhazmy13.hijridatepicker', name: 'libary', version: '1.0.0')
}
```

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
