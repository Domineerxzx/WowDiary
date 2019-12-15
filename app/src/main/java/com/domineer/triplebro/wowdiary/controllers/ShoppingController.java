package com.domineer.triplebro.wowdiary.controllers;

import android.content.Context;

import com.domineer.triplebro.wowdiary.models.GoodsInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/14,16:06
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ShoppingController {

    private Context context;
    private DataBaseProvider dataBaseProvider;

    public ShoppingController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<GoodsInfo> getGoodsInfoList() {
        return dataBaseProvider.getGoodsInfoList();
    }
}
