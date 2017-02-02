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
    Test currentTest;
    Question currentQuestion;
    Realm realm;

    RealmList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        Realm.init(this);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");

        realm = Realm.getDefaultInstance();
        currentTest = realm.where(Test.class).equalTo("name",name).findFirst();
        questions = currentTest.getQuestions();

        currentId = 0;

        getNextQuestion();
    }

    private void getNextQuestion(){
        if (questions.size()-1>=currentId) {
            currentQuestion = questions.get(currentId);

            questionTV.setText(currentQuestion.getQuestion());
            answerB1.setText(currentQuestion.answers.get(0).getAnswer());
            answerB2.setText(currentQuestion.answers.get(1).getAnswer());
            answerB3.setText(currentQuestion.answers.get(2).getAnswer());
            answerB4.setText(currentQuestion.answers.get(3).getAnswer());
        }
        else endTest();
    }

    private void endTest(){
        realm.beginTransaction();
        currentTest.setComplete(true);
        realm.commitTransaction();
    }

    @OnClick({R.id.answer1,R.id.answer2,R.id.answer3,R.id.answer4})
    public void clickAnswer(Button btn){
        switch (btn.getId()){
            case(R.id.answer1):{
                answer(0,btn);
                break;
            }
            case(R.id.answer2):{
                answer(1,btn);
                break;
            }
            case(R.id.answer3):{
                answer(2,btn);
                break;
            }
            case(R.id.answer4):{
                answer(3,btn);
                break;
            }
        }
        currentId++;
        getNextQuestion();
    }

    private void answer(int id,Button btn){
        Answer currentAnswer = currentQuestion.getAnswers().get(id);
        if (currentAnswer.isCorrect()){
            btn.setBackgroundResource(R.drawable.correct_answer);
        }
        else{
            btn.setBackgroundResource(R.drawable.uncorrect_answer);
        }
        realm.beginTransaction();
        currentQuestion.setCurrentAnswer(currentAnswer);
        realm.commitTransaction();
    }

    @OnClick(R.id.test_previous_question)
    public void previousQuestion(){
        currentId--;
        getNextQuestion();
    }

    @OnClick(R.id.test_next_question)
    public void nextQuestion(){
        currentId++;
        getNextQuestion();
    }
}
