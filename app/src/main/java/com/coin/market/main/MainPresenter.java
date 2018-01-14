package com.coin.market.main;

import com.coin.market.CoinMarketApplication;
import com.coin.market.model.AltCoin;
import com.coin.market.shared.MemoryShared;
import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.util.FaLog;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by t430 on 1/13/2018.
 */

public class MainPresenter extends BasePresenter<MainView>{
    public void loadAllFavouriteAltcoin(){
        RealmResults<AltCoin> altCoins = CoinMarketApplication.realm.where(AltCoin.class).findAll();
        MemoryShared.getSharedInstance().setFavAltcointList(altCoins);
        FaLog.e("Size fav "+altCoins.size());
    }
}
