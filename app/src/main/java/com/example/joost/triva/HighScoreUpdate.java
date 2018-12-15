package com.example.joost.triva;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import javax.security.auth.callback.Callback;

public class HighScoreUpdate implements Response.ErrorListener, Response.Listener {

    private String name;
    private int score;
    private UpdateCallback activity;




    public HighScoreUpdate(String name, int score, UpdateCallback activity, Context context) {
        this.name = name;
        this.score = score;
        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new StringRequest(Request.Method.POST, "https://ide50-jvtea.cs50.io:8080/list", HighScoreUpdate.this, HighScoreUpdate.this;
    }

    public interface UpdateCallback {
        void gotUpdate(String message);
        void gotUpdateError(String message);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotUpdateError("Uploading player score failed.");
    }

    @Override
    public void onResponse(Object response) {
        activity.gotUpdate("Uploading player score!");
    }
}
