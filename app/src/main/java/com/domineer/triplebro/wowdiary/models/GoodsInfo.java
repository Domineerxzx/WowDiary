package com.domineer.triplebro.wowdiary.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/12/14,16:19
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class GoodsInfo implements Serializable {

    private int _id;
    private String name;
    private String image;
    private String price;
    private int admin_id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
