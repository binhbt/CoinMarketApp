package com.coin.market.event;

/**
 * Created by t430 on 1/14/2018.
 */

public class SearchNotify {
    private String query;

    public SearchNotify(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
