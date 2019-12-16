package com.domineer.triplebro.wowdiary.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.activities.GoodsDetailActivity;
import com.domineer.triplebro.wowdiary.adapters.ShoppingAdapter;
import com.domineer.triplebro.wowdiary.controllers.ShoppingController;
import com.domineer.triplebro.wowdiary.interfaces.OnItemClickListener;
import com.domineer.triplebro.wowdiary.models.GoodsInfo;
import com.domineer.triplebro.wowdiary.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment implements OnItemClickListener {

    private View fragment_shopping;
    private Banner bn_banner;
    private RecyclerView rv_shopping;
    private ShoppingController shoppingController;
    private List<GoodsInfo> goodInfoList;
    private ShoppingAdapter shoppingAdapter;
    private List<String> bannerImageList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_shopping = inflater.inflate(R.layout.fragment_shopping, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_shopping;
    }

    private void initView() {
        bn_banner = (Banner) fragment_shopping.findViewById(R.id.bn_banner);
        rv_shopping = (RecyclerView) fragment_shopping.findViewById(R.id.rv_shopping);
        rv_shopping.setLayoutManager(new GridLayoutManager(getActivity(),2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        bn_banner.setImageLoader(new GlideImageLoader());
        bn_banner.isAutoPlay(true);
        bn_banner.setDelayTime(5000);
        bn_banner.setIndicatorGravity(BannerConfig.CENTER);

    }

    private void initData() {
        shoppingController = new ShoppingController(getActivity());
        goodInfoList = shoppingController.getGoodsInfoList();
        shoppingAdapter = new ShoppingAdapter(getActivity(), goodInfoList);
        bannerImageList = new ArrayList<String>();
        if(goodInfoList.size() <= 5){
            for (int i = 0; i < goodInfoList.size(); i++) {
                String image = goodInfoList.get(i).getImage();
                bannerImageList.add(image);
            }
        }else{
            for (int i = 0; i < 5; i++) {
                String image = goodInfoList.get(i).getImage();
                bannerImageList.add(image);
            }
        }
        bn_banner.setImages(bannerImageList);
        bn_banner.start();
        bn_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.putExtra("goodsInfo", goodInfoList.get(position));
                startActivity(intent);
            }
        });
        rv_shopping.setAdapter(shoppingAdapter);
    }

    private void setOnClickListener() {
        shoppingAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent goodsDetail = new Intent(getActivity(), GoodsDetailActivity.class);
        goodsDetail.putExtra("goodsInfo",goodInfoList.get(position));
        getActivity().startActivity(goodsDetail);

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
