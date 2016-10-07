package net.alhazmy13.hijridatepicker.month;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.alhazmy13.hijridatepicker.R;

import java.util.List;

/**
 * Created by Alhazmy13 on 10/8/16.
 * HijriDatePicker
 */
class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthViewHolder> {
    private List<MonthItems> itemList;
    private Context mContext;
    private MonthAdapterCallBack onMonthChanged;

    MonthAdapter(Context context, MonthAdapterCallBack onMonthChanged, List<MonthItems> itemList) {
        this.itemList = itemList;
        this.mContext = context;
        this.onMonthChanged = onMonthChanged;
    }

    @Override
    public MonthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.month_item, parent, false);
        return new MonthViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MonthViewHolder holder, final int position) {
        holder.textView.setText(String.valueOf(itemList.get(position).monthName));
        if (itemList.get(position).isCurrentMonth) {
            TypedValue typedValue = new TypedValue();
            mContext.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            holder.textView.setBackgroundColor(typedValue.data);
            holder.textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
        }
        holder.textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                onMonthChanged.onMonthSelected(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MonthViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MonthViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.monthTextView);
        }
    }
}
