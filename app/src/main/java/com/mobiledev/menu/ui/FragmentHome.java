package com.mobiledev.menu.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobiledev.menu.MainActivity;
import com.mobiledev.menu.R;

/**
 * Created by Manu on 11/11/2017.
 */

public class FragmentHome extends Fragment {

    private View parentView;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_home, container, false);
        activity = (MainActivity)getActivity();
        return parentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.setUpTitle(getString(R.string.home));
    }
}
