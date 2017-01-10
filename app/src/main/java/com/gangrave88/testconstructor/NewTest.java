package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

public class NewTest extends Activity{

    RealmList<Question> questions;
    @BindView(R.id.name_test)TextView name_test;
    @BindView(R.id.complexity)SeekBar complexity;
    @BindView(R.id.question_list) ListView question_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_test);

        ButterKnife.bind(this);

        setQuestionsList();
    }

    @OnClick(R.id.new_question)
    public void newQuestion(){
        Intent intent = new Intent(this, NewQuestionList.class);
        startActivityForResult(intent,1);
        setQuestionsList();
    }

    @OnClick(R.id.save_test)
    public void saveTest(){
        Realm realm = Realm.getDefaultInstance();
        Test test = new Test(name_test.toString(),complexity.getProgress(),questions);

        realm.beginTransaction();
        realm.copyToRealm(test);
        realm.commitTransaction();

        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK) {
            RealmList<Answer> answers = new RealmList<>();
            answers.add(new Answer(data.getStringExtra("answer1TV"),data.getBooleanExtra("answer1Ch",false)));
            answers.add(new Answer(data.getStringExtra("answer2TV"),data.getBooleanExtra("answer2Ch",false)));
            answers.add(new Answer(data.getStringExtra("answer3TV"),data.getBooleanExtra("answer3Ch",false)));
            answers.add(new Answer(data.getStringExtra("answer4TV"),data.getBooleanExtra("answer4Ch",false)));
            Question question = new Question(data.getStringExtra("questianTV"),answers);

            questions.add(question);
        }
    }

    private void setQuestionsList(){
//        QuestionAdapter questionAdapter = new QuestionAdapter(this,questions);
//        question_list.setAdapter(questionAdapter);
    }
}
