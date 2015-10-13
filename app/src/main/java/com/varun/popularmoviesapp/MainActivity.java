package com.varun.popularmoviesapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));

        setContentView(R.layout.activity_main);


        ConnectionDetector cd = new ConnectionDetector(this);

        if (cd.isConnectingToInternet()) {
            new ForMovieConfig().execute();
        } else {
            Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    class ForMovieConfig extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL url = new URL(Constants.CONFIGURATION_URL + "?api_key=" + Constants.API_KEY);

                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.connect();

                // GENERATE PARAMS (JSON)
//                getVersion = new JSONStringer().object().key("api_key")
//                        .value(Constants.API_KEY).endObject();

//                // SET PARAMS
//                OutputStream os = con.getOutputStream();
//                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//
//                osw.write(getVersion.toString());
//                osw.flush();
//                osw.close();

                mResponse = readStream(con.getInputStream());

//                Log.e(Constants.TAG, "RESULT : " + mReponse);

            } catch (Exception e) {
                Log.e(Constants.TAG, "CONNECTION EXCEPTION: " + e);
            }

            try {
                JSONObject jdata = new JSONObject(mResponse);

                String images = jdata.getString(Constants.IMAGES_KEY);
                String change_key = jdata.getString(Constants.CHANGE_KEYS_KEY);

                JSONObject jImages = new JSONObject(images);

                Constants.BASE_URL = jImages.getString(Constants.BASE_URL_KEY);
                Constants.SECURE_BASE_URL = jImages.getString(Constants.SECURE_BASE_URL_KEY);

                JSONArray jBackdropSizes = jImages.getJSONArray(Constants.BACKDROP_SIZES_KEY);
                Constants.BACKDROP_SIZES = new ArrayList<String>();

                for (int i = 0; i < jBackdropSizes.length(); i++) {
                    Constants.BACKDROP_SIZES.add(jBackdropSizes.getString(i));
                }

                JSONArray jLogoSizes = jImages.getJSONArray(Constants.LOGO_SIZES_KEY);
                Constants.LOGO_SIZES = new ArrayList<String>();

                for (int i = 0; i < jLogoSizes.length(); i++) {
                    Constants.LOGO_SIZES.add(jLogoSizes.getString(i));
                }

                JSONArray jPosterSizes = jImages.getJSONArray(Constants.POSTER_SIZES_KEY);
                Constants.POSTER_SIZES = new ArrayList<String>();

                for (int i = 0; i < jPosterSizes.length(); i++) {
                    Constants.POSTER_SIZES.add(jPosterSizes.getString(i));
                }

                JSONArray jProfileSizes = jImages.getJSONArray(Constants.PROFILE_SIZES_KEY);
                Constants.PROFILE_SIZES = new ArrayList<String>();

                for (int i = 0; i < jProfileSizes.length(); i++) {
                    Constants.PROFILE_SIZES.add(jProfileSizes.getString(i));
                }

                JSONArray jStillSizes = jImages.getJSONArray(Constants.STILL_SIZES_KEY);
                Constants.STILL_SIZES = new ArrayList<String>();
                for (int i = 0; i < jStillSizes.length(); i++) {
                    Constants.STILL_SIZES.add(jStillSizes.getString(i));
                }

            } catch (JSONException j) {
                Log.e(Constants.TAG, "CONFIG JSON EXCEPTION: " + j);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
        }
    }
}
