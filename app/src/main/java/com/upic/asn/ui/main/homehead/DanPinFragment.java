package com.upic.asn.ui.main.homehead;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.upic.asn.R;
import com.upic.asn.ui.base.BaseFragment;

/**
 * Created by ZYF on 2016/12/12.
 */
public class DanPinFragment extends BaseFragment {
    TextView textView;
    Button button;
    @Override
    public int getContentView() {
        return R.layout.weekend_item_danpin;
    }

    @Override
    public void initView() {
        textView = (TextView) view.findViewById(R.id.danpin_txt);
        button = (Button) view.findViewById(R.id.danpin_b);
    }

    @Override
    public void initClick() {
        button.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        textView.setText("12133333333333");
    }
}
