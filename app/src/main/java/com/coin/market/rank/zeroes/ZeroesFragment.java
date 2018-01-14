package com.coin.market.rank.zeroes;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;

import com.coin.market.BaseFragment;
import com.coin.market.R;
import com.coin.market.event.NotifyEvent;
import com.coin.market.home.all.AllCoinView;
import com.coin.market.model.AltCoin;
import com.coin.market.shared.MemoryShared;
import com.coin.market.sort.BadComparator;
import com.coin.market.sort.RankComparator;
import com.vn.fa.adapter.multipleviewtype.IViewBinder;
import com.vn.fa.base.adapter.FaAdapter;
import com.vn.fa.widget.RecyclerViewWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * Created by t430 on 1/14/2018.
 */

public class ZeroesFragment extends BaseFragment implements AllCoinView,
        SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.recyclerview)RecyclerViewWrapper recyclerViewWrapper;
    private FaAdapter faAdapter;
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_divider));
        recyclerViewWrapper.addItemDecoration(itemDecorator);
        recyclerViewWrapper.setRefreshListener(this);
        upDateSort();
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
    public void loadDataToView(List<Object> coinList) {
        if (faAdapter == null){
            faAdapter = new FaAdapter();
            recyclerViewWrapper.setAdapter(faAdapter);
        }
        if (coinList != null && coinList.size() >0) {
            faAdapter.clear();
            List<IViewBinder> viewBinders = (List<IViewBinder>) (List) coinList;
            faAdapter.addAllDataObject(viewBinders);
        }
    }

    @Override
    public void onRefresh() {
        upDateSort();
    }


    @Override
    public void handleEvent(Object event) {
        super.handleEvent(event);
        if (event instanceof NotifyEvent){
            NotifyEvent evt = (NotifyEvent)event;
            if (evt.getType() == NotifyEvent.Type.CHANGE_SETTING){
                upDateSort();
            }
            if (evt.getType() == NotifyEvent.Type.CHANGE_SORT_SETTING){
                upDateSort();
            }
            if (evt.getType() == NotifyEvent.Type.ALL_REFRESH){
                onRefresh();
            }
        }

    }
    private void upDateSort(){
        List<AltCoin> all = MemoryShared.getSharedInstance().getAltCoinList();
        if (faAdapter == null){
            faAdapter = new FaAdapter();
            recyclerViewWrapper.setAdapter(faAdapter);
        }
        if (all != null && all.size() >0 && MemoryShared.getSharedInstance().getGlobalMarketCap() != null) {
            faAdapter.clear();
            Collections.sort(all, new BadComparator());
            List<AltCoin> sub100 = all.subList(0, 100);
            List<IViewBinder> viewBinders = (List<IViewBinder>) (List) sub100;
            faAdapter.addAllDataObject(viewBinders);
        }
    }


}
