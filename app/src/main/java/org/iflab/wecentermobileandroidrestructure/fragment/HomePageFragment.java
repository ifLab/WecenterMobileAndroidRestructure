package org.iflab.wecentermobileandroidrestructure.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.melnykov.fab.FloatingActionButton;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.activity.PublishAnswerArticle;
import org.iflab.wecentermobileandroidrestructure.adapter.HomePageAdapter;

/**
 * Created by hcjcch on 15/5/28.
 */
public class HomePageFragment extends Fragment {
    private ListView listHomepage;
    private FloatingActionButton fab;
    private SwipeRefreshLayout refreshLayout;

    public static HomePageFragment newInstances() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_homepage, container, false);
        findViews(relativeLayout);
        setViews();
        setListeners();
        return relativeLayout;
    }

    private void findViews(View view) {
        listHomepage = (ListView) view.findViewById(R.id.list_homepage);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
    }

    private void setViews() {
        listHomepage.setAdapter(new HomePageAdapter(getActivity().getApplicationContext()));
        fab.attachToListView(listHomepage);
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), PublishAnswerArticle.class));
            }
        });
    }

    private void setListeners() {

    }
}