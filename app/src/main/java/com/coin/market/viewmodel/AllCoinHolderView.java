package com.coin.market.viewmodel;

import android.view.View;
import android.widget.TextView;

import com.coin.market.CoinMarketApplication;
import com.coin.market.R;
import com.coin.market.event.NotifyEvent;
import com.coin.market.model.AltCoin;
import com.coin.market.model.CurrentcyEnum;
import com.coin.market.model.PerCenTageChangeEnum;
import com.coin.market.shared.MemoryShared;
import com.coin.market.util.Util;
import com.fa.loader.widget.FAImageView;
import com.varunest.sparkbutton.SparkButton;
import com.vn.fa.adapter.multipleviewtype.BinderViewHolder;
import com.vn.fa.base.holder.VegaBinderView;
import com.vn.fa.base.holder.VegaViewHolder;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by t430 on 1/9/2018.
 */

public class AllCoinHolderView extends VegaBinderView<AltCoin> {
    public AllCoinHolderView(AltCoin data) {
        super(data);
    }
    @Override
    public void bindViewHolder(BinderViewHolder holder, int position) {
        PhotoViewHolder holder1 = (PhotoViewHolder) holder;
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
        double marketcap = data.getMarket_cap_usd();
        String currentCyUnit ="$";
        if (MemoryShared.getSharedInstance().getSettingCurrentCy() == CurrentcyEnum.BTC.ordinal()){
            price = data.getPrice_btc();
            currentCyUnit ="฿";// "Ɓ";"𝕭"
        }
        holder1.txtPrice.setText(currentCyUnit+Util.getCurrentcyFormat(price));
        holder1.txtmarketCap.setText("$"+Util.getCurrentcyFormat(marketcap));
        float change = data.getPercent_change_24h();
        if (MemoryShared.getSharedInstance().getSettingPercentageChanged()
                == PerCenTageChangeEnum.HOUR.ordinal()){
            change = data.getPercent_change_1h();
        }
        if (MemoryShared.getSharedInstance().getSettingPercentageChanged()
                == PerCenTageChangeEnum.WEEK.ordinal()){
            change = data.getPercent_change_7d();
        }
        if (change <0){
            holder1.txtChange.setTextColor(holder1.txtChange.getContext().getResources().getColor(R.color.red));
        }else{
            holder1.txtChange.setTextColor(holder1.txtChange.getContext().getResources().getColor(R.color.green));
        }
        if (data.isFavourite()){
            holder1.btnFav.setChecked(true);
            //holder1.btnFav.setBackgroundResource(R.drawable.ic_star_fill);
        }else{
            holder1.btnFav.setChecked(false);
            //holder1.btnFav.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
        }
        holder1.txtChange.setSelected(true);
        holder1.txtmarketCap.setSelected(true);
        holder1.txtChange.setText(Util.getChangeString(change));
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        View.OnClickListener itemClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!data.isFavourite()) {
                    holder1.btnFav.setChecked(true);
                    holder1.btnFav.playAnimation();
                    //holder1.btnFav.setBackgroundResource(R.drawable.ic_star_fill);
                    data.setFavourite(true);
                    CoinMarketApplication.realm.beginTransaction();
                    CoinMarketApplication.realm.copyToRealm(data);
                    CoinMarketApplication.realm.commitTransaction();
                }else{
                    //holder1.btnFav.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
                    holder1.btnFav.setChecked(false);
                    data.setFavourite(false);
                    CoinMarketApplication.realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<AltCoin> result = realm.where(AltCoin.class).equalTo("id",data.getId()).findAll();
                            result.deleteAllFromRealm();
                        }
                    });
                }
                sendEvent(new NotifyEvent(NotifyEvent.Type.FAV_REFRESH));
            }
        };
        holder1.btnFav.setOnClickListener(itemClick);
        holder1.viewFav.setOnClickListener(itemClick);
    }


    @Override
    public BinderViewHolder newHolder(View parent) {
        return new PhotoViewHolder(parent);
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
        SparkButton btnFav;
        @Bind(R.id.view_fav)
        View viewFav;
        public PhotoViewHolder(View view) {
            super(view);
        }
    }
}
