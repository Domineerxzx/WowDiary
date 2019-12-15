package com.domineer.triplebro.wowdiary.handlers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.wowdiary.activities.AdminManagerActivity;
import com.domineer.triplebro.wowdiary.activities.LoginOrRegisterActivity;
import com.domineer.triplebro.wowdiary.activities.MainActivity;
import com.domineer.triplebro.wowdiary.activities.SplashActivity;
import com.domineer.triplebro.wowdiary.controllers.DataInitController;
import com.domineer.triplebro.wowdiary.properties.ProjectProperties;

public class AdPictureHandler extends Handler {

    private Context context;
    private ImageView iv_ad;
    private DataInitController dataInitController;

    public AdPictureHandler(Context context, ImageView iv_ad) {
        this.context = context;
        this.iv_ad = iv_ad;
    }

    public DataInitController getDataInitController() {
        return dataInitController;
    }

    public void setDataInitController(DataInitController dataInitController) {
        this.dataInitController = dataInitController;
    }

    @Override
    public void handleMessage(Message msg) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int user_id = userInfo.getInt("user_id", -1);
        SharedPreferences adminInfo = context.getSharedPreferences("adminInfo", Context.MODE_PRIVATE);
        int admin_id = adminInfo.getInt("admin_id", -1);
        switch (msg.what) {
            case ProjectProperties.AD_PICTURE:
                String adPicture = (String) msg.obj;
                Glide.with(context).load(adPicture).into(iv_ad);
                break;
            case ProjectProperties.SKIP:
                if(admin_id != -1){
                    if(dataInitController == null){
                        break;
                    }else{
                        context.unbindService(dataInitController);
                        Intent main = new Intent(context, AdminManagerActivity.class);
                        context.startActivity(main);
                        ((SplashActivity) context).finish();
                        break;
                    }
                }else if(user_id != -1){
                    if(dataInitController == null){
                        break;
                    }else{
                        context.unbindService(dataInitController);
                        Intent main = new Intent(context, MainActivity.class);
                        context.startActivity(main);
                        ((SplashActivity) context).finish();
                        break;
                    }
                }else{
                    context.unbindService(dataInitController);
                    Intent login_or_register = new Intent(context, LoginOrRegisterActivity.class);
                    context.startActivity(login_or_register);
                    ((SplashActivity) context).finish();
                    break;
                }
        }
    }
}
