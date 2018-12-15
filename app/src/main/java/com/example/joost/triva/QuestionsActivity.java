package com.example.joost.triva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionsActivity extends AppCompatActivity implements QuestionsRequest.Callback {

    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> correctAnswers = new ArrayList<>();
    ArrayList<ArrayList<String>> allIncorrectAnswers = new ArrayList<>();
    ArrayList<ArrayList<String>> allAnswers = new ArrayList<>();
//    Button[] buttons;


    String userName;

    // IS THIS RIGHT WAY?!
    int questionNumber;
    int questionsCorrect;
    final int totalQuestions = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        QuestionsRequest request = new QuestionsRequest(this);
        request.getQuestions(this);

        Intent intent = getIntent();
        this.userName = (String) intent.getSerializableExtra("user_name");

    }


    // ROEP DE REQUEST AAN IN STARTACTIVITY OM PROBLEEM VAN LEGE BUTTONS TE VOORKOMEN
    @Override
    public void gotQuestions(ArrayList<String> questions, ArrayList<String> correctAnswers,
                             ArrayList<ArrayList<String>> allIncorrectAnswers) {
        // Log.d("probeersel", questions.size() + "in gotQuestions");
        Log.d("proberen", "in got questions");

        //save arraylists into instance variables
        this.questions = questions;
        this. correctAnswers = correctAnswers;
        this.allIncorrectAnswers = allIncorrectAnswers;


        setInformation();
    }

    @Override
    public void gotQuestionsError(String message) {


    }

    // Sets questions and answers options on each screen. FIND WAY TO UPDATE SCREEN WITH ONCLICK!
    public void setInformation(){

        // make arraylists containing all answers for each question in random order
        for (int i = 0; i < questions.size(); i++) {
            String correctAnswer = correctAnswers.get(i);
            ArrayList<String> theseAnswers = allIncorrectAnswers.get(i);
            theseAnswers.add(correctAnswer);

            // randomize order and odd to total list
            Collections.shuffle(theseAnswers);
            allAnswers.add(theseAnswers);
        }

        // start by displaying first question
        questionNumber = 0;
        questionsCorrect = 0;

        // set button text to answers and set to visible
        Button buttonA = findViewById(R.id.buttonA);
        buttonA.setText(allAnswers.get(questionNumber).get(0));
        buttonA.setVisibility(View.VISIBLE);

        Button buttonB = findViewById(R.id.buttonB);
        buttonB.setText(allAnswers.get(questionNumber).get(1));
        buttonB.setVisibility(View.VISIBLE);

        Button buttonC = findViewById(R.id.buttonC);
        buttonC.setText(allAnswers.get(questionNumber).get(2));
        buttonC.setVisibility(View.VISIBLE);

        Button buttonD = findViewById(R.id.buttonD);
        buttonD.setText(allAnswers.get(questionNumber).get(3));
        buttonD.setVisibility(View.VISIBLE);

        // set question text
        TextView questionText = findViewById(R.id.questionText);
        questionText.setText(questions.get(questionNumber));
    }

    public void answerClicked(View view){

//        Log.d("dingen", "text: " + clickedButton.getText());

        Button clickedButton = findViewById(view.getId());
        String userAnswer = (String) clickedButton.getText();

        Log.d("dingen", "useranswer: " + userAnswer);
        Log.d("dingen", "correctAnswer: " + correctAnswers.get(questionNumber));



        if (userAnswer.equals(correctAnswers.get(questionNumber))){
            questionsCorrect += 1;
        }

        questionNumber += 1;

        if (questionNumber == totalQuestions-1) {
            Intent intent = new Intent(QuestionsActivity.this, ResultsActivity.class);
            intent.putExtra("total_questions", totalQuestions);
            intent.putExtra("questions_correct", questionsCorrect);
            intent.putExtra("user_name", userName);
            startActivity(intent);
            finish();
        }

        Log.d("dingen", "question_number: " + questionNumber);
        Log.d("dingen", "questions_correct: " + questionsCorrect);

        // set button text to answers
        Button buttonA = findViewById(R.id.buttonA);
        buttonA.setText(allAnswers.get(questionNumber).get(0));
        Button buttonB = findViewById(R.id.buttonB);
        buttonB.setText(allAnswers.get(questionNumber).get(1));
        Button buttonC = findViewById(R.id.buttonC);
        buttonC.setText(allAnswers.get(questionNumber).get(2));
        Button buttonD = findViewById(R.id.buttonD);
        buttonD.setText(allAnswers.get(questionNumber).get(3));

        // set question text
        TextView questionText = findViewById(R.id.questionText);
        questionText.setText(questions.get(questionNumber));

    }

}
