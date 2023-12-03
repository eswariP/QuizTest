package com.example.quiztest;

import android.content.Context;

public class Question {
    String questionText;
    boolean answer;

    int color;

    public Question(String questionText, boolean answer, int color) {
        this.questionText = questionText;
        this.answer = answer;
        this.color = color;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
