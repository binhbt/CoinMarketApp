package com.coin.market;

import android.os.Bundle;

import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.mvp.MvpView;
import com.vn.fa.base.ui.FaFragment;

/**
 * Created by t430 on 1/7/2018.
 */

public class BaseFragment extends FaFragment implements MvpView{
    protected BasePresenter presenter;
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (presenter != null)
            presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        if (presenter != null)
            presenter.detachView();
        super.onDestroyView();
    }
}
