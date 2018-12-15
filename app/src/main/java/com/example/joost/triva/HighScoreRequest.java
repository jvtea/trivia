package com.example.joost.triva;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class HighScoreRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;

    public HighScoreRequest(Context context) {
        this.context = context;
    }

    //CHANGE INPUT VARIABLES
    public interface Callback {
        void gotHighScore(ArrayList<String> questions, ArrayList<String> answers,
                          ArrayList<ArrayList<String>> allIncorrectAnswers);
        void gotHighScoreError(String message);
    }

    public void getHighScore(Callback activity){
        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest("https://ide50-jvtea.cs50.io:8080/list",
                        null, this, this);
        queue.add(jsonObjectRequest);

        this.activity = activity;
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray highScoreArray = null;
        try {
            highScoreArray = response.getJSONArray("items");
            Log.d("anderedingen", highScoreArray + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // fill this list with MenuItems, create those in for loop below
        ArrayList<String> scoreList = new ArrayList<>();

        for (int index = 0; index < highScoreArray.length(); index++) {
            try {
                JSONObject playerScore = highScoreArray.getJSONObject(index);
                String name = playerScore.getString("name");
                String scoreString = playerScore.getString("score");
                int score =
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}
