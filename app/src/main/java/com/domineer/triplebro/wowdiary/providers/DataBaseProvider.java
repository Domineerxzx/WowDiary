package com.domineer.triplebro.wowdiary.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.domineer.triplebro.wowdiary.database.WowDiaryDataBase;
import com.domineer.triplebro.wowdiary.models.DairyInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Domineer
 * @data 2019/8/15,23:26
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DataBaseProvider implements DataProvider {

    private Context context;
    private WowDiaryDataBase wowDiaryDataBase;

    public DataBaseProvider(Context context) {
        this.context = context;
        wowDiaryDataBase = new WowDiaryDataBase(context);
    }

    public List<DairyInfo> getDairyInfoListByDate(int year, int month, int dayOfMonth, int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        Cursor dairyInfoCursor = db.query("dairyInfo", null, "year = ? and month = ? and day = ? and user_id = ?"
                , new String[]{String.valueOf(year), String.valueOf(month), String.valueOf(dayOfMonth)
                        , String.valueOf(user_id)}, null, null, null);
        List<DairyInfo> dairyInfoList = new ArrayList<>();
        if (dairyInfoCursor != null && dairyInfoCursor.getCount() > 0) {
            while (dairyInfoCursor.moveToNext()) {
                DairyInfo dairyInfo = new DairyInfo();
                dairyInfo.set_id(dairyInfoCursor.getInt(0));
                dairyInfo.setTitle(dairyInfoCursor.getString(1));
                dairyInfo.setContent(dairyInfoCursor.getString(2));
                dairyInfo.setYear(dairyInfoCursor.getString(3));
                dairyInfo.setMonth(dairyInfoCursor.getString(4));
                dairyInfo.setDay(dairyInfoCursor.getString(5));
                dairyInfo.setHour(dairyInfoCursor.getString(6));
                dairyInfo.setMin(dairyInfoCursor.getString(7));
                dairyInfo.setSecond(dairyInfoCursor.getString(8));
                dairyInfo.setUser_id(dairyInfoCursor.getInt(9));
                dairyInfoList.add(dairyInfo);
            }
        }
        if (dairyInfoCursor != null) {
            dairyInfoCursor.close();
        }
        db.close();
        return dairyInfoList;
    }

    public boolean getIsCollect(int dairy_id, int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        Cursor collectInfoCursor = db.query("collectInfo", null, "dairy_id = ? and user_id = ?"
                , new String[]{String.valueOf(dairy_id), String.valueOf(user_id)}, null, null, null);
        if (collectInfoCursor != null && collectInfoCursor.getCount() > 0) {
            collectInfoCursor.close();
            db.close();
            return true;
        } else {
            if (collectInfoCursor != null) {
                collectInfoCursor.close();
            }
            db.close();
            return false;
        }
    }

    public void addCollect(int dairy_id, int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dairy_id", dairy_id);
        values.put("user_id", user_id);
        db.insert("collectInfo", null, values);
        db.close();
    }

    public void deleteCollect(int dairy_id, int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        db.delete("collectInfo", "dairy_id = ? and user_id = ?", new String[]{String.valueOf(dairy_id), String.valueOf(user_id)});
        db.close();
    }

    public List<DairyInfo> getDairyInfoListByUserId(int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        Cursor dairyInfoCursor = db.query("dairyInfo", null, "user_id = ?"
                , new String[]{String.valueOf(user_id)}, null, null, "_id DESC");
        List<DairyInfo> dairyInfoList = new ArrayList<>();
        if (dairyInfoCursor != null && dairyInfoCursor.getCount() > 0) {
            while (dairyInfoCursor.moveToNext()) {
                DairyInfo dairyInfo = new DairyInfo();
                dairyInfo.set_id(dairyInfoCursor.getInt(0));
                dairyInfo.setTitle(dairyInfoCursor.getString(1));
                dairyInfo.setContent(dairyInfoCursor.getString(2));
                dairyInfo.setYear(dairyInfoCursor.getString(3));
                dairyInfo.setMonth(dairyInfoCursor.getString(4));
                dairyInfo.setDay(dairyInfoCursor.getString(5));
                dairyInfo.setHour(dairyInfoCursor.getString(6));
                dairyInfo.setMin(dairyInfoCursor.getString(7));
                dairyInfo.setSecond(dairyInfoCursor.getString(8));
                dairyInfo.setUser_id(dairyInfoCursor.getInt(9));
                dairyInfoList.add(dairyInfo);
            }
        }
        if (dairyInfoCursor != null) {
            dairyInfoCursor.close();
        }
        db.close();
        return dairyInfoList;
    }

    public void addDairyInfo(String title, String content, int user_id, List<String> imageList) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("user_id", user_id);
        contentValues.put("content", content);
        Calendar instance = Calendar.getInstance();
        contentValues.put("year", instance.get(Calendar.YEAR));
        contentValues.put("month", instance.get(Calendar.MONTH) + 1);
        contentValues.put("day", instance.get(Calendar.DAY_OF_MONTH));
        contentValues.put("hour", instance.get(Calendar.HOUR_OF_DAY));
        contentValues.put("min", instance.get(Calendar.MINUTE));
        contentValues.put("second", instance.get(Calendar.SECOND));
        long dairyInfoId = db.insert("dairyInfo", null, contentValues);
        for (String image : imageList) {
            contentValues = new ContentValues();
            contentValues.put("dairy_id", dairyInfoId);
            contentValues.put("user_id", user_id);
            contentValues.put("image", image);
            db.insert("dairyImageInfo", null, contentValues);
        }
        db.close();
    }

    public List<DairyInfo> getCollectDairyInfoList(int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        Cursor collectInfoCursor = db.query("collectInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, "_id DESC");
        List<Integer> collectDairyIdList = new ArrayList<>();
        if (collectInfoCursor != null && collectInfoCursor.getCount() > 0) {
            while (collectInfoCursor.moveToNext()) {
                collectDairyIdList.add(collectInfoCursor.getInt(1));
            }
        }
        if (collectInfoCursor != null) {
            collectInfoCursor.close();
        }
        List<DairyInfo> dairyInfoList = new ArrayList<>();
        for (int dairy_id : collectDairyIdList) {
            Cursor dairyInfoCursor = db.query("dairyInfo", null, "_id = ?"
                    , new String[]{String.valueOf(dairy_id)}, null, null, null);
            if (dairyInfoCursor != null && dairyInfoCursor.getCount() > 0) {
                dairyInfoCursor.moveToNext();
                DairyInfo dairyInfo = new DairyInfo();
                dairyInfo.set_id(dairyInfoCursor.getInt(0));
                dairyInfo.setTitle(dairyInfoCursor.getString(1));
                dairyInfo.setContent(dairyInfoCursor.getString(2));
                dairyInfo.setYear(dairyInfoCursor.getString(3));
                dairyInfo.setMonth(dairyInfoCursor.getString(4));
                dairyInfo.setDay(dairyInfoCursor.getString(5));
                dairyInfo.setHour(dairyInfoCursor.getString(6));
                dairyInfo.setMin(dairyInfoCursor.getString(7));
                dairyInfo.setSecond(dairyInfoCursor.getString(8));
                dairyInfo.setUser_id(dairyInfoCursor.getInt(9));
                dairyInfoList.add(dairyInfo);
            }
            if (dairyInfoCursor != null) {
                dairyInfoCursor.close();
            }
        }
        db.close();
        return dairyInfoList;
    }

    public List<String> getDairyImageInfoList(int dairy_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        List<String> imageList = new ArrayList<>();
        Cursor dairyImageInfoCursor = db.query("dairyImageInfo", null, "dairy_id = ?", new String[]{String.valueOf(dairy_id)}, null, null, null);
        if(dairyImageInfoCursor!=null && dairyImageInfoCursor.getCount()>0){
            while (dairyImageInfoCursor.moveToNext()){
                imageList.add(dairyImageInfoCursor.getString(1));
            }
        }
        if (dairyImageInfoCursor != null) {
            dairyImageInfoCursor.close();
        }
        db.close();
        return imageList;
    }
}
