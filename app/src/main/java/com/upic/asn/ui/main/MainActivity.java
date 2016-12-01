package com.upic.asn.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.upic.asn.R;
import com.upic.asn.ui.base.BaseActivity;
import com.upic.asn.ui.main.adapter.FragmentMainTabAdapter;

/**
 * Created by ZYF on 2016/11/16.
 */
public class MainActivity extends BaseActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    private String[] mTabTitles;
    private Integer[] mTabIcons;
    private Fragment[] mFragments;
    private FragmentMainTabAdapter fragmentMainTabAdapter;
    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        viewPager = (ViewPager) $(R.id.vp_content);
        tabLayout = (TabLayout) $(R.id.tabl_navigation);
    }

    @Override
    public void initClick() {
    }

    @Override
    public void initData() {
        mTabTitles = new String[]{"新闻", "图片", "我的","我的"};
        mTabIcons = new Integer[]{R.drawable.tab1_selector, R.drawable.tab2_selector, R.drawable.tab3_selector,R.drawable.tab3_selector};
        mFragments = new Fragment[]{new FragmentTab1(), new FragmentTab2(), new FragmentTab3(),new FragmentTab3()};
        setupViewPager();
        setupTabLayout();
        tabLayout.getTabAt(0).select();
    }

    private void setupTabLayout() {
        //TabGravity:放置Tab的Gravity,有GRAVITY_CENTER 和 GRAVITY_FILL两种效果。顾名思义，一个是居中，另一个是尽可能的填充
        //（注意，GRAVITY_FILL需要和MODE_FIXED一起使用才有效果）
        //TabMode:布局中Tab的行为模式（behavior mode），有两种值：MODE_FIXED 和 MODE_SCROLLABLE。
        //MODE_FIXED:固定tabs，并同时显示所有的tabs。
        //MODE_SCROLLABLE：可滚动tabs，显示一部分tabs，在这个模式下能包含长标签和大量的tabs，最好用于用户不需要直接比较tabs
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            Log.d("aaa",""+tabLayout.getTabCount());
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(fragmentMainTabAdapter.getTabView(i));//设置tab内容
        }
        tabLayout.requestFocus();
    }

    private void setupViewPager() {
        fragmentMainTabAdapter = new FragmentMainTabAdapter(context, getSupportFragmentManager(), mFragments, mTabTitles, mTabIcons);
        viewPager.setAdapter(fragmentMainTabAdapter);
        viewPager.setOffscreenPageLimit(2);//设置相邻两个页面会被缓存
    }
}
