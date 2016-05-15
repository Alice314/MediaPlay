package com.wusui.mediaplay.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wusui.mediaplay.R;

/**
 * Created by fg on 2016/5/14.
 */
public class WesternFragment extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        super.onCreateView(inflater, container, savedInstance);
        View WesternView = inflater.inflate(R.layout.fragment_western, container,false);


        return WesternView;

    }
}
