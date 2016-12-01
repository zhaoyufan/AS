package com.upic.asn.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.upic.asn.R;

/**
 * Created by ZYF on 2016/11/15.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{

    public Activity context;

    public RelativeLayout title_bar;//顶部标题栏
    public TextView tv_left, tv_title, tv_right;
    public ImageView iv_right;
    public ProgressBar pb;//搜索栏

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        context = this;
        initView();
        initData();
        initClick();
    }

    public void initTitleBar(String left, String title, String right, int res, View.OnClickListener onClickListener){
        title_bar = (RelativeLayout) $(R.id.title_bar);
        title_bar.setBackgroundColor(getResources().getColor(R.color.common_title_bg));
        tv_left = (TextView) $(R.id.tv_left);//返回按钮
        tv_title = (TextView) $(R.id.tv_title);//标题
        tv_right = (TextView) $(R.id.tv_right);//(右侧)按钮
        iv_right = (ImageView) $(R.id.iv_right);//右侧图片按钮

        pb = (ProgressBar) $(R.id.pb);// 标题栏数据加载ProgressBar

        if (!TextUtils.isEmpty(left)) {
            tv_left.setText(left);
            tv_left.setVisibility(View.VISIBLE);
            tv_left.setOnClickListener(onClickListener);
        } else {
            tv_left.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
            tv_title.setVisibility(View.VISIBLE);
        } else {
            tv_title.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(right)) {
            tv_right.setText(right);
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setOnClickListener(onClickListener);
        } else {
            tv_right.setVisibility(View.GONE);
        }

        if (res != 0) {
            iv_right.setImageResource(res);
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setOnClickListener(onClickListener);
        } else {
            iv_right.setVisibility(View.GONE);
        }
    }

    public View $(int id) {
        return findViewById(id);
    }
    /**
     * 设置布局文件
     * @return
     */
    public abstract int getContentView();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 设置点击事件
     */
    public abstract void initClick();

    /**
     * 初始化数据
     */
    public abstract void initData();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                finish();
        }
    }
    public void startActivity(Class cla) {
        startActivity(new Intent(this, cla));
    }
}

