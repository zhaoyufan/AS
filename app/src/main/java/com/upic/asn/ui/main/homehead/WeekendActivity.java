package com.upic.asn.ui.main.homehead;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.upic.asn.R;
import com.upic.asn.ui.base.BaseActivity;
import com.upic.asn.ui.main.MainActivity;

public class WeekendActivity extends BaseActivity implements View.OnClickListener {

    private Button title_left_btn , title_right_btn;
    private TextView back;

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
        back = (TextView) findViewById(R.id.weekend_left);
        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

    }


    @Override
    public void initClick() {
        title_left_btn.setOnClickListener(this);
        title_right_btn.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        title_left_btn.performClick();//模拟点击事件，使左边按钮被点击
        setTabSelection(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.constact_group:
                setTabSelection(0);
                break;
            case R.id.constact_all:
                setTabSelection(1);
                break;
            case R.id.weekend_left:
                Intent intent = new Intent(WeekendActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    public void setTabSelection(int id) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (id) {//0表示第一张以此类推
            case 0:
                title_left_btn.setEnabled(false);
                title_right_btn.setEnabled(true);
                if (storeFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    storeFragment = new StoreFragment();
                    transaction.add(R.id.weekend_content, storeFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(storeFragment);
                }
                break;
            case 1:
                title_left_btn.setEnabled(true);
                title_right_btn.setEnabled(false);
                if (danPinFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    danPinFragment = new DanPinFragment();
                    transaction.add(R.id.weekend_content, danPinFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(danPinFragment);
                }
                break;
        }
        transaction.commit();//事务的提交
    }

    private void hideFragments(FragmentTransaction mTransaction) {
        if(danPinFragment != null){
            mTransaction.hide(danPinFragment);
        }
        if(storeFragment != null){
            mTransaction.hide(storeFragment);
        }
    }

}
