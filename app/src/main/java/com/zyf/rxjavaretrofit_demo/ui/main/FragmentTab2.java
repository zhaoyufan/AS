package com.zyf.rxjavaretrofit_demo.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.zyf.rxjavaretrofit_demo.R;
import com.zyf.rxjavaretrofit_demo.ui.base.BaseFragment;
import com.zyf.rxjavaretrofit_demo.ui.images.FragmentImageTab1;
import com.zyf.rxjavaretrofit_demo.ui.images.FragmentImageTab2;
import com.zyf.rxjavaretrofit_demo.ui.main.adapter.FragmentVedioAdapter;

/**
 * Created by ZYF on 2016/11/16.
 */
public class FragmentTab2 extends BaseFragment {
    TabLayout mTabLayout;
    ViewPager mViewPager;

    Fragment[] mFragments;
    String[] mTitles;
    FragmentVedioAdapter fragmentVedioAdapter;
    @Override
    public int getContentView() {
        return R.layout.fragment_2;
    }

    @Override
    public void initView() {
        initTitleBar("", "图片", "", R.mipmap.ic_search, this);
        mTabLayout = (TabLayout) $(R.id.mTabLayout);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.common_title_bg));
        mViewPager = (ViewPager) $(R.id.mViewPager);
    }

    @Override
    public void initClick() {

    }

    @Override
    public void initData() {
        mFragments = new Fragment[]{new FragmentImageTab1(), new FragmentImageTab2()};
        mTitles = new String[]{"美女1", "美女2"};
        fragmentVedioAdapter = new FragmentVedioAdapter(context, ((FragmentActivity) context).getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(fragmentVedioAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).select();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_right://标题栏右侧图标
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
