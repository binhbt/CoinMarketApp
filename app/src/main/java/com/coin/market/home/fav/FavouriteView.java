package com.coin.market.home.fav;

import com.coin.market.BaseMvpView;

import java.util.List;

/**
 * Created by t430 on 1/9/2018.
 */

public interface FavouriteView extends BaseMvpView{
    void loadDataToView(List<Object> coinList);

}
