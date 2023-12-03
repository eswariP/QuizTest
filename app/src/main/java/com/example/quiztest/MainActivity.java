package com.example.quiztest;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    QuestionFragment QuestionF;
    Button FBtn, TBtn;
    ArrayList<Question> questionList;
    ProgressBar ProgressBarValue;
    QuestionBank QuizList;
    int userScore;
    int qnIndex, correctAnsCnt;
    int NumOfQns = 10;
    int noOfAttempts = 0;
    //FrameLayout frameLayoutval;
    boolean customQuestion = false;
    Fragment f;
    FileStorageManager fileStorageManager = new FileStorageManager();
    String resultMessage = "";
    String averageMessage = "";

    // FileStoreManger fM= new FileStorageManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FBtn = findViewById(R.id.btn_false);
        TBtn = findViewById(R.id.btn_true);
        ProgressBarValue = findViewById(R.id.ProgressVal);
        //  frameLayoutval=findViewById(R.id.frame_layout);
        FBtn.setOnClickListener(this);
        TBtn.setOnClickListener(this);
        //ProgressBarValue.setOnClickListener(this);
        // frameLayout.setOnClickListener(this);
        QuizList = new QuestionBank();

        f = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (f != null) {
            (getSupportFragmentManager().beginTransaction()).remove(f).commit();
            questionList = ((MyApp) getApplication()).getAppQuestionList();
            qnIndex = ((MyApp) getApplication()).getQuestionIndex();
            correctAnsCnt = ((MyApp) getApplication()).getCorrectAnswer();
        } else {
            questionList = QuizList.getQuestionsList(MainActivity.this);
            Collections.shuffle(questionList);
            ((MyApp) getApplication()).setAppQuestionList(questionList);
            qnIndex = 0;
            ((MyApp) getApplication()).setQuestionIndex(qnIndex);
            correctAnsCnt = 0;
            ((MyApp) getApplication()).setCorrectAnswer(correctAnsCnt);

        }

        // qnIndex++;
        // need to take numOf question for progressbar
        String question = questionList.get(qnIndex).questionText;
        int color = questionList.get(qnIndex).color;
        ProgressBarValue.setProgress(qnIndex * 100 / 10);
        QuestionF = QuestionFragment.newInstance(question, color);
        (getSupportFragmentManager().beginTransaction()).add(R.id.frame_layout, QuestionF).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_true:

                if (questionList.get(qnIndex).answer == true) {

                    correctAnsCnt++;
                    Toast.makeText(MainActivity.this, R.string.correct_Answer, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "InCorrect Answer", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_false:
                if (questionList.get(qnIndex).answer == false) {
                    correctAnsCnt++;
                    Toast.makeText(MainActivity.this, R.string.correct_Answer, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "inCorrect Answer", Toast.LENGTH_SHORT).show();
                }


                break;

        }
        qnIndex++;
        if (qnIndex == 10) {

            showResults(correctAnsCnt);
            Collections.shuffle(questionList);
            ((MyApp) getApplication()).setAppQuestionList(questionList);
            qnIndex = 0;
            correctAnsCnt = 0;

        }
        ((MyApp) getApplication()).setQuestionIndex(qnIndex);
        ((MyApp) getApplication()).setCorrectAnswer(correctAnsCnt);
        f = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (f != null) {
            (getSupportFragmentManager().beginTransaction()).remove(f).commit();
        }
        String question = questionList.get(qnIndex).questionText;
        int color = questionList.get(qnIndex).color;
        ProgressBarValue.setProgress(qnIndex * 100 / 10);
        QuestionF = QuestionFragment.newInstance(question, color);
        (getSupportFragmentManager().beginTransaction()).add(R.id.frame_layout, QuestionF).commit();
    }

    void showResults(int userScore) {
        AlertDialog.Builder balert = new AlertDialog.Builder(this);
        balert.setTitle("quiz is finished");
        balert.setMessage("Final Score is" + " " + userScore + "out Of " + NumOfQns);
        balert.setNegativeButton("ignore", null);
        balert.setPositiveButton(R.string.alert_Save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fileStorageManager.writeResult(MainActivity.this, userScore);
                // finish();
            }
        });
        balert.setCancelable(false);
        // balert.show();
        balert.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.question_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Average:


                //int   averageScore = (double) userScore / (numberOfQuestions * numberOfAttempts);
                getAverage();
                //int average=correctAnsCnt*NumOfQns/10;
                //Toast.makeText(this, "Average is " , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Rest:
                askThenDelete();
                // Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SelectNoOFQns:
                NoofQns();
               // Toast.makeText(this, "SelectNo of questions" + QuizList.questionsList.size(), Toast.LENGTH_SHORT).show();
                return true;
            // default:
            //  return super.onOptionsItemSelected(item);
        }
        // }
        return false;
    }


    void getAverage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        List<String> resultList = fileStorageManager.readAllResults(MainActivity.this);

        int sum = 0;
        int average = 0;
        for (String result : resultList) {
            sum = sum + Integer.parseInt(result);
        }
        if (resultList.size() > 0) {
            average = sum / resultList.size();
        }
        averageMessage = averageMessage.replace("$$correctAnswer$$", average + "").replace("$$noOfAttempts$$", resultList.size() + "");
        builder.setTitle("the average is" + average + "No of Attempts " + resultList.size());
        builder.setMessage(averageMessage);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // fm.writeToDoToFile(MainActivity.this,correctAnswers);
            }

        });

        builder.setNegativeButton("SAVE", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

                // getAllToDoFromDB();

            }

        });

        builder.create().show();

    }


    void askThenDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        resultMessage = resultMessage.replace("$correctAnswer$", correctAnsCnt + "").replace("$totalQuestions$", questionList.size() + "");
        builder.setTitle("Are you sure you want to  Reset the file");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fileStorageManager.deleteAllToDos(MainActivity.this);
                // getAllToDOFromFile();
            }
        });
        builder.create().show();
    }

    void  NoofQns() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("select No of Questions ");
        builder.setPositiveButton("10Q", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // fileStorageManager.deleteAllToDos(MainActivity.this);
                // getAllToDOFromFile();
                //   Toast.makeText(MainActivity.this, "Number of questions are 5", Toast.LENGTH_SHORT);
                int d = QuizList.questionsList.size();
            }
        });
        builder.create().show();


       /* builder.setNegativeButton("10Q", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {
          int k=QuizList.questionsList.size();
                // getAllToDoFromDB();
               // Toast.makeText(MainActivity.this, "Number of questions are 5", Toast.LENGTH_SHORT);
            }

        });

        builder.create().show();


    }*/
    }
}

