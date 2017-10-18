package radio.elenahudson.uk.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import radio.elenahudson.uk.R;
import radio.elenahudson.uk.RadioObject;
import radio.elenahudson.uk.ulti.general.General;

import static radio.elenahudson.uk.HomeActivity.playercontrol;


public class ListviewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<RadioObject> arrayListRadio = new ArrayList<RadioObject>();
    private ArrayList<RadioObject> arrayListSecon = new ArrayList<RadioObject>();
    private Context conText;
    public int selectedItem;


    public ListviewAdapter(Context context, ArrayList<RadioObject> items) {
        conText = context;
        inflater = LayoutInflater.from(context);
        arrayListRadio.clear();
        arrayListRadio.addAll(items);
        arrayListSecon.addAll(items);
    }

    @Override
    public int getCount() {
        return arrayListRadio.size();
    }

    public Object getItem(int position) {
        return arrayListRadio.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_viewitem, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.txtvname);
            holder.imgLogo = (ImageView) convertView.findViewById(R.id.imv_logo);
            holder.txtlocation = (TextView) convertView.findViewById(R.id.txtvLocation);
            holder.txtBitrate =(TextView)convertView.findViewById(R.id.txt_bitrate);
            holder.txtUpdate =(ImageView)convertView.findViewById(R.id.txt_update);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final RadioObject itemobject = arrayListRadio.get(position);
        holder.txtName.setText(itemobject.name);
        holder.txtBitrate.setText((itemobject.bitrate));

        //  set background when onclick item
        if (selectedItem == position && playercontrol !=null && itemobject.link.equals(General.getStringPreference(conText,"url_station"))) {
            convertView.setBackgroundResource(R.drawable.bg_select_item);
            final View finalConvertView = convertView;
            holder.txtUpdate.setVisibility(View.VISIBLE);
            holder.txtUpdate.setImageResource(R.drawable.pause);
            finalConvertView.findViewById(R.id.txtvname).startAnimation(AnimationUtils.loadAnimation(conText, R.anim.move_right_to_left));

        } else {
            convertView.findViewById(R.id.txtvname).clearAnimation();
            convertView.setBackgroundResource(R.color.colorAccent);
            holder.txtUpdate.setVisibility(View.GONE);
        }
        holder.txtlocation.setText(itemobject.location +"/"+itemobject.category);


        if (itemobject.pic.equals("") == false) {
            if (itemobject.pic.equals("null")) {
                holder.imgLogo.setVisibility(View.GONE);
            } else {
                holder.imgLogo.setVisibility(View.VISIBLE);
                Picasso.with(conText)
                        .load(itemobject.pic)
                        .into(holder.imgLogo);
            }

        } else {
            holder.imgLogo.setVisibility(View.GONE);


        }


        return convertView;
    }

    public void notifyListObjectChanged(ArrayList<RadioObject> objectsChanged) {
        if (objectsChanged != null) {
            arrayListRadio.clear();
            arrayListRadio.addAll(objectsChanged);
            notifyDataSetChanged();
        }
    }

    class ViewHolder {
        TextView txtName;
        ImageView imgLogo;
        TextView txtlocation;
        TextView txtBitrate;
        ImageView txtUpdate;
    }

    public void resetdata(String query) {

        query = query.toLowerCase();
        arrayListRadio.clear();

        if (query.isEmpty()) {
            arrayListRadio.addAll(arrayListSecon);
        } else {
            ArrayList<RadioObject> newObjs = new ArrayList<RadioObject>();
            for (RadioObject continent : arrayListSecon) {
                if (continent.name.toString().toLowerCase().trim().contains(query)) {
                    newObjs.add(continent);
                }}
            arrayListRadio.addAll(newObjs);
        }

        notifyDataSetChanged();

    }

}
