package com.example.quiztest;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    ArrayList<Question> appQuestionList;
    int questionIndex;
    int correctAnswer;

    public ArrayList<Question> getAppQuestionList() {
        if(appQuestionList==null){
            appQuestionList = new ArrayList<>(0);
        }
        return appQuestionList;
    }

    public void setAppQuestionList(ArrayList<Question> appQuestionBank) {
        this.appQuestionList = appQuestionBank;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
