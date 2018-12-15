package com.example.joost.triva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startClicked(View view){

        EditText userInput = findViewById(R.id.editText);
        String userName = userInput.getText().toString();
        
        if (userName.length() == 0) {
            Toast.makeText(this, "Please enter a user name!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(StartActivity.this, QuestionsActivity.class);
            intent.putExtra("user_name", userName);
            startActivity(intent);
        }
    }


}
