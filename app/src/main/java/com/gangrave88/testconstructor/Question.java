package com.gangrave88.testconstructor;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Question extends RealmObject{
    private String question;
    private Answer currentAnswer;
    private RealmList<Answer> answers;

    public Question(){
        super();
    }

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

    public RealmList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(RealmList<Answer> answers) {
        this.answers = answers;
    }

    public Answer getCurrentAnswer() {
        return currentAnswer;
    }

    public void setCurrentAnswer(Answer currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

    public void setOneAnswer(Answer answer){
        answers.add(answer);
    }

    public boolean answerIsCorrect(String answer){
        for (Answer a:answers){
            if (answer.equals(a)) return true;
        }
        return false;
    }
}
