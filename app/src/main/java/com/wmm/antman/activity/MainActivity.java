package com.wmm.antman.activity;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wmm.antman.R;
import com.wmm.antman.adapter.ViewPagerAdapter;
import com.wmm.antman.utils.ToastUtil;
import com.wmm.antman.witget.CustomViewPager;


public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private TabLayout mTabLayout;
    public static Toolbar mToolbar;
    private CustomViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ViewPagerAdapter mViewPagerAdapter;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mViewPager = (CustomViewPager) findViewById(R.id.viewpager_main);
        mViewPager.setNoScroll(false);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_main);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        initTabLayout();
        initToolbar();
    }

    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.showOverflowMenu();
            mToolbar.setOnMenuItemClickListener(this);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
            mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        }
    }

    /**
     * 初始化  TabLayout
     */
    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.main_tab_one));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.main_tab_two));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.main_tab_three));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.main_tab_four));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        mTabLayout.setSelected(false);
        mViewPagerAdapter = new ViewPagerAdapter(MainActivity.this, getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.titlebar_search:
                final View coordinatorLayoutView = findViewById(R.id.toolbar_main);
                Snackbar.make(coordinatorLayoutView, "h", Snackbar.LENGTH_LONG).setAction("add", mOnClickListener).show();
                //TODO 搜索
                break;
            case R.id.titlebar_share:
                //TODO 分享
                break;
        }
        return true;
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ToastUtil.showCommonToast("add", 1000, 0, Gravity.CENTER);
        }
    };

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(
                mTabLayout.getTag().toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), R.string.exit,
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
