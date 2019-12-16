package com.domineer.triplebro.wowdiary.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.models.GoodsInfo;
import com.domineer.triplebro.wowdiary.models.LocationInfo;
import com.domineer.triplebro.wowdiary.models.OrderInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/15,18:03
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<OrderInfo> orderInfoList;
    private final DataBaseProvider dataBaseProvider;
    private List<LocationInfo> locationInfoList;
    private final List<GoodsInfo> goodsInfoList;

    public OrderAdapter(Context context, List<OrderInfo> orderInfoList) {
        this.context = context;
        this.orderInfoList = orderInfoList;
        dataBaseProvider = new DataBaseProvider(context);
        if (orderInfoList.size() != 0) {
            locationInfoList = dataBaseProvider.getLocationInfo(orderInfoList.get(0).getUser_id());
        }
        goodsInfoList = dataBaseProvider.getGoodsInfoList();
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return orderInfoList.size();
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_order, null);
            viewHolder.tv_order_id = convertView.findViewById(R.id.tv_order_id);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_mobile = convertView.findViewById(R.id.tv_mobile);
            viewHolder.tv_location = convertView.findViewById(R.id.tv_location);
            viewHolder.iv_goods = convertView.findViewById(R.id.iv_goods);
            viewHolder.tv_price = convertView.findViewById(R.id.tv_price);
            viewHolder.tv_goods_name = convertView.findViewById(R.id.tv_goods_name);
            viewHolder.tv_over_order = convertView.findViewById(R.id.tv_over_order);
            viewHolder.tv_is_over = convertView.findViewById(R.id.tv_is_over);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        for (LocationInfo locationInfo : locationInfoList) {
            if (locationInfo.get_id() == orderInfoList.get(position).getLocation_id()) {
                viewHolder.tv_name.setText(locationInfo.getName());
                viewHolder.tv_location.setText(locationInfo.getLocation());
                viewHolder.tv_mobile.setText(locationInfo.getMobile());
                break;
            }
        }
        for (GoodsInfo goodsInfo : goodsInfoList) {
            if (goodsInfo.get_id() == orderInfoList.get(position).getGoods_id()) {
                viewHolder.tv_goods_name.setText(goodsInfo.getName());
                viewHolder.tv_price.setText(goodsInfo.getPrice());
                if (goodsInfo.getImage() != null && goodsInfo.getImage().length() > 0) {
                    Glide.with(context).load(goodsInfo.getImage()).into(viewHolder.iv_goods);
                } else {
                    Glide.with(context).load(R.drawable.image_default).into(viewHolder.iv_goods);
                }
                break;
            }
        }
        if (orderInfoList.get(position).getIs_over() == 0) {
            viewHolder.tv_is_over.setVisibility(View.GONE);
            viewHolder.tv_over_order.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tv_is_over.setVisibility(View.VISIBLE);
            viewHolder.tv_over_order.setVisibility(View.GONE);
        }
        viewHolder.tv_over_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.tv_is_over.setVisibility(View.VISIBLE);
                viewHolder.tv_over_order.setVisibility(View.GONE);
                dataBaseProvider.updateOrderIsOver(orderInfoList.get(position).get_id());
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_order_id;
        private TextView tv_name;
        private TextView tv_mobile;
        private TextView tv_location;
        private ImageView iv_goods;
        private TextView tv_price;
        private TextView tv_goods_name;
        private TextView tv_over_order;
        private TextView tv_is_over;
    }
}
