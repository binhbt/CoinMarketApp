package com.coin.market.home.all;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;

import com.coin.market.BaseFragment;
import com.coin.market.R;
import com.coin.market.model.AltCoin;
import com.coin.market.model.GlobalMarketCap;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;
import com.vn.fa.base.adapter.FaAdapter;
import com.vn.fa.widget.RecyclerViewWrapper;

import java.util.List;

import butterknife.Bind;

/**
 * Created by t430 on 1/7/2018.
 */

public class AllCoinFargment extends BaseFragment implements AllCoinView,
        SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.recyclerview)RecyclerViewWrapper recyclerViewWrapper;
    private FaAdapter faAdapter;
    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter = new AllCoinPresenter();
        super.initView(savedInstanceState);
        faAdapter = new FaAdapter();
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_divider));
        recyclerViewWrapper.addItemDecoration(itemDecorator);
        recyclerViewWrapper.setRefreshListener(this);
        ((AllCoinPresenter)presenter).loadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_coin;
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public void loadDataToView(List<AltCoin> coinList) {
        List<IViewBinder> viewBinders = (List<IViewBinder>) (List) coinList;
        GlobalMarketCap globalMarketCap = new GlobalMarketCap();
        faAdapter.addDataObject((IViewBinder)globalMarketCap);
        faAdapter.addAllDataObject(viewBinders);
        recyclerViewWrapper.setAdapter(faAdapter);
    }

    @Override
    public void onRefresh() {
        ((AllCoinPresenter)presenter).loadData();
    }
}
