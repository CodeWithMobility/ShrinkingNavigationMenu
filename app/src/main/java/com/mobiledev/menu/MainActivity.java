package com.mobiledev.menu;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobiledev.menu.ui.FragmentAboutus;
import com.mobiledev.menu.ui.FragmentContactus;
import com.mobiledev.menu.ui.FragmentHome;
import com.mobiledev.menu.ui.FragmentSettings;
import com.mobiledev.menu.utilities.DragLayout;

public class MainActivity extends AppCompatActivity {
    private DragLayout mDraglayout;
    private Boolean isOpened = false;

    private ListView mListView;
    private ImageView iv_icon;
    private TextView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBar();

        setContentView(R.layout.activity_main);
        initDragLayout();
        initView();
        changeFragment(new FragmentHome(), false);
    }

    private void initDragLayout() {
        mDraglayout =  findViewById(R.id.dl);
        mDraglayout.setDragListener(new DragLayout.DragListener() {
            @Override
            public void onOpen() {
                isOpened = true;
            }

            @Override
            public void onClose() {
                isOpened = false;
            }

            @Override
            public void onDrag(float percent) {
                // ViewHelper.setAlpha(iv_icon, 1 - percent);
            }
        });
    }

    private void initView() {
        iv_icon =  findViewById(R.id.iv_icon);

        mListView = findViewById(R.id.lv);
        mListView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                R.layout.item_text, new String[]{getString(R.string.home), getString(R.string.aboutus),
                getString(R.string.contactus) ,getString(R.string.settings)
        }));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                navigation(position);
                mDraglayout.close();
            }
        });
        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mDraglayout.open();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void navigation(int position) {
        switch (position) {
            case 0:
                changeFragment(new FragmentHome(), false);
                break;
            case 1:
                changeFragment(new FragmentAboutus(), false);
                break;
            case 2:
                changeFragment(new FragmentContactus(), false);
                break;
            case 3:
                changeFragment(new FragmentSettings(), false);
                break;

        }
    }

    private void changeFragment(Fragment targetFragment, boolean state) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, targetFragment);
        if (state)
            ft.addToBackStack(targetFragment.getClass().getName());
        ft.commit();
    }

    public void setUpTitle(String mTitle) {
        mTitleView = (TextView) findViewById(R.id.headerTitle);
        mTitleView.setText(mTitle);
    }



    @Override
    public void onBackPressed() {
        if (isOpened) {
            mDraglayout.close();
        } else {
            super.onBackPressed();
        }
    }



    private void statusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));


        }
    }
}