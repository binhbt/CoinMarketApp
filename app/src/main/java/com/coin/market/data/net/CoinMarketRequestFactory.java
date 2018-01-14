package com.coin.market.data.net;

import com.vn.fa.base.VegaRequestFactory;
import com.vn.fa.base.data.net.FaRequest;

/**
 * Created by t430 on 1/8/2018.
 */

public class CoinMarketRequestFactory extends VegaRequestFactory {
    static volatile CoinMarketRequestFactory coinMarketRequestFactory;

    public static CoinMarketRequestFactory getInstance() {
        if (coinMarketRequestFactory == null) {
            synchronized (VegaRequestFactory.class) {
                if (coinMarketRequestFactory == null) {
                    coinMarketRequestFactory = new CoinMarketRequestFactory();
                }
            }
        }
        return coinMarketRequestFactory;
    }

    public enum CoinRequestType {
        ALL_COIN_REQUEST("ticker"),
        GLOBAL_MARKET_CAP("global"),
        HOME_REQUEST1("other_app1.json");
        private String text;

        private CoinRequestType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    @Override
    public void init() {

    }
    public FaRequest getRequest(CoinRequestType demoRequestType) {
        return new BaseRequest().path(demoRequestType.toString());
    }
}
