package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class Testing extends Activity {

    @BindView(R.id.question)TextView questionTV;
    @BindView(R.id.answer1)Button answerB1;
    @BindView(R.id.answer2)Button answerB2;
    @BindView(R.id.answer3)Button answerB3;
    @BindView(R.id.answer4)Button answerB4;

    int currentId;
    String name;
    Question currentQuestion;

    RealmList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Realm.init(this);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");

        Realm realm = Realm.getDefaultInstance();
        questions = realm.where(Test.class).equalTo("name",name).findFirst().getQuestions();

        currentId = 0;

        getNextQuestion();
    }

    private void getNextQuestion(){
        currentQuestion = questions.get(currentId);

        questionTV.setText(currentQuestion.getQuestion());
        answerB1.setText(currentQuestion.answers.get(0).getAnswer());
        answerB2.setText(currentQuestion.answers.get(1).getAnswer());
        answerB3.setText(currentQuestion.answers.get(2).getAnswer());
        answerB4.setText(currentQuestion.answers.get(3).getAnswer());
    }

    @OnClick({R.id.answer1,R.id.answer2,R.id.answer3,R.id.answer4})
    public void clickAnswer(Button btn){
        switch (btn.getId()){
            case(R.id.answer1):{
                answer(0);
                break;
            }
            case(R.id.answer2):{
                answer(1);
                break;
            }
            case(R.id.answer3):{
                answer(2);
                break;
            }
            case(R.id.answer4):{
                answer(3);
                break;
            }
        }
        currentId++;
        getNextQuestion();
    }

    private void answer(int id){
        if (currentQuestion.getAnswers().get(id).isCorrect()){
            Toast.makeText(this,"Правельный ответ!!!!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Лузер!!! ))",Toast.LENGTH_SHORT).show();
        }
    }
}
