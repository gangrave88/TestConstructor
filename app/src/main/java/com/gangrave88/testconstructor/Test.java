package com.gangrave88.testconstructor;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Test extends RealmObject{
    public String name;
    public int difficult;
    public RealmList<Question> questions;

    public Test(){
        super();
    }

    public Test(String name, int difficult, RealmList<Question> questions) {
        this.name = name;
        this.difficult = difficult;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public RealmList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<Question> questions) {
        this.questions = questions;
    }
}
