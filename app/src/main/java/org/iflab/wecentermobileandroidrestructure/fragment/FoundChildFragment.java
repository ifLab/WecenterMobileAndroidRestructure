package org.iflab.wecentermobileandroidrestructure.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.iflab.wecentermobileandroidrestructure.R;
import org.iflab.wecentermobileandroidrestructure.adapter.FoundAdapter;

/**
 * Created by hcjcch on 15/6/22.
 */
public class FoundChildFragment extends BaseFragment {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    public static FoundChildFragment newInstances(int id) {
        FoundChildFragment foundChildFragment = new FoundChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", id);
        foundChildFragment.setArguments(bundle);
        return foundChildFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_found_child, container, false);
        findViews(relativeLayout);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        FoundAdapter adapter = new FoundAdapter(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return relativeLayout;
    }

    private void findViews(RelativeLayout relativeLayout) {
        refreshLayout = (SwipeRefreshLayout) relativeLayout.findViewById(R.id.swipyrefreshlayout);
        recyclerView = (RecyclerView) relativeLayout.findViewById(R.id.list_found);
    }
}
