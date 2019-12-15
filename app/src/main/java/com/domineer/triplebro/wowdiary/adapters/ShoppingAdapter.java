package com.domineer.triplebro.wowdiary.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.interfaces.OnItemClickListener;
import com.domineer.triplebro.wowdiary.models.GoodsInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/14,16:44
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private Context context;
    private List<GoodsInfo> goodsInfoList;
    private OnItemClickListener onItemClickListener;
    public ShoppingAdapter(Context context, List<GoodsInfo> goodInfoList) {
        this.context = context;
        this.goodsInfoList = goodInfoList;
    }

    public List<GoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
    }

    public void setGoodsInfoList(List<GoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;

    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_goods, null);
        ViewHolder viewHolder = new ViewHolder(inflate,onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_goods_name.setText(goodsInfoList.get(position).getName());
        holder.tv_price.setText(goodsInfoList.get(position).getPrice());
        if(goodsInfoList.get(position).getImage()!= null && goodsInfoList.get(position).getImage().length()>0){
            Glide.with(context).load(goodsInfoList.get(position).getImage()).into(holder.iv_goods);
        }else{
            Glide.with(context).load(R.drawable.image_default).into(holder.iv_goods);
        }
    }

    @Override
    public int getItemCount() {
        return goodsInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_goods;
        private TextView tv_goods_name;
        private TextView tv_price;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            initView(itemView);
            this.onItemClickListener = onItemClickListener;
        }

        private void initView(View itemView) {
            tv_goods_name = itemView.findViewById(R.id.tv_goods_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            iv_goods = itemView.findViewById(R.id.iv_goods);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getPosition());
        }
    }
}
