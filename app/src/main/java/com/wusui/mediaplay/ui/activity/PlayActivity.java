package com.wusui.mediaplay.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.os.ResultReceiver;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wusui.mediaplay.R;
import com.wusui.mediaplay.Utils.ImageLoader;
import com.wusui.mediaplay.model.Song;
import com.wusui.mediaplay.ui.service.DownloadService;

import java.io.IOException;

/**
 * Created by fg on 2016/5/14.
 */
public class PlayActivity extends BaseActivity implements View.OnClickListener {


    private Song mSong;
    private boolean isPrepared = false;
    private ImageLoader mImageLoader;
    private static Button mPlay;
    private ImageView mImageView;
    private ProgressDialog mProgressDialog;
    private static Button mPause;

    MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener() {

        @Override
        public void onPrepared(MediaPlayer mp) {
            isPrepared = true;
            Log.e("vvvvvv", "准备完成");

        }
    };

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initButton();
        Bundle bundle = getIntent().getExtras();
        mSong = (Song) bundle.get("Song");
        mProgressDialog = new ProgressDialog(PlayActivity.this);
        mImageLoader = new ImageLoader(this,mImageView);
        initToolBar();
        initPlayer();
    }

    private void initPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();
        if (mSong.getDownUrl() != null)
            try {
                mediaPlayer.setDataSource(mSong.getDownUrl());
                mediaPlayer.setOnPreparedListener(mPreparedListener);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    private void initToolBar() {
      Toolbar  mToolbar = getToolbar();
        mToolbar.setTitle(mSong.getSongname());
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
         mImageView = (ImageView) findViewById(R.id.image_big);
        if (mSong.getAlbumpic_big() != null){
            mImageLoader.displayImage(mSong.getAlbumpic_big(),mImageView);
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    private void initButton() {
        mPlay = (Button) findViewById(R.id.play);
        mPause = (Button)findViewById(R.id.pause);
       Button mPrevious = (Button) findViewById(R.id.previous);
       Button mNext = (Button) findViewById(R.id.next);
       Button mLoop = (Button) findViewById(R.id.loop);
       Button mList = (Button) findViewById(R.id.list);

        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mPrevious.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mLoop.setOnClickListener(this);
        mList.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (mediaPlayer == null)
            return;

        switch (v.getId()) {
            case R.id.play:
                if (isPrepared)
                    mediaPlayer.start();
                break;
            case R.id.pause:
                mediaPlayer.pause();
            case R.id.previous:

                break;
            case R.id.next:

                break;
            case R.id.loop:

                break;
            case R.id.list:
                mProgressDialog.show();
                Intent intent = new Intent(this, DownloadService.class);
                intent.putExtra("url", mSong);
                intent.putExtra("receiver", new DownloadReceiver(new Handler()));
                startService(intent);

                break;
            default:
                break;
        }
    }

    private class DownloadReceiver extends ResultReceiver {

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public DownloadReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == DownloadService.UPDATE_PROGRESS);
            int progress = resultData.getInt("progress");
            mProgressDialog.setProgress(progress);
            if (progress == 100){
                mProgressDialog.dismiss();
                Toast.makeText(PlayActivity.this,mSong.getSongname()+"下载成功",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
