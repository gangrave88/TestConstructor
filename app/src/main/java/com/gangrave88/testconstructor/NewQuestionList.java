package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;

public class NewQuestionList extends Activity{

    ArrayList<Question> questions;
    int currentItem;

    @BindView(R.id.previous)Button btnPrevious;
    @BindView(R.id.question)EditText questionET;
    @BindView(R.id.answer1)EditText answer1ET;
    @BindView(R.id.answer2)EditText answer2ET;
    @BindView(R.id.answer3)EditText answer3ET;
    @BindView(R.id.answer4)EditText answer4ET;
    @BindView(R.id.answer_correct1)CheckBox chAnswerCorrect1;
    @BindView(R.id.answer_correct2)CheckBox chAnswerCorrect2;
    @BindView(R.id.answer_correct3)CheckBox chAnswerCorrect3;
    @BindView(R.id.answer_correct4)CheckBox chAnswerCorrect4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_new_question);

        ButterKnife.bind(this);

        questions = new ArrayList<>();
        currentItem = 0;
    }

    @OnClick(R.id.save_question)
    public void saveQuestion(){
//        Intent intent = new Intent();
//        intent.putParcelableArrayListExtra("questions",questions);
//        setResult(RESULT_OK,intent);
//        finish();
    }

    @OnClick(R.id.next)
    public void nextQuestion(){
        if (checkView()) {
            checkUpdateCreate();
            currentItem++;
            checkClearRead();
        }
        else {
            masage();
        }
    }

    @OnClick(R.id.previous)
    public void previousQuestion(){
        if(checkView()){
            checkUpdateCreate();
            currentItem--;
            checkClearRead();
        }
        else{
            masage();
        }
    }

    private void masage(){
        Toast.makeText(this,"Не все поля заполнены!",Toast.LENGTH_SHORT).show();
    }

    private void checkUpdateCreate(){
        if(questions.isEmpty() || currentItem>questions.size()-1) {
            createQuestion();
        }
        else{
            updateQuestion();
        }
    }

    private void checkClearRead(){
        if(currentItem<=questions.size()-1){
            readQuestion();
        }
        else{
            clearView();
        }
    }

    private void clearView(){
        questionET.setText("");
        answer1ET.setText("");
        answer2ET.setText("");
        answer3ET.setText("");
        answer4ET.setText("");
        chAnswerCorrect1.setChecked(false);
        chAnswerCorrect2.setChecked(false);
        chAnswerCorrect3.setChecked(false);
        chAnswerCorrect4.setChecked(false);
    }

    private boolean checkView(){
        boolean ok=true;

        if (questionET.toString().isEmpty()) ok=false;
        if (answer1ET.toString().isEmpty()) ok=false;
        if (answer2ET.toString().isEmpty()) ok=false;
        if (answer3ET.toString().isEmpty()) ok=false;
        if (answer4ET.toString().isEmpty()) ok=false;
        if (chAnswerCorrect1.isChecked()==false && chAnswerCorrect2.isChecked()==false &&
                chAnswerCorrect3.isChecked()==false && chAnswerCorrect4.isChecked()==false) ok=false;

        return ok;
    }

    private void createQuestion(){
        RealmList<Answer> answers = new RealmList<>();
        answers.add(new Answer(answer1ET.getText().toString(),chAnswerCorrect1.isChecked()));
        answers.add(new Answer(answer2ET.getText().toString(),chAnswerCorrect2.isChecked()));
        answers.add(new Answer(answer3ET.getText().toString(),chAnswerCorrect3.isChecked()));
        answers.add(new Answer(answer4ET.getText().toString(),chAnswerCorrect4.isChecked()));
        questions.add(new Question(questionET.getText().toString(),answers));
    }

    private void updateQuestion(){
        RealmList<Answer> answers = new RealmList<>();
        answers.add(new Answer(answer1ET.getText().toString(),chAnswerCorrect1.isChecked()));
        answers.add(new Answer(answer2ET.getText().toString(),chAnswerCorrect2.isChecked()));
        answers.add(new Answer(answer3ET.getText().toString(),chAnswerCorrect3.isChecked()));
        answers.add(new Answer(answer4ET.getText().toString(),chAnswerCorrect4.isChecked()));
        questions.set(currentItem, new Question(questionET.getText().toString(),answers));
    }

    private void readQuestion(){
        Question currentQuestion = questions.get(currentItem);
        questionET.setText(currentQuestion.getQuestion());
        RealmList<Answer> answers = currentQuestion.getAnswers();
        answer1ET.setText(answers.get(0).getAnswer());
        answer2ET.setText(answers.get(1).getAnswer());
        answer3ET.setText(answers.get(2).getAnswer());
        answer4ET.setText(answers.get(3).getAnswer());
        chAnswerCorrect1.setChecked(answers.get(0).isCorrect());
        chAnswerCorrect2.setChecked(answers.get(1).isCorrect());
        chAnswerCorrect3.setChecked(answers.get(2).isCorrect());
        chAnswerCorrect4.setChecked(answers.get(3).isCorrect());
    }
}
