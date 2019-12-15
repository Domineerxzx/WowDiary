package com.domineer.triplebro.wowdiary.controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.domineer.triplebro.wowdiary.activities.LocationActivity;
import com.domineer.triplebro.wowdiary.models.LocationInfo;
import com.domineer.triplebro.wowdiary.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/12/15,13:52
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class LocationController {

    private Context context;
    private DataBaseProvider dataBaseProvider;

    public LocationController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<LocationInfo> getLocationInfo(int user_id) {
        return dataBaseProvider.getLocationInfo(user_id);
    }

    public void deleteLocationInfo(int location_id) {
        dataBaseProvider.deleteLocationInfo(location_id);
    }

    public void addLocationInfo(String name, String mobile, String location, int user_id) {
        dataBaseProvider.addLocationInfo(name,mobile,location,user_id);
    }
}
