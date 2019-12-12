package com.domineer.triplebro.wowdiary.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.domineer.triplebro.wowdiary.R;

public class BottomFragment extends Fragment implements View.OnClickListener {

    private View fragment_bottom;
    private LinearLayout ll_date;
    private LinearLayout ll_my_dairy;
    private LinearLayout ll_issue;
    private LinearLayout ll_shopping;
    private LinearLayout ll_myself;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Button bt_date;
    private Button bt_my_dairy;
    private Button bt_issue;
    private Button bt_shopping;
    private Button bt_myself;
    private TextView tv_date;
    private TextView tv_my_dairy;
    private TextView tv_issue;
    private TextView tv_shopping;
    private TextView tv_myself;

    private Button lastFunctionButton;
    private TextView lastFunctionTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_bottom = inflater.inflate(R.layout.fragment_bottom, null);
        initView();
        setOnClick();
        fragmentManager = getActivity().getFragmentManager();
        return fragment_bottom;
    }

    private void initView() {
        ll_date = fragment_bottom.findViewById(R.id.ll_date);
        ll_my_dairy = fragment_bottom.findViewById(R.id.ll_my_dairy);
        ll_issue = fragment_bottom.findViewById(R.id.ll_issue);
        ll_shopping = fragment_bottom.findViewById(R.id.ll_shopping);
        ll_myself = fragment_bottom.findViewById(R.id.ll_myself);
        bt_date = fragment_bottom.findViewById(R.id.bt_date);
        bt_my_dairy = fragment_bottom.findViewById(R.id.bt_my_dairy);
        bt_issue = fragment_bottom.findViewById(R.id.bt_issue);
        bt_shopping = fragment_bottom.findViewById(R.id.bt_shopping);
        bt_myself = fragment_bottom.findViewById(R.id.bt_myself);
        tv_date = fragment_bottom.findViewById(R.id.tv_date);
        tv_my_dairy = fragment_bottom.findViewById(R.id.tv_my_dairy);
        tv_issue = fragment_bottom.findViewById(R.id.tv_issue);
        tv_shopping = fragment_bottom.findViewById(R.id.tv_shopping);
        tv_myself = fragment_bottom.findViewById(R.id.tv_myself);

        if (lastFunctionTextView == null) {
            lastFunctionTextView = tv_date;
        }
        if (lastFunctionButton == null) {
            lastFunctionButton = bt_date;
        }

    }

    public void setOnClick() {

        ll_date.setOnClickListener(this);
        ll_my_dairy.setOnClickListener(this);
        ll_issue.setOnClickListener(this);
        ll_shopping.setOnClickListener(this);
        ll_myself.setOnClickListener(this);
        bt_date.setOnClickListener(this);
        bt_my_dairy.setOnClickListener(this);
        bt_issue.setOnClickListener(this);
        bt_shopping.setOnClickListener(this);
        bt_myself.setOnClickListener(this);
        tv_date.setOnClickListener(this);
        tv_my_dairy.setOnClickListener(this);
        tv_issue.setOnClickListener(this);
        tv_shopping.setOnClickListener(this);
        tv_myself.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_date:
            case R.id.bt_date:
            case R.id.tv_date:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new DateFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_date);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_date.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_date;
                break;
            case R.id.ll_my_dairy:
            case R.id.bt_my_dairy:
            case R.id.tv_my_dairy:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new MyDairyFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_my_dairy);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_my_dairy.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_my_dairy;
                break;
            case R.id.ll_issue:
            case R.id.bt_issue:
            case R.id.tv_issue:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new IssueFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_issue);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_issue.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_issue;
                break;
            case R.id.ll_shopping:
            case R.id.bt_shopping:
            case R.id.tv_shopping:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new ShoppingFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_shopping);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_shopping.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_shopping;
                break;
            case R.id.ll_myself:
            case R.id.bt_myself:
            case R.id.tv_myself:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new MyselfFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_myself);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_myself.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_myself;
                break;
        }
    }

    private void changeImageForButton(Button lastFunctionButton, Button onClickButton) {
        switch (lastFunctionButton.getId()) {
            case R.id.bt_date:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_my_dairy:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_issue:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_shopping:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
            case R.id.bt_myself:
                lastFunctionButton.setBackgroundResource(R.mipmap.ic_launcher_round);
                break;
        }
        switch (onClickButton.getId()) {
            case R.id.bt_date:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_my_dairy:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_issue:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_shopping:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
            case R.id.bt_myself:
                onClickButton.setBackgroundResource(R.mipmap.ic_launcher);
                break;
        }
    this.lastFunctionButton =onClickButton;
    }

}
