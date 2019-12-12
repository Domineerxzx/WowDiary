package com.domineer.triplebro.wowdiary.controllers;

import android.app.Activity;
import android.content.Context;

import com.domineer.triplebro.wowdiary.models.DairyInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/11,11:56
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DateController {
    private Context context;
    private DataBaseProvider dataBaseProvider;

    public DateController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<DairyInfo> getDairyInfoListByDate(int year, int month, int dayOfMonth, int user_id) {
        return dataBaseProvider.getDairyInfoListByDate(year,month,dayOfMonth,user_id);
    }

    public boolean getIsCollect(int dairy_id, int user_id) {
        return dataBaseProvider.getIsCollect(dairy_id,user_id);
    }

    public void addCollect(int dairy_id, int user_id) {
        dataBaseProvider.addCollect(dairy_id,user_id);
    }

    public void deleteCollect(int dairy_id, int user_id) {
        dataBaseProvider.deleteCollect(dairy_id,user_id);
    }
}
