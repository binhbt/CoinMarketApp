package com.coin.market.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import com.coin.market.data.net.BaseRequest;
import com.coin.market.shared.MemoryShared;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.data.net.FaRequest;
import com.vn.fa.base.util.SharedPrefsUtils;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void initView(Bundle savedInstanceState) {
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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

                    }
                });

        builder.create().show();
    }
    private void loadCurrentSettings(){
        int sort = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.SORT_BY, 0);
        int percentageChange = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.PERCENTAGE_CHANGED, 0);
        int currentCy = SharedPrefsUtils.getIntegerPreference(MainActivity.this, Const.CURRENTCY, 0);
        MemoryShared.getsharedInstance().setSettingSort(sort);
        MemoryShared.getsharedInstance().setSettingPercentageChanged(percentageChange);
        MemoryShared.getsharedInstance().setSettingCurrentCy(currentCy);

    }
}
