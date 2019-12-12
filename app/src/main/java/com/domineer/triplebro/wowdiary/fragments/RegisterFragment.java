package com.domineer.triplebro.wowdiary.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.controllers.RegisterController;
import com.domineer.triplebro.wowdiary.properties.ProjectProperties;

/**
 * @author Domineer
 * @data 2019/12/5,11:29
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private View fragment_register;
    private EditText et_phone_number;
    private EditText et_username;
    private EditText et_password;
    private Button bt_create;
    private CheckBox cb_agree;
    private Button bt_admin_register;
    private Button bt_user_register;
    private int userType;
    private String phone_number;
    private String password;
    private String username;
    private RegisterController registerController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_register = inflater.inflate(R.layout.fragment_register, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_register;
    }

    private void initView() {
        et_phone_number = fragment_register.findViewById(R.id.et_phone_number);
        et_username = fragment_register.findViewById(R.id.et_username);
        et_password = fragment_register.findViewById(R.id.et_password);
        bt_create = fragment_register.findViewById(R.id.bt_create);
        cb_agree = fragment_register.findViewById(R.id.cb_agree);
        bt_admin_register = (Button) fragment_register.findViewById(R.id.bt_admin_register);
        bt_user_register = (Button) fragment_register.findViewById(R.id.bt_user_register);
    }

    private void initData() {
        registerController = new RegisterController(getActivity());
    }

    private void setOnClickListener() {
        bt_create.setOnClickListener(this);
        bt_admin_register.setOnClickListener(this);
        bt_user_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_create:
                phone_number = et_phone_number.getText().toString();
                password = et_password.getText().toString();
                username = et_username.getText().toString();
                phone_number = et_phone_number.getText().toString();
                if (username.length() == 0) {
                    Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (phone_number.length() != 11) {
                    Toast.makeText(getActivity(), "手机号有误", Toast.LENGTH_SHORT).show();
                } else if (password.length() == 0) {
                    Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!cb_agree.isChecked()) {
                    Toast.makeText(getActivity(), "请查看并同意条款与条件", Toast.LENGTH_SHORT).show();
                } else {
                    registerController.register(phone_number,password, username, userType);
                }
                break;
            case R.id.bt_admin_register:
                bt_admin_register.setBackgroundResource(R.drawable.shape_user_button);
                bt_user_register.setBackgroundResource(R.drawable.shape_alpha_card);
                userType = ProjectProperties.ADMIN;
                break;
            case R.id.bt_user_register:
                bt_admin_register.setBackgroundResource(R.drawable.shape_alpha_card);
                bt_user_register.setBackgroundResource(R.drawable.shape_user_button);
                userType = ProjectProperties.USER;
                break;
        }
    }
}
