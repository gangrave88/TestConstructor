package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import io.realm.Realm;


public class BeginTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_new_question);

        ButterKnife.bind(this);

        Realm.init(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
    }
}
