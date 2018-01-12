package com.coin.market.model;

import com.coin.market.viewmodel.AllCoinHolderView;
import com.vn.fa.adapter.multipleviewtype.DataBinder;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;

import io.realm.RealmObject;

/**
 * Created by leobui on 1/10/2018.
 */

public class AltCoin implements IViewBinder{
    public enum Type{
        ALL_COIN,
        FAVOURITE_COIN
    }
    private Type type;
    private String id;
    private String name;
    private String symbol;
    private int rank;
    private double price_usd;
    private double price_btc;
    private double d_volume_usd;
    private long market_cap_usd;
    private double available_supply;
    private double total_supply;
    private double max_supply;
    private float percent_change_1h;
    private float percent_change_24h;
    private float percent_change_7d;
    private long last_updated;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }

    public double getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(double price_btc) {
        this.price_btc = price_btc;
    }

    public double getD_volume_usd() {
        return d_volume_usd;
    }

    public void setD_volume_usd(double d_volume_usd) {
        this.d_volume_usd = d_volume_usd;
    }

    public long getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(long market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public double getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(double available_supply) {
        this.available_supply = available_supply;
    }

    public double getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(double total_supply) {
        this.total_supply = total_supply;
    }

    public double getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(double max_supply) {
        this.max_supply = max_supply;
    }

    public float getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(float percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public float getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public float getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(float percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public long getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(long last_updated) {
        this.last_updated = last_updated;
    }

    @Override
    public DataBinder getViewBinder() {
        return new AllCoinHolderView(this).itemViewType(1);
    }
}
