package com.domineer.triplebro.wowdiary.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.adapters.ShoppingAdapter;
import com.domineer.triplebro.wowdiary.controllers.AdminController;
import com.domineer.triplebro.wowdiary.interfaces.OnItemClickListener;
import com.domineer.triplebro.wowdiary.models.GoodsInfo;
import com.domineer.triplebro.wowdiary.utils.dialogUtils.TwoButtonDialog;

import java.util.List;

public class DeleteGoodActivity extends Activity implements View.OnClickListener, OnItemClickListener {

    private ImageView iv_close_delete_goods;
    private SharedPreferences adminInfo;
    private int admin_id;
    private AdminController adminController;
    private List<GoodsInfo> goodsInfoList;
    private RecyclerView rv_delete_goods;
    private ShoppingAdapter shoppingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_delete_goods = (ImageView) findViewById(R.id.iv_close_delete_goods);
        rv_delete_goods = (RecyclerView) findViewById(R.id.rv_delete_goods);
        rv_delete_goods.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void initData() {
        adminInfo = getSharedPreferences("adminInfo", MODE_PRIVATE);
        admin_id = adminInfo.getInt("user_id", -1);
        adminController = new AdminController(this);
        goodsInfoList = adminController.getGoodsInfoListByAdminId(admin_id);
        shoppingAdapter = new ShoppingAdapter(this, goodsInfoList);
        rv_delete_goods.setAdapter(shoppingAdapter);
    }

    private void setOnClickListener() {
        iv_close_delete_goods.setOnClickListener(this);
        shoppingAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_delete_goods:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(View view, final int position) {
        TwoButtonDialog twoButtonDialog = new TwoButtonDialog();
        twoButtonDialog.show("删除商品", "是否删除此商品？(提示：删除商品信息会将对应的订单信息一并删除，请谨慎处理。)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GoodsInfo removeGoodsInfo = goodsInfoList.remove(position);
                shoppingAdapter.setGoodsInfoList(goodsInfoList);
                adminController.deleteGoodsInfoById(removeGoodsInfo.get_id());
                dialog.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        },getFragmentManager());
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
