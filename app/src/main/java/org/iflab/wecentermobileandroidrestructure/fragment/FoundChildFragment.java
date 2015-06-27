package org.iflab.wecentermobileandroidrestructure.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.iflab.wecentermobileandroidrestructure.R;

/**
 * Created by hcjcch on 15/6/22.
 */
public class FoundChildFragment extends BaseFragment {
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
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_found_child,container,false);
        return relativeLayout;
    }
}
