package com.wusui.mediaplay.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wusui.mediaplay.model.Song;
import com.wusui.mediaplay.model.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class JsonUtility {

    public static List<Song> handlePictureResponse(String response) {
        Log.e("handlePictureResponse", response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONObject("showapi_res_body").
                    getJSONObject("pagebean").getJSONArray("songlist");
            Gson gson = new Gson();
            List<Song> songs = new ArrayList<>();
            songs = gson.fromJson(jsonArray.toString(), new TypeToken<List<Song>>() {
            }.getType());

            for (Song s:songs) {
                if (s.getSongname() != null)
                Log.e("handlePictureResponse",s.getSongname());
            }
            Log.e ("JsonUtility", jsonArray.toString());
            return songs;
        }catch (JSONException e)
        {
            Log.e("dddddddddddddddd", "解析出错");
            e.printStackTrace();
        }
        return null;

    }

}

