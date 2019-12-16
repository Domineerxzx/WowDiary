package com.domineer.triplebro.wowdiary.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.domineer.triplebro.wowdiary.models.OrderInfo;
import com.domineer.triplebro.wowdiary.database.WowDiaryDataBase;
import com.domineer.triplebro.wowdiary.models.DairyInfo;
import com.domineer.triplebro.wowdiary.models.GoodsInfo;
import com.domineer.triplebro.wowdiary.models.LocationInfo;

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
        if (dairyImageInfoCursor != null && dairyImageInfoCursor.getCount() > 0) {
            while (dairyImageInfoCursor.moveToNext()) {
                imageList.add(dairyImageInfoCursor.getString(1));
            }
        }
        if (dairyImageInfoCursor != null) {
            dairyImageInfoCursor.close();
        }
        db.close();
        return imageList;
    }

    public List<GoodsInfo> getGoodsInfoList() {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        Cursor goodsInfoCursor = db.query("goodsInfo", null, null, null, null, null, null);
        List<GoodsInfo> goodsInfoList = new ArrayList<>();
        if (goodsInfoCursor != null && goodsInfoCursor.getCount() > 0) {
            while (goodsInfoCursor.moveToNext()) {
                GoodsInfo goodsInfo = new GoodsInfo();
                goodsInfo.set_id(goodsInfoCursor.getInt(0));
                goodsInfo.setName(goodsInfoCursor.getString(1));
                goodsInfo.setPrice(goodsInfoCursor.getString(2));
                goodsInfo.setImage(goodsInfoCursor.getString(3));
                goodsInfo.setAdmin_id(goodsInfoCursor.getInt(4));
                goodsInfoList.add(goodsInfo);
            }
        }
        if (goodsInfoCursor != null) {
            goodsInfoCursor.close();
        }
        db.close();
        return goodsInfoList;
    }

    public List<LocationInfo> getLocationInfo(int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        Cursor locationInfoCursor = db.query("locationInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        List<LocationInfo> locationInfoList = new ArrayList<>();
        if (locationInfoCursor != null && locationInfoCursor.getCount() > 0) {
            while (locationInfoCursor.moveToNext()) {
                LocationInfo locationInfo = new LocationInfo();
                locationInfo.set_id(locationInfoCursor.getInt(0));
                locationInfo.setLocation(locationInfoCursor.getString(1));
                locationInfo.setName(locationInfoCursor.getString(2));
                locationInfo.setMobile(locationInfoCursor.getString(3));
                locationInfo.setUser_id(locationInfoCursor.getInt(4));
                locationInfoList.add(locationInfo);
            }
        }
        if (locationInfoCursor != null) {
            locationInfoCursor.close();
        }
        db.close();
        return locationInfoList;
    }

    public void deleteLocationInfo(int location_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        db.delete("orderInfo", "location_id = ?", new String[]{String.valueOf(location_id)});
        db.delete("locationInfo", "_id = ?", new String[]{String.valueOf(location_id)});
        db.close();
    }

    public void addLocationInfo(String name, String mobile, String location, int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobile", mobile);
        contentValues.put("location", location);
        contentValues.put("user_id", user_id);
        db.insert("locationInfo", null, contentValues);
        db.close();
    }

    public void addOrderInfo(int user_id, int goods_id, int location_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("goods_id", goods_id);
        contentValues.put("location_id", location_id);
        contentValues.put("is_over", 0);
        db.insert("orderInfo", null, contentValues);
        db.close();
    }

    public List<OrderInfo> getOrderInfoList(int user_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        Cursor orderInfoCursor = db.query("orderInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        List<OrderInfo> orderInfoList = new ArrayList<>();
        if (orderInfoCursor != null && orderInfoCursor.getCount() > 0) {
            while (orderInfoCursor.moveToNext()) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.set_id(orderInfoCursor.getInt(0));
                orderInfo.setUser_id(orderInfoCursor.getInt(1));
                orderInfo.setGoods_id(orderInfoCursor.getInt(2));
                orderInfo.setLocation_id(orderInfoCursor.getInt(3));
                orderInfoList.add(orderInfo);
            }
        }
        if (orderInfoCursor != null) {
            orderInfoCursor.close();
        }
        db.close();
        return orderInfoList;
    }

    public void updateOrderIsOver(int order_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_over", 1);
        db.update("orderInfo", contentValues, "_id = ?", new String[]{String.valueOf(order_id)});
        db.close();
    }

    public void addGoodsInfo(String name, String price, String image_show, int admin_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("good_name", name);
        contentValues.put("good_price", price);
        contentValues.put("good_image", image_show);
        contentValues.put("admin_id", admin_id);
        db.insert("goodsInfo", null, contentValues);
        db.close();
    }

    public List<GoodsInfo> getGoodsInfoListByAdminId(int admin_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        Cursor goodsInfoCursor = db.query("goodsInfo", null, "admin_id = ?", new String[]{String.valueOf(admin_id)}, null, null, null);
        List<GoodsInfo> goodsInfoList = new ArrayList<>();
        if (goodsInfoCursor != null && goodsInfoCursor.getCount() > 0) {
            while (goodsInfoCursor.moveToNext()) {
                GoodsInfo goodsInfo = new GoodsInfo();
                goodsInfo.set_id(goodsInfoCursor.getInt(0));
                goodsInfo.setName(goodsInfoCursor.getString(1));
                goodsInfo.setPrice(goodsInfoCursor.getString(2));
                goodsInfo.setImage(goodsInfoCursor.getString(3));
                goodsInfo.setAdmin_id(goodsInfoCursor.getInt(4));
                goodsInfoList.add(goodsInfo);
            }
        }
        if (goodsInfoCursor != null) {
            goodsInfoCursor.close();
        }
        db.close();
        return goodsInfoList;
    }

    public void deleteGoodsInfoById(int goods_id) {
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        db.delete("orderInfo", "goods_id = ?", new String[]{String.valueOf(goods_id)});
        db.delete("goodsInfo", "_id = ?", new String[]{String.valueOf(goods_id)});
        db.close();
    }
}
