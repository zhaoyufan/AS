package com.upic.asn.ui.main.homehead;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.adapter.base.BaseAdapter;
import com.upic.asn.ui.base.BaseFragment;
import com.upic.asn.ui.main.homehead.adapter.IntelligentAdapter;
import com.upic.asn.ui.main.homehead.adapter.SortingMyAdapter;
import com.upic.asn.ui.main.homehead.adapter.WanLeClassifyAdapter1;
import com.upic.asn.ui.main.homehead.adapter.WanLeClassifyAdapter2;
import com.upic.asn.ui.main.homehead.adapter.WeekendRecyclerAdapter;
import com.upic.asn.ui.main.homehead.bean.City;
import com.upic.asn.ui.main.homehead.bean.City_2;
import com.upic.asn.ui.main.homehead.bean.Intelligent;
import com.upic.asn.ui.main.homehead.bean.SortSelect;
import com.upic.asn.ui.main.homehead.bean.WanLeList;
import com.upic.asn.view.pullrecyclerview.PullBaseView;
import com.upic.asn.view.pullrecyclerview.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/12/12.
 */
public class StoreFragment extends BaseFragment implements
        BaseAdapter.OnItemClickListener,
        PullBaseView.OnHeaderRefreshListener{
    private DisplayMetrics dm;
    private PopupWindow popWindow;
    private ListView wanle_classify_1,wanle_classify_2;
    private TextView wanle, sorting, intelligent;//玩乐，分类筛选，只能排序
    //wanle
    private List<Object> cityList;//一级菜单数据，每个一级item中包含二级菜单数据
    private WanLeClassifyAdapter1 wanLeClassifyAdapter1;//玩乐一级菜单适配器
    private WanLeClassifyAdapter2 wanLeClassifyAdapter2;//二级菜单适配器
    private TextView pop_wanle_theme,pop_wanle_item;
    //sorting
    private List<SortSelect> sortSelects1,sortSelects2,sortSelects3;
    private Button pop_sorting_sure,pop_sorting_reset;
    private GridView pop_sorting_gv1,pop_sorting_gv2,pop_sorting_gv3;
    private SortingMyAdapter sortingMyAdapter1,sortingMyAdapter2,sortingMyAdapter3;
    //pop_intelligent
    private List<Object> intelligents;
    private ListView pop_intelligent_list;
    private IntelligentAdapter intelligentAdapter;

    private List<Object> listWanLeList;
    private WeekendRecyclerAdapter recyclerAdapter;
    private PullRecyclerView recyclerView;

    private int intelligetnsLastPosition = -1;


    @Override
    public int getContentView() {
        return R.layout.weekend_item_store;
    }

    @Override
    public void initView() {
        cityList = new ArrayList<>();
        intelligents = new ArrayList<>();
        listWanLeList = new ArrayList<>();
        dm = new DisplayMetrics();
        // 取得窗口属性
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        initCity();
        wanle = (TextView) view.findViewById(R.id.weekend_item_wanle);
        sorting = (TextView) view.findViewById(R.id.weekend_item_sorting);
        intelligent = (TextView) view.findViewById(R.id.weekend_item_intelligent);

        recyclerView = (PullRecyclerView) view.findViewById(R.id.weekend_item_store_recyclerview);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
        recyclerView.setCanScrollAtRereshing(false);//设置正在刷新时是否可以滑动，默认不可滑动
        recyclerView.setCanPullDown(true);//设置是否可下拉
        recyclerView.setCanPullUp(false);//设置是否可上拉
    }

    private void initRecyclerView(){
        recyclerAdapter = new WeekendRecyclerAdapter(context,listWanLeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void initClick() {
        wanle.setOnClickListener(this);
        sorting.setOnClickListener(this);
        intelligent.setOnClickListener(this);
    }

    @Override
    public void initData() {
        loadRecyclerViewData();
        initRecyclerView();
        addIntellages();
    }

    private void loadRecyclerViewData() {
        WanLeList wanLeList1 = new WanLeList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg","天胜四不用农庄","51分钟/6千米","289","289");
        WanLeList wanLeList2 = new WanLeList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg","天胜四不用农庄","51分钟/6千米","289","289");
        WanLeList wanLeList3 = new WanLeList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg","天胜四不用农庄","51分钟/6千米","289","289");
        WanLeList wanLeList4 = new WanLeList("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg","天胜四不用农庄","51分钟/6千米","289","289");
        listWanLeList.add(wanLeList1);
        listWanLeList.add(wanLeList2);
        listWanLeList.add(wanLeList3);
        listWanLeList.add(wanLeList4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.weekend_item_wanle:
                downWanLePopwindow();break;
            case R.id.weekend_item_sorting:
                downSortingPopwindow();break;
            case R.id.weekend_item_intelligent:
                downIntelligentPopwindow();break;
        }
    }

    private void downWanLePopwindow() {
        // showAsDropDown(View anchor);相对某个控件的位置（正左下方），无偏移
        // showAsDropDown(View anchor, int x, int
        // y);相对某个控件的位置，有偏移;x表示相对x轴的偏移，正表示向左，负表示向右；y表示相对y轴的偏移，正是向下，负是向上；
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_wanle,
                null);
        setPopwindow(contentView);

        initPopWindownView(contentView,1);

        wanLeClassifyAdapter1 = new WanLeClassifyAdapter1(context, cityList);
        wanLeClassifyAdapter1.setSelectItem(0);
        initWanLeClassifyAdapter1(((City)cityList.get(0)).getLists());
        wanle_classify_1.setAdapter(wanLeClassifyAdapter1);

        setItemClickEvent(1);
        popWindow.showAsDropDown(wanle);//设置弹出方向为tv1下面
    }

    private void downSortingPopwindow(){
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_sorting,null);
        setPopwindow(contentView);

        initPopWindownView(contentView,2);

        setItemClickEvent(2);
        sortingMyAdapter1 = new SortingMyAdapter(context,getData1());
        pop_sorting_gv1.setAdapter(sortingMyAdapter1);
        sortingMyAdapter2 = new SortingMyAdapter(context,getData2());
        pop_sorting_gv2.setAdapter(sortingMyAdapter2);
        sortingMyAdapter3 = new SortingMyAdapter(context,getData3());
        pop_sorting_gv3.setAdapter(sortingMyAdapter3);

        popWindow.showAsDropDown(wanle);//设置弹出方向为wanle下面
    }

    private void downIntelligentPopwindow(){
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_intelligent,null);

        setPopwindow(contentView);

        initPopWindownView(contentView,3);

        intelligentAdapter = new IntelligentAdapter(context,intelligents);
        pop_intelligent_list.setAdapter(intelligentAdapter);
        if(intelligetnsLastPosition == -1){
            intelligentAdapter.setSelectItem(0);
        }else {
            intelligentAdapter.setSelectItem(intelligetnsLastPosition);
        }
        setItemClickEvent(3);
        popWindow.showAsDropDown(wanle);//设置弹出方向为intelligent下面
    }

    private void setPopwindow(View contentView) {
        int screenHeight = dm.heightPixels;
        // 这里就给具体大小的数字，要不然位置不好计算
        popWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                screenHeight);
        // popWindow.setAnimationStyle(R.style.anim);// 淡入淡出动画
        // popWindow.setTouchable(false);// 是否响应touch事件
        popWindow.setFocusable(true);// 是否具有获取焦点的能力
        // 点击PopupWindow以外的区域，PopupWindow是否会消失。
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);
    }

    private void initPopWindownView(View contentView,int type) {
        switch (type){
            case 1:
                wanle_classify_1 = (ListView) contentView.findViewById(R.id.wanle_classify_1);
                wanle_classify_2 = (ListView) contentView.findViewById(R.id.wanle_classify_2);
                pop_wanle_theme = (TextView) contentView.findViewById(R.id.pop_wanle_theme);
                pop_wanle_item = (TextView) contentView.findViewById(R.id.pop_wanle_item);
                break;
            case 2:
                pop_sorting_sure = (Button) contentView.findViewById(R.id.pop_sorting_sure);
                pop_sorting_reset = (Button) contentView.findViewById(R.id.pop_sorting_reset);
                pop_sorting_gv1 = (GridView) contentView.findViewById(R.id.pop_sorting_1);
                pop_sorting_gv2 = (GridView) contentView.findViewById(R.id.pop_sorting_2);
                pop_sorting_gv3 = (GridView) contentView.findViewById(R.id.pop_sorting_3);
                break;
            case 3:
                pop_intelligent_list = (ListView) contentView.findViewById(R.id.pop_intelligent_list);
                break;

        }
    }
    private void setItemClickEvent(int type){
        switch (type){
            case 1:
                pop_wanle_theme.setSelected(true);
                pop_wanle_theme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop_wanle_item.setSelected(false);
                        pop_wanle_theme.setSelected(true);
                    }
                });

                pop_wanle_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop_wanle_theme.setSelected(false);
                        pop_wanle_item.setSelected(true);
                    }
                });

                wanle_classify_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        List<Object> lists = ((City)cityList.get(position)).getLists();
                        initWanLeClassifyAdapter1(lists);
                        wanLeClassifyAdapter1.setSelectItem(position);
                        wanLeClassifyAdapter1.notifyDataSetChanged();
                    }
                });

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
                break;
            case 2:
                pop_sorting_gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        sortingMyAdapter1.changeState(i);
                        Toast.makeText(context,sortSelects1.get(i).getContent(),Toast.LENGTH_SHORT).show();
                    }
                });
                pop_sorting_gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        sortingMyAdapter2.changeState(i);
                        Toast.makeText(context,sortSelects2.get(i).getContent(),Toast.LENGTH_SHORT).show();
                    }
                });
                pop_sorting_gv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        sortingMyAdapter3.changeState(i);
                        Toast.makeText(context,sortSelects3.get(i).getContent(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 3:
                pop_intelligent_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Intelligent intelligent1 = (Intelligent) intelligentAdapter.getItem(position);
                        Toast.makeText(context,intelligent1.getContent(),Toast.LENGTH_SHORT).show();
                        intelligent.setText(intelligent1.getContent());
                        intelligetnsLastPosition = position;
                        popWindow.dismiss();
                    }
                });
                break;
        }

    }
    private void initWanLeClassifyAdapter1(List<Object> objectList){
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
        City c1 = new City("玩乐",city2List1);
        City c2 = new City("出行圈",city2List2);
        City c3 = new City("品农",city2List3);
        cityList.add(c1);
        cityList.add(c2);
        cityList.add(c3);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(context,"点击"+((WanLeList)listWanLeList.get(position)).getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {

    }

    //以下三个getData是Sort的数据
    private List<SortSelect> getData1(){
        sortSelects1 = new ArrayList<SortSelect>();
        sortSelects1.add(new SortSelect(0,"时间不限"));
        sortSelects1.add(new SortSelect(1,"本周末"));
        sortSelects1.add(new SortSelect(2,"7天内"));
        sortSelects1.add(new SortSelect(3,"7-30天"));
        sortSelects1.add(new SortSelect(4,"30天后"));
        return sortSelects1;
    }
    private List<SortSelect> getData2(){
        sortSelects2 = new ArrayList<SortSelect>();
        sortSelects2.add(new SortSelect(0,"全部"));
        sortSelects2.add(new SortSelect(1,"鼓楼"));
        sortSelects2.add(new SortSelect(2,"鄞州高教园区"));
        sortSelects2.add(new SortSelect(3,"汽车南站"));
            sortSelects2.add(new SortSelect(4,"崇寿镇"));
        sortSelects2.add(new SortSelect(5,"汽车东站"));
        sortSelects2.add(new SortSelect(6,"大榭"));
        sortSelects2.add(new SortSelect(7,"庄市"));
        sortSelects2.add(new SortSelect(8,"龙山镇"));
        sortSelects2.add(new SortSelect(9,"兴达路沿线"));
        sortSelects2.add(new SortSelect(10,"姜山"));
        sortSelects2.add(new SortSelect(11,"其他"));
        return sortSelects2;
    }
    private List<SortSelect> getData3(){
        sortSelects3 = new ArrayList<SortSelect>();
        sortSelects3.add(new SortSelect(0,"1"));
        sortSelects3.add(new SortSelect(1,"2"));
        sortSelects3.add(new SortSelect(2,"5"));
        sortSelects3.add(new SortSelect(3,"10人以上"));
        sortSelects3.add(new SortSelect(4,"20人以上"));
        sortSelects3.add(new SortSelect(5,"30人以上"));
        return sortSelects3;
    }

    private void addIntellages(){
        Intelligent i1 = new Intelligent("11111111",1);
        Intelligent i2 = new Intelligent("2222222",2);
        Intelligent i3 = new Intelligent("33333",3);
        Intelligent i4 = new Intelligent("444",4);
        intelligents.add(i1);
        intelligents.add(i2);
        intelligents.add(i3);
        intelligents.add(i4);

    }
}