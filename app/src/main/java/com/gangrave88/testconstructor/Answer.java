package com.gangrave88.testconstructor;

import io.realm.RealmObject;

public class Answer extends RealmObject{

    public String answer;
    public boolean correct;

    public Answer() {
        super();
    }

    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
