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

public class RankComparator implements Comparator<AltCoin> {
    @Override
    public int compare(AltCoin o1, AltCoin o2) {
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
}