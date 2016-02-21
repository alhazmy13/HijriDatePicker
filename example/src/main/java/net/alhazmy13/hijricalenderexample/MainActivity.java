package net.alhazmy13.hijricalenderexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.alhazmy13.hijridatepicker.HijriCalendarDialog;
import net.alhazmy13.hijridatepicker.HijriCalendarView;

public class MainActivity extends AppCompatActivity implements HijriCalendarView.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Without setting default date
//                new HijriCalendarDialog.Builder(MainActivity.this)
//                        .setOnDateSetListener(MainActivity.this)
//                        .setMinMaxHijriYear(1430,1450)
//                        .setMinMaxGregorianYear(2013,2020)
//                        .setUILanguage(HijriCalendarDialog.Language.English)
//                        .setMode(HijriCalendarDialog.Mode.Hijri)
//                        .show();

                //Setting default date
                new HijriCalendarDialog.Builder(MainActivity.this)
                        .setOnDateSetListener(MainActivity.this)
                        .setMinMaxHijriYear(1430, 1450)
                        .setMinMaxGregorianYear(2013, 2020)
                        .setUILanguage(HijriCalendarDialog.Language.English)
                        .setMode(HijriCalendarDialog.Mode.Hijri)
                        .setDefaultHijriDate(8, 0, 1437)//months start from 0
                        .show();


            }
        });

    }

    @Override
    public void onDateSet(int year, int month, int day) {
        ((TextView) findViewById(R.id.textView)).setText(year+"/"+(month+1)+"/"+day);
    }


}
