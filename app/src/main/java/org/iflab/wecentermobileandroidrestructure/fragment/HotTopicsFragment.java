package org.iflab.wecentermobileandroidrestructure.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.iflab.wecentermobileandroidrestructure.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * HotTopics
 */
public class HotTopicsFragment extends BaseFragment {


    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    public static HotTopicsFragment hotTopicsFragment;

    public HotTopicsFragment() {}

    public static HotTopicsFragment newInstance(){
        if(hotTopicsFragment == null){
            hotTopicsFragment = new HotTopicsFragment();
        }
        return hotTopicsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hot_topics, container, false);
        ButterKnife.bind(this,view);
        viewPager.setAdapter(new SectionPagerAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HotTopicChildFragment.newInstance(HotTopicChildFragment.WEEK);
                case 1:
                    return HotTopicChildFragment.newInstance(HotTopicChildFragment.MONTH);
                case 2:
                    return HotTopicChildFragment.newInstance(HotTopicChildFragment.SELF);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "一周话题";
                case 1:
                    return "一月话题";
                case 2:
                    return "我关注的";
                default:
                    return "";
            }
        }
    }


}
