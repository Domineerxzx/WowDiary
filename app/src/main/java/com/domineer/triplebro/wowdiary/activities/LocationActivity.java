package com.domineer.triplebro.wowdiary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.adapters.LocationAdapter;
import com.domineer.triplebro.wowdiary.controllers.LocationController;
import com.domineer.triplebro.wowdiary.models.LocationInfo;
import com.domineer.triplebro.wowdiary.utils.dialogUtils.TwoButtonDialog;

import java.util.List;

public class LocationActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ImageView iv_close_location;
    private LocationController locationController;
    private ListView lv_location;
    private ImageView iv_add_location;
    private SharedPreferences userInfo;
    private int user_id;
    private List<LocationInfo> locationInfoList;
    private LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initView();
        initData();
        setOnClickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationInfoList = locationController.getLocationInfo(user_id);
        locationAdapter.setLocationInfoList(locationInfoList);
    }

    private void initView() {
        iv_close_location = (ImageView) findViewById(R.id.iv_close_location);
        iv_add_location = (ImageView) findViewById(R.id.iv_add_location);
        lv_location = (ListView) findViewById(R.id.lv_location);
    }

    private void initData() {
        locationController = new LocationController(this);
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        locationInfoList = locationController.getLocationInfo(user_id);
        locationAdapter = new LocationAdapter(this, locationInfoList);
        lv_location.setAdapter(locationAdapter);
    }

    private void setOnClickListener() {
        iv_close_location.setOnClickListener(this);
        iv_add_location.setOnClickListener(this);
        lv_location.setOnItemClickListener(this);
        lv_location.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_location:
                finish();
                break;
            case R.id.iv_add_location:
                Intent addLocation = new Intent(this, AddLocationActivity.class);
                startActivity(addLocation);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SharedPreferences.Editor edit = userInfo.edit();
        edit.putString("location_name",locationInfoList.get(position).getName());
        edit.putString("location_mobile",locationInfoList.get(position).getMobile());
        edit.putString("location",locationInfoList.get(position).getLocation());
        edit.putInt("location_id",locationInfoList.get(position).get_id());
        edit.commit();
        finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
        twoButtonDialog.show("删除地址信息", "是否要删除此条地址信息？(提示：删除地址信息会将对应的订单信息一并删除，请谨慎处理。)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LocationInfo removeLocation = locationInfoList.remove(position);
                locationController.deleteLocationInfo(removeLocation.get_id());
                locationAdapter.setLocationInfoList(locationInfoList);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        },getFragmentManager());
        return true;
    }
}
