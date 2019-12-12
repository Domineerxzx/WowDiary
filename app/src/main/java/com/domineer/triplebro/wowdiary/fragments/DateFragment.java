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
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.activities.DairyDetailActivity;
import com.domineer.triplebro.wowdiary.adapters.DateAdapter;
import com.domineer.triplebro.wowdiary.controllers.DateController;
import com.domineer.triplebro.wowdiary.models.DairyInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateFragment extends Fragment implements CalendarView.OnDateChangeListener, AdapterView.OnItemClickListener {

    private View fragment_date;
    private CalendarView cv_date;
    private ListView lv_date;
    private int user_id;
    private SharedPreferences userInfo;
    private DateController dateController;
    private DateAdapter dateAdapter;
    private List<DairyInfo> dairyInfoListByDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_date = inflater.inflate(R.layout.fragment_date, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_date;
    }

    private void initView() {
        cv_date = (CalendarView) fragment_date.findViewById(R.id.cv_date);
        lv_date = (ListView) fragment_date.findViewById(R.id.lv_date);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        dateController = new DateController(getActivity());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dateAdapter = new DateAdapter(getActivity(), new ArrayList<DairyInfo>());
        lv_date.setAdapter(dateAdapter);
        dairyInfoListByDate = dateController.getDairyInfoListByDate(year, month, day, user_id);
        dateAdapter.setDairyInfoList(dairyInfoListByDate);
    }

    private void setOnClickListener() {
        cv_date.setOnDateChangeListener(this);
        lv_date.setOnItemClickListener(this);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        dairyInfoListByDate = dateController.getDairyInfoListByDate(year, month+1, dayOfMonth, user_id);
        dateAdapter.setDairyInfoList(dairyInfoListByDate);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent dairyDetail = new Intent(getActivity(), DairyDetailActivity.class);
        dairyDetail.putExtra("dairyInfo",dairyInfoListByDate.get(position));
        getActivity().startActivity(dairyDetail);
    }
}
