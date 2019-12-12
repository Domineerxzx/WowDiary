package com.domineer.triplebro.wowdiary.controllers;

import android.content.Context;

import com.domineer.triplebro.wowdiary.activities.DairyDetailActivity;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/12,15:30
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DairyDetailController {
    private Context context;
    private DataBaseProvider dataBaseProvider;

    public DairyDetailController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<String> getDairyImageInfoList(int dairy_id) {
        return dataBaseProvider.getDairyImageInfoList(dairy_id);
    }
}
