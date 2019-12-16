package com.domineer.triplebro.wowdiary.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.adapters.ShoppingAdapter;
import com.domineer.triplebro.wowdiary.controllers.GoodsController;
import com.domineer.triplebro.wowdiary.interfaces.OnItemClickListener;
import com.domineer.triplebro.wowdiary.models.GoodsInfo;

import java.util.List;

public class GoodsDetailActivity extends Activity implements View.OnClickListener, OnItemClickListener {

    private TextView tv_goods_name;
    private TextView tv_price;
    private ImageView iv_goods;
    private ImageView iv_location;
    private TextView tv_buy;
    private GoodsController goodsController;
    private GoodsInfo goodsDetail;
    private SharedPreferences userInfo;
    private int user_id;
    private ImageView iv_close_goods_detail;
    private RecyclerView rv_shopping;
    private List<GoodsInfo> goodsInfoList;
    private ShoppingAdapter shoppingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
        tv_price = (TextView) findViewById(R.id.tv_price);
        iv_goods = (ImageView) findViewById(R.id.iv_goods);
        iv_location = (ImageView) findViewById(R.id.iv_location);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        iv_close_goods_detail = (ImageView) findViewById(R.id.iv_close_goods_detail);
        rv_shopping = (RecyclerView) findViewById(R.id.rv_shopping);
        rv_shopping.setLayoutManager(new GridLayoutManager(this,2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        goodsDetail = (GoodsInfo) intent.getSerializableExtra("goodsInfo");
        goodsController = new GoodsController(this);
        goodsInfoList = goodsController.getGoodsInfoList();
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        tv_goods_name.setText(goodsDetail.getName());
        tv_price.setText("￥"+goodsDetail.getPrice());
        shoppingAdapter = new ShoppingAdapter(this, goodsInfoList);
        rv_shopping.setAdapter(shoppingAdapter);
        if(goodsDetail.getImage()!= null && goodsDetail.getImage().length()>0){
            Glide.with(this).load(goodsDetail.getImage()).into(iv_goods);
        }else{
            Glide.with(this).load(R.drawable.image_default).into(iv_goods);
        }
    }

    private void setOnClickListener() {
        tv_buy.setOnClickListener(this);
        iv_close_goods_detail.setOnClickListener(this);
        iv_location.setOnClickListener(this);
        shoppingAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_goods_detail:
                finish();
                break;
            case R.id.iv_location:
                Intent location = new Intent(this, LocationActivity.class);
                startActivity(location);
                break;
            case R.id.tv_buy:
                Intent createOrder = new Intent(this, CreateOrderActivity.class);
                createOrder.putExtra("goodsInfo",goodsDetail);
                startActivity(createOrder);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent goodsDetail = new Intent(this, GoodsDetailActivity.class);
        goodsDetail.putExtra("goodsInfo",goodsInfoList.get(position));
        startActivity(goodsDetail);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
