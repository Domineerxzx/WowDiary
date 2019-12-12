package com.domineer.triplebro.wowdiary.controllers;

import android.content.Context;

import com.domineer.triplebro.wowdiary.activities.CollectActivity;
import com.domineer.triplebro.wowdiary.models.DairyInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/12,13:58
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CollectController {

    private Context context;
    private DataBaseProvider dataBaseProvider;

    public CollectController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<DairyInfo> getCollectDairyInfoList(int user_id) {
        return dataBaseProvider.getCollectDairyInfoList(user_id);
    }
}
