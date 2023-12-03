package com.example.quiztest;

import android.content.Context;

import java.util.ArrayList;

public class QuestionBank {
    ArrayList<Question> questionsList =new  ArrayList<>();

    public ArrayList<Question> getQuestionsList(Context context) {


            Question question1=new Question(context.getResources().getString(R.string.question1),true,R.color.Magenta);
            questionsList.add(question1);
            Question question2 = new Question(context.getResources().getString(R.string.question2),false,R.color.Coral);
            questionsList.add(question2);
            Question question3 =new Question(context.getResources().getString(R.string.question3),true,R.color.DarkSalmon);
            questionsList.add(question3);
            Question question4=new Question(context.getResources().getString(R.string.question4),false,R.color.DarkSalmon);
            questionsList.add(question4);
            Question question5=new Question(context.getResources().getString(R.string.question5),false,R.color.BurlyWood);
            questionsList.add(question5);
            Question question6=new Question(context.getResources().getString(R.string.question6),false,R.color.CornflowerBlue);
            questionsList.add(question6);
            Question question7=new Question(context.getResources().getString(R.string.question7),false,R.color.Coral);
            questionsList.add(question7);
            Question question8=new Question(context.getResources().getString(R.string.question8),true,R.color.Gainsboro);
            questionsList.add(question8);
            Question question9=new Question(context.getResources().getString(R.string.question9),true,R.color.Magenta);
            questionsList.add(question9);
            Question question10=new Question(context.getResources().getString(R.string.question10),true,R.color.DarkOliveGreen);
            questionsList.add(question10);


        return questionsList;
    }
}
