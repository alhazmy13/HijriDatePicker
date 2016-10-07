package net.alhazmy13.hijridatepicker.month;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import net.alhazmy13.hijridatepicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alhazmy13 on 10/15/15.
 * HijriDatePicker
 */
public class MonthDialog extends Dialog implements MonthAdapterCallBack {
    private MonthAdapter mAdapter;
    private List<MonthItems> items;
    private OnMonthChanged listen;

    public MonthDialog(Context context, OnMonthChanged listen) {
        super(context);
        this.listen = listen;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_months);
        this.items = new ArrayList<>();
        this.mAdapter = new MonthAdapter(context, this, items);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        mRecyclerView.setAdapter(mAdapter);

    }

    public void setMonthNames(String[] monthNames, int currentMonth) {
        for (int i = 0; i < monthNames.length; i++) {
            items.add(new MonthItems(monthNames[i], i == currentMonth));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMonthSelected(int month) {
        listen.onMonthChanged(month - 1);
        dismiss();
    }


    public interface OnMonthChanged {
        void onMonthChanged(int month);
    }


}
