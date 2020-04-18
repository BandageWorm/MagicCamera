package com.seu.magiccamera.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.magiccamera.R;
import com.seu.magiccamera.activity.CameraActivity;
import com.seu.magiccamera.adapter.FilterAdapter;
import com.seu.magicfilter.MagicEngine;
import com.seu.magicfilter.filter.helper.MagicFilterType;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ImageEditFilterFragment extends ImageEditBaseFragment {

    public ImageEditFilterFragment(Context context, MagicEngine engine) {
        super(context);
        mEngine = engine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_edit_filter, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = getView().findViewById(R.id.filter_listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        FilterAdapter adapter = new FilterAdapter(mContext, CameraActivity.types);
        adapter.setOnFilterChangeListener(new FilterAdapter.onFilterChangeListener() {
            @Override
            public void onFilterChanged(MagicFilterType filterType) {
                mEngine.setFilter(filterType);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {

    }

    @Override
    protected boolean isChanged() {

        return false;
    }
}
