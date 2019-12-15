package com.domineer.triplebro.wowdiary.controllers;

import android.content.Context;

import com.domineer.triplebro.wowdiary.models.GoodsInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/15,12:37
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class GoodsController {

    private Context context;
    private DataBaseProvider dataBaseProvider;

    public GoodsController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<GoodsInfo> getGoodsInfoList() {
        return dataBaseProvider.getGoodsInfoList();
    }
}
