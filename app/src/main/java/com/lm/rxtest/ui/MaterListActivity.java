package com.lm.rxtest.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lm.rxtest.R;
import com.lm.rxtest.base.BaseActivity;
import com.lm.rxtest.base.BasePresenter;
import com.lm.rxtest.databinding.ActivityMaterListBinding;

import java.util.ArrayList;
import java.util.List;

import ml.gsy.com.library.adapters.recyclerview.CommonAdapter;
import ml.gsy.com.library.adapters.recyclerview.MultiItemTypeAdapter;
import ml.gsy.com.library.adapters.recyclerview.base.ViewHolder;

public class MaterListActivity extends BaseActivity<BasePresenter, ActivityMaterListBinding> {


    private List<String> mDataList = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    @Override
    protected boolean isPrestener() {
        return false;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("点击图片会有神奇的事情哦");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mater_list;
    }

    @Override
    protected void initData() {
        super.initData();
        for (int i = 0; i < 10; i++) {
            mDataList.add("");
        }
        mAdapter = new CommonAdapter<String>(aty, R.layout.item_mater_list, mDataList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Intent intent = new Intent(aty, MaterialActivity.class);
                if (android.os.Build.VERSION.SDK_INT > 20) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(aty, holder.itemView, "transitionImg").toBundle());
                } else {
                    startActivity(intent);
                }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mBinding.rcBody.setLayoutManager(new LinearLayoutManager(aty));

        mBinding.rcBody.setAdapter(mAdapter);
    }
}
