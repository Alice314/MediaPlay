package com.wusui.mediaplay.ui.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.wusui.mediaplay.R;

/**
 * Created by fg on 2016/5/14.
 */
public class PlayActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private Button mPlay;
    private Button mPause;
    private Button mStop;
    private Button mLoop;
    private Button mList;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initToolBar();
        initButton();
        initPlayer();
    }

    private void initPlayer() {

    }


    private void initToolBar() {
            mToolbar = getToolbar();
            mToolbar.setTitle("Backdrops");
            mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(mToolbar);

    }


    private void initButton(){
        mPlay = (Button) findViewById(R.id.play);
        mPause = (Button)findViewById(R.id.pause);
        mStop = (Button)findViewById(R.id.stop);
        mLoop = (Button)findViewById(R.id.loop);
        mList = (Button)findViewById(R.id.list);

        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mLoop.setOnClickListener(this);
        mList.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:

                break;
            case R.id.pause:

                break;
            case R.id.stop:

                break;
            case R.id.loop:

                break;
            case R.id.list:

                break;
            default:
                break;
        }
    }
}
