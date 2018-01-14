package com.coin.market.event;

/**
 * Created by t430 on 1/13/2018.
 */

public class NotifyEvent {
    public enum Type {
        ALL_REFRESH,
        FAV_REFRESH,
        FAV_DELETE,
        CHANGE_SETTING,
        CHANGE_SORT_SETTING,
        SHOW_SEARCH,
        HIDE_SEARCH
    }
    private Type type;
    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public NotifyEvent(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
