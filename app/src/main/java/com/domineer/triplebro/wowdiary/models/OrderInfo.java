package com.domineer.triplebro.wowdiary.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/12/15,17:58
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class OrderInfo implements Serializable {

    private int _id;
    private int user_id;
    private int location_id;
    private int goods_id;
    private int is_over;

    public OrderInfo() {
    }

    public OrderInfo(int _id, int user_id, int location_id, int goods_id, int is_over) {
        this._id = _id;
        this.user_id = user_id;
        this.location_id = location_id;
        this.goods_id = goods_id;
        this.is_over = is_over;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getIs_over() {
        return is_over;
    }

    public void setIs_over(int is_over) {
        this.is_over = is_over;
    }
}
