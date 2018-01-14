package com.coin.market.sort;

import com.coin.market.model.AltCoin;
import com.coin.market.model.CurrentcyEnum;
import com.coin.market.model.PerCenTageChangeEnum;
import com.coin.market.model.SortByEnum;
import com.coin.market.shared.MemoryShared;

import java.util.Comparator;

/**
 * Created by t430 on 1/14/2018.
 */

public class CustomComparator implements Comparator<AltCoin> {
    @Override
    public int compare(AltCoin o1, AltCoin o2) {
        int sortMode = MemoryShared.getSharedInstance().getSettingSort();
        if (sortMode == SortByEnum.NAME.ordinal()){
            return o1.getName().compareTo(o2.getName());
        }
        if (sortMode == SortByEnum.NAMELH.ordinal()){
            return o2.getName().compareTo(o1.getName());
        }
        if (sortMode == SortByEnum.PRICE.ordinal()){
            int priceMode = MemoryShared.getSharedInstance().getSettingCurrentCy();
            if (priceMode == CurrentcyEnum.USD.ordinal()){
                return o2.getPrice_usd() - o1.getPrice_usd() >0?1:-1;
            }
            if (priceMode == CurrentcyEnum.BTC.ordinal()){
                return o2.getPrice_btc() - o1.getPrice_btc() >0?1:-1;
            }
            return o2.getPrice_usd() - o1.getPrice_usd() >0?1:-1;

        }
        if (sortMode == SortByEnum.PRICELH.ordinal()){
            int priceMode = MemoryShared.getSharedInstance().getSettingCurrentCy();
            if (priceMode == CurrentcyEnum.USD.ordinal()){
                return o1.getPrice_usd() - o2.getPrice_usd() >0?1:-1;
            }
            if (priceMode == CurrentcyEnum.BTC.ordinal()){
                return o1.getPrice_btc() - o2.getPrice_btc() >0?1:-1;
            }
            return o1.getPrice_usd() - o2.getPrice_usd() >0?1:-1;

        }

        if (sortMode == SortByEnum.MARKETCAP.ordinal()){
            return o2.getMarket_cap_usd() - o1.getMarket_cap_usd() >0?1:-1;
        }
        if (sortMode == SortByEnum.MARKETCAPLH.ordinal()){
            return o1.getMarket_cap_usd() - o2.getMarket_cap_usd() >0?1:-1;
        }

        if (sortMode == SortByEnum.CHANGE.ordinal()){
            int changeMode = MemoryShared.getSharedInstance().getSettingPercentageChanged();
            if (changeMode == PerCenTageChangeEnum.HOUR.ordinal()){
                return o2.getPercent_change_1h() - o1.getPercent_change_1h() >0?1:-1;
            }
            if (changeMode == PerCenTageChangeEnum.DAY.ordinal()){
                return o2.getPercent_change_24h() - o1.getPercent_change_24h() >0?1:-1;
            }
            if (changeMode == PerCenTageChangeEnum.WEEK.ordinal()){
                return o2.getPercent_change_7d() - o1.getPercent_change_7d() >0?1:-1;
            }
            return o2.getPercent_change_24h() - o1.getPercent_change_24h() >0?1:-1;

        }
        if (sortMode == SortByEnum.CHANGELH.ordinal()){
            int changeMode = MemoryShared.getSharedInstance().getSettingPercentageChanged();
            if (changeMode == PerCenTageChangeEnum.HOUR.ordinal()){
                return o1.getPercent_change_1h() - o2.getPercent_change_1h() >0?1:-1;
            }
            if (changeMode == PerCenTageChangeEnum.DAY.ordinal()){
                return o1.getPercent_change_24h() - o2.getPercent_change_24h() >0?1:-1;
            }
            if (changeMode == PerCenTageChangeEnum.WEEK.ordinal()){
                return o1.getPercent_change_7d() - o2.getPercent_change_7d() >0?1:-1;
            }
            return o1.getPercent_change_24h() - o2.getPercent_change_24h() >0?1:-1;

        }

        if (sortMode == SortByEnum.VOLUMN24H.ordinal()){
            return o2.getD_volume_usd() - o1.getD_volume_usd() >0?1:-1;
        }
        if (sortMode == SortByEnum.VOLUMN24HLH.ordinal()){
            return o1.getD_volume_usd() - o2.getD_volume_usd() >0?1:-1;
        }


        return o2.getMarket_cap_usd() - o1.getMarket_cap_usd() >0?1:-1;
    }
}