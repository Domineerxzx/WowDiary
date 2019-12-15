package com.domineer.triplebro.wowdiary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.utils.PermissionUtil;

public class AdminManagerActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_admin_manager;
    private TextView tv_add_goods;
    private TextView tv_delete_goods;
    private SharedPreferences adminInfo;
    private int admin_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_admin_manager = (ImageView) findViewById(R.id.iv_close_admin_manager);
        tv_add_goods = (TextView) findViewById(R.id.tv_add_goods);
        tv_delete_goods = (TextView) findViewById(R.id.tv_delete_goods);
    }

    private void initData() {
        PermissionUtil.requestPower(this, this, "android.permission.CAMERA"); //请求权限
        PermissionUtil.requestPower(this, this, "android.permission.WRITE_EXTERNAL_STORAGE");
        adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
        admin_id = adminInfo.getInt("admin_id", -1);
    }

    private void setOnClickListener() {
        iv_close_admin_manager.setOnClickListener(this);
        tv_add_goods.setOnClickListener(this);
        tv_delete_goods.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_admin_manager:
                SharedPreferences.Editor edit = adminInfo.edit();
                edit.clear();
                edit.commit();
                Intent login_or_register = new Intent(this, LoginOrRegisterActivity.class);
                startActivity(login_or_register);
                finish();
                break;
            case R.id.tv_add_goods:
                Intent add_goods = new Intent(this, AddGoodActivity.class);
                startActivity(add_goods);
                break;
            case R.id.tv_delete_goods:
                Intent delete_goods = new Intent(this, DeleteGoodActivity.class);
                startActivity(delete_goods);
                break;
        }
    }
}
