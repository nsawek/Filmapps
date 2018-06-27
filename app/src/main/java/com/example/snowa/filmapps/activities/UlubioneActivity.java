package com.example.snowa.filmapps.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.snowa.filmapps.R;
import com.example.snowa.filmapps.database.Ulubione;
import com.example.snowa.filmapps.model.Film;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class UlubioneActivity extends AppCompatActivity {

    private List<Film> lstFilm ;
    private RecyclerView recyclerView ;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulubione);


        lstFilm = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);

        Realm realm = Realm.getDefaultInstance ();
        RealmResults<Ulubione> favorites = realm
                .where(Ulubione. class )
                .sort( "date" , Sort. DESCENDING )
                .findAll();
        if (favorites.size() > 0 ) {
            Toast.makeText(this,"Pobrano ulubione",Toast. LENGTH_SHORT).show();
            for (Ulubione ulubione : favorites) {
                Log. d ( "FAV" , ulubione.getTitle() + " - " + ulubione.getYear());
            }
        } else {
            Toast.makeText(this,"Brak ulubionych",Toast.LENGTH_SHORT).show();
        }
    }
}
