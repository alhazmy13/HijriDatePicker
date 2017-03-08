<p align="left">
  <img src="https://cloud.githubusercontent.com/assets/4659608/12700270/e1730608-c7ed-11e5-8a18-a382fe7b63bd.png" width="500">
</p>
# Hijri Date Picker (UmAlQuraCalendar)
![](https://img.shields.io/badge/Platform-Android-brightgreen.svg)
![](https://img.shields.io/badge/Android-CustomView-blue.svg)
![](https://img.shields.io/crates/l/rustc-serialize.svg)
![](https://img.shields.io/badge/version-2.0.0_beta-blue.svg)

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
<artifactId>library</artifactId>
<version>2.0.0-beta </version>
</dependency>
```
**Gradle**

```gradle

dependencies {
	compile 'net.alhazmy13.hijridatepicker:library:2.0.0-beta'
}
```

# Usage
The library follows the same API as other pickers in the Android framework.
After adding the library, you need to:

1. Implement an `onDateSet`
2. Create a `HijriCalendarDialog` using the supplied factory
3. Theme the pickers

### Implement an `OnTimeSetListener/OnDateSetListener`
In order to receive the date  set in the picker, you will need to implement the `OnDateSetListener`  interfaces. Typically this will be the `Dialog`  that creates the Pickers. The callbacks use the same API as the standard Android pickers.

<u><b>Note:</b> Months start from 0.</u>

```java
 // TimePickerDialog
 @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        // YOUR CODE
    }
 // GregorianDatePickerDialog
   @Override
    public void onDateSet(GregorianDatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        //YOUR CODE
    }
    
    // HijriDatePickerDialog
       @Override
    public void onDateSet(HijriDatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        //YOUR CODE
    }
    
```

### Create a `GregorianDatePickerDialog`
You will need to create a new instance of `GregorianDatePickerDialog `. Once the dialogs are configured, you can call `show()`.

```java
Calendar now = Calendar.getInstance();
GregorianDatePickerDialog dpd = GregorianDatePickerDialog(
		  CONTEXT,
		  now.get(Calendar.YEAR),
		  now.get(Calendar.MONTH),
		  now.get(Calendar.DAY_OF_MONTH));
dpd.show(getFragmentManager(), "GregorianDatePickerDialog");
```

### Create a `HijriDatePickerDialog`
Same as `GregorianDatePickerDialog` but you need to use `UmmalquraCalendar` insted of `Calendar` class.

```java
UmmalquraCalendar now = new UmmalquraCalendar();
HijriDatePickerDialog dpd = HijriDatePickerDialog(
		  CONTEXT,
		  now.get(UmmalquraCalendar.YEAR),
		  now.get(UmmalquraCalendar.MONTH),
		  now.get(UmmalquraCalendar.DAY_OF_MONTH));
dpd.show(getFragmentManager(), "HijriDatePickerDialog");
```

### `TimePickerDialog`


```java
Calendar now = Calendar.getInstance();
TimePickerDialog tpd = TimePickerDialog.newInstance(
                        TimePickerFragment.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        mode24Hours.isChecked()
                );
```




## Credits
* [ummalqura-calendar Library](https://github.com/msarhan/ummalqura-calendar) 
* [MaterialDateTimePicker](https://github.com/wdullaer/MaterialDateTimePicker).


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
    

