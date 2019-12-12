package com.domineer.triplebro.wowdiary.controllers;

import android.app.Activity;
import android.content.Context;

import com.domineer.triplebro.wowdiary.models.DairyInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/12,10:12
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class MyDairyController {
    private Context context;
    private DataBaseProvider dataBaseProvider;

    public MyDairyController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<DairyInfo> getDairyInfoListByUserId(int user_id) {
        return dataBaseProvider.getDairyInfoListByUserId(user_id);
    }
}
