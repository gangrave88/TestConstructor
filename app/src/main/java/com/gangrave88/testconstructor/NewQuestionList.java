package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

//        btnEnabled();
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
        if (checkView()){
            createQuestion();
            currentItem++;
            readQuestion();
        }
        else Toast.makeText(this,"Не все поля заполнены!",Toast.LENGTH_SHORT).show();
//        btnEnabled();
    }

    @OnClick(R.id.previous)
    public void previousQuestion(){
        currentItem--;
        readQuestion();
//        btnEnabled();
    }

//    private void btnEnabled(){
//        if (currentItem==0 || questions.size()==0) btnPrevious.setEnabled(false);
//    }

    private void readQuestion(){
        Question currentQuestion = questions.get(currentItem);
        if (currentQuestion==null){
            questionET.setText("");
            answer1ET.setText("");
            answer2ET.setText("");
            answer3ET.setText("");
            answer4ET.setText("");
            chAnswerCorrect1.setEnabled(false);
            chAnswerCorrect2.setEnabled(false);
            chAnswerCorrect3.setEnabled(false);
            chAnswerCorrect4.setEnabled(false);
        }
        else{
            questionET.setText(currentQuestion.getQuestion());
            List<Answer> answers = currentQuestion.getAnswers();
            answer1ET.setText(answers.get(0).getAnswer());
            answer2ET.setText(answers.get(1).getAnswer());
            answer3ET.setText(answers.get(2).getAnswer());
            answer4ET.setText(answers.get(3).getAnswer());
            chAnswerCorrect1.setEnabled(answers.get(0).isCorrect());
            chAnswerCorrect2.setEnabled(answers.get(1).isCorrect());
            chAnswerCorrect3.setEnabled(answers.get(2).isCorrect());
            chAnswerCorrect4.setEnabled(answers.get(3).isCorrect());
        }
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
        Question currentQuestion;
        if (questions.isEmpty()) {currentQuestion=null;}
        else {currentQuestion = questions.get(currentItem);}
        if (currentQuestion==null){
            RealmList<Answer> answers = new RealmList<>();
            answers.add(new Answer(answer1ET.toString(),chAnswerCorrect1.isChecked()));
            answers.add(new Answer(answer2ET.toString(),chAnswerCorrect2.isChecked()));
            answers.add(new Answer(answer3ET.toString(),chAnswerCorrect3.isChecked()));
            answers.add(new Answer(answer4ET.toString(),chAnswerCorrect4.isChecked()));
            questions.add(new Question(questionET.toString(),answers));
        }
        else {
            currentQuestion.setQuestion(questionET.toString());
            currentQuestion.answers.get(0).setAnswer(answer1ET.toString());
            currentQuestion.answers.get(1).setAnswer(answer2ET.toString());
            currentQuestion.answers.get(2).setAnswer(answer3ET.toString());
            currentQuestion.answers.get(3).setAnswer(answer4ET.toString());
            currentQuestion.answers.get(0).setCorrect(chAnswerCorrect1.isChecked());
            currentQuestion.answers.get(1).setCorrect(chAnswerCorrect2.isChecked());
            currentQuestion.answers.get(2).setCorrect(chAnswerCorrect3.isChecked());
            currentQuestion.answers.get(3).setCorrect(chAnswerCorrect4.isChecked());
        }
    }
}
