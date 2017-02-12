package com.gangrave88.testconstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Test extends RealmObject{
    @PrimaryKey
    public int id;

    public String name;
    public int difficult;
    public Date dateCreate;
    public RealmList<Question> questions;
    public Question currentQuestion;
    public boolean complete;

    public Test(){
        super();
    }

    public Test(String name, int difficult, RealmList<Question> questions) {
        this.name = name;
        this.difficult = difficult;
        this.questions = questions;
        this.dateCreate = new Date();
    }

    public Test(String name, int difficult, RealmList<Question> questions, Date dateCreate) {
        this.questions = questions;
        this.difficult = difficult;
        this.name = name;
        this.dateCreate = dateCreate;
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

    public int getQuestionCount(){
        return questions.size();
    }

    public String getDateCreate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return dateFormat.format(dateCreate);
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}