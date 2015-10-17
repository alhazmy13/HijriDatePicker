package net.alhazmy13.hijridatepicker;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alhazmy13 on 10/15/15.
 */
public class MonthDialog extends Dialog implements View.OnTouchListener {
    private Context context;
    ArrayList<TextView> textViews=new ArrayList<>();

    interface OnMonthChanged{
        void onMonthChanged(int month);
    }
    private OnMonthChanged onMonthChanged;
    public void setOnDateChanged(OnMonthChanged listen) {
        onMonthChanged = listen;
    }
    public MonthDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.months_dialog);
        this.context=context;
        textViews.add((TextView)findViewById(R.id.m1TextView));
        textViews.add((TextView)findViewById(R.id.m2TextView));
        textViews.add((TextView)findViewById(R.id.m3TextView));
        textViews.add((TextView)findViewById(R.id.m4TextView));
        textViews.add((TextView)findViewById(R.id.m5TextView));
        textViews.add((TextView)findViewById(R.id.m6TextView));
        textViews.add((TextView)findViewById(R.id.m7TextView));
        textViews.add((TextView)findViewById(R.id.m8TextView));
        textViews.add((TextView)findViewById(R.id.m9TextView));
        textViews.add((TextView)findViewById(R.id.m10TextView));
        textViews.add((TextView)findViewById(R.id.m11TextView));
        textViews.add((TextView)findViewById(R.id.m12TextView));
        for(int i=0;i<textViews.size();i++)
            textViews.get(i).setOnTouchListener(this);


    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView temp=(TextView)view;
        if (onMonthChanged != null) {
            onMonthChanged.onMonthChanged(textViews.indexOf(temp));
        }
        dismiss();
        return false;
    }
}
