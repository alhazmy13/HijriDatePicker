<p align="left">
  <img src="https://cloud.githubusercontent.com/assets/4659608/12700270/e1730608-c7ed-11e5-8a18-a382fe7b63bd.png" width="500">
</p>
# Hijri Date Picker
![](https://img.shields.io/badge/Platform-Android-brightgreen.svg)
![](https://img.shields.io/badge/Android-CustomView-blue.svg)
![](https://img.shields.io/crates/l/rustc-serialize.svg)
![](https://img.shields.io/badge/version-1.3.6-blue.svg)

This library offers a hijri (Islamic Calendar) Date Picker  designed on [Google's Material Design Principals For Pickers](http://www.google.com/design/spec/components/pickers.html) for Android 4.1 (API 16) +.

عربي | English
---- | ----
![Arabic](https://cloud.githubusercontent.com/assets/4659608/10579117/0466d434-767f-11e5-8172-534f1a47c608.png) | ![English](https://cloud.githubusercontent.com/assets/4659608/10579118/04684ee0-767f-11e5-8432-a9b5d67713f9.png) 

You can report any issue on issues page. **Note: If you speak Arabic, you can submit issues with Arabic language and I will check them. :)**

##Installation
**Maven**
```xml
<dependency>
<groupId>net.alhazmy13.hijridatepicker</groupId>
<artifactId>libary</artifactId>
<version>1.3.6</version>
</dependency>
```
**Gradle**
```gradle

dependencies {
	compile 'net.alhazmy13.hijridatepicker:libary:1.3.6'
}
```

# Usage
The library follows the same API as other pickers in the Android framework.
After adding the library, you need to:

1. Implement an `OnDateSetListener`
2. Create a `HijriCalendarDialog` using the supplied factory
3. Theme the pickers

### Implement an `OnDateSetListener`
In order to receive the date  set in the picker, you will need to implement the `OnDateSetListener`  interfaces. Typically this will be the `Dialog`  that creates the Pickers. The callbacks use the same API as the standard Android pickers.

<u><b>Note:</b> Months start from 0.</u>

```java
  @Override
    public void onDateSet(int year, int month, int day) {
        Toast.makeText(getApplicationContext(),year+"/"+(month+1)+"/"+day+"/",Toast.LENGTH_SHORT).show();

    }
```

### Create a `HijriCalendarDialog`
You will need to create a new instance of `HijriCalendarDialog`. Once the dialogs are configured, you can call `show()`.
```java
         new HijriCalendarDialog.Builder(this)
                .setOnDateSetListener(this)
                .show();
```

### Theme the pickers
You can theme the pickers by overwriting the color resources `hijri_date_picker_accent_color` in your project.
```xml
<color name="hijri_date_picker_accent_color">#009688</color>
```

### Additional Options
* `SetUILanguage` You can change the display language to your preferred language `ARABIC` or `ENGLISH`
```java
.setUILanguage(HijriCalendarDialog.Language.Arabic);
```
* `setMode` to change the mode from `Hijri` to `Gregorian`
```java
.setMode(HijriCalendarDialog.Mode.Gregorian)
```
* `setMaxHijriYear` To set the maximum Hijri year for dialg
```java
.setMaxHijriYear(1440);
```
* `setMinHijriYear` To set the minimum hijri year for dialg
```java
.setMinYear(1437);
```
* `setMinMaxHijriYear` To set the minimum and maximum hijri year for dialog
```java
.setMinMaxHijriYear(1430,1440);
```
* `setMaxGregorianYear` To set the maximum Gregorian year for dialg
```java
.setMaxGregorianYear(1440);
```
* `setMinGregorianYear` To set the minimum Gregorian year for dialg
```java
.setMinGregorianYear(1437);
```
* `setMinMaxGregorianYear` To set the minimum and maximum Gregorian year for dialog
```java
.setMinMaxGregorianYear(1430,1440);
```
* `setDefaultHijriDate`
* ```java
.setDefaultHijriDate(8, 0, 1437) //months start from 0
```

### Credits 
Thanks to  [ummalqura-calendar Library](https://github.com/msarhan/ummalqura-calendar) and [AndroidViewAnimations](https://github.com/daimajia/AndroidViewAnimations).


## License

    Copyright 2015 alhazmy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    

