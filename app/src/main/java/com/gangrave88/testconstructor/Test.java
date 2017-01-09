package com.gangrave88.testconstructor;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Test extends RealmObject{
    public String name;
    public int complexity;
    public RealmList<Question> questions;

    public Test() {
        super();
    }

    public Test(String name, int complexity, RealmList<Question> questions) {
        this.name = name;
        this.complexity = complexity;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<Question> questions) {
        this.questions = questions;
    }
}
