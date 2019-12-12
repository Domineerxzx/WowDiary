package com.domineer.triplebro.wowdiary.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/12/11,14:32
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DairyInfo implements Serializable {

    private int _id;
    private String title;
    private String content;
    private String year;
    private String month;
    private String day;
    private String hour;
    private String min;
    private String second;
    private int user_id;

    public DairyInfo() {
    }

    public DairyInfo(int _id, String title, String content, String year, String month, String day, String hour, String min, String second, int user_id) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.second = second;
        this.user_id = user_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "DairyInfo{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                ", min='" + min + '\'' +
                ", second='" + second + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
