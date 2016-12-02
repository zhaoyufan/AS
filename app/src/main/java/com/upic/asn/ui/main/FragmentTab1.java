package com.upic.asn.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.ui.base.BaseFragment;
import com.upic.asn.ui.main.adapter.FragmentVedioAdapter;


/**
 * Created by ZYF on 2016/11/16.
 */
public class FragmentTab1 extends BaseFragment implements View.OnClickListener
{
    LinearLayout rl_title,left;//标题栏
    TextView tv_search;//搜索框

    ImageView iv_message,iv_qr;//二维码
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentVedioAdapter fragmentVedioAdapter;
    private String[] mTabTitles;
    private Fragment[] mFragments;

    @Override
    public int getContentView() {
        return R.layout.fragment_1;
    }

    @Override
    public void initView() {
        rl_title = (LinearLayout) $(R.id.rl_title);//标题栏
        left = (LinearLayout) $(R.id.left);//左侧城市选择
        iv_message = (ImageView) $(R.id.iv_message);//消息
        rl_title.getBackground().setAlpha(0);
        tv_search = (TextView) $(R.id.tv_search);//搜索
        iv_qr = (ImageView) $(R.id.iv_qr);//二维码
        tabLayout = (TabLayout) $(R.id.fragmnet_1_tabLayout);
        viewPager = (ViewPager) $(R.id.fragmnet_1_viewpager);
    }

    @Override
    public void initClick() {
        tv_search.setOnClickListener(this);
        iv_qr.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        left.setOnClickListener(this);
        //initRecyclerView();
    }

    @Override
    public void initData() {
        mTabTitles = new String[]{"出行圈", "玩乐"};
        mFragments = new Fragment[]{new FragmentTab1_2(), new FragmentTab1_1()};
        fragmentVedioAdapter = new FragmentVedioAdapter(context, ((FragmentActivity) context).getSupportFragmentManager(), mFragments, mTabTitles);
        viewPager.setAdapter(fragmentVedioAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(1).select();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_qr:
                Toast.makeText(context, "二维码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.left:
                Toast.makeText(context, "城市选择", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_message:
                Toast.makeText(context, "消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
