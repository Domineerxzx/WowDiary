package com.domineer.triplebro.wowdiary.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.adapters.OrderAdapter;
import com.domineer.triplebro.wowdiary.controllers.OrderController;
import com.domineer.triplebro.wowdiary.models.OrderInfo;

import java.util.List;

public class OrderActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_order;
    private ListView lv_order;
    private OrderController orderController;
    private SharedPreferences userInfo;
    private int user_id;
    private List<OrderInfo> orderInfoList;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_order = (ImageView) findViewById(R.id.iv_close_order);
        lv_order = (ListView) findViewById(R.id.lv_order);
    }

    private void initData() {
        orderController = new OrderController(this);
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        orderInfoList = orderController.getOrderInfoList(user_id);
        orderAdapter = new OrderAdapter(this, orderInfoList);
        lv_order.setAdapter(orderAdapter);
    }

    private void setOnClickListener() {
        iv_close_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_order:
                finish();
                break;
        }
    }
}
