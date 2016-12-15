package com.upic.asn.ui.main.homehead;

import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.ui.base.BaseFragment;
import com.upic.asn.ui.main.homehead.adapter.WanLeClassifyAdapter1;
import com.upic.asn.ui.main.homehead.adapter.WanLeClassifyAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/12/12.
 */
public class StoreFragment extends BaseFragment {
    private DisplayMetrics dm;
    private PopupWindow popWindow;
    private ListView wanle_classify_1,wanle_classify_2;
    private TextView wanle, sorting , intelligent;
    private TextView wanleTheme,wanleItem;
    private List<Object> cityList;
    private WanLeClassifyAdapter1 wanLeClassifyAdapter1;
    private WanLeClassifyAdapter2 wanLeClassifyAdapter2;

    @Override
    public int getContentView() {
        return R.layout.weekend_item_store;
    }

    @Override
    public void initView() {
        dm = new DisplayMetrics();
        // 取得窗口属性
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        initCity();
        wanle = (TextView) view.findViewById(R.id.weekend_item_wanle);
    }

    @Override
    public void initClick() {
        wanle.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weekend_item_wanle:
                downWanLePopwindow();break;
            //点击主题，重新初始化数据，
            case R.id.pop_wanle_theme:break;
            //点击项目
            case R.id.pop_wanle_item:break;
        }
    }

    private void downWanLePopwindow() {
        // showAsDropDown(View anchor);相对某个控件的位置（正左下方），无偏移
        // showAsDropDown(View anchor, int x, int
        // y);相对某个控件的位置，有偏移;x表示相对x轴的偏移，正表示向左，负表示向右；y表示相对y轴的偏移，正是向下，负是向上；
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_wanle,
                null);
        int screenHeight = dm.heightPixels * 2 / 4;
        // 这里就给具体大小的数字，要不然位置不好计算
        popWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);
        // popWindow.setAnimationStyle(R.style.anim);// 淡入淡出动画
        // popWindow.setTouchable(false);// 是否响应touch事件
        popWindow.setFocusable(true);// 是否具有获取焦点的能力
        // 点击PopupWindow以外的区域，PopupWindow是否会消失。
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);

        wanle_classify_1 = (ListView) contentView.findViewById(R.id.wanle_classify_1);
        wanle_classify_2 = (ListView) contentView.findViewById(R.id.wanle_classify_2);
        wanleTheme = (TextView) contentView.findViewById(R.id.pop_wanle_theme);
        wanleItem = (TextView) contentView.findViewById(R.id.pop_wanle_item);
        wanleTheme.setOnClickListener(this);
        wanleItem.setOnClickListener(this);

        wanLeClassifyAdapter1 = new WanLeClassifyAdapter1(context, cityList);
        wanLeClassifyAdapter1.setSelectItem(0);
        List<Object> lists = ((City)cityList.get(0)).getLists();
        initAdapter(lists);
        wanLeClassifyAdapter2.setSelectItem(0);
        wanle_classify_1.setAdapter(wanLeClassifyAdapter1);

        wanle_classify_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                List<Object> lists = ((City)cityList.get(position)).getLists();
                initAdapter(lists);
                wanLeClassifyAdapter1.setSelectItem(position);
                wanLeClassifyAdapter1.notifyDataSetChanged();
            }
        });
        wanle_classify_1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 一定要设置这个属性，否则ListView不会刷新

        wanle_classify_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                City_2 city2 = (City_2) wanLeClassifyAdapter2.getItem(position);
                Toast.makeText(context, city2.getName(), Toast.LENGTH_SHORT).show();
                wanLeClassifyAdapter2.setSelectItem(position);
                wanLeClassifyAdapter2.notifyDataSetChanged();
                wanle.setText(city2.getName());
                popWindow.dismiss();
            }
        });
        popWindow.showAsDropDown(wanle);//设置弹出方向为wanle下面
    }

    private void initAdapter(List<Object> objectList){
        wanLeClassifyAdapter2 = new WanLeClassifyAdapter2(context, objectList);
        wanle_classify_2.setAdapter(wanLeClassifyAdapter2);
        wanLeClassifyAdapter2.notifyDataSetChanged();
    }

    private void initCity(){
        List<Object> city2List1 = new ArrayList<>();
        List<Object> city2List2 = new ArrayList<>();
        List<Object> city2List3 = new ArrayList<>();
        City_2 city1 = new City_2("城市11市1");
        City_2 city2 = new City_2("城市11市2");
        City_2 city3 = new City_2("城市11市3");
        City_2 city4 = new City_2("城市11市4");
        City_2 city5 = new City_2("城市11市5");
        city2List1.add(city1);
        city2List1.add(city2);
        city2List1.add(city3);
        city2List1.add(city4);
        city2List1.add(city5);
        City_2 city6 = new City_2("城市222市1");
        City_2 city7 = new City_2("城市222市2");
        City_2 city8 = new City_2("城市222市3");
        City_2 city9 = new City_2("城市222市4");
        City_2 city10 = new City_2("城市222市5");
        city2List2.add(city6);
        city2List2.add(city7);
        city2List2.add(city8);
        city2List2.add(city9);
        city2List2.add(city10);

        City_2 city11 = new City_2("城市333市1");
        City_2 city12 = new City_2("城市333市2");
        City_2 city13 = new City_2("城市333市3");
        City_2 city14 = new City_2("城市333市4");
        City_2 city15 = new City_2("城市333市5");
        city2List3.add(city11);
        city2List3.add(city12);
        city2List3.add(city13);
        city2List3.add(city14);
        city2List3.add(city15);
        City c1 = new City("城市1",city2List1);
        City c2 = new City("城市2",city2List2);
        City c3 = new City("城市3",city2List3);
        cityList = new ArrayList<>();
        cityList.add(c1);
        cityList.add(c2);
        cityList.add(c3);
    }
}
