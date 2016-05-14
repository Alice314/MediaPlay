package com.wusui.mediaplay.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class GsonUtility {
    private static List<String> urlList = new ArrayList<>();
    private static List<String> singernameList = new ArrayList<>();
    private static List<String> songnameList = new ArrayList<>();
    private static ArrayList list = new ArrayList();

    public static List<String> handlePictureResponse(String response)
    {
        try {
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0;i < jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            urlList.add(jsonObject.getString("url"));
            singernameList.add(jsonObject.getString("singername"));
            songnameList.add(jsonObject.getString("songname"));


            list.add(urlList);
            list.add(singernameList);
            list.add(songnameList);
    }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
