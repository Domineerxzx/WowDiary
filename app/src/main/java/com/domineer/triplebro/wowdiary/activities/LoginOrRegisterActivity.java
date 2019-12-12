package com.domineer.triplebro.wowdiary.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.adapters.ViewPagerAdapter;
import com.domineer.triplebro.wowdiary.fragments.LoginFragment;
import com.domineer.triplebro.wowdiary.fragments.RegisterFragment;
import com.domineer.triplebro.wowdiary.utils.PermissionUtil;
import com.domineer.triplebro.wowdiary.views.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

public class LoginOrRegisterActivity extends AppCompatActivity {

    private NavitationLayout nl_login_or_register;
    private ViewPager vp_login_or_register;
    private String[] titles;
    private List<Fragment> loginFragmentList;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        vp_login_or_register = (ViewPager) findViewById(R.id.vp_login_or_register);
    }

    private void initData() {
        PermissionUtil.requestPower(this, this, "android.permission.CAMERA"); //请求权限
        PermissionUtil.requestPower(this, this, "android.permission.WRITE_EXTERNAL_STORAGE");
        loginFragmentList = new ArrayList<>();
        LoginFragment loginFragment = new LoginFragment();
        loginFragmentList.add(loginFragment);
        RegisterFragment registerFragment = new RegisterFragment();
        loginFragmentList.add(registerFragment);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), loginFragmentList);
        vp_login_or_register.setAdapter(pagerAdapter);
        vp_login_or_register.setClickable(false);
    }

    private void setOnClickListener() {

    }
}
