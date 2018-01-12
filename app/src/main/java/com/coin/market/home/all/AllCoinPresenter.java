package com.coin.market.home.all;

import android.util.Log;

import com.coin.market.data.net.BaseRequest;
import com.coin.market.data.net.CoinMarketRequestFactory;
import com.coin.market.model.AltCoin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.data.net.FaRequest;
import com.vn.fa.base.data.net.FaRequest.RequestCallBack;
import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.util.FaLog;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Created by t430 on 1/9/2018.
 */

public class AllCoinPresenter extends BasePresenter<AllCoinView>{
    public void loadData() {
        CoinMarketRequestFactory.getInstance().getRequest(CoinMarketRequestFactory.CoinRequestType.ALL_COIN_REQUEST)
                .addParams("limit", "0")
                .dataType(new TypeToken<String>() {}.getType())
                .callBack(new RequestCallBack<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onFinish(String result) {
                        if (result != null) {
                            if (result != null) {
                                result = result.replaceAll("24h_volume_usd", "d_volume_usd").
                                        replaceAll("null","0");
                                Type type = new TypeToken<List<AltCoin>>() {}.getType();
                                List<AltCoin> altList = new Gson().fromJson(result, type);
                                for (AltCoin alt:altList
                                        ) {
                                    alt.setType(AltCoin.Type.ALL_COIN);
                                    FaLog.e(alt.getName());
                                }
                                getMvpView().loadDataToView(altList);
                                FaLog.e("result", result);
                            }
                        }
                        //testUser();
                        //mixRequest();
                    }
                })
                .doRequest();
    }
}
