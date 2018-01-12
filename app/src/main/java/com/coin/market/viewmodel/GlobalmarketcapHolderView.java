package com.coin.market.viewmodel;

import android.view.View;
import android.widget.TextView;

import com.coin.market.R;
import com.coin.market.model.AltCoin;
import com.coin.market.model.GlobalMarketCap;
import com.vn.fa.adapter.multipleviewtype.BinderViewHolder;
import com.vn.fa.base.holder.VegaBinderView;
import com.vn.fa.base.holder.VegaViewHolder;

import butterknife.Bind;

/**
 * Created by t430 on 1/11/2018.
 */

public class GlobalmarketcapHolderView extends VegaBinderView<GlobalMarketCap> {
    public GlobalmarketcapHolderView(GlobalMarketCap data) {
        super(data);
    }
    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        GlobalmarketcapHolderView.ViewHolder holder1 = (GlobalmarketcapHolderView.ViewHolder) holder;

        //holder1.title.setText(data.getName());
    }


    @Override
    public BinderViewHolder newHolder(View parent) {
        return new GlobalmarketcapHolderView.ViewHolder(parent);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_global_market_cap;
    }

    public class ViewHolder extends VegaViewHolder {
        @Bind(R.id.txt_global_market_cap)
        TextView txtGlobalMarketCap;

        public ViewHolder(View view) {
            super(view);
        }
    }
}
