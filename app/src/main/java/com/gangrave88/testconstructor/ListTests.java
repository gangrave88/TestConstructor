package com.gangrave88.testconstructor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListTests extends Activity {

    @BindView(R.id.list_tests_1)
    ListView listTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tests);

        Realm.init(this);

        ButterKnife.bind(this);
        updateList();
    }

    private void updateList(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Test> tests = realm.where(Test.class).findAll();
        TestAdapterList testAdapterList = new TestAdapterList(this,tests);
        listTests.setAdapter(testAdapterList);
    }
}
