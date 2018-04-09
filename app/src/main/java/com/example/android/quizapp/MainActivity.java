package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    int calculatePercentage = 100/5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // will open browser and open Lakers wikipedia page when Lakers Wikipedia button is clicked

    public void open(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Los_Angeles_Lakers"));
        startActivity(browserIntent);
    }

    /**
     * when get score button is clicked
     *
     * @return score summary
     */

    public void Submit(View view) {
        // Enter users Name who took quiz
        EditText nameEditTextView = findViewById(R.id.InputName);
        String enterName = nameEditTextView.getText().toString();

        // Question # 2 text enter answer
       EditText totalChipsTextView = findViewById(R.id.question_2_answer);
       String chipCity = totalChipsTextView.getText().toString();

// when the correct checkbox is checked in question 3
        RadioButton questionThree = findViewById(R.id.question_3_answer_b);
        boolean question3Answer = questionThree.isChecked();

        // when the correct checkbox is checked in question 4
        RadioButton questionFour = findViewById(R.id.question_4_answer_a);
        boolean question4Answer = questionFour.isChecked();

// when the first checkbox is checked in question one
        CheckBox questionOneAnswerA = findViewById(R.id.question_1_answer_a);
        boolean questiononea = questionOneAnswerA.isChecked();


        // when the second checkbox is checked in question one
        CheckBox questionOneAnswerB = findViewById(R.id.question_1_answer_b);
        boolean questiononeb = questionOneAnswerB.isChecked();


// when the first checkbox is checked in question one
        CheckBox questionOneAnswerC = findViewById(R.id.question_1_answer_c);
        boolean questiononec = questionOneAnswerC.isChecked();


//Calculate Score
        int score = calculateScore(questiononea, questiononeb, questiononec, question3Answer, question4Answer, chipCity);

//Will provide score summary when quiz is completed.
        Toast.makeText(this, scoreSummary(enterName, score), Toast.LENGTH_LONG).show();
    }




    /**
     * Calculates the score.
     *
     * @return total score
     */
    private int calculateScore (boolean questiononea, boolean questiononeb, boolean questiononec, boolean question3Answer, boolean question4Answer, String chipCity) {

        //If first box is checked in question 1 it will add 0  to  base score
        if (questiononea) {
            score = score - 0;
        }

        //If second box is checked in question 1 it will add 1 to total score
        if (questiononeb) {
            score = score + 1;
        }

        //If third box is checked in question 1 it will add 1 to total score
        if (questiononec) {
            score = score + 1;
        }

        /// if question # 2 is answered correctly this will add 1 to total score
        if (chipCity.equalsIgnoreCase("16")) {
            score = score + 1;
        }

        //If third box is checked in question 3 it will add 1 to total score
        if (question3Answer){
            score = score + 1;
        }

        //If first box is checked in question 4 it will add 1 to total score
        if (question4Answer){
            score = score + 1;
        }


        //formula to get total score
        score = score * calculatePercentage;

        //Calculates the Total Score
        return score;
    }


    private String scoreSummary(String enterName, int score ) {
        String scoreMessage = getString(R.string.name)+ " " + enterName;
        scoreMessage += "\n" + getString(R.string.total_score) + " " + score + getString(R.string.Percentage);
        scoreMessage += "\n" + getString(R.string.thank_you);
        return scoreMessage;
    }

    /**
     * will email score

     */

    public void Email(View view) {

        // Enter users Name who took quiz
        EditText nameEditTextView = findViewById(R.id.InputName);
        String enterName = nameEditTextView.getText().toString();


        // Will Email an order Summary via email app
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.email_text) + " " + enterName);
        intent.putExtra(Intent.EXTRA_TEXT, scoreSummary(enterName, score));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



    /**
     * will reset score to zero
     */

    public void Reset(View view) {
        score = 0;
    }
}