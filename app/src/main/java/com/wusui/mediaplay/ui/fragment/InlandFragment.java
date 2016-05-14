package com.wusui.mediaplay.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wusui.mediaplay.R;
import com.wusui.mediaplay.Utils.HttpUtils;
import com.wusui.mediaplay.ui.activity.ImageLoader;
import com.wusui.mediaplay.ui.activity.PlayActivity;
import com.wusui.mediaplay.ui.adapter.MusicAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class InlandFragment extends BaseFragment {
    private static RecyclerView mRecyclerView;
    private static MusicAdapter mAdapter;
    private static List<Bitmap> mSmallBitmaps = new ArrayList<>();
    private List<String>mSingerName = new ArrayList<>();
    private static List<String>mSongName = new ArrayList<>();
    private static Context mContext;

    public static final int LOAD_SUCCESS = 1;
    public static final int LOAD_ERROR = -1;
    public static final int LOAD_BITMAP = 2;

    private List<String> picUrls;
    private static List<String>json;

    private ImageLoader mImageLoader;


    private static class MyHandler extends android.os.Handler {
        private final WeakReference<InlandFragment> mFragment;

        public MyHandler(InlandFragment fragment) {
            mFragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mFragment.get() != null) {
                switch (msg.what){
                    case LOAD_SUCCESS:
                    int position = msg.arg1;
                        if (position == -1){
                            json.add(null);
                            return;
                        }

                        break;
                    case LOAD_ERROR:
                        //Toast.makeText(MainActivity.this,(String)msg.obj,Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        }}

    private final MyHandler mHandler = new MyHandler(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        super.onCreateView(inflater, container, savedInstance);
        View communityView = inflater.inflate(R.layout.fragment, container,false);
        mRecyclerView = (RecyclerView)communityView.findViewById(R.id.id_recyclerView);
        initView();
        return communityView;

    }


    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter = new MusicAdapter(mContext, picUrls, mSongName, mSingerName));
        mAdapter.setmOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), PlayActivity.class);
                startActivity(intent);
            }
        });
    }

    private void requestPictureJson(){
        String url = "http://route.showapi.com/213-4?showapi_sign=a458769125494c16bbaf5078b59309a6%20&showapi_appid=19029&topid=5\n";

        HttpUtils.sendHttpRequest(url, new HttpUtils.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Message message = Message.obtain();
                message.obj = picUrls;
                message.what = LOAD_SUCCESS;
                message.arg1 = -1;
                mHandler.sendMessage(message);

            }

            @Override
            public void onError(Exception e) {
                Message message = Message.obtain();
                message.what = LOAD_ERROR;
                message.obj = "JSON请求失败: " + e.toString();
                mHandler.sendMessage(message);
            }
        });

    }
}
