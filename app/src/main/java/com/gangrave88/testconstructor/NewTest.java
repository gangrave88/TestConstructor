package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTest extends Activity{

    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_test);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.new_question)
    public void newQuestion(){
        Intent intent = new Intent(this, NewQuestion.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK) {
            List<Answer> answers = new ArrayList<>();
            answers.add(new Answer(data.getStringExtra("answer1TV"),data.getBooleanExtra("answer1Ch",false)));
            answers.add(new Answer(data.getStringExtra("answer2TV"),data.getBooleanExtra("answer2Ch",false)));
            answers.add(new Answer(data.getStringExtra("answer3TV"),data.getBooleanExtra("answer3Ch",false)));
            answers.add(new Answer(data.getStringExtra("answer4TV"),data.getBooleanExtra("answer4Ch",false)));
            Question question = new Question(data.getStringExtra("questianTV"),answers);

            questions.add(question);
        }
    }
}
