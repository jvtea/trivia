package com.example.joost.triva;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateRequest extends StringRequest {

    public UpdateRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> parameters = new HashMap<>();

        //HIER BEN JE GEBLEVEN. DEZE MOET MISSCHIEN NAAR HIGHSCOREUPDATE
        parameters.put("Name", HighScoreUpdate.name);
        return super.getParams();
    }
}
