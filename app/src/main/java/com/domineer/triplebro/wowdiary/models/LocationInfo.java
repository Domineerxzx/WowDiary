package com.domineer.triplebro.wowdiary.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/12/15,15:09
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class LocationInfo implements Serializable {

    private int _id;
    private String location;
    private String name;
    private String mobile;
    private int user_id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
