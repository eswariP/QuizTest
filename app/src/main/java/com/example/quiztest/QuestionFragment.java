package com.example.quiztest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionFragment extends Fragment {


   /* interface savingQuestionsClickListener{
        void saveQuestionButtonClicked(String QuestionFragment, Boolean answerFragment);
    }
    savingQuestionsClickListener listener;*/
//    public QuestionFragment(){
//        super(R.layout.fragment_question);
//
//    }
    static String textFromMainActivity ="Java is Object Oriented Language";
    static int colorId ;
    public static QuestionFragment newInstance(String msgQn, int color){
        QuestionFragment qf =new QuestionFragment();
        textFromMainActivity = msgQn;
        colorId =color;
        return qf;
    }

@Nullable
    @Override

public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup
        container, @Nullable Bundle savedInstanceState){
    super.onCreateView(inflater, container, savedInstanceState);
    View v = inflater.inflate(R.layout.fragment_question, container, false);
    TextView text = v.findViewById(R.id.question_text);
    text.setText(textFromMainActivity);
    text.setBackgroundResource(colorId);


    return v;
}

}
