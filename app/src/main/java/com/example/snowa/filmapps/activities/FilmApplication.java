package com.example.snowa.filmapps.activities;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FilmApplication extends Application {

    @Override
    public void onCreate(){

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion( 1 )
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.compactRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);

        super.onCreate();
    }
}
