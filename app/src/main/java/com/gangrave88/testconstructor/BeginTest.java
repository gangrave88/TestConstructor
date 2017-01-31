package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class BeginTest extends Activity {

    String name;

    @BindView(R.id.test_name)TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_test);

        ButterKnife.bind(this);

        Realm.init(this);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        textName.setText(name);
    }

    @OnClick(R.id.begin_test)
    public void beginTest(){
        Intent intent = new Intent(this,Testing.class);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
