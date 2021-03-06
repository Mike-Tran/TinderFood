package edu.gatech.seclass.myapplication;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.myapplication.ProfileModel;

public class Utils {

    private static final String TAG = "Utils";

    /**
     * Parses the JSON data.
     * @param context
     * @return
     */
    public static List<ProfileModel> loadProfiles(Context context){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "srcDirs/profiles.json"));
            List<ProfileModel> profileList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                ProfileModel profile = gson.fromJson(array.getString(i), ProfileModel.class);
                profileList.add(profile);
            }
            return profileList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Load hard coded data into our list of ProfileModel
     * @param context
     * @param jsonFileName
     * @return
     */
    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}