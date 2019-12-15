package com.domineer.triplebro.wowdiary.controllers;

import android.content.Context;

import com.domineer.triplebro.wowdiary.models.OrderInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/15,17:02
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class OrderController {

    private Context context;
    private DataBaseProvider dataBaseProvider;

    public OrderController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public void addOrderInfo(int user_id, int goods_id, int location_id) {
        dataBaseProvider.addOrderInfo(user_id,goods_id,location_id);
    }

    public List<OrderInfo> getOrderInfoList(int user_id) {
        return dataBaseProvider.getOrderInfoList(user_id);
    }
}
