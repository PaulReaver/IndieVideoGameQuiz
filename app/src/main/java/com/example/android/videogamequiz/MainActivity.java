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

    /*Declaration of variables and views*/
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
    TextView scoreView;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Initializes all views*/
        initializeViews();

        /*Passing the saved state value to the score variable and displays it.*/
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(KEY_SCORE);
            displayScore(score);
        }
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
        scoreView.setText(String.valueOf(score));
    }

    /*Assigns RadioButtons CheckBoxes and EditTexts to corresponding variables*/
    public void initializeViews() {
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
        scoreView = (TextView) findViewById(R.id.scoreView);
    }

    /*Clears all answers*/
    public void clearAnswers() {

        /*Clears 1st question's answer*/
        limboRadioGroup.clearCheck();

        /*Clears 2nd question's answer*/
        journeyRadioGroup.clearCheck();

        /*Clears 3rd question's answers*/
        wrongAnswer3.setChecked(false);
        correctAnswer3A.setChecked(false);
        correctAnswer3B.setChecked(false);

        /*Clears 4th question's answers*/
        wrongAnswer4.setChecked(false);
        correctAnswer4A.setChecked(false);
        correctAnswer4B.setChecked(false);

        /*Clears 5th question's answer*/
        correctAnswer5.setText("");

        /*Clears 6th question's answer*/
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
            Toast.makeText(this, R.string.name_field, Toast.LENGTH_SHORT).show();
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
        if (answer5.equalsIgnoreCase(getString(R.string.mugman))) {
            rightAnswers += 1;
            score += 1500;
        }

        /*Checks if question 6 was answered correctly*/
        if (answer6.equalsIgnoreCase(getString(R.string.vellie))) {
            rightAnswers += 1;
            score += 1800;
        }

        /*Text if 0 questions were answered correctly*/
        if (rightAnswers == 0) {
            message = getString(R.string.you_have_answered) + rightAnswers + getString(R.string.out_of_6_questions_correctly);
            message = message + getString(R.string.oops);
        }

        /*Text if below 3 questions were answered correctly*/
        else if (rightAnswers < 3) {
            message = getString(R.string.you_have_answered) + rightAnswers + getString(R.string.out_of_6_questions_correctly);
            message = message + getString(R.string.nice_try);
        }

        /*Text if below 6 questions were answered correctly*/
        else if (rightAnswers < 6) {
            message = getString(R.string.you_have_answered) + rightAnswers + getString(R.string.out_of_6_questions_correctly);
            message = message + getString(R.string.good_job);
        }

        /*Text if all questions were answered correctly*/
        else {
            message = getString(R.string.all_6);
        }

        if (nameIsValid) {
            /*Toast message with the results*/
            Toast.makeText(this, name + getString(R.string.space) + message, Toast.LENGTH_LONG).show();
            displayScore(score);
            clearAnswers();
        } else {
            score = 0;
            displayScore(score);
        }
    }
}
