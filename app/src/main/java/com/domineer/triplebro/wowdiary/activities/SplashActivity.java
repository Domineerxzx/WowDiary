package com.domineer.triplebro.wowdiary.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.controllers.DataInitController;
import com.domineer.triplebro.wowdiary.handlers.AdPictureHandler;
import com.domineer.triplebro.wowdiary.services.NetworkConnectionService;
import com.idescout.sql.SqlScoutServer;

public class SplashActivity extends Activity implements View.OnClickListener {

    private TextView tv_skip;
    private ImageView iv_ad;
    private AdPictureHandler adPictureHandler;
    private DataInitController dataInitController;
    private SharedPreferences userInfo;
    private int user_id;
    private SharedPreferences adminInfo;
    private int admin_id;
    /*private AdPictureHandler adPictureHandler;
    private DataInitManager dataInitController;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SqlScoutServer.create(this, getPackageName());
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        iv_ad = (ImageView) findViewById(R.id.iv_ad);
    }

    private void initData() {
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
        admin_id = adminInfo.getInt("admin_id", -1);
        adPictureHandler = new AdPictureHandler(this, iv_ad);
        dataInitController = new DataInitController(this, adPictureHandler);
        adPictureHandler.setDataInitController(dataInitController);
        Intent intent = new Intent(this, NetworkConnectionService.class);
        startService(intent);
        dataInitController.getAdPicture();
    }

    private void setOnClickListener() {
        tv_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                adPictureHandler.setDataInitController(null);
                unbindService(dataInitController);
                if(admin_id != -1){
                    Intent main = new Intent(this, AdminManagerActivity.class);
                    startActivity(main);
                }else if (user_id != -1) {
                    Intent main = new Intent(this, MainActivity.class);
                    startActivity(main);
                } else {
                    Intent login_or_register = new Intent(this, LoginOrRegisterActivity.class);
                    startActivity(login_or_register);
                }
                finish();
                break;
        }
    }
}