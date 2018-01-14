package com.coin.market.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.coin.market.BaseActivity;
import com.coin.market.CoinMarketApplication;
import com.coin.market.Const;
import com.coin.market.R;
import com.coin.market.event.NotifyEvent;
import com.coin.market.model.AltCoin;
import com.coin.market.util.Util;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by t430 on 1/13/2018.
 */

public class DetailActivity extends BaseActivity{
    private AltCoin altCoin;
    private Menu menu;
    @Bind(R.id.main_webview)WebView webview;
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (getIntent().getExtras() != null){
            altCoin = (AltCoin) getIntent().getExtras().getSerializable(Const.ALT_COIN_DATA);
            if (altCoin != null){
                settitleBar(altCoin.getName()+"("+altCoin.getSymbol()+")");
                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                // Force links and redirects to open in the WebView instead of in a browser
                webview.setWebViewClient(new WebViewClient());
                webview.loadUrl(Util.buildDetail(altCoin.getId()));
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        this.menu = menu;
        final MenuItem favItem = menu.findItem(R.id.action_fav);
        if (altCoin.isFavourite()){
            favItem.setIcon(R.drawable.ic_star_on);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_fav) {
            favClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean isToolBar() {
        return true;
    }

    @Override
    protected boolean isBackEnabled() {
        return true;
    }
    public void settitleBar(String title){
        getSupportActionBar().setTitle(title);
    }
    private void favClick(){
        final MenuItem favItem = menu.findItem(R.id.action_fav);
        if (!altCoin.isFavourite()) {
            favItem.setIcon(R.drawable.ic_star_on);
            //holder1.btnFav.setBackgroundResource(R.drawable.ic_star_fill);
            altCoin.setFavourite(true);
            CoinMarketApplication.realm.beginTransaction();
            CoinMarketApplication.realm.copyToRealm(altCoin);
            CoinMarketApplication.realm.commitTransaction();
        }else{
            //holder1.btnFav.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
            favItem.setIcon(R.drawable.ic_star_off);
            altCoin.setFavourite(false);
            CoinMarketApplication.realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<AltCoin> result = realm.where(AltCoin.class).equalTo("id",altCoin.getId()).findAll();
                    result.deleteAllFromRealm();
                }
            });
        }
        sendEvent(new NotifyEvent(NotifyEvent.Type.FAV_REFRESH));
        sendEvent(new NotifyEvent(NotifyEvent.Type.ALL_REFRESH));

    }
    @Override
    public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
