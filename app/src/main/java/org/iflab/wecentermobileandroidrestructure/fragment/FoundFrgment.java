package org.iflab.wecentermobileandroidrestructure.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.ui.PagerSlidingTabStrip;

/**
 * Created by hcjcch on 15/6/21.
 */
public class FoundFrgment extends BaseFragment {

    private PagerSlidingTabStrip tabs;
    private ViewPager viewPager;
    private DisplayMetrics dm;
    private static String[] titles = {"推荐", "热门", "最新", "待答"};

    public static FoundFrgment newInstances() {
        FoundFrgment foundFrgment = new FoundFrgment();
        Bundle bundle = new Bundle();
        foundFrgment.setArguments(bundle);
        return foundFrgment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_found, container, false);
        dm = getResources().getDisplayMetrics();
        findView(relativeLayout);
        viewPager.setAdapter(new Adapter(getActivity().getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
        setTabsValue();
        return relativeLayout;
    }

    private void findView(RelativeLayout relativeLayout) {
        viewPager = (ViewPager) relativeLayout.findViewById(R.id.viewpager);
        tabs = (PagerSlidingTabStrip) relativeLayout.findViewById(R.id.tabs);
    }

    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        tabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        tabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        tabs.setTextColor(getActivity().getResources().getColor(R.color.text_color_grey));
        // 设置Tab Indicator的颜色
        tabs.setIndicatorColor(Color.parseColor("#50000000"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        tabs.setSelectedTextColor(Color.parseColor("#234564"));
        // 取消点击Tab时的背景色
        tabs.setTextColor(Color.parseColor("#ffffff"));
        tabs.setTabBackground(0);
    }

    class Adapter extends FragmentStatePagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FoundChildFragment childFragment = FoundChildFragment.newInstances(position);
            return childFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            FoundChildFragment fragment = (FoundChildFragment) super.instantiateItem(container, position);
            return fragment;
        }
    }
}