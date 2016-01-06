package net.alhazmy13.hijricalenderexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import net.alhazmy13.hijridatepicker.HijriCalendarDialog;
import net.alhazmy13.hijridatepicker.HijriCalendarView;

public class MainActivity extends AppCompatActivity implements HijriCalendarView.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new HijriCalendarDialog.Builder(this)
                .setOnDateSetListener(this)
                .show();

    }



    @Override
    public void onDateSet(int year, int month, int day) {
        Toast.makeText(this,year+"/"+month+"/"+day,Toast.LENGTH_SHORT).show();
    }
}
