package com.gangrave88.testconstructor;

import android.widget.Toast;

import java.util.Date;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class MigrationRealm implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema realmSchema = realm.getSchema();

        if(oldVersion == 0){
            RealmObjectSchema testShema = realmSchema.get("Test");

            testShema.addField("difficult",int.class).transform(new RealmObjectSchema.Function(){
                @Override
                public void apply(DynamicRealmObject obj) {
                    obj.set("difficult",obj.getInt("complexity"));
                }
            }).removeField("complexity");
            oldVersion++;
        }

        if (oldVersion==1){
            RealmObjectSchema testSchema = realmSchema.get("Test");

            testSchema.addField("id",int.class);
            testSchema.addField("dateCreate", Date.class);
            testSchema.addField("currentQuestion", Question.class);
            testSchema.addField("complete", boolean.class);

            RealmObjectSchema questionSchema = realmSchema.get("Question");

            questionSchema.addField("currentAnswer",Answer.class);
            oldVersion++;
        }
    }
}
