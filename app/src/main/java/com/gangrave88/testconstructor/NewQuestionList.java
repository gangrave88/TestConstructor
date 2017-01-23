package com.gangrave88.testconstructor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

public class NewQuestionList extends Activity {

    RealmList<Question> questions;
    int currentItem;
    String name;
    int complexity;

    @BindView(R.id.previous)
    Button btnPrevious;
    @BindView(R.id.question)
    EditText questionET;
    @BindView(R.id.answer1)
    EditText answer1ET;
    @BindView(R.id.answer2)
    EditText answer2ET;
    @BindView(R.id.answer3)
    EditText answer3ET;
    @BindView(R.id.answer4)
    EditText answer4ET;
    @BindView(R.id.number)
    TextView numberTV;
    @BindView(R.id.answer_correct1)
    CheckBox chAnswerCorrect1;
    @BindView(R.id.answer_correct2)
    CheckBox chAnswerCorrect2;
    @BindView(R.id.answer_correct3)
    CheckBox chAnswerCorrect3;
    @BindView(R.id.answer_correct4)
    CheckBox chAnswerCorrect4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_new_question);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        complexity = intent.getIntExtra("complexity",3);

        questions = new RealmList<>();
        currentItem = 0;

        btnEnableDisable();

        updateNumber();
    }

    @OnClick(R.id.save_question)
    public void saveQuestion() {
        if(currentItem>questions.size()-1){
            createQuestion();
        }
        else {
            updateQuestion();
        }
        //Write to DB
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Test test = realm.createObject(Test.class);
        test.setName(name);
        test.setComplexity(complexity);
        test.setQuestions(questions);
        realm.commitTransaction();
    }

    @OnClick(R.id.next)
    public void nextQuestion() {
//        if (checkView()) {
        checkUpdateCreate();
        currentItem++;
        checkClearRead();
        btnEnableDisable();
//        }
//        else {
//            masage();
//        }
        updateNumber();
    }

    @OnClick(R.id.previous)
    public void previousQuestion() {
//        if (!checkAllEmpty()) {
//            ask();
//        }
        currentItem--;
        checkClearRead();
        btnEnableDisable();
        updateNumber();
    }

    private void btnEnableDisable() {
        if (currentItem == 0) {
            btnPrevious.setEnabled(false);
        } else {
            btnPrevious.setEnabled(true);
        }
    }

//    private void masage(){
//        Toast.makeText(this,"Не все поля заполнены!",Toast.LENGTH_SHORT).show();
//    }

    private void checkUpdateCreate() {
        if (questions.isEmpty() || currentItem > questions.size() - 1) {
            createQuestion();
        } else {
            updateQuestion();
        }
    }

    private void checkClearRead() {
        if (currentItem <= questions.size() - 1) {
            readQuestion();
        } else {
            clearView();
        }
    }

    private void clearView() {
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

//    private boolean checkAllEmpty(){
//        return questionET.toString().isEmpty() && answer1ET.toString().isEmpty() &&
//                answer2ET.toString().isEmpty() && answer3ET.toString().isEmpty() &&
//                answer4ET.toString().isEmpty() && chAnswerCorrect1.isChecked()==false &&
//                chAnswerCorrect2.isChecked()==false && chAnswerCorrect3.isChecked()==false &&
//                chAnswerCorrect4.isChecked()==false;
//    }

//    private boolean checkView(){
//        boolean ok=true;
//
//        if (questionET.toString().isEmpty()) ok=false;
//        if (answer1ET.toString().isEmpty()) ok=false;
//        if (answer2ET.toString().isEmpty()) ok=false;
//        if (answer3ET.toString().isEmpty()) ok=false;
//        if (answer4ET.toString().isEmpty()) ok=false;
//        if (chAnswerCorrect1.isChecked()==false && chAnswerCorrect2.isChecked()==false &&
//                chAnswerCorrect3.isChecked()==false && chAnswerCorrect4.isChecked()==false) ok=false;
//
//        return ok;
//    }

    private void createQuestion() {
        RealmList<Answer> answers = new RealmList<>();
        answers.add(new Answer(answer1ET.getText().toString(), chAnswerCorrect1.isChecked()));
        answers.add(new Answer(answer2ET.getText().toString(), chAnswerCorrect2.isChecked()));
        answers.add(new Answer(answer3ET.getText().toString(), chAnswerCorrect3.isChecked()));
        answers.add(new Answer(answer4ET.getText().toString(), chAnswerCorrect4.isChecked()));
        questions.add(new Question(questionET.getText().toString(), answers));
    }

    private void updateQuestion() {
        RealmList<Answer> answers = new RealmList<>();
        answers.add(new Answer(answer1ET.getText().toString(), chAnswerCorrect1.isChecked()));
        answers.add(new Answer(answer2ET.getText().toString(), chAnswerCorrect2.isChecked()));
        answers.add(new Answer(answer3ET.getText().toString(), chAnswerCorrect3.isChecked()));
        answers.add(new Answer(answer4ET.getText().toString(), chAnswerCorrect4.isChecked()));
        questions.set(currentItem, new Question(questionET.getText().toString(), answers));
    }

    private void readQuestion() {
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

//    private void ask(){
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setTitle("Сохранить вопрос?");
//        alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                createQuestion();
//            }
//        });
//        alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//        alertDialog.show();
//    }

    private void updateNumber() {
        numberTV.setText(String.valueOf(currentItem + 1));
    }
}