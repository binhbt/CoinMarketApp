package com.coin.market.main;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.coin.market.BaseActivity;
import com.coin.market.Const;
import com.coin.market.R;
import com.coin.market.event.NotifyEvent;
import com.coin.market.event.SearchNotify;
import com.coin.market.global.GlobalDataFragment;
import com.coin.market.home.HomeFragment;
import com.coin.market.model.CurrentcyEnum;
import com.coin.market.model.PerCenTageChangeEnum;
import com.coin.market.model.SortByEnum;
import com.coin.market.rank.RankFragment;
import com.coin.market.shared.MemoryShared;
import com.vn.fa.base.util.SharedPrefsUtils;

public class MainActivity extends BaseActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {
    private Menu menu;
    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter = new MainPresenter();
        super.initView(savedInstanceState);
        testRequest();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        loadCurrentSettings();
        ((MainPresenter)presenter).loadAllFavouriteAltcoin();
        showFragment(new HomeFragment(), null, R.id.main_content);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    private void testRequest(){

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);

        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Detect SearchView icon clicks
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemsVisibility(menu, searchItem, false);
            }
        });
        // Detect SearchView close
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setItemsVisibility(menu, searchItem, true);
                return false;
            }
        });
        searchView.findViewById(R.id.search_src_text).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                setItemsVisibility(menu, searchItem, !b);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                sendEvent(new SearchNotify(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                sendEvent(new SearchNotify(query));
                return true;
            }

        });
        updateSettingView();
        return true;
    }
    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort) {
            showSortDialog();
            return true;
        }
        if (id == R.id.action_change_time) {
            showTimeOrderDialog();
            return true;
        }
        if (id == R.id.action_change_currentcy) {
            showCurrentcyDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            showFragment(new HomeFragment(), null, R.id.main_content);
            // Handle the camera action
        } else if (id == R.id.nav_rank) {
            showFragment(new RankFragment(), null, R.id.main_content);
        } else if (id == R.id.nav_global) {
            showFragment(new GlobalDataFragment(), null, R.id.main_content);

        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_send) {
            rateApp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void showSortDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //10
        final CharSequence[] array = {"Name", "Price", "Market Cap", "Change", "Volumn 24h", "Name LH", "Price LH","Market Cap LH", "Change LH", "Volumn 24h LH"};
        int current = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.SORT_BY, 0);

        builder.setTitle("Sort By")
                .setSingleChoiceItems(array, current, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPrefsUtils.setIntegerPreference(MainActivity.this, Const.SORT_BY, which);
                        loadCurrentSettings();
                        updateSettingView();
                        sendEvent(new NotifyEvent(NotifyEvent.Type.CHANGE_SORT_SETTING));
                    }
                });

        builder.create().show();
    }
    public void showTimeOrderDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //3
        final CharSequence[] array = {"1h", "24h", "7d"};
        int current = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.PERCENTAGE_CHANGED, 0);

        builder.setTitle("Percentage Change(%)")
                .setSingleChoiceItems(array, current, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPrefsUtils.setIntegerPreference(MainActivity.this, Const.PERCENTAGE_CHANGED, which);
                        loadCurrentSettings();
                        updateSettingView();
                        sendEvent(new NotifyEvent(NotifyEvent.Type.CHANGE_SETTING));

                    }
                });

        builder.create().show();
    }
    public void showCurrentcyDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //2
        final CharSequence[] array = {"USD - U.S.Dollar", "BTC - BitCoin"};
        int current = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.CURRENTCY, 0);

        builder.setTitle("Default Currentcy")
                .setSingleChoiceItems(array, current, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPrefsUtils.setIntegerPreference(MainActivity.this, Const.CURRENTCY, which);
                        loadCurrentSettings();
                        updateSettingView();
                        sendEvent(new NotifyEvent(NotifyEvent.Type.CHANGE_SETTING));
                    }
                });

        builder.create().show();
    }
    private void loadCurrentSettings(){
        int sort = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.SORT_BY, SortByEnum.MARKETCAP.ordinal());
        int percentageChange = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.PERCENTAGE_CHANGED, 0);
        int currentCy = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.CURRENTCY, 0);
        MemoryShared.getSharedInstance().setSettingSort(sort);
        MemoryShared.getSharedInstance().setSettingPercentageChanged(percentageChange);
        MemoryShared.getSharedInstance().setSettingCurrentCy(currentCy);
    }
    private void updateSettingView(){
        final MenuItem timeChange = menu.findItem(R.id.action_change_time);
        if (MemoryShared.getSharedInstance().getSettingPercentageChanged() == PerCenTageChangeEnum.DAY.ordinal()){
            timeChange.setTitle("24h");
        }
        if (MemoryShared.getSharedInstance().getSettingPercentageChanged() == PerCenTageChangeEnum.HOUR.ordinal()){
            timeChange.setTitle("1h");
        }
        if (MemoryShared.getSharedInstance().getSettingPercentageChanged() == PerCenTageChangeEnum.WEEK.ordinal()){
            timeChange.setTitle("7d");
        }
        final MenuItem currentcy = menu.findItem(R.id.action_change_currentcy);
        if (MemoryShared.getSharedInstance().getSettingCurrentCy() == CurrentcyEnum.BTC.ordinal()){
            currentcy.setTitle("฿");
            currentcy.setIcon(R.drawable.bitcoin);
        }
        if (MemoryShared.getSharedInstance().getSettingCurrentCy() == CurrentcyEnum.USD.ordinal()){
            currentcy.setTitle("$");
            currentcy.setIcon(R.drawable.ic_monetization_on_white_24dp);

        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void handleEvent(Object event) {
        super.handleEvent(event);
        if (event instanceof NotifyEvent){
            final MenuItem search = menu.findItem(R.id.action_search);
            final SearchView searchView = (SearchView) search.getActionView();

            NotifyEvent evt =(NotifyEvent) event;
            if (evt.getType() == NotifyEvent.Type.SHOW_SEARCH){
                search.setVisible(true);
                searchView.setVisibility(View.VISIBLE);
            }
            if (evt.getType() == NotifyEvent.Type.HIDE_SEARCH){
                search.setVisible(false);
                searchView.setVisibility(View.GONE);
                searchView.clearFocus();
            }
        }
    }
    public void settitleBar(String title){
        getSupportActionBar().setTitle(title);
    }
    public void changeToolBarForRank(){
        if (menu == null) return;
        final MenuItem search = menu.findItem(R.id.action_search);
        final MenuItem sort = menu.findItem(R.id.action_sort);
        search.setVisible(false);
        sort.setVisible(false);
    }
    public void showAllToolBar(){
        if (menu == null) return;
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            item.setVisible(true);
        }
    }
    public void hideAllToolBar(){
        if (menu == null) return;
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            item.setVisible(false);
        }
    }
    private void shareApp(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    private void rateApp(){
        Uri uri = Uri.parse("market://details?id=" + getApplication().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplication().getPackageName())));
        }
    }
}
