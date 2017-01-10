package com.gangrave88.testconstructor;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class Answer extends RealmObject implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answer);
        dest.writeInt(correct?1:0);
    }

    public static final Parcelable.Creator CREATOR=new Parcelable.Creator(){

        @Override
        public Object createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[0];
        }
    };

    private Answer(Parcel parcel) {
        answer = parcel.readString();
        correct = parcel.readInt()==1;
    }
}
