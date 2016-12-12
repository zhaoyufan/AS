package com.upic.asn.ui.main;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.ui.base.BaseActivity;

/**
 * Created by ZYF on 2016/11/16.
 */
public class MainActivity extends BaseActivity {

    private FragmentTab1 fragmentTab1;
    private FragmentTab2 fragmentTab2;
    private FragmentTab3 fragmentTab3;
    private FragmentTab4 fragmentTab4;
    private LinearLayout tablayout1, tablayout2, tablayout3, tablayout4;

    private ImageView toggleImageView, plusImageView;

    private PopupWindow popWindow;
    private DisplayMetrics dm;

    private FragmentManager fragmentManager;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tablayout1 = (LinearLayout) findViewById(R.id.tablayout_1);
        tablayout2 = (LinearLayout) findViewById(R.id.tablayout_2);
        tablayout3 = (LinearLayout) findViewById(R.id.tablayout_3);
        tablayout4 = (LinearLayout) findViewById(R.id.tablayout_4);

        toggleImageView = (ImageView) findViewById(R.id.toggle_btn);
        plusImageView = (ImageView) findViewById(R.id.plus_btn);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void initClick() {
        tablayout1.setOnClickListener(this);
        tablayout2.setOnClickListener(this);
        tablayout3.setOnClickListener(this);
        tablayout4.setOnClickListener(this);
        toggleImageView.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTabSelection(0);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tablayout_1:
                setTabSelection(0);
                break;
            case R.id.tablayout_2:
                setTabSelection(1);
                break;
            case R.id.tablayout_3:
                setTabSelection(2);
                break;
            case R.id.tablayout_4:
                setTabSelection(3);
                break;
            case R.id.toggle_btn:
                clickToggleBtn();
                break;
        }
    }

    public void setTabSelection(int id) {
        // 开启一个Fragment事务
        // FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (id) {//0表示第一张以此类推
            case 0:
                if (fragmentTab1 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragmentTab1 = new FragmentTab1();
                    transaction.add(R.id.content, fragmentTab1);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragmentTab1);
                }
                tablayout1.setSelected(true);
                tablayout2.setSelected(false);
                tablayout3.setSelected(false);
                tablayout4.setSelected(false);
                break;
            case 1:
                if (fragmentTab2 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragmentTab2 = new FragmentTab2();
                    transaction.add(R.id.content, fragmentTab2);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragmentTab2);
                }
                tablayout1.setSelected(false);
                tablayout2.setSelected(true);
                tablayout3.setSelected(false);
                tablayout4.setSelected(false);
                break;
            case 2:
                if (fragmentTab3 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragmentTab3 = new FragmentTab3();
                    transaction.add(R.id.content, fragmentTab3);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragmentTab3);
                }
                tablayout1.setSelected(false);
                tablayout2.setSelected(false);
                tablayout3.setSelected(true);
                tablayout4.setSelected(false);
                break;
            case 3:
                if (fragmentTab4 == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    fragmentTab4 = new FragmentTab4();
                    transaction.add(R.id.content, fragmentTab4);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragmentTab4);
                }
                tablayout1.setSelected(false);
                tablayout2.setSelected(false);
                tablayout3.setSelected(false);
                tablayout4.setSelected(true);
                break;
        }
        transaction.commit();//事务的提交
    }

    public void hideFragments(FragmentTransaction transaction){
        if(fragmentTab1 != null){
            transaction.hide(fragmentTab1);
        }
        if(fragmentTab2 != null){
            transaction.hide(fragmentTab2);
        }
        if(fragmentTab3 != null){
            transaction.hide(fragmentTab3);
        }
        if(fragmentTab4 != null){
            transaction.hide(fragmentTab4);
        }
    }

    private void clickToggleBtn() {
        showPopupWindow(toggleImageView);
        plusImageView.setSelected(true);
    }

    private void changeButtonImage() {
        plusImageView.setSelected(false);
    }

    private void showPopupWindow(View parent) {
        if (popWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.popwindow_layout, null);
            dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            popWindow = new PopupWindow(view, dm.widthPixels, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // popWindow.showAtLocation(parent, Gravity.FILL, 0, 0);
        popWindow.showAsDropDown(parent, 0,0);

        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeButtonImage();
            }
        });

        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                //changeButtonImage();
                ImageView img = (ImageView) view.findViewById(R.id.pop_img);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"点击图片",Toast.LENGTH_SHORT).show();
//                        popWindow.dismiss();
                    }
                });
                return false;
            }
        });
    }
}
