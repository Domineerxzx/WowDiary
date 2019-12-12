package com.domineer.triplebro.wowdiary.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.activities.DairyDetailActivity;
import com.domineer.triplebro.wowdiary.adapters.DateAdapter;
import com.domineer.triplebro.wowdiary.controllers.MyDairyController;
import com.domineer.triplebro.wowdiary.models.DairyInfo;

import java.util.List;

public class MyDairyFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View fragment_my_dairy;
    private ListView lv_my_dairy;
    private SharedPreferences userInfo;
    private int user_id;
    private MyDairyController myDairyController;
    private List<DairyInfo> dairyInfoList;
    private DateAdapter dateAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_my_dairy = inflater.inflate(R.layout.fragment_my_dairy, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_my_dairy;
    }

    private void initView() {
        lv_my_dairy = (ListView) fragment_my_dairy.findViewById(R.id.lv_my_dairy);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        myDairyController = new MyDairyController(getActivity());
        dairyInfoList = myDairyController.getDairyInfoListByUserId(user_id);
        dateAdapter = new DateAdapter(getActivity(), dairyInfoList);
        lv_my_dairy.setAdapter(dateAdapter);
    }

    private void setOnClickListener() {
        lv_my_dairy.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent dairyDetail = new Intent(getActivity(), DairyDetailActivity.class);
        dairyDetail.putExtra("dairyInfo",dairyInfoList.get(position));
        getActivity().startActivity(dairyDetail);
    }
}
