package net.alhazmy13.hijridatepicker.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.alhazmy13.hijridatepicker.R;

import java.util.List;


/**
 * Created by Alhazmy13 on 10/6/16.
 * HijriDatePicker
 */
public class HijriCalendarAdapter extends RecyclerView.Adapter<HijriCalendarAdapter.HijriCalendarViewHolder> {

    private List<HijriCalenderItem> items;
    private Context mContext;

    public HijriCalendarAdapter(Context context, List<HijriCalenderItem> items) {
        this.items = items;
        this.mContext = context;
    }

    @Override
    public HijriCalendarAdapter.HijriCalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hijri_calendar_item, parent, false);
        return new HijriCalendarViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(HijriCalendarViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(items.get(position).getText()));
        if (items.get(position).isCurrentDay()) {
            holder.textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.hijri_date_picker_oval));
            holder.textView.setTextColor(ContextCompat.getColor(mContext,android.R.color.white));
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    class HijriCalendarViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        HijriCalendarViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
