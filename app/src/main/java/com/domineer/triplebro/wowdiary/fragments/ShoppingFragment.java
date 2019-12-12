package com.domineer.triplebro.wowdiary.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.domineer.triplebro.wowdiary.R;

public class ShoppingFragment extends Fragment {

    private View fragment_shopping;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_shopping = inflater.inflate(R.layout.fragment_shopping, null);
        return fragment_shopping;
    }
}
