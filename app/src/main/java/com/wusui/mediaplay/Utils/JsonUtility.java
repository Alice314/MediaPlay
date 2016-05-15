package com.wusui.mediaplay.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.wusui.mediaplay.model.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class JsonUtility {

    private static List<String> urlList = new ArrayList<>();
    private static Status fromJson;
    private static List<Status.songlist> sSonglists;
    public static List<String> handlePictureResponse(String response){

        Gson gson = new Gson();
        fromJson = gson.fromJson(response,Status.class);
        sSonglists = fromJson.getSonglists();
        Log.e("Utility.class","解析成功");
        for (int i = 0; i < sSonglists.size(); i++) {
            Log.e("Utility.class","THIS IS URL="+ sSonglists.get(i).getAlbumpic_small());
            urlList.add(sSonglists.get(i).getAlbumpic_small());
        }
        return urlList;
    }

}

