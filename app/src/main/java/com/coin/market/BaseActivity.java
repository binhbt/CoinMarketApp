package com.coin.market;

import android.content.Context;
import android.os.Bundle;

import com.vn.fa.base.ui.FaActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by t430 on 1/4/2018.
 */

public class BaseActivity extends FaActivity{
    @Override
    protected void initView(Bundle savedInstanceState) {

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected int getLayoutId() {
        return 0;
    }
}
