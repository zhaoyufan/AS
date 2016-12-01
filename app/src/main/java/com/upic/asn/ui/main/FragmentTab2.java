package com.upic.asn.ui.main;

import android.view.View;

import com.upic.asn.R;
import com.upic.asn.ui.base.BaseFragment;

/**
 * Created by ZYF on 2016/11/16.
 */
public class FragmentTab2 extends BaseFragment {
    @Override
    public int getContentView() {
        return R.layout.fragment_2;
    }

    @Override
    public void initView() {
        initTitleBar("", "图片", "", R.mipmap.ic_search, this);
    }

    @Override
    public void initClick() {

    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
    }
}
