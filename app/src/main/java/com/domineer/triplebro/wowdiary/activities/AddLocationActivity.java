package com.domineer.triplebro.wowdiary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.controllers.LocationController;

public class AddLocationActivity extends Activity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_location;
    private EditText et_mobile;
    private ImageView iv_close_add_location;
    private TextView tv_add_location;
    private LocationController locationController;
    private SharedPreferences userInfo;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_location = (EditText) findViewById(R.id.et_location);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        iv_close_add_location = (ImageView) findViewById(R.id.iv_close_add_location);
        tv_add_location = (TextView) findViewById(R.id.tv_add_location);
    }

    private void initData() {
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        locationController = new LocationController(this);
    }

    private void setOnClickListener() {
        tv_add_location.setOnClickListener(this);
        iv_close_add_location.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_add_location:
                finish();
                break;
            case R.id.tv_add_location:
                String name = et_name.getText().toString().trim();
                String mobile = et_mobile.getText().toString().trim();
                String location = et_location.getText().toString().trim();
                locationController.addLocationInfo(name,mobile,location,user_id);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                et_location.setText("");
                et_name.setText("");
                et_mobile.setText("");
                break;
        }
    }
}
