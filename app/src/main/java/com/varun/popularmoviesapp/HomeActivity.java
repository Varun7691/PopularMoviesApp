package com.varun.popularmoviesapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.varun.popularmoviesapp.Constants.Constants;
import com.varun.popularmoviesapp.Utils.ConnectionDetector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    String mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ConnectionDetector cd = new ConnectionDetector(this);

        if (cd.isConnectingToInternet()) {
            new ForPopularMovies().execute();
        } else {
            Toast.makeText(HomeActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private String readStream(InputStream in) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            return null;
        }
    }

    class ForPopularMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL url = new URL(Constants.POPULAR_MOVIES_URL + "?api_key=" + Constants.API_KEY);

                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.connect();


                mResponse = readStream(con.getInputStream());

            } catch (Exception e) {
                Log.e(Constants.TAG, "CONNECTION EXCEPTION: " + e);
            }

            try {
                JSONObject jdata = new JSONObject(mResponse);

                JSONArray results = jdata.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject jMovie = results.getJSONObject(i);

                    boolean adult = jMovie.getBoolean("adult");
                    String backdrop_path = jMovie.getString("backdrop_path");
                    String original_title = jMovie.getString("original_title");
                }

            } catch (JSONException j) {
                Log.e(Constants.TAG, "POPULAR MOVIES JSON EXCEPTION: " + j);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
