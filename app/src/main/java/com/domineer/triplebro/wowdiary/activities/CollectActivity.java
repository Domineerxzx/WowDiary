package com.domineer.triplebro.wowdiary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.adapters.DateAdapter;
import com.domineer.triplebro.wowdiary.controllers.CollectController;
import com.domineer.triplebro.wowdiary.models.DairyInfo;

import java.util.List;

public class CollectActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ImageView iv_close_collect;
    private ListView lv_collect;
    private CollectController collectController;
    private int user_id;
    private SharedPreferences userInfo;
    private List<DairyInfo> dairyInfoList;
    private DateAdapter dateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_collect = (ImageView) findViewById(R.id.iv_close_collect);
        lv_collect = (ListView) findViewById(R.id.lv_collect);
    }

    private void initData() {
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        collectController = new CollectController(this);
        dairyInfoList = collectController.getCollectDairyInfoList(user_id);
        dateAdapter = new DateAdapter(this, dairyInfoList);
        lv_collect.setAdapter(dateAdapter);
    }

    private void setOnClickListener() {
        iv_close_collect.setOnClickListener(this);
        lv_collect.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent dairyDetail = new Intent(this, DairyDetailActivity.class);
        dairyDetail.putExtra("dairyInfo",dairyInfoList.get(position));
        startActivity(dairyDetail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_collect:
                finish();
                break;
        }
    }
}
