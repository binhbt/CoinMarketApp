package com.coin.market;

import android.content.Context;
import android.os.Bundle;

import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.mvp.MvpView;
import com.vn.fa.base.ui.FaActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by t430 on 1/4/2018.
 */

public class BaseActivity extends FaActivity implements MvpView{
    protected BasePresenter presenter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (presenter != null)
            presenter.attachView(this);
        StartAppSDK.init(this, "200723542", true);
        StartAppAd.disableSplash();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        if (presenter != null)
            presenter.detachView();
        super.onDestroy();
    }
}
