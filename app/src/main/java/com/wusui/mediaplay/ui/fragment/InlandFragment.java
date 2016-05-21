package com.wusui.mediaplay.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wusui.mediaplay.R;
import com.wusui.mediaplay.Utils.FileUtils;
import com.wusui.mediaplay.Utils.HttpUtils;
import com.wusui.mediaplay.Utils.JsonUtility;
import com.wusui.mediaplay.model.Song;
import com.wusui.mediaplay.ui.activity.PlayActivity;
import com.wusui.mediaplay.ui.adapter.MusicAdapter;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class InlandFragment extends Fragment {
    private static RecyclerView mRecyclerView;
    private static MusicAdapter mAdapter;
    private static List<Bitmap> mSmallBitmaps = new ArrayList<>();
    private List<String>mSingerName = new ArrayList<>();
    private static List<String>mSongName = new ArrayList<>();
    private List<Song> mSongs = new ArrayList<>();
    private Context mContext;

    public static final int LOAD_SUCCESS = 1;
    public static final int LOAD_ERROR = -1;
    public static final int LOAD_BITMAP = 2;

    private List<String> picUrls = new ArrayList<>();
    public static final String FILE_NAME = "music";
    public static final String FILE_NAME_END = ".jpg";


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
                        mAdapter.notifyDataSetChanged();
                        break;
                    case LOAD_ERROR:
                        //Toast.makeText(MainActivity.this,(String)msg.obj,Toast.LENGTH_SHORT).show();
                        break;
                    case LOAD_BITMAP:
                        int position = msg.arg1;
                        if (position == -1) {
                            mSmallBitmaps.add(null);
                            return;
                        }
                        Bitmap bitmap = (Bitmap) msg.obj;
                        mSmallBitmaps.set(position, bitmap);
                        Log.e("InlandFragment.class","线程中拿图片没问题");
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }

        }}

    private final MyHandler mHandler = new MyHandler(this);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        super.onCreateView(inflater, container, savedInstance);
        View InlandView = inflater.inflate(R.layout.fragment_inland, container,false);
        mRecyclerView = (RecyclerView)InlandView.findViewById(R.id.id_recyclerView);
        initView();
        return InlandView;

    }


    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MusicAdapter(getActivity(),mSongs,mRecyclerView);
        mAdapter.setmOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Song",mSongs.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        requestMusicJson();
    }

    private void requestMusicJson() {
        String url = "http://route.showapi.com/213-4?showapi_sign=a458769125494c16bbaf5078b59309a6%20&showapi_appid=19029&topid=5";

        HttpUtils.sendHttpRequest(url, new HttpUtils.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Log.e("InlandFragment.class","请求地址没问题");
                requestMusicsPics(response);
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

    private void requestMusicsPics(String response) {
        Log.e("requestMusicsPics",response);
        if (JsonUtility.handlePictureResponse(response) != null)
            mSongs.addAll(JsonUtility.handlePictureResponse(response));
        mHandler.sendEmptyMessage(LOAD_SUCCESS);

    }

    private void requestMusicPic(String musicPicUrl, final int position) {
        HttpUtils.sendHttpRequestForBitmap(musicPicUrl, new HttpUtils.HttpForBitmapListener() {
            @Override
            public void onFinish(Bitmap bitmap) {
                Message message = Message.obtain();
                message.obj = bitmap;
                message.what = LOAD_BITMAP;
                message.arg1 = position;
                mHandler.sendMessage(message);
                Log.e("InlandFragment.class","请求单张没问题");

            }

            @Override
            public void onError(String e) {
                Message message = Message.obtain();
                message.what = LOAD_ERROR;
                message.obj = "第" + position + "张图片请求失败: " + e;
                mHandler.sendMessage(message);
            }

            @Override
            public void onFinish(InputStream inputStream) {
                saveMusicPic(inputStream, position);
            }
        });
    }


    private void saveMusicPic(InputStream inputStream, final int position) {
        FileUtils.saveFile(inputStream, FILE_NAME + "_" + position + FILE_NAME_END, new FileUtils.SaveFileListener() {
            @Override
            public void onSuccess() {
                Log.e("InlandFragment.class","保存图片没问题");

            }

            @Override
            public void onFail() {
                Message message = Message.obtain();
                message.what = LOAD_ERROR;
                message.obj = "第" + position + "张图片保存失败";
                mHandler.sendMessage(message);
            }
        });
    }


}
