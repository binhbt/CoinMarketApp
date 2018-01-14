package com.coin.market.home.all;

import com.coin.market.data.net.BaseRequest;
import com.coin.market.data.net.CoinMarketRequestFactory;
import com.coin.market.model.AltCoin;
import com.coin.market.model.GlobalMarketCap;
import com.coin.market.shared.MemoryShared;
import com.coin.market.sort.CustomComparator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.data.net.FaRequest;
import com.vn.fa.base.data.net.FaRequest.RequestCallBack;
import com.vn.fa.base.data.net.request.retrofit.RetrofitAdapterFactory;
import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.util.FaLog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by t430 on 1/9/2018.
 */

public class AllCoinPresenter extends BasePresenter<AllCoinView>{
    public void loadData() {
        final FaRequest request1 = CoinMarketRequestFactory.getInstance().getRequest(CoinMarketRequestFactory.CoinRequestType.GLOBAL_MARKET_CAP)
                .params(new HashMap<>())
                .requesAdaptert(new RetrofitAdapterFactory())
                .dataType(new TypeToken<GlobalMarketCap>() {}.getType());

        final FaRequest request2 = CoinMarketRequestFactory.getInstance().getRequest(CoinMarketRequestFactory.CoinRequestType.ALL_COIN_REQUEST)
                .params(new HashMap<>())
                .addParams("limit", "0")
                .dataType(new TypeToken<String>() {}.getType());
        BaseRequest mixRequest = new BaseRequest(){
            @Override
            public Observable getApi() {
                Observable<GlobalMarketCap> combined = Observable.zip(request1.getApi(), request2.getApi(), new BiFunction<GlobalMarketCap , String, List<Object>>() {
                    @Override
                    public List<Object> apply(GlobalMarketCap globalMarketCap, String result) {
                        List<Object> list = new ArrayList<>();
                        if (result != null) {
                            result = result.replaceAll("24h_volume_usd", "d_volume_usd").
                                    replaceAll("null","0");
                            Type type = new TypeToken<List<AltCoin>>() {}.getType();
                            List<AltCoin> altList = new Gson().fromJson(result, type);
                            for (AltCoin alt:altList
                                    ) {
                                alt.setType(AltCoin.Type.ALL_COIN);
                                //FaLog.e(alt.getName());
                            }
                            Collections.sort(altList, new CustomComparator());
                            MemoryShared.getSharedInstance().setAltCoinList(altList);
                            MemoryShared.getSharedInstance().setGlobalMarketCap(globalMarketCap);
                            list.add(globalMarketCap);
                            list.addAll(altList);
                        }
                        return list;
                    }
                });
                return combined;
            }
        };
        mixRequest
                .callBack(new RequestCallBack<List<Object>>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onFinish(List<Object> result) {
                        if (result != null) {
                            if (result.size() >0) {
                                result = processForFav(result, MemoryShared.getSharedInstance().getFavAlcointList());
                                getMvpView().loadDataToView(result);
                                FaLog.e("result", result.size()+"");
                            }
                        }
                    }
                })
                .doRequest();
    }
    private List<Object> processForFav(List<Object> all, List<AltCoin> fav){
        if (fav == null || fav.size() ==0) return all;
        for (Object alt:all
             ) {
            if (alt instanceof AltCoin)
                ((AltCoin)alt).setFavourite(isFav(((AltCoin)alt), fav));

        }
        return all;
    }
    private boolean isFav(AltCoin altCoin, List<AltCoin> fav){
        for (Object alt:fav
             ) {
            if (alt instanceof AltCoin)
            if (((AltCoin)alt).getId().equals(altCoin.getId())){
                return true;
            }
        }
        return false;
    }
}
