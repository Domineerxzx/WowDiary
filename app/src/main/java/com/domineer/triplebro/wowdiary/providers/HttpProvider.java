package com.domineer.triplebro.wowdiary.providers;

import android.os.Message;

import com.domineer.triplebro.wowdiary.handlers.AdPictureHandler;
import com.domineer.triplebro.wowdiary.properties.ProjectProperties;
import com.domineer.triplebro.wowdiary.utils.httpUtils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author Domineer
 * @data 2019/8/25,7:20
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HttpProvider implements DataProvider {


    public void getAdPicture(String adPicturePath, final AdPictureHandler adPictureHandler) {
        HttpUtils.sendOkHttpRequest(adPicturePath, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String adPicture = response.body().string();
                Message message = new Message();
                message.obj = adPicture;
                message.what = ProjectProperties.AD_PICTURE;
                adPictureHandler.sendMessage(message);
                try {
                    Thread.sleep(2500);
                    adPictureHandler.sendEmptyMessage(ProjectProperties.SKIP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
