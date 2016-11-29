package com.zyf.rxjavaretrofit_demo.ui.main;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zyf.rxjavaretrofit_demo.R;
import com.zyf.rxjavaretrofit_demo.ui.base.BaseFragment;

/**
 * Created by ZYF on 2016/11/16.
 */
public class FragmentTab3 extends BaseFragment {
    LinearLayout ll_head;
    LinearLayout about;
    @Override
    public int getContentView() {
        return R.layout.fragment_3;
    }

    @Override
    public void initView() {
        initTitleBar("", "我的", "", 0, this);
        ll_head = (LinearLayout) $(R.id.ll_head);
        ll_head.setBackgroundColor(getResources().getColor(R.color.common_title_bg));
        about = (LinearLayout) $(R.id.about);
    }

    @Override
    public void initClick() {
        about.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.about){
            Toast.makeText(getContext(),"点击关于",Toast.LENGTH_SHORT).show();
        }
    }
}
