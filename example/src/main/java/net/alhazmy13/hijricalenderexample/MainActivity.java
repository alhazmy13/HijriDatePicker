package net.alhazmy13.hijricalenderexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.alhazmy13.hijridatepicker.HijriCalendarDialog;

public class MainActivity extends AppCompatActivity implements HijriCalendarDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HijriCalendarDialog dialog=new HijriCalendarDialog(this);
        dialog.setUILanguage(HijriCalendarDialog.ARABIC);
        dialog.setOnDateSetListener(this);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(int year, int month, int day) {
        Toast.makeText(this,year+"/"+month+"/"+day,Toast.LENGTH_SHORT).show();
    }
}
