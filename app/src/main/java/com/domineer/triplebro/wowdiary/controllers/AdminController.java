package com.domineer.triplebro.wowdiary.controllers;

import android.content.Context;

import com.domineer.triplebro.wowdiary.activities.AddGoodActivity;
import com.domineer.triplebro.wowdiary.models.GoodsInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/15,19:19
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AdminController {

    private Context context;
    private DataBaseProvider dataBaseProvider;

    public AdminController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public void addGoodsInfo(String name, String price, String image_show, int admin_id) {
        dataBaseProvider.addGoodsInfo(name,price,image_show,admin_id);
    }

    public List<GoodsInfo> getGoodsInfoListByAdminId(int admin_id) {
        return dataBaseProvider.getGoodsInfoListByAdminId(admin_id);
    }

    public void deleteGoodsInfoById(int goods_id) {
        dataBaseProvider.deleteGoodsInfoById(goods_id);
    }
}
