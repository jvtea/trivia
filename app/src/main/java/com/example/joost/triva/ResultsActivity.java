package com.example.joost.triva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        int totalQuestions = (int) intent.getSerializableExtra("total_questions");
        int questionsCorrect = (int) intent.getSerializableExtra("questions_correct");
        String userName = (String) intent.getSerializableExtra("user_name");

        // set text for results
        TextView resultsGreeting = findViewById(R.id.resultsGreeting);
        float correctRatio = (float) questionsCorrect/totalQuestions;

        Log.d("dingen", "ratio: " + correctRatio);

        // ALSO SET DIFFERENT IMAGES!
        if (correctRatio <= 0.15) {
            resultsGreeting.setText("Wow, you really suck, " + userName + "! Have you heard about the concept 'music' before? It's pretty cool stuff.");
        } else
        if (correctRatio <= 0.5) {
            resultsGreeting.setText("Your performance was quite bad, " + userName + ",and you should at least feel sóme sense of shame right now.");
        } else
        if (correctRatio <= 0.7) {
            resultsGreeting.setText("Congratulations, " + userName +"! You did pretty good out there!");
        } else
        if (correctRatio <= 0.9) {
            resultsGreeting.setText("Wow, " + userName + "! You are a wizard of musical knowledge. Well done, P.I.M.P!");
        }
        else {
            resultsGreeting.setText("HOT DAMN, " + userName + ", you are a musical GENIUS. A legend of music. A vinyl dragon. If Eric Clapton wasn't already, you'd be GOD!");
        }

        // set score display for user
        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText(questionsCorrect + "/" + totalQuestions);

    }




}
