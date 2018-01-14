package com.coin.market.home.all;

import com.coin.market.BaseMvpView;
import com.coin.market.model.AltCoin;

import java.util.List;

/**
 * Created by t430 on 1/9/2018.
 */

public interface AllCoinView extends BaseMvpView{
    void loadDataToView(List<Object> coinList);
}
