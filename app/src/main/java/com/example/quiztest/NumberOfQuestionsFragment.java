package com.example.quiztest;

import android.widget.RadioButton;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class NumberOfQuestionsFragment extends DialogFragment {
    interface FragmentListener{
        void setQuestionCount(int count);
    }
    //RadioButton rb;
}
