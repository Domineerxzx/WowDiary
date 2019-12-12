package com.domineer.triplebro.wowdiary.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.domineer.triplebro.wowdiary.R;
import com.domineer.triplebro.wowdiary.adapters.SubmitAdapter;
import com.domineer.triplebro.wowdiary.controllers.IssueController;
import com.domineer.triplebro.wowdiary.interfaces.OnItemClickListener;
import com.domineer.triplebro.wowdiary.properties.ProjectProperties;
import com.domineer.triplebro.wowdiary.utils.RealPathFromUriUtils;
import com.domineer.triplebro.wowdiary.utils.dialogUtils.ChooseUserHeadDialogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class IssueFragment extends Fragment implements View.OnClickListener, OnItemClickListener {

    private View fragment_issue;
    private EditText et_title;
    private EditText et_content;
    private RecyclerView rv_dairy;
    private TextView tv_submit_dairy;
    private SharedPreferences userInfo;
    private int user_id;
    private IssueController issueController;
    private SubmitAdapter submitAdapter;
    private String phone_number;
    private long timeStamp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_issue = inflater.inflate(R.layout.fragment_issue, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_issue;
    }

    private void initView() {
        et_title = (EditText) fragment_issue.findViewById(R.id.et_title);
        et_content = (EditText) fragment_issue.findViewById(R.id.et_content);
        rv_dairy = (RecyclerView) fragment_issue.findViewById(R.id.rv_dairy);
        tv_submit_dairy = (TextView) fragment_issue.findViewById(R.id.tv_submit_dairy);
        rv_dairy.setLayoutManager(new GridLayoutManager(getActivity(),3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        issueController = new IssueController(getActivity());
        submitAdapter = new SubmitAdapter(getActivity(), new ArrayList<String>());
        rv_dairy.setAdapter(submitAdapter);
    }

    private void setOnClickListener() {
        tv_submit_dairy.setOnClickListener(this);
        submitAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_submit_dairy:
                String title = et_title.getText().toString().trim();
                String content = et_content.getText().toString().trim();
                issueController.addDairyInfo(title,content,user_id,submitAdapter.getData());
                et_title.setText("");
                et_content.setText("");
                submitAdapter.setData(new ArrayList<String>());
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        phone_number = userInfo.getString("phone_number", "");
        timeStamp = System.currentTimeMillis();
        ChooseUserHeadDialogUtil.showSelectSubmitDialog(this, phone_number, timeStamp);
    }

    @Override
    public void onItemLongClick(View view) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        String s = "";
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    s = RealPathFromUriUtils.getRealPathFromUri(getActivity(), data.getData());
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    s = getActivity().getFilesDir() + File.separator + "images" + File.separator + phone_number + timeStamp + ".jpg";
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (isCheck) {
            List<String> answerAdapterData = submitAdapter.getData();
            if (answerAdapterData.size() == 0) {
                answerAdapterData.add(s);
            } else {
                answerAdapterData.remove(answerAdapterData.size() - 1);
                answerAdapterData.add(s);
            }
            if (answerAdapterData.size() != 9) {
                answerAdapterData.add("");
            }
            submitAdapter.setData(answerAdapterData);
        } else {
            Toast.makeText(getActivity(), "取消选择", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
