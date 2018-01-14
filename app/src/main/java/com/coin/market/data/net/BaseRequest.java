package com.coin.market.data.net;

import com.coin.market.CoinMarketApplication;
import com.vn.fa.base.data.DataRepository;
import com.vn.fa.base.data.cache.CacheType;
import com.vn.fa.base.data.net.FaRequest;
import com.vn.fa.base.data.net.request.StringAdtapterFactory;
import com.vn.fa.base.data.net.request.okhttp.OkHttpAdapterFactory;
import com.vn.fa.base.domain.Repository;
import com.vn.fa.net.adapter.Request;

/**
 * Created by t430 on 1/4/2018.
 */

public class BaseRequest  extends FaRequest {
    public BaseRequest(){
        //Add callback for all request
        addCallBack(new RequestCallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onFinish(Object result) {
//                if (container != null){
//                    Toast.makeText(((Activity)container), "base callback", Toast.LENGTH_LONG).show();
//                }
                //doRequest();
            }
        });
    }
    public static final String API_ENDPOINT = "https://api.coinmarketcap.com/v1/";
    @Override
    public boolean isLogging() {
        return true;
    }

    @Override
    public Repository getDataRepository() {
        return new DataRepository(getEndPoints(), CoinMarketApplication.getCacheProviders());
    }

    @Override
    public boolean isCache() {
        return true;
    }

    @Override
    public CacheType getCacheType() {
        return CacheType.NET_FIRST;
    }

    @Override
    public String getBaseUrl() {
        return API_ENDPOINT;
    }

    @Override
    public Request.Factory getRequestAdapter() {
        return new StringAdtapterFactory();
    }

    @Override
    public boolean isNewInstance() {
        return true;
    }
}
