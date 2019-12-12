package com.domineer.triplebro.wowdiary.services;

import android.app.Activity;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.domineer.triplebro.wowdiary.database.WowDiaryDataBase;
import com.domineer.triplebro.wowdiary.handlers.AdPictureHandler;
import com.domineer.triplebro.wowdiary.handlers.LoginHandler;
import com.domineer.triplebro.wowdiary.handlers.RegisterHandler;
import com.domineer.triplebro.wowdiary.properties.ProjectProperties;
import com.domineer.triplebro.wowdiary.providers.HttpProvider;
import com.domineer.triplebro.wowdiary.utils.httpUtils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class NetworkConnectionService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {

        public void login(Context context, String phone_number, String password, ServiceConnection serviceConnection, int userType) {
            NetworkConnectionService.this.login(context, phone_number, password, serviceConnection, userType);
        }

        public void register(Context context, String phone_number,/* String request_code,*/ String password, String nickname, ServiceConnection serviceConnection, int userType) {
            NetworkConnectionService.this.register(context, phone_number, /*request_code,*/ password, nickname, serviceConnection, userType);
        }

        public void getRequestCode(Context context, String phone_number, ServiceConnection serviceConnection) {
            NetworkConnectionService.this.getRequestCode(context, phone_number, serviceConnection);
        }

        public void getAdPicture(AdPictureHandler adPictureHandler) {
            NetworkConnectionService.this.getAdPicture(adPictureHandler);
        }
    }

    private void getAdPicture(AdPictureHandler adPictureHandler) {
        HttpProvider httpProvider = new HttpProvider();
        httpProvider.getAdPicture(ProjectProperties.AD_PICTURE_PATH, adPictureHandler);
    }

    private void updateRegisterInfo(final Context context, final String phone_number, final String password, final String nickname, ServiceConnection serviceConnection, final RegisterHandler registerHandler, final int userType, long userInfo) {

        if (userType == ProjectProperties.ADMIN) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("adminInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("user_id", Integer.parseInt(String.valueOf(userInfo)));
            edit.putString("nickname", nickname);
            edit.putString("phone_number", phone_number);
            edit.putString("password", password);
            edit.commit();
            registerHandler.sendEmptyMessage(ProjectProperties.REGISTER_ADMIN_SUCCESS);
        } else {
            SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("user_id", Integer.parseInt(String.valueOf(userInfo)));
            edit.putString("nickname", nickname);
            edit.putString("phone_number", phone_number);
            edit.putString("password", password);
            edit.commit();
            registerHandler.sendEmptyMessage(ProjectProperties.REGISTER_SUCCESS);
        }

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("userPhoneNumber", phone_number);
        builder.add("userNickName", nickname);
        builder.add("userPassword", password);
        builder.build();
        HttpUtils.sendOkHttpRequest(ProjectProperties.ADDRESS_UPDATE_REGISTER_INFO, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String register_message = response.body().string();
                if (register_message.equals("{\"message\":1}")) {
                    if (userType == ProjectProperties.ADMIN) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("adminInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("nickname", nickname);
                        edit.putString("phone_number", phone_number);
                        edit.putString("password", password);
                        edit.commit();
                        registerHandler.sendEmptyMessage(ProjectProperties.REGISTER_ADMIN_SUCCESS);
                    } else {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("nickname", nickname);
                        edit.putString("phone_number", phone_number);
                        edit.putString("password", password);
                        edit.commit();
                        registerHandler.sendEmptyMessage(ProjectProperties.REGISTER_SUCCESS);
                    }
                } else {
                    registerHandler.sendEmptyMessage(ProjectProperties.REGISTER_FAILED);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "更新注册信息失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void getRequestCode(final Context context, final String phone_number, ServiceConnection serviceConnection) {
        final RegisterHandler registerHandler = new RegisterHandler(context, serviceConnection);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phoneNumber", phone_number);
        builder.build();
        HttpUtils.sendOkHttpRequest(ProjectProperties.ADDRESS_GET_REQUEST_CODE, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String register_message = response.body().string();
                System.out.println("registerRequestCode------------------------------------" + register_message);
                if (register_message.contains("{\"message\":1}")) {
                    registerHandler.sendEmptyMessage(ProjectProperties.GET_REQUEST_CODE_SUCCESS);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "获取验证码", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    registerHandler.sendEmptyMessage(ProjectProperties.GET_REQUEST_CODE_FAILED);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "获取验证码失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void register(final Context context, final String phone_number, /*String request_code, */final String password, final String nickname, final ServiceConnection serviceConnection, final int userType) {
        final RegisterHandler registerHandler = new RegisterHandler(context, serviceConnection);
        long userInfo;
        WowDiaryDataBase wowDiaryDataBase = new WowDiaryDataBase(context);
        SQLiteDatabase writableDatabase = wowDiaryDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone_number", phone_number);
        contentValues.put("password", password);
        if (userType == ProjectProperties.ADMIN) {
            userInfo = writableDatabase.insert("adminInfo", null, contentValues);
        } else {
            contentValues.put("nickname", nickname);
            userInfo = writableDatabase.insert("userInfo", null, contentValues);
        }
        updateRegisterInfo(context, phone_number, password, nickname, serviceConnection, registerHandler, userType, userInfo);
        if (userInfo != -1) {
            System.out.println("SUCCESS-----------------------数据库插入成功" + userInfo);
            writableDatabase.close();
        } else {
            System.out.println("error-----------------------数据库插入失败" + userInfo);
            writableDatabase.close();
        }
    }

    private void login(final Context context, final String phone_number, final String password, ServiceConnection serviceConnection, final int userType) {

        int isShutUp;
        int userId;
        int adminId;
        String userHead;
        String nickname;
        final LoginHandler loginHandler = new LoginHandler(context, serviceConnection);
        boolean isCheckSuccess = false;
        WowDiaryDataBase wowDiaryDataBase = new WowDiaryDataBase(context);
        SQLiteDatabase db = wowDiaryDataBase.getWritableDatabase();
        if (userType == ProjectProperties.ADMIN) {
            Cursor adminInfo = db.query("adminInfo", null, "phone_number = ?", new String[]{phone_number}, null, null, null);
            if (adminInfo != null && adminInfo.getCount() > 0) {
                while (adminInfo.moveToNext()) {
                    String pw = adminInfo.getString(2);
                    adminId = adminInfo.getInt(0);
                    String nickName = adminInfo.getString(3);
                    String adminHead = adminInfo.getString(4);
                    if (password.equals(pw)) {
                        isCheckSuccess = true;
                        SharedPreferences sharedPreferences = context.getSharedPreferences("adminInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putInt("admin_id", adminId);
                        edit.putString("phone_number", phone_number);
                        edit.putString("password", password);
                        edit.putString("nickName", nickName);
                        edit.putString("userHead", adminHead);
                        edit.commit();
                        loginHandler.sendEmptyMessage(ProjectProperties.LOGIN_ADMIN_SUCCESS);
                        return;
                    }
                }
                if (isCheckSuccess == false) {
                    loginHandler.sendEmptyMessage(ProjectProperties.LOGIN_ADMIN_FAILED);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                loginHandler.sendEmptyMessage(ProjectProperties.LOGIN_ADMIN_FAILED);
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "未找到该管理员", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Cursor userInfo = db.query("userInfo", null, "phone_number = ?", new String[]{phone_number}, null, null, null);
            if (userInfo != null && userInfo.getCount() > 0) {
                while (userInfo.moveToNext()) {
                    String pw = userInfo.getString(2);
                    if (password.equals(pw)) {
                        isCheckSuccess = true;
                        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putInt("user_id", userInfo.getInt(0));
                        edit.putString("phone_number", phone_number);
                        edit.putString("password", password);
                        edit.putString("nickname", userInfo.getString(3));
                        edit.putString("userHead", userInfo.getString(4));
                        edit.commit();
                        loginHandler.sendEmptyMessage(ProjectProperties.LOGIN_SUCCESS);
                        return;
                    }
                }
                if (isCheckSuccess == false) {
                    loginHandler.sendEmptyMessage(ProjectProperties.LOGIN_FAILED);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                loginHandler.sendEmptyMessage(ProjectProperties.LOGIN_FAILED);
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "未找到该用户", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }
}
