package com.domineer.triplebro.wowdiary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.controllers.OrderController;
import com.domineer.triplebro.wowdiary.models.GoodsInfo;

public class CreateOrderActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_create_order;
    private OrderController orderController;
    private GoodsInfo goodsInfo;
    private SharedPreferences userInfo;
    private int user_id;
    private RelativeLayout rl_location_select;
    private TextView tv_location;
    private TextView tv_mobile;
    private TextView tv_name;
    private ImageView iv_select_location;
    private TextView tv_price;
    private TextView tv_goods_name;
    private ImageView iv_goods;
    private String location_name;
    private String location;
    private String location_mobile;
    private TextView tv_create_order;
    private int location_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        initView();
        initData();
        setOnClickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        location_name = userInfo.getString("location_name", "");
        location = userInfo.getString("location", "");
        location_mobile = userInfo.getString("location_mobile", "");
        location_id = userInfo.getInt("location_id", -1);
        if(location.length() == 0 || location_name.length() == 0 ||location_mobile.length() == 0){
            tv_location.setText("请选择收货地址");
        }else{
            tv_name.setText(location_name);
            tv_location.setText(location);
            tv_mobile.setText(location_mobile);
        }
    }

    private void initView() {
        iv_close_create_order = (ImageView) findViewById(R.id.iv_close_create_order);
        iv_goods = (ImageView) findViewById(R.id.iv_goods);
        tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
        tv_price = (TextView) findViewById(R.id.tv_price);
        iv_select_location = (ImageView) findViewById(R.id.iv_select_location);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_location = (TextView) findViewById(R.id.tv_location);
        rl_location_select = (RelativeLayout) findViewById(R.id.rl_location_select);
        tv_create_order = (TextView) findViewById(R.id.tv_create_order);
    }

    private void initData() {
        Intent intent = getIntent();
        goodsInfo = (GoodsInfo) intent.getSerializableExtra("goodsInfo");
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        orderController = new OrderController(this);
        tv_goods_name.setText(goodsInfo.getName());
        tv_price.setText(goodsInfo.getPrice());
        if(goodsInfo.getImage() != null && goodsInfo.getImage().length()>0){
            Glide.with(this).load(goodsInfo.getImage()).into(iv_goods);
        }else{
            Glide.with(this).load(R.drawable.image_default).into(iv_goods);
        }
    }

    private void setOnClickListener() {
        iv_close_create_order.setOnClickListener(this);
        iv_select_location.setOnClickListener(this);
        tv_location.setOnClickListener(this);
        tv_mobile.setOnClickListener(this);
        tv_name.setOnClickListener(this);
        rl_location_select.setOnClickListener(this);
        tv_create_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_create_order:
                finish();
                break;
            case R.id.tv_name:
            case R.id.iv_select_location:
            case R.id.tv_location:
            case R.id.tv_mobile:
            case R.id.rl_location_select:
                Intent location = new Intent(this, LocationActivity.class);
                startActivity(location);
                break;
            case R.id.tv_create_order:
                orderController.addOrderInfo(user_id,goodsInfo.get_id(),location_id);
                Toast.makeText(this, "创建订单成功，可在订单管理中查看", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
