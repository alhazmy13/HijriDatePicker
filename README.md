# Hijri Date Picker
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
<version>1.0.1</version>
</dependency>
```
**Gradle**
```gradle

dependencies {
	compile 'net.alhazmy13.hijridatepicker:libary:1.0.1'
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
You can theme the pickers by overwriting the color resources `mdtp_accent_color` in your project.
```xml
<color name="mdtp_accent_color">#009688</color>
```

### Additional Options
* `SetUILanguage` You can change the display language to your preferred language `ARABIC` or `ENGLISH`
```java
dialog.setUILanguage(HijriCalendarDialog.ARABIC);
```

### Credits 
Thanks to  [ummalqura-calendar Library](https://github.com/msarhan/ummalqura-calendar) and [AndroidViewAnimations](https://github.com/daimajia/AndroidViewAnimations).


## License

    Copyright 2014 rengwuxian

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    

