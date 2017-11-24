package com.lm.rxtest.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.lm.rxtest.R;
import com.lm.rxtest.base.BaseActivity;
import com.lm.rxtest.base.BasePresenter;
import com.lm.rxtest.databinding.ActivityTestBinding;
import com.lm.rxtest.widget.MyHeadView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import ml.gsy.com.library.adapters.recyclerview.CommonAdapter;
import ml.gsy.com.library.adapters.recyclerview.base.ViewHolder;

public class TestActivity extends BaseActivity<BasePresenter, ActivityTestBinding> {

    private List<String> mDataList = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected boolean isPrestener() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }


    @Override
    protected void initData() {
        super.initData();
        setSlideable(false);

        for (int i = 0; i < 10; i++) {
            mDataList.add("");
        }

        mAdapter = new CommonAdapter<String>(aty, R.layout.item_test, mDataList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                TextView txt = holder.getView(R.id.tv_txt);
                txt.setText("我是第" + (position + 1) + "项");
            }
        };
        mBinding.recyclerview.setLayoutManager(new LinearLayoutManager(aty));
        mBinding.recyclerview.setAdapter(mAdapter);


        //设置 Header 样式
        mBinding.refreshLayout.setRefreshHeader(new MyHeadView(this));
        //设置 Footer 样式
      //  mBinding.refreshLayout.setRefreshFooter(new BallPulseFooter(this));
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                mBinding.refreshLayout.resetNoMoreData();
                mDataList.clear();
                for (int i = 0; i < 10; i++) {
                    mDataList.add("");
                }
                mAdapter.notifyDataSetChanged();

            }
        });

        mBinding.refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);

                for (int i = 0; i < 10; i++) {
                    mDataList.add("");
                }
                mAdapter.notifyDataSetChanged();
                if (mDataList.size()==40) {

                    mBinding.refreshLayout.finishLoadmoreWithNoMoreData();
                }

            }
        });

    }
}
