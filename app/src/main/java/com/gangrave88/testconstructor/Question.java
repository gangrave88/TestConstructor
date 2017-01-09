package com.gangrave88.testconstructor;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Question extends RealmObject{
    public String question;
    public RealmList<Answer> answers;

    public Question(String question, RealmList<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(RealmList<Answer> answers) {
        this.answers = answers;
    }
}
