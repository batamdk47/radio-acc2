package radio.elenahudson.uk.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import radio.elenahudson.uk.R;
import radio.elenahudson.uk.TimerObject;


public class TimerAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<TimerObject> mListObject = new ArrayList<TimerObject>();
    private Context mContext;

    public TimerAdapter(Context context, ArrayList<TimerObject> items) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListObject.clear();
        mListObject.addAll(items);
    }

    @Override
    public int getCount() {
        return mListObject.size();
    }

    public Object getItem(int position) {
        return mListObject.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.timer_lisview_item, null);
            holder = new ViewHolder();
            holder.txtvTimer = (TextView) convertView.findViewById(R.id.txtv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TimerObject itemobject = mListObject.get(position);
        holder.txtvTimer.setText(itemobject.timer);
        return convertView;
    }


    class ViewHolder {
        TextView txtvTimer;
    }
}
