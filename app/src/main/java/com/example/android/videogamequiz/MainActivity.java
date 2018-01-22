package com.example.android.videogamequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*Declaration of variables*/
    static final String KEY_SCORE = "";
    RadioGroup limboRadioGroup;
    RadioGroup journeyRadioGroup;
    RadioButton correctAnswer1;
    RadioButton correctAnswer2;
    CheckBox wrongAnswer3;
    CheckBox correctAnswer3A;
    CheckBox correctAnswer3B;
    CheckBox wrongAnswer4;
    CheckBox correctAnswer4A;
    CheckBox correctAnswer4B;
    EditText nameField;
    EditText correctAnswer5;
    EditText correctAnswer6;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Passing the saved state value to the score variable and displays it.*/
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(KEY_SCORE);
            displayScore(score);
        }

        /*Assigns RadioButtons CheckBoxes and EditTexts to corresponding variables*/
        limboRadioGroup = (RadioGroup) findViewById(R.id.limboRadioGroup);
        journeyRadioGroup = (RadioGroup) findViewById(R.id.journeyRadioGroup);
        correctAnswer1 = (RadioButton) findViewById(R.id.correctAnswer1);
        correctAnswer2 = (RadioButton) findViewById(R.id.correctAnswer2);
        wrongAnswer3 = (CheckBox) findViewById(R.id.wrongAnswer3);
        correctAnswer3A = (CheckBox) findViewById(R.id.correctAnswer3A);
        correctAnswer3B = (CheckBox) findViewById(R.id.correctAnswer3B);
        wrongAnswer4 = (CheckBox) findViewById(R.id.wrongAnswer4);
        correctAnswer4A = (CheckBox) findViewById(R.id.correctAnswer4A);
        correctAnswer4B = (CheckBox) findViewById(R.id.correctAnswer4B);
        nameField = (EditText) findViewById(R.id.nameField);
        correctAnswer5 = (EditText) findViewById(R.id.correctAnswer5);
        correctAnswer6 = (EditText) findViewById(R.id.correctAnswer6);
    }

    /*Saving the instance when screen rotates*/
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_SCORE, score);

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    /*Displays the score.*/
    public void displayScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.scoreView);
        scoreView.setText(String.valueOf(score));
    }

    /*Clears all answers*/
    public void clearAnswers() {

        /*Clears 1st question's answer */
        limboRadioGroup.clearCheck();

        /*Clears 2nd question's answer */
        journeyRadioGroup.clearCheck();

        /*Clears 3rd question's answer */
        if (wrongAnswer3.isChecked()) {
            wrongAnswer3.setChecked(false);
        }

        if (correctAnswer3A.isChecked()) {
            correctAnswer3A.setChecked(false);
        }

        if (correctAnswer3B.isChecked()) {
            correctAnswer3B.setChecked(false);
        }

        /*Clears 4th question's answer */
        if (wrongAnswer4.isChecked()) {
            wrongAnswer4.setChecked(false);
        }

        if (correctAnswer4A.isChecked()) {
            correctAnswer4A.setChecked(false);
        }

        if (correctAnswer4B.isChecked()) {
            correctAnswer4B.setChecked(false);
        }

        /*Clears 5th question's answer */
        correctAnswer5.setText("");

        /*Clears 6th question's answer */
        correctAnswer6.setText("");
    }

    /*Method is called the submit button is clicked*/
    public void submit(View view) {

        /*Declaration of variables*/
        boolean nameIsValid = true;
        int rightAnswers = 0;
        score = 0;
        String message = "";

        /*Gets the user's name and passes it in a variable*/
        String name = nameField.getText().toString();

        /*Gets the answer from question 5*/
        String answer5 = correctAnswer5.getText().toString();

        /*Gets the answer from question 6*/
        String answer6 = correctAnswer6.getText().toString();

        /*Checks if the name field is empty and shows appropriate message*/
        if (name.isEmpty()) {
            Toast.makeText(this, "The name field cannot be empty", Toast.LENGTH_SHORT).show();
            nameIsValid = false;
        }

        /*Checks if question 1 was answered correctly*/
        if (correctAnswer1.isChecked()) {
            rightAnswers += 1;
            score += 400;
        }

        /*Checks if question 2 was answered correctly*/
        if (correctAnswer2.isChecked()) {
            rightAnswers += 1;
            score += 500;
        }

        /*Checks if question 3 was answered correctly*/
        if (correctAnswer3A.isChecked() && correctAnswer3B.isChecked() && !wrongAnswer3.isChecked()) {
            rightAnswers += 1;
            score += 800;
        }

        /*Checks if question 4 was answered correctly*/
        if (correctAnswer4A.isChecked() && correctAnswer4B.isChecked() && !wrongAnswer4.isChecked()) {
            rightAnswers += 1;
            score += 1000;
        }

        /*Checks if question 5 was answered correctly*/
        if (answer5.equalsIgnoreCase("Mugman")) {
            rightAnswers += 1;
            score += 1500;
        }

        /*Checks if question 6 was answered correctly*/
        if (answer6.equalsIgnoreCase("Vellie")) {
            rightAnswers += 1;
            score += 1800;
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
            displayScore(score);
            clearAnswers();
        } else {
            score = 0;
            displayScore(score);
        }
    }
}
