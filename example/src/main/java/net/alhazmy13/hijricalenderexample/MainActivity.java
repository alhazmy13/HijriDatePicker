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
                new HijriCalendarDialog.Builder(MainActivity.this)
                        .setOnDateSetListener(MainActivity.this)
                        .setMode(HijriCalendarDialog.Mode.Hijri)
                        .setEnableScrolling(false)
                        .setUILanguage(HijriCalendarDialog.Language.Arabic)
                        .setUIView(HijriCalendarDialog.UiView.Land)
                        .show();

            }
        });

    }

    @Override
    public void onDateSet(int year, int month, int day) {
        ((TextView) findViewById(R.id.textView)).setText(year + "/" + (month + 1) + "/" + day);
    }


}
