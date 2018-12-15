package com.example.joost.triva;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;
    // constructor
    public QuestionsRequest(Context context) {
        this.context = context;
    }

    //
    public interface Callback {
        void gotQuestions(ArrayList<String> questions, ArrayList<String> answers,
                          ArrayList<ArrayList<String>> allIncorrectAnswers);
        void gotQuestionsError(String message);
    }

    // get trivia information from API in JSON format
    public void getQuestions (Callback activity){

        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest("https://opentdb.com/api.php?amount=20&category=12&difficulty=easy&type=multiple",
                null, this, this);
        queue.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray quizInfo = null;
        try {
            quizInfo = response.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // initialize ArrayLists to be filled with questions and answers
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> correctAnswers = new ArrayList<>();
        ArrayList<ArrayList<String>> allIncorrectAnswers = new ArrayList<>();

        // get questions and answers from JSONArray and store in ArrayLists
        for (int i = 0; i < quizInfo.length(); i++){
            try {
                JSONObject item = quizInfo.getJSONObject(i);

                // add question
                String question = item.getString("question");
                question = question.replace("&#039", "").replace(";", "'");
                questions.add(question);

                // add correctAnswer
                String correctAnswer = item.getString("correct_answer");
                correctAnswers.add(correctAnswer);

                // get incorrect answers, parse to ArrayList and add
                String incorrectString = item.getString("incorrect_answers");
                incorrectString = incorrectString.replaceAll("\"", "");
                incorrectString = incorrectString.replace("[", "").replace("]","");
                incorrectString = incorrectString.replace("&quot", "\"");
//                incorrectString = incorrectString.replaceAll("&Uuml", "");
                ArrayList<String> incorrectAnswers = new ArrayList (Arrays.asList(incorrectString.split(",")));
                allIncorrectAnswers.add(incorrectAnswers);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        // pass information on the QuestionsActivity
        activity.gotQuestions(questions, correctAnswers, allIncorrectAnswers);

    }
}
