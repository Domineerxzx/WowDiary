package com.domineer.triplebro.wowdiary.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.controllers.DateController;
import com.domineer.triplebro.wowdiary.models.DairyInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/11,14:33
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DateAdapter extends BaseAdapter {

    private Context context;
    private List<DairyInfo> dairyInfoList;
    private boolean isCollect;

    public DateAdapter(Context context, List<DairyInfo> dairyInfoList) {
        this.context = context;
        this.dairyInfoList = dairyInfoList;
    }

    public List<DairyInfo> getDairyInfoList() {
        return dairyInfoList;
    }

    public void setDairyInfoList(List<DairyInfo> dairyInfoList) {
        this.dairyInfoList = dairyInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dairyInfoList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_dairy,null);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            viewHolder.tv_time = convertView.findViewById(R.id.tv_time);
            viewHolder.iv_collect = convertView.findViewById(R.id.iv_collect);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_title.setText(dairyInfoList.get(position).getTitle());
        viewHolder.tv_content.setText(dairyInfoList.get(position).getContent());
        String time = dairyInfoList.get(position).getYear()+"年"+dairyInfoList.get(position).getMonth()
                +"月"+dairyInfoList.get(position).getDay()+"日  "+dairyInfoList.get(position).getHour()
                +":"+dairyInfoList.get(position).getMin()+":"+dairyInfoList.get(position).getSecond();
        viewHolder.tv_time.setText(time);
        final DateController dateController = new DateController(context);
        isCollect = dateController.getIsCollect(dairyInfoList.get(position).get_id(),dairyInfoList.get(position).getUser_id());
        if(isCollect){
            viewHolder.iv_collect.setBackgroundResource(R.mipmap.collect_click);
        }else{
            viewHolder.iv_collect.setBackgroundResource(R.mipmap.collect_unclick);
        }
        viewHolder.iv_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCollect){
                    viewHolder.iv_collect.setBackgroundResource(R.mipmap.collect_unclick);
                    dateController.deleteCollect(dairyInfoList.get(position).get_id(),dairyInfoList.get(position).getUser_id());
                    isCollect = false;
                }else{
                    viewHolder.iv_collect.setBackgroundResource(R.mipmap.collect_click);
                    dateController.addCollect(dairyInfoList.get(position).get_id(),dairyInfoList.get(position).getUser_id());
                    isCollect = true;
                }
            }
        });
        return convertView;
    }

    private class ViewHolder{

        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_time;
        private ImageView iv_collect;
    }
}
