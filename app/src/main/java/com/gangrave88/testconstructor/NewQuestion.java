package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewQuestion extends Activity {

    @BindView(R.id.question)TextView questianTV;
    @BindView(R.id.answer1) TextView answer1TV;
    @BindView(R.id.answer2) TextView answer2TV;
    @BindView(R.id.answer3) TextView answer3TV;
    @BindView(R.id.answer4) TextView answer4TV;
    @BindView(R.id.answer_correct1) CheckBox answer1Ch;
    @BindView(R.id.answer_correct2) CheckBox answer2Ch;
    @BindView(R.id.answer_correct3) CheckBox answer3Ch;
    @BindView(R.id.answer_correct4) CheckBox answer4Ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_question);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.save_question)
    private void seveQuestion(){
        if (checkQuestian()){
            sendResult(createAnsver());
        }
        else{
            textViewEmpty();
        }
    }

    private List<Answer> createAnsver(){
        List<Answer> list = new ArrayList<>();

        list.add(new Answer(answer1TV.toString(),answer1Ch.isChecked()));
        list.add(new Answer(answer2TV.toString(),answer2Ch.isChecked()));
        list.add(new Answer(answer3TV.toString(),answer3Ch.isChecked()));
        list.add(new Answer(answer4TV.toString(),answer4Ch.isChecked()));

        return list;
    }

    private void sendResult(List<Answer> answerList){
        Intent intent = new Intent();
        Question question = new Question(questianTV.toString(),answerList);
        intent.putExtra("list", (Serializable) question);
    }

    private void textViewEmpty(){
        Toast toast = new Toast(this);
        toast.setText("Не все поля заполнены!!!");
        toast.show();
    }

    private boolean checkQuestian(){
        boolean ok = true;
        if (questianTV.equals(""))ok=false;
        if (answer1TV.equals(""))ok=false;
        if (answer2TV.equals(""))ok=false;
        if (answer3TV.equals(""))ok=false;
        if (answer4TV.equals(""))ok=false;
        if (answer1Ch.isChecked()==false && answer2Ch.isChecked()==false
                && answer3Ch.isChecked()==false && answer4Ch.isChecked()==false) ok=false;
        return ok;
    }
}
