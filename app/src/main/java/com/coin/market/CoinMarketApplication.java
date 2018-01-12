package com.coin.market;

import com.fa.loader.FALoader;
import com.vn.fa.base.FaApplication;
import com.vn.fa.base.data.cache.CacheProviders;
import com.vn.fa.base.data.cache.rxcache.RxCacheAdapterFactory;
import com.vn.fa.base.util.FaLog;

import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by t430 on 1/4/2018.
 */

public class CoinMarketApplication extends FaApplication {
    public static FaApplication faApplication;
    public static RxCacheAdapterFactory cacheProviders;
    @Override
    public void onCreate() {
        super.onCreate();
        FALoader.type = FALoader.Type.FRESCO;
        FALoader.initialize(this);
        faApplication = this;
        FaLog.init(BuildConfig.DEBUG);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/System San Francisco Display Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
    private void initDb(){
        Realm.init(this);
    }
    public static CacheProviders getCacheProviders(){
        if (cacheProviders == null){
            cacheProviders = new RxCacheAdapterFactory();
            cacheProviders.init(faApplication.getFilesDir());
        }
        return cacheProviders;
    }
    public static FaApplication getInstance(){
        return faApplication;
    }
}
