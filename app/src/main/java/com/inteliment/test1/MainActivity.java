package com.inteliment.test1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inteliment.test1.adapter.ViewPageAdaper;
import com.inteliment.test1.model.AppTheme;
import com.inteliment.test1.preferences.SharedPref;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TabLayout mTabLayout;
    List<String> mItemList;
    ViewPager mPager;
    Toolbar toolbar;
    CoordinatorLayout parentPanel;
    int themeColor=R.color.colorPrimary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
// setting theme according to the app preference
        setApplicationTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setApplicationTheme(){

        // check the app preference for the selected theme and setting it to the activity
        if(SharedPref.getTheme(MainActivity.this).equals(AppTheme.BLUE)){
            setTheme(R.style.AppTheme_blue);
            themeColor=R.color.colorPrimary;
        }else if(SharedPref.getTheme(MainActivity.this).equals(AppTheme.RED)){
            setTheme(R.style.AppTheme_red);
            themeColor=R.color.colorPrimary_red;
        }else if(SharedPref.getTheme(MainActivity.this).equals(AppTheme.GREEN)){
            setTheme(R.style.AppTheme_green);
            themeColor=R.color.colorPrimary_green;
        }

    }


    private void initView(){


        mTabLayout=((TabLayout)findViewById(R.id.tabs));
        toolbar=((Toolbar)findViewById(R.id.toolbar));
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        parentPanel=((CoordinatorLayout)findViewById(R.id.mainPanel));
        parentPanel.setBackgroundColor(getResources().getColor(themeColor));
        mItemList= Arrays.asList(getResources().getStringArray(R.array.item_list));
        for (String str:mItemList ) {
             mTabLayout.addTab(mTabLayout.newTab().setText(str));
        }


        // setting the tab0 as the default selected tab
        if(mTabLayout.getTabCount()>=0) {
            mTabLayout.getTabAt(0).select();
            ((TextView)findViewById(R.id.seletedItem)).setText(mItemList.get(0));
        }
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                // showing the selected tab info
                ((TextView) findViewById(R.id.seletedItem)).setText(mItemList.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        loadViewPager();
        ((Button)findViewById(R.id.redButton)).setOnClickListener(this);
        ((Button)findViewById(R.id.greenButton)).setOnClickListener(this);
        ((Button)findViewById(R.id.blueButton)).setOnClickListener(this);



    }


    private void loadViewPager(){

        // loading view pagers
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new ViewPageAdaper(getSupportFragmentManager()));


        // pager indicator
        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setSnap(true);
        indicator.setFillColor(getResources().getColor(themeColor));
        final float density = getResources().getDisplayMetrics().density;
        // setting the size of indicator
        indicator.setRadius(5 * density);

    }



    @Override
    public void onClick(View v) {

        // click listeners for the theme buttons
        switch (v.getId()){
            case R.id.redButton:
                if(!SharedPref.getTheme(MainActivity.this).equals(AppTheme.RED)) {
                    SharedPref.setTheme(MainActivity.this, AppTheme.RED);
                    startActivity(new Intent(MainActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                break;
            case R.id.blueButton:
                if(!SharedPref.getTheme(MainActivity.this).equals(AppTheme.BLUE)) {
                    SharedPref.setTheme(MainActivity.this, AppTheme.BLUE);
                    startActivity(new Intent(MainActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                break;
            case R.id.greenButton:
                if(!SharedPref.getTheme(MainActivity.this).equals(AppTheme.GREEN)) {
                    SharedPref.setTheme(MainActivity.this, AppTheme.GREEN);
                    startActivity(new Intent(MainActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                break;
        }
    }
}
