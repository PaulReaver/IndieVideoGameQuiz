package com.example.android.videogamequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*Method is called the submit button is clicked*/
    public void submit(View view) {

        /*Declaration of variables*/
        boolean nameIsValid = true;
        int rightAnswers = 0;
        String message = "";

        /*Gets the user's name and passes it in a variable*/
        EditText nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        /*Gets the answer from question 1*/
        RadioButton correctAnswer1 = (RadioButton) findViewById(R.id.correctAnswer1);

        /*Gets the answer from question 2*/
        RadioButton correctAnswer2 = (RadioButton) findViewById(R.id.correctAnswer2);

        /*Gets the answers from question 3*/
        CheckBox wrongAnswer3 = (CheckBox) findViewById(R.id.wrongAnswer3);
        CheckBox correctAnswer3A = (CheckBox) findViewById(R.id.correctAnswer3A);
        CheckBox correctAnswer3B = (CheckBox) findViewById(R.id.correctAnswer3B);

        /*Gets the answers from question 4*/
        CheckBox wrongAnswer4 = (CheckBox) findViewById(R.id.wrongAnswer4);
        CheckBox correctAnswer4A = (CheckBox) findViewById(R.id.correctAnswer4A);
        CheckBox correctAnswer4B = (CheckBox) findViewById(R.id.correctAnswer4B);

        /*Gets the answer from question 5*/
        EditText correctAnswer5 = (EditText) findViewById(R.id.correctAnswer5);
        String answer5 = correctAnswer5.getText().toString();

        /*Gets the answer from question 6*/
        EditText correctAnswer6 = (EditText) findViewById(R.id.correctAnswer6);
        String answer6 = correctAnswer6.getText().toString();

        /*Checks if the name field is empty and shows appropriate message*/
        if (name.isEmpty()) {
            Toast.makeText(this, "The name field cannot be empty", Toast.LENGTH_SHORT).show();
            nameIsValid = false;
        }

        /*Checks if question 1 was answered correctly*/
        if (correctAnswer1.isChecked()) {
            rightAnswers += 1;
        }

        /*Checks if question 2 was answered correctly*/
        if (correctAnswer2.isChecked()) {
            rightAnswers += 1;
        }

        /*Checks if question 3 was answered correctly*/
        if (correctAnswer3A.isChecked() && correctAnswer3B.isChecked() && !wrongAnswer3.isChecked()) {
            rightAnswers += 1;
        }

        /*Checks if question 4 was answered correctly*/
        if (correctAnswer4A.isChecked() && correctAnswer4B.isChecked() && !wrongAnswer4.isChecked()) {
            rightAnswers += 1;
        }

        /*Checks if question 5 was answered correctly*/
        if (answer5.equals("Mugman")) {
            rightAnswers += 1;
        }

        /*Checks if question 6 was answered correctly*/
        if (answer6.equals("Vellie")) {
            rightAnswers += 1;
        }

        /*Text if 0 questions were answered correctly*/
        if (rightAnswers == 0) {
            message = "you have answered " + rightAnswers + " out of 6 questions correctly.";
            message = message + " Oops!";
        }

        /*Text if below 3 questions were answered correctly*/
        else if (rightAnswers < 3) {
            message = "you have answered " + rightAnswers + " out of 6 questions correctly.";
            message = message + " Nice try, but you can do better than that!";
        }

        /*Text if below 6 questions were answered correctly*/
        else if (rightAnswers < 6) {
            message = "you have answered " + rightAnswers + " out of 6 questions correctly.";
            message = message + " Good job! Try to get them all next time!";
        }

        /*Text if all questions were answered correctly*/
        else {
            message = "you have answered all 6 questions correctly. Congratulations! You really know your indie games!";
        }

        if (nameIsValid) {
            /*Toast message with the results*/
            Toast.makeText(this, name + " " + message, Toast.LENGTH_LONG).show();
        }
    }
}
