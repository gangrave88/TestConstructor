package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewQuestion extends Activity implements Serializable{

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
    public void saveQuestion(){
        if (checkQuestian()){
            sendResult();
        }
        else{
            textViewEmpty();
        }
    }

    private void sendResult(){
        Intent intent = new Intent();
        intent.putExtra("question", questianTV.toString());
        intent.putExtra("answer1TV", answer1TV.toString());
        intent.putExtra("answer2TV", answer2TV.toString());
        intent.putExtra("answer3TV", answer3TV.toString());
        intent.putExtra("answer4TV", answer4TV.toString());
        intent.putExtra("answer1Ch", answer1Ch.isChecked());
        intent.putExtra("answer2Ch", answer2Ch.isChecked());
        intent.putExtra("answer3Ch", answer3Ch.isChecked());
        intent.putExtra("answer4Ch", answer4Ch.isChecked());
        setResult(RESULT_OK,intent);
        finish();
    }

    private void textViewEmpty(){
        Toast.makeText(this,"Не все поля заполнены!",Toast.LENGTH_LONG).show();
    }

    private boolean checkQuestian(){
        boolean ok = true;
        if (questianTV.toString().equals(""))ok=false;
        if (answer1TV.toString().equals(""))ok=false;
        if (answer2TV.toString().equals(""))ok=false;
        if (answer3TV.toString().equals(""))ok=false;
        if (answer4TV.toString().equals(""))ok=false;
        if (answer1Ch.isChecked()==false && answer2Ch.isChecked()==false
                && answer3Ch.isChecked()==false && answer4Ch.isChecked()==false) ok=false;
        return ok;
    }
}
