package com.domineer.triplebro.wowdiary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.controllers.AdminController;
import com.domineer.triplebro.wowdiary.properties.ProjectProperties;
import com.domineer.triplebro.wowdiary.utils.RealPathFromUriUtils;
import com.domineer.triplebro.wowdiary.utils.dialogUtils.ChooseUserHeadDialogUtil;

import java.io.File;
import java.util.List;

public class AddGoodActivity extends Activity implements View.OnClickListener {

    private SharedPreferences adminInfo;
    private int admin_id;
    private ImageView iv_close_add_goods;
    private ImageView iv_goods_image_show;
    private ImageView iv_delete_goods_image_show;
    private EditText et_name;
    private TextView tv_add_goods;
    private EditText et_price;
    private String phone_number;
    private String image_show;
    private long timeStamp;
    private AdminController adminController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_good);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_add_goods = (ImageView) findViewById(R.id.iv_close_add_goods);
        iv_goods_image_show = (ImageView) findViewById(R.id.iv_goods_image_show);
        iv_delete_goods_image_show = (ImageView) findViewById(R.id.iv_delete_goods_image_show);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_add_goods = (TextView) findViewById(R.id.tv_add_goods);
        et_price = (EditText) findViewById(R.id.et_price);
        iv_delete_goods_image_show.setVisibility(View.GONE);
        iv_delete_goods_image_show.bringToFront();
        iv_goods_image_show.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void initData() {
        adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
        admin_id = adminInfo.getInt("user_id", -1);
        phone_number = adminInfo.getString("phone_number", "");
        Glide.with(this).load(R.drawable.submit).into(iv_goods_image_show);
        image_show = "";
        adminController = new AdminController(this);
    }

    private void setOnClickListener() {
        iv_close_add_goods.setOnClickListener(this);
        iv_goods_image_show.setOnClickListener(this);
        iv_delete_goods_image_show.setOnClickListener(this);
        tv_add_goods.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_add_goods:
                finish();
                break;
            case R.id.iv_goods_image_show:
                adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
                phone_number = adminInfo.getString("phone_number", "");
                timeStamp = System.currentTimeMillis();
                ChooseUserHeadDialogUtil.showSelectSubmitDialog(this, phone_number, timeStamp);
                break;
            case R.id.iv_delete_goods_image_show:
                Glide.with(this).load(R.drawable.submit).into(iv_goods_image_show);
                image_show = "";
                iv_delete_goods_image_show.setVisibility(View.GONE);
                break;
            case R.id.tv_add_goods:
                String name = et_name.getText().toString().trim();
                String price = et_price.getText().toString().trim();
                if(name.length()== 0){
                    Toast.makeText(this, "商品名称不能为空", Toast.LENGTH_SHORT).show();
                }else if(price.length() == 0){
                    Toast.makeText(this, "商品价格不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    adminController.addGoodsInfo(name,price,image_show,admin_id);
                    Toast.makeText(this, "添加商品成功", Toast.LENGTH_SHORT).show();
                    et_name.setText("");
                    et_price.setText("");
                    Glide.with(this).load(R.drawable.submit).into(iv_goods_image_show);
                    image_show = "";
                    iv_delete_goods_image_show.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        String s = "";
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    s = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    s = getFilesDir() + File.separator + "images" + File.separator + phone_number + timeStamp + ".jpg";
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (isCheck) {

            Glide.with(this).load(s).into(iv_goods_image_show);
            image_show = s;
            iv_delete_goods_image_show.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "取消选择", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
