package com.myapp.android.localfinder;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Kavya on 12/13/2016.
 */

public class downloader extends AsyncTask<String , Integer , ArrayList> {

    ResultsActivity activity;
    public downloader(ResultsActivity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.displayprogessbar();
    }

    @Override
    protected ArrayList doInBackground(String... params) {

        String yql = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20local.search%20where%20zip%3D%27"+params[1]+ "%27%20and%20query%3D%27"+params[0]+"%27&format=json&callback=";
        ArrayList<Results> resultsArrayList = new ArrayList<Results>();
        try {
            URL theurl = new URL(yql);
            BufferedReader reader = new BufferedReader(new InputStreamReader(theurl.openConnection().getInputStream(), "UTF-8"));
            String json = reader.readLine();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject queryObject = jsonObject.getJSONObject("query");
            JSONObject resultsObject = queryObject.getJSONObject("results");
            JSONArray resultsArray = resultsObject.getJSONArray("Result");
            for (int i = 0; i < resultsArray.length(); i++) {
                publishProgress((i+1)*10);
                Thread.sleep(100);
                JSONObject singleObject = resultsArray.getJSONObject(i);
                String title = singleObject.getString("Title");
                String address = singleObject.getString("Address");
                String city = singleObject.getString("City");
                String state = singleObject.getString("State");
                String phone = singleObject.getString("Phone");
                double latitude = Double.parseDouble(singleObject.getString("Latitude"));
                double longitude = Double.parseDouble(singleObject.getString("Longitude"));
                String distance = singleObject.getString("Distance");
                String businessURL = singleObject.getString("Url");
                Results result = new Results(title, address, city, state, phone, latitude, longitude, distance, businessURL);
                resultsArrayList.add(result);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultsArrayList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        activity.setProgressBarprogress(values[0]);

    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        activity.drawlistview(arrayList);
    }
}
