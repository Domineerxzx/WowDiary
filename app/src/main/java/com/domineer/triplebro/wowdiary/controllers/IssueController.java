package com.domineer.triplebro.wowdiary.controllers;

import android.app.Activity;
import android.content.Context;

import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/12,11:17
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class IssueController {

    private Context context;
    private DataBaseProvider dataBaseProvider;

    public IssueController(Context context) {
        this.context =context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public void addDairyInfo(String title, String content, int user_id, List<String> imageList) {
        dataBaseProvider.addDairyInfo(title,content,user_id,imageList);
    }
}
