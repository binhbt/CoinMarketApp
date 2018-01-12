package com.coin.market.viewmodel;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coin.market.R;
import com.coin.market.model.AltCoin;
import com.coin.market.model.CurrentcyEnum;
import com.coin.market.shared.MemoryShared;
import com.coin.market.util.Util;
import com.fa.loader.widget.FAImageView;
import com.vn.fa.adapter.multipleviewtype.BinderViewHolder;
import com.vn.fa.base.holder.VegaBinderView;
import com.vn.fa.base.holder.VegaViewHolder;

import butterknife.Bind;

/**
 * Created by t430 on 1/9/2018.
 */

public class FavCoinHolderView  extends VegaBinderView<AltCoin> {
    public FavCoinHolderView(AltCoin data) {
        super(data);
    }
    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        FavCoinHolderView.PhotoViewHolder holder1 = (FavCoinHolderView.PhotoViewHolder) holder;
        holder1.imgThumb
                .callback(new FAImageView.Callback() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Toast.makeText(holder1.imgThumb.getContext(), "Error", Toast.LENGTH_LONG).show();
                        holder1.imgThumb.setImageResource(R.drawable.default_coin_img_placeholder);
                    }
                })
                .placeholder(R.drawable.default_coin_img_placeholder)
                .error(R.drawable.default_coin_img_placeholder)
                .circle(true)
                //.cornerRadius(50f)
                //.borderColor(R.color.colorAccent)
                //.border(3)
                .loadImage(Util.buildThumb(data.getId()));
        holder1.txtNumber.setText(data.getRank()+"");
        holder1.txtName.setText(data.getName()+"("+data.getSymbol()+")");
        double price = data.getPrice_usd();
        String currentCyUnit ="$";
        if (MemoryShared.getsharedInstance().getSettingCurrentCy() == CurrentcyEnum.BTC.ordinal()){
            price = data.getPrice_btc();
            currentCyUnit ="𝕭";// "Ɓ";
        }
        holder1.txtPrice.setText(currentCyUnit+Util.getCurrentcyFormat(price));
        holder1.txtmarketCap.setText(data.getMarket_cap_usd()+"");
        float change = data.getPercent_change_24h();
        if (change <0){
            holder1.txtChange.setTextColor(holder1.txtChange.getContext().getResources().getColor(R.color.red));
        }else{
            holder1.txtChange.setTextColor(holder1.txtChange.getContext().getResources().getColor(R.color.green));
        }
        holder1.txtChange.setText(data.getPercent_change_24h()+"");
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder1.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder1.btnFav.setBackgroundResource(R.drawable.ic_star_fill);
            }
        });
        holder1.viewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder1.btnFav.setBackgroundResource(R.drawable.ic_star_fill);
            }
        });
    }


    @Override
    public BinderViewHolder newHolder(View parent) {
        return new FavCoinHolderView.PhotoViewHolder(parent);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_all_coin;
    }

    public class PhotoViewHolder extends VegaViewHolder {
        @Bind(R.id.thumb)
        FAImageView imgThumb;
        @Bind(R.id.txt_name)
        TextView txtName;
        @Bind(R.id.txt_number)
        TextView txtNumber;
        @Bind(R.id.txt_price)
        TextView txtPrice;
        @Bind(R.id.txt_market_cap)
        TextView txtmarketCap;
        @Bind(R.id.txt_change)
        TextView txtChange;
        @Bind(R.id.btn_fav)
        Button btnFav;
        @Bind(R.id.view_fav)
        View viewFav;
        public PhotoViewHolder(View view) {
            super(view);
        }
    }
}
