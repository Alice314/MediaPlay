package com.wusui.mediaplay.ui.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.os.ResultReceiver;

import com.wusui.mediaplay.model.Song;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by fg on 2016/5/20.
 */
public class DownloadService extends IntentService {

    private Song mSong;
    public static final int UPDATE_PROGRESS=8344;
    public static final String FILE_NAME = "music_my";
    public static final String FILE_NAME_END = ".mp3";

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mSong = (Song) intent.getSerializableExtra("url");
        String urlToDownload = mSong.getDownUrl();
        ResultReceiver receiver = (ResultReceiver)intent.getParcelableExtra("receiver");
        try {
            URL url = new URL(urlToDownload);
            URLConnection connection = url.openConnection();
            connection.connect();

            int fileLength = connection.getContentLength();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());

            File file = new File(Environment.getExternalStorageDirectory(),FILE_NAME + "_" + mSong.getSongname() + FILE_NAME_END);
            if(!file.exists())
                file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            byte data[]=new byte[1024];
            long total =0;
            int count;
            while ((count = inputStream.read(data))!=-1){
                total += count;
                Bundle resultData = new Bundle();
                resultData.putInt("progress",(int) (total * 100 / fileLength));
                receiver.send(UPDATE_PROGRESS,resultData);
                outputStream.write(data,0,count);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Bundle resultData = new Bundle();
        resultData.putInt("progress",100);
        receiver.send(UPDATE_PROGRESS,resultData);
    }

}
