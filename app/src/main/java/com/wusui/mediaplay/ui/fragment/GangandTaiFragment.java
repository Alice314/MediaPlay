package com.wusui.mediaplay.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wusui.mediaplay.R;

/**
 * Created by fg on 2016/5/14.
 */
public class GangandTaiFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        super.onCreateView(inflater, container, savedInstance);
        View GangandTaiView = inflater.inflate(R.layout.fragment_gangandtai, container,false);

        return GangandTaiView;

    }
}
