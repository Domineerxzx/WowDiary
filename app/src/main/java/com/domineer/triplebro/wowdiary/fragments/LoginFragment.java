package com.domineer.triplebro.wowdiary.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.controllers.LoginController;
import com.domineer.triplebro.wowdiary.properties.ProjectProperties;

/**
 * @author Domineer
 * @data 2019/12/5,11:28
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private View fragment_login;
    private Button bt_login;
    private EditText et_phone_number;
    private EditText et_password;
    private Button bt_admin_login;
    private Button bt_user_login;
    private int userType;
    private LoginController loginController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_login = inflater.inflate(R.layout.fragment_login, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_login;
    }

    private void initView() {
        bt_login = (Button) fragment_login.findViewById(R.id.bt_login);
        et_phone_number = (EditText) fragment_login.findViewById(R.id.et_phone_number);
        et_password = (EditText) fragment_login.findViewById(R.id.et_password);
        bt_admin_login = (Button) fragment_login.findViewById(R.id.bt_admin_login);
        bt_user_login = (Button) fragment_login.findViewById(R.id.bt_user_login);
    }

    private void initData() {
        loginController = new LoginController(getActivity());
    }

    private void setOnClickListener() {
        bt_login.setOnClickListener(this);
        bt_admin_login.setOnClickListener(this);
        bt_user_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                String phone_number = et_phone_number.getText().toString();
                String password = et_password.getText().toString();
                if (phone_number.length() != 0 && password.length() != 0) {
                    loginController.login(phone_number, password, userType);
                } else {
                    Toast.makeText(getActivity(), "手机号或密码不能为空！！！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_admin_login:
                bt_admin_login.setBackgroundResource(R.drawable.shape_user_button);
                bt_user_login.setBackgroundResource(R.drawable.shape_alpha_card);
                userType = ProjectProperties.ADMIN;
                break;
            case R.id.bt_user_login:
                bt_admin_login.setBackgroundResource(R.drawable.shape_alpha_card);
                bt_user_login.setBackgroundResource(R.drawable.shape_user_button);
                userType = ProjectProperties.USER;
                break;
            default:
                break;
        }
    }
}
