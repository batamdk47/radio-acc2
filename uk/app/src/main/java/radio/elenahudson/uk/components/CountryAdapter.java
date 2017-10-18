package radio.elenahudson.uk.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import radio.elenahudson.uk.CountryObject;
import radio.elenahudson.uk.R;


public class CountryAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<CountryObject> mListObject = new ArrayList<>();
    private Context mContext;

    public CountryAdapter(Context context, ArrayList<CountryObject> items) {
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
            convertView = mInflater.inflate(R.layout.country_listview_item, null);
            holder = new ViewHolder();
            holder.txtvCountry = (TextView) convertView.findViewById(R.id.txtv_country);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CountryObject itemobject = mListObject.get(position);
        holder.txtvCountry.setText(itemobject.country);
        return convertView;
    }


    class ViewHolder {
        TextView txtvCountry;
    }
}
