package com.domineer.triplebro.wowdiary.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.adapters.PhotoWallAdapter;
import com.domineer.triplebro.wowdiary.controllers.DairyDetailController;
import com.domineer.triplebro.wowdiary.interfaces.OnItemClickListener;
import com.domineer.triplebro.wowdiary.models.DairyInfo;

import java.util.ArrayList;
import java.util.List;

public class DairyDetailActivity extends Activity implements View.OnClickListener, OnItemClickListener {

    private TextView tv_title_content;
    private TextView tv_content_content;
    private RecyclerView rv_dairy;
    private DairyDetailController dairyDetailController;
    private DairyInfo dairyInfo;
    private TextView tv_time;
    private PhotoWallAdapter photoWallAdapter;
    private ImageView iv_close_dairy_detail;
    private List<String> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy_ditail);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_title_content = (TextView) findViewById(R.id.tv_title_content);
        tv_content_content = (TextView) findViewById(R.id.tv_content_content);
        tv_time = (TextView) findViewById(R.id.tv_time);
        rv_dairy = (RecyclerView) findViewById(R.id.rv_dairy);
        rv_dairy.setLayoutManager(new GridLayoutManager(this,3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        iv_close_dairy_detail = (ImageView) findViewById(R.id.iv_close_dairy_detail);
    }

    private void initData() {
        Intent intent = getIntent();
        dairyInfo = (DairyInfo) intent.getSerializableExtra("dairyInfo");
        dairyDetailController = new DairyDetailController(this);
        String time = dairyInfo.getYear()+"年"+dairyInfo.getMonth()
                +"月"+dairyInfo.getDay()+"日  "+dairyInfo.getHour()
                +":"+dairyInfo.getMin()+":"+dairyInfo.getSecond();
        tv_time.setText(time);
        tv_title_content.setText(dairyInfo.getTitle());
        tv_content_content.setText(dairyInfo.getContent());
        imageList = dairyDetailController.getDairyImageInfoList(dairyInfo.get_id());
        photoWallAdapter = new PhotoWallAdapter(this, imageList);
        rv_dairy.setAdapter(photoWallAdapter);
    }

    private void setOnClickListener() {
        iv_close_dairy_detail.setOnClickListener(this);
        photoWallAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_dairy_detail:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view) {

    }
}
