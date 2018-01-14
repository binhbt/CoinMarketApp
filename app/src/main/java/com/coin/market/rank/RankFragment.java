package com.coin.market.rank;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.coin.market.BaseFragment;
import com.coin.market.R;
import com.coin.market.event.NotifyEvent;
import com.coin.market.home.HomeFragment;
import com.coin.market.home.all.AllCoinFargment;
import com.coin.market.home.fav.FavouriteFragment;
import com.coin.market.main.MainActivity;
import com.coin.market.rank.heroes.HeroesFragment;
import com.coin.market.rank.zeroes.ZeroesFragment;

/**
 * Created by t430 on 1/14/2018.
 */

public class RankFragment  extends BaseFragment {
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        viewPager.setAdapter(new RankFragment.SampleFragmentPagerAdapter(getChildFragmentManager()));
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position ==0){
                    //sendEvent(new NotifyEvent(NotifyEvent.Type.SHOW_SEARCH));
                }
                if (position == 1){
                    //sendEvent(new NotifyEvent(NotifyEvent.Type.HIDE_SEARCH));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (getActivity() != null){
            ((MainActivity)getActivity()).setTitle("Top 100");
            ((MainActivity)getActivity()).changeToolBarForRank();

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 2;
        private String tabTitles[] = new String[] { "Heros", "Zeroes"};

        public SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if (position ==1) return  new ZeroesFragment();
            return new HeroesFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}
