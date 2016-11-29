package com.zyf.rxjavaretrofit_demo.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zyf.rxjavaretrofit_demo.R;
import com.zyf.rxjavaretrofit_demo.adapter.BaseAdapter;
import com.zyf.rxjavaretrofit_demo.adapter.NewsAdapter;
import com.zyf.rxjavaretrofit_demo.model.ImageModel;
import com.zyf.rxjavaretrofit_demo.model.News;
import com.zyf.rxjavaretrofit_demo.model.view.NewsListener;
import com.zyf.rxjavaretrofit_demo.ui.base.BaseFragment;
import com.zyf.rxjavaretrofit_demo.util.SysUtil;
import com.zyf.rxjavaretrofit_demo.view.pullrecyclerview.PullBaseView;
import com.zyf.rxjavaretrofit_demo.view.pullrecyclerview.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/11/16.
 */
public class FragmentTab1_2 extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_1_2,null);
    }
}
