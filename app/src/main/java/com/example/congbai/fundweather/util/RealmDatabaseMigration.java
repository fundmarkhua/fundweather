package com.example.congbai.fundweather.util;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by fundmarkhua on 2017/2/28
 * Email:57525101@qq.com
 */

public class RealmDatabaseMigration implements io.realm.RealmMigration {

    /**
     * 数据库迁移方法范例  需要的时候编写
     *
     * @param realm
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        // Access the Realm schema in order to create, modify or delete classes and their fields.
        RealmSchema schema = realm.getSchema();

        /************************************************
         // Version 0
         class Person
         @Required
         String firstName;
         @Required
         String lastName;
         int    age;

         // Version 1
         class Person
         @Required
         String fullName;            // combine firstName and lastName into single field.
         int age;
         ************************************************/
        // Migrate from version 0 to version 1
        if (oldVersion == 0) {
            RealmObjectSchema personSchema = schema.get("Person");

            // Combine 'firstName' and 'lastName' in a new field called 'fullName'
            personSchema
                    .addField("fullName", String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("fullName", obj.getString("firstName") + " " + obj.getString("lastName"));
                        }
                    })
                    .removeField("firstName")
                    .removeField("lastName");
            oldVersion++;
        }
        /************************************************
         // Version 2
         class Pet                   // add a new model class
         @Required
         String name;
         String type;

         class Person
         @Required
         String fullName;
         int age;
         RealmList<Pet> pets;    // add an array property

         ************************************************/
        // Migrate from version 1 to version 2
        if (oldVersion == 1) {

            // Create a new class
            RealmObjectSchema petSchema = schema.create("Pet")
                    .addField("name", String.class, FieldAttribute.REQUIRED)
                    .addField("type", String.class, FieldAttribute.REQUIRED);

            // Add a new field to an old class and populate it with initial data
            schema.get("Person")
                    .addRealmListField("pets", petSchema)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            if (obj.getString("fullName").equals("JP McDonald")) {
                                DynamicRealmObject pet = realm.createObject("Pet");
                                pet.setString("name", "Jimbo");
                                pet.setString("type", "dog");
                                obj.getList("pets").add(pet);
                            }
                        }
                    });
            oldVersion++;
        }
    }

}
