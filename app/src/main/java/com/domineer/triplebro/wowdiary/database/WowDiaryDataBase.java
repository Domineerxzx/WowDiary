package com.domineer.triplebro.wowdiary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author Domineer
 * @data 2019/11/28,19:25
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class WowDiaryDataBase extends SQLiteOpenHelper {

    private Context context;

    public WowDiaryDataBase(@Nullable Context context) {
        super(context, "WowDiaryDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户表
        db.execSQL("create table userInfo(_id Integer primary key autoincrement,phone_number varchar(20),password varchar(20)" +
                ",nickname varchar(20),user_head varchar(200))");

        //管理员表
        db.execSQL("create table adminInfo(_id Integer primary key autoincrement,phone_number varchar(20),password varchar(20),nickname varchar(20),user_head varchar(200))");

        //日记信息表
        db.execSQL("create table dairyInfo(_id Integer primary key autoincrement,title varchar(200),content varchar(2000),year varchar(200),month varchar(200),day varchar(200),hour varchar(200),min varchar(200),second varchar(200),user_id Integer,FOREIGN KEY (user_id) REFERENCES userInfo(_id))");

        //日记照片信息
        db.execSQL("create table dairyImageInfo(_id Integer primary key autoincrement,image varchar(200),dairy_id Integer,user_id Integer,FOREIGN KEY (user_id) REFERENCES userInfo(_id),FOREIGN KEY (dairy_id) REFERENCES dairyInfo(_id))");

        //收藏表
        db.execSQL("create table collectInfo(_id Integer primary key autoincrement,dairy_id Integer,user_id Integer,FOREIGN KEY (user_id) REFERENCES userInfo(_id),FOREIGN KEY (dairy_id) REFERENCES dairyInfo(_id))");

        //商品表
        db.execSQL("create table goodsInfo(_id Integer primary key autoincrement,good_name varchar(200),good_price varchar(200),good_image varchar(200),admin_id Integer,FOREIGN KEY(admin_id) REFERENCES admianInfo(_id))");

        //地址表
        db.execSQL("create table locationInfo(_id Integer primary key autoincrement,location varchar(2000),name varchar(200),mobile varchar(20),user_id Integer,FOREIGN KEY (user_id) REFERENCES userInfo(_id))");

        //订单表
        db.execSQL("create table orderInfo(_id Integer primary key autoincrement,user_id Integer,goods_id Integer,location_id Integer,is_over Integer,FOREIGN KEY (user_id) REFERENCES userInfo(_id),FOREIGN KEY (goods_id) REFERENCES goodsInfo(_id),FOREIGN KEY (location_id) REFERENCES locationInfo(_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints 开启外键约束
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }
}
