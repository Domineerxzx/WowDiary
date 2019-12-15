package com.domineer.triplebro.wowdiary.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.models.LocationInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/15,16:00
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class LocationAdapter extends BaseAdapter {

    private Context context;
    private List<LocationInfo> locationInfoList;

    public LocationAdapter(Context context, List<LocationInfo> locationInfoList) {
        this.context = context;
        this.locationInfoList = locationInfoList;
    }

    public List<LocationInfo> getLocationInfoList() {
        return locationInfoList;
    }

    public void setLocationInfoList(List<LocationInfo> locationInfoList) {
        this.locationInfoList = locationInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return locationInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_location,null);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_mobile = convertView.findViewById(R.id.tv_mobile);
            viewHolder.tv_location = convertView.findViewById(R.id.tv_location);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_name.setText(locationInfoList.get(position).getName());
        viewHolder.tv_mobile.setText(locationInfoList.get(position).getMobile());
        viewHolder.tv_location.setText(locationInfoList.get(position).getLocation());
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_name;
        private TextView tv_mobile;
        private TextView tv_location;
    }
}
