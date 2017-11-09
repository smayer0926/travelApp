package com.mayer.travelapp.service;


import android.util.Log;

import com.mayer.travelapp.Constants;
import com.mayer.travelapp.model.Travel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TravelService {
    public static void findDestinations(String location, String interests, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TRAVEL_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.TRAVEL_QUERY, location);
        urlBuilder.addQueryParameter(Constants.TRAVEL_INTERESTS, interests);
        urlBuilder.addQueryParameter(Constants.API_ID, Constants.TRAVEL_APPID);
        urlBuilder.addQueryParameter(Constants.API_CODE, Constants.TRAVEL_APPCODE);



        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        System.out.println(request);
        System.out.println(url);

        Call call = client.newCall(request);
        call.enqueue(callback   );
    }
    public ArrayList<Travel> processResults(Response response){
     ArrayList<Travel> travelIdeas = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject travelJSON = new JSONObject(jsonData);
                JSONObject travelInformationJSON = travelJSON.getJSONObject("results");
                JSONArray nextLevelInformationJSON = travelInformationJSON.getJSONArray("items");
                for(int i = 0; i< nextLevelInformationJSON.length(); i++){
                    JSONObject travelStuffJSON = nextLevelInformationJSON.getJSONObject(i);

                    String name = travelStuffJSON.getString("title");

                    String vicinity = travelStuffJSON.getString("vicinity");

                    JSONObject categoriesJSON = travelStuffJSON.getJSONObject("category");
                    String category = categoriesJSON.getString("title");

                    Travel travel = new Travel(name, vicinity, category);
                    travelIdeas.add(travel);
                }
            }


        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        System.out.println(travelIdeas);
        return travelIdeas;
    }
}
