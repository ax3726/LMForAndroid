package com.lm.rxtest.ui;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.lm.rxtest.R;
import com.lm.rxtest.base.BaseActivity;
import com.lm.rxtest.base.EmptyState;
import com.lm.rxtest.base.StateModel;
import com.lm.rxtest.databinding.ActivityMainBinding;
import com.lm.rxtest.model.UserInfoModel;
import com.lm.rxtest.prestener.MainPrestener;
import com.lm.rxtest.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import ml.gsy.com.library.views.AndroidBug5497Workaround;
import rx.Observable;

public class MainActivity extends BaseActivity<MainPrestener, ActivityMainBinding> implements IMainView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPrestener createPresenter() {
        return new MainPrestener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Log.e("msg", "UI渲染耗时:" + l);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        mTitleBarLayout.setTitle("侧滑可以返回哦");
        mTitleBarLayout.setLeftShow(false);
        mTitleBarLayout.setRightTxt("更多");
        mTitleBarLayout.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("你点击了更多");

            }
        });
    }

    @Override
    protected void initData() {

        mBinding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MaterListActivity.class);
            }
        });
        mBinding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestActivity.class);
            }
        });
        AndroidBug5497Workaround.assistActivity(findViewById(android.R.id.content));// 解决输入法弹出时布局被顶上去的BUG

        mStateModel.setEmptyState(EmptyState.PROGRESS);//设置页面状态为加载中
        //mStateModel.setEmptyState(EmptyState.NORMAL);//设置页面状态为正常
        //mStateModel.setEmptyState(EmptyState.EMPTY);//设置页面状态为暂无数据
        //EmptyState类里面可自定义添加状态

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mStateModel.setEmptyState(EmptyState.EMPTY);
            }
        }, 3000);
        mPresenter.getUserInfo1();
        mStateModel.setIOnClickListener(new StateModel.IOnClickListener() {
            @Override
            public void click(View view) {
                mStateModel.setEmptyState(EmptyState.PROGRESS);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mStateModel.setEmptyState(EmptyState.NORMAL);
                    }
                }, 3000);
            }
        });


        List<String> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add("项" + i);
        }
        Observable.from(list)
                .subscribe(s -> Log.e("m1g1", s));
/*

        RxTextView.textChanges(mBinding.etTxt)
                .filter(charSequence -> !"11".equals(charSequence.toString()))
                .map(charSequence -> "黎明" + charSequence)
                .subscribe(str -> showToast(str));

        RxViewClick(mBinding.btn)
                .subscribe(aVoid -> {
                  */
/*  mStateModel.setEmptyState(EmptyState.PROGRESS);
                    Observable.timer(1000, TimeUnit.MILLISECONDS)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aLong -> mPresenter.getUserInfo());*//*

                 */
/*   String all_url = "https://github.com/wzgiceman/RxjavaRetrofitDemo-master/archive/master.zip";//全路径

                    Uri url = Uri.parse(all_url);
                    showToast("host:" + url.getHost() + "\t\t\t" + url.getPath());*//*

                    // mPresenter.downLoadFile();
                    startActivity(TestActivity.class);
                });
*/


    }

    @Override
    public void getUserInfo(UserInfoModel userInfoModel) {
        // showToast("获取到了!");
     /*   long l = System.currentTimeMillis() - cur;
        Log.e("msg", "inflate之前加载数据耗时:" + l);*/
        mStateModel.setEmptyState(EmptyState.NORMAL);
    }

    @Override
    public void getUserInfo1(UserInfoModel userInfoModel) {
        /*long l = System.currentTimeMillis() - cur;
        Log.e("msg", "inflate之后加载数据耗时:" + l);*/
        showToast("获取到了");
    }

    @Override
    public void login() {
        showToast("跳转主界面");


    }

    @Override
    public void downProgress(long total, long precent) {
        //runOnUiThread(() -> mBinding.txt.setText("总大小:" + total + " \t\t\t\t进度:" + precent));
    }


}
