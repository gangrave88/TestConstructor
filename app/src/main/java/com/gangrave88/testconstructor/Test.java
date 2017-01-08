package com.gangrave88.testconstructor;

import java.util.List;

public class Test{
    public String name;
    public int complexity;
    public List<Question> questions;

    public Test(String name, int complexity, List<Question> questions) {
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

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
