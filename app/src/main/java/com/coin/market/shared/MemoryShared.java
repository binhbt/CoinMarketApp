package com.coin.market.shared;

import com.coin.market.model.AltCoin;
import com.coin.market.model.GlobalMarketCap;
import com.coin.market.model.SortByEnum;
import com.vn.fa.base.data.cache.CacheProviders;
import com.vn.fa.base.data.cache.rxcache.RxCacheAdapterFactory;

import java.util.List;

/**
 * Created by t430 on 1/11/2018.
 */

public class MemoryShared {
    private static MemoryShared instance;
    public static MemoryShared getSharedInstance(){
        if (instance == null){
            instance = new MemoryShared();
        }
        return instance;
    }
    private int settingSort = SortByEnum.MARKETCAP.ordinal();
    private int settingPercentageChanged;
    private int settingCurrentCy;

    public int getSettingSort() {
        return settingSort;
    }

    public void setSettingSort(int settingSort) {
        this.settingSort = settingSort;
    }

    public int getSettingPercentageChanged() {
        return settingPercentageChanged;
    }

    public void setSettingPercentageChanged(int settingPercentageChanged) {
        this.settingPercentageChanged = settingPercentageChanged;
    }

    public int getSettingCurrentCy() {
        return settingCurrentCy;
    }

    public void setSettingCurrentCy(int settingCurrentCy) {
        this.settingCurrentCy = settingCurrentCy;
    }

    public List<AltCoin> getAltCoinList() {
        return altCoinList;
    }

    public void setAltCoinList(List<AltCoin> altCoinList) {
        this.altCoinList = altCoinList;
    }

    private List<AltCoin> altCoinList;

    public List<AltCoin> getFavAlcointList() {
        return favAlcointList;
    }

    public void setFavAltcointList(List<AltCoin> favAlcointList) {
        this.favAlcointList = favAlcointList;
    }

    private List<AltCoin> favAlcointList;
    private GlobalMarketCap globalMarketCap;

    public GlobalMarketCap getGlobalMarketCap() {
        return globalMarketCap;
    }

    public void setGlobalMarketCap(GlobalMarketCap globalMarketCap) {
        this.globalMarketCap = globalMarketCap;
    }
}
