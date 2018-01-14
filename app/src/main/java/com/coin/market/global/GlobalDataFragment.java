package com.coin.market.global;

import android.os.Bundle;
import android.widget.TextView;

import com.coin.market.BaseFragment;
import com.coin.market.R;
import com.coin.market.main.MainActivity;
import com.coin.market.model.GlobalMarketCap;
import com.coin.market.shared.MemoryShared;
import com.coin.market.util.Util;

import butterknife.Bind;

/**
 * Created by t430 on 1/14/2018.
 */

public class GlobalDataFragment extends BaseFragment{
    @Bind(R.id.txt_total)TextView txtTotal;
    @Bind(R.id.txt_total_24_volumn)TextView txtTotal24vol;
    @Bind(R.id.txt_btc_percent)TextView txtBtcPercent;
    @Bind(R.id.txt_active_currentcy)TextView txtActiveCurrentcy;
    @Bind(R.id.txt_active_assets)TextView txtActiveAssets;
    @Bind(R.id.txt_active_market)TextView txtActiveMarket;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (getActivity() != null){
            ((MainActivity)getActivity()).setTitle("Global Data");
            ((MainActivity)getActivity()).hideAllToolBar();
        }
        GlobalMarketCap globalMarketCap = MemoryShared.getSharedInstance().getGlobalMarketCap();
        if (globalMarketCap !=null){
            txtTotal.setText("$"+Util.getNumberFormat(globalMarketCap.getTotal_market_cap_usd()));
            txtTotal24vol.setText("$"+Util.getNumberFormat(globalMarketCap.getTotal_24h_volume_usd()));
            txtBtcPercent.setText(Util.formatDouble(globalMarketCap.getBitcoin_percentage_of_market_cap())+"%");
            txtActiveCurrentcy.setText(Util.getNumberFormat(globalMarketCap.getActive_currencies()));
            txtActiveAssets.setText(Util.getNumberFormat(globalMarketCap.getActive_assets()));
            txtActiveMarket.setText(Util.getNumberFormat(globalMarketCap.getActive_markets()));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_global_data;
    }
}
