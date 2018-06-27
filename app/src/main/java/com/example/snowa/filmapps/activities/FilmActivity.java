package com.example.snowa.filmapps.activities;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.snowa.filmapps.R;
import com.example.snowa.filmapps.database.Ulubione;

import java.util.Date;

import io.realm.Realm;

public class FilmActivity extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private String title ;
    private String len;
    private String time ;
    private String summary ;
    private String rating ;
    private int year;
    private String image_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title  = getIntent().getExtras().getString("t_title");
        summary = getIntent().getExtras().getString("s_summary");
        len = getIntent().getExtras().getString("l_len") ;
        time = getIntent().getExtras().getString("t_time");
        year = getIntent().getExtras().getInt("y_year") ;
        rating = getIntent().getExtras().getString("r_rating") ;
        image_url = getIntent().getExtras().getString("film_img") ;

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_title = findViewById(R.id.t_title);
        TextView tv_len = findViewById(R.id.l_len);
        TextView tv_time = findViewById(R.id.t_time) ;
        TextView tv_summary = findViewById(R.id.s_summary);
        TextView tv_rating  = findViewById(R.id.r_rating) ;
        ImageView img = findViewById(R.id.aa_thumbnail);

        tv_title.setText(title);
        tv_time.setText(time);
        tv_summary.setText(summary);
        tv_rating.setText(rating);
        tv_len.setText(len);

        collapsingToolbarLayout.setTitle(title);
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        Glide.with(this).load(image_url).apply(requestOptions).into(img);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ulubione_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.itemFavorite:
                addRemoveFavorite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addRemoveFavorite(){
        Realm realm = Realm.getDefaultInstance();
        Ulubione ulubione = realm
                .where(Ulubione.class )
                .equalTo( "title" , title )
                .findFirst();
        if (ulubione == null ) {
            // TODO brak w ulubionych
        } else {
            // TODO istnieje w ulubionych
        }
        if (ulubione == null ) {
            addToFavorites(realm);
        } else {
            removeFromFavorites(realm, ulubione);
        }
    }
    private void addToFavorites(Realm realm) {

        realm.executeTransaction( new Realm.Transaction() {
            @Override
            public void execute( @NonNull Realm realm) {
                Ulubione ulubione = realm.createObject(Ulubione. class );
                ulubione.setTitle( title );
                ulubione.setLen(len);
                ulubione.setTime(time);
                ulubione.setSummary(summary);
                ulubione.setRating(rating);
                ulubione.setYear(year);
                ulubione.setImage_url(image_url);
                ulubione.setDate( new Date());
                Toast. makeText (FilmActivity. this , "Dodano do ulubionych" ,
                        Toast. LENGTH_SHORT ).show();
            }
        });
    }
    private void removeFromFavorites(Realm realm, final Ulubione ulubione) {

        realm.executeTransaction( new Realm.Transaction() {
            @Override
            public void execute( @NonNull Realm realm) {
                ulubione.deleteFromRealm();
                Toast. makeText (FilmActivity. this , "Usunięto z ulubionych" ,
                        Toast. LENGTH_SHORT ).show();
            }
        });
    }

    }

