package com.coin.market.model;

import com.coin.market.viewmodel.GlobalmarketcapHolderView;
import com.vn.fa.adapter.multipleviewtype.DataBinder;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;

/**
 * Created by t430 on 1/11/2018.
 */

public class GlobalMarketCap implements IViewBinder{
    @Override
    public DataBinder getViewBinder() {
        return new GlobalmarketcapHolderView(this).itemViewType(0);
    }
}
