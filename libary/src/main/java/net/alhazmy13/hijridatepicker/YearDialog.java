

/*
 * Copyright (c) 2016.
 * Created by Alhazmy13 6/1/2016.
 * http://Alhazmy13.net
 */

package net.alhazmy13.hijridatepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;


/**
 * Created by Alhazmy13 on 1/6/16.
 * HijriDatePicker
 */
class YearDialog extends Dialog implements DialogInterface.OnDismissListener,View.OnClickListener {
    private Context mContext;
    private NumberPicker numberPicker;
    private OnYearChanged onYearChanged;
    private Button okButton;
    private int year;


    interface OnYearChanged {
        void onYearChanged(int year);

    }
    private Button ok,close;
    protected void setOnYearChanged(OnYearChanged listen) {
        onYearChanged = listen;
    }
    public YearDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_year);
        this.mContext=context;
        this.setOnDismissListener(this);
        initViews();
        if(GeneralAttribute.mode.getModeValue() == HijriCalendarDialog.Mode.Hijri.getModeValue()) {
            numberPicker.setMaxValue(GeneralAttribute.hijri_max);
            numberPicker.setMinValue(GeneralAttribute.hijri_min);
        }else{
            numberPicker.setMaxValue(GeneralAttribute.gregorian_max);
            numberPicker.setMinValue(GeneralAttribute.gregorian_min);
        }

    }

    private void initViews() {
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        okButton = (Button) findViewById(R.id.okBT);
        okButton.setOnClickListener(this);
    }

    protected void setTitle(String title){
        ((TextView) findViewById(R.id.title)).setText(title);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        onYearChanged.onYearChanged(numberPicker.getValue());
    }

    public void setYear(int year) {
        this.numberPicker.setValue(year);
    }

    @Override
    public void onClick(View view) {
        onYearChanged.onYearChanged(numberPicker.getValue());
        dismiss();
    }

}
