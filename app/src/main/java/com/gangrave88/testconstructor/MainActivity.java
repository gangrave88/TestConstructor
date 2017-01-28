package com.gangrave88.testconstructor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Realm.init(this);

        updateDB();
    }

    private void updateDB(){
        RealmConfiguration conf0 = new RealmConfiguration.Builder().
                schemaVersion(0).
                migration(new MigrationRealm()).
                build();

        Realm realm = Realm.getInstance(conf0);
        realm.close();
    }

    @OnClick(R.id.new_test)
    public void createNewTest(){
        Intent intent = new Intent(this, NewTest.class);
        startActivity(intent);
    }

    @OnClick(R.id.test_read_db)
    public void readDB(){
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Test> testRealmQuery = realm.where(Test.class).findAll();
        for (Test test:testRealmQuery){
            Toast.makeText(this,test.getName(),Toast.LENGTH_SHORT).show();
        }
        realm.close();
    }

    @OnClick(R.id.clear_db)
    public void clearDB(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Test> testRealmQuery = realm.where(Test.class).findAll();
        realm.beginTransaction();
        testRealmQuery.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @OnClick(R.id.list_test)
    public void openListTests(){
        Intent intent = new Intent(this,ListTests.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_settings)
    public void settings(){
        Intent intent = new Intent(this,Settings.class);
        startActivity(intent);
    }
}
