package com.mayer.travelapp.service;


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
        String url = urlBuilder.build().toString();
        urlBuilder.addQueryParameter(Constants.TRAVEL_QUERY, location);
        urlBuilder.addQueryParameter(Constants.TRAVEL_INTERESTS, interests);
        urlBuilder.addQueryParameter(Constants.API_ID, Constants.TRAVEL_APPID);
        urlBuilder.addQueryParameter(Constants.API_CODE, Constants.TRAVEL_APPCODE);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback   );
    }
    public ArrayList<Travel> processResults(Response response){
     ArrayList<Travel> travelIdeas = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject travelJSON = new JSONObject(jsonData);
                JSONArray travelInformationJSON = travelJSON.getJSONArray("results");
                JSONArray nextLevelInformationJSON = travelJSON.getJSONArray("items");
                System.out.println(nextLevelInformationJSON);
                for(int i = 0; i< nextLevelInformationJSON.length(); i++){
                    JSONObject travelStuffJSON = travelInformationJSON.getJSONObject(i);
                    String title = travelStuffJSON.getString("title");
                    String vicinity = travelStuffJSON.getString("vicinity");
                    JSONArray categoryJSON = travelStuffJSON.getJSONArray("category");
                    ArrayList<String> categories = new ArrayList<>();
                        for(int j = 0; j < categoryJSON.length(); j++){
                            categories.add(categoryJSON.getJSONObject(j).getString("title"));
                        }
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return travelIdeas;
    }
}
