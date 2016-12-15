package com.upic.asn.ui.main.homehead;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.upic.asn.R;
import com.upic.asn.ui.base.BaseActivity;

public class WeekendActivity extends BaseActivity implements View.OnClickListener {

    private Button title_left_btn , title_right_btn;

    /**
     * Fragment管理器
     */
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    /**
     * 两个Fragment
     */
    private StoreFragment storeFragment;
    private DanPinFragment danPinFragment;

    @Override
    public int getContentView() {
        return R.layout.activity_weekend;
    }

    @Override
    public void initView() {
        title_left_btn = (Button)findViewById(R.id.constact_group);
        title_right_btn = (Button)findViewById(R.id.constact_all);

        title_left_btn.setOnClickListener(this);
        title_left_btn.performClick();//模拟点击事件，使左边按钮被点击

        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

        storeFragment = new StoreFragment();
        mTransaction.replace(R.id.weekend_content, storeFragment);
        mTransaction.commit();

        title_right_btn.setOnClickListener(this);
    }


    @Override
    public void initClick() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constact_group:
                if(title_left_btn.isEnabled()){
                    title_left_btn.setEnabled(false);
                    title_right_btn.setEnabled(true);
                }
                mFragmentManager = getSupportFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                if(storeFragment == null){
                    storeFragment = new StoreFragment();

                }
                mTransaction.replace(R.id.weekend_content, storeFragment);
                mTransaction.commit();
                break;

            case R.id.constact_all:
                if(title_right_btn.isEnabled()){
                    title_left_btn.setEnabled(true);
                    title_right_btn.setEnabled(false);
                }
                mFragmentManager = getSupportFragmentManager();
                mTransaction = mFragmentManager.beginTransaction();
                if(danPinFragment == null){
                    danPinFragment = new DanPinFragment();
                }
                mTransaction.replace(R.id.weekend_content, danPinFragment);
                mTransaction.commit();
                break;
        }
    }
}
