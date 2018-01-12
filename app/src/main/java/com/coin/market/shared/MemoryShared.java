package com.coin.market.shared;

import com.vn.fa.base.data.cache.CacheProviders;
import com.vn.fa.base.data.cache.rxcache.RxCacheAdapterFactory;

/**
 * Created by t430 on 1/11/2018.
 */

public class MemoryShared {
    private static MemoryShared instance;
    public static MemoryShared getsharedInstance(){
        if (instance == null){
            instance = new MemoryShared();
        }
        return instance;
    }
    private int settingSort;
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
}
