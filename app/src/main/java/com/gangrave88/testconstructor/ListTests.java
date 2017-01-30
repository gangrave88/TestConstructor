package com.gangrave88.testconstructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListTests extends Activity {

    RealmResults<Test> tests;

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
        tests = realm.where(Test.class).findAll();
        TestAdapterList testAdapterList = new TestAdapterList(this,tests);
        listTests.setAdapter(testAdapterList);

        listTests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Test test = tests.get(position);

                Intent intent = new Intent(ListTests.this, BeginTest.class);
                intent.putExtra("name",test.getName());
                intent.putExtra("diff",test.getDifficult());
                startActivity(intent);
            }
        });
    }

//    @OnItemClick(R.id.list_test)
//    public void clicIrem(int position){
//        Toast.makeText(this,listTests.getItemAtPosition(position).getClass().getName(),Toast.LENGTH_SHORT).show();
//    }
}
