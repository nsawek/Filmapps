package com.example.snowa.filmapps.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.snowa.filmapps.R;
import com.example.snowa.filmapps.adapters.RecyclerViewAdapter;
import com.example.snowa.filmapps.model.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//pobieranie danych link do jsona
    private final String JSON_URL = "https://hydramovies.com/api-v2/?source=http://hydramovies.com/api-v2/current-Movie-Data.csv" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Film> lstFilm ;
    private RecyclerView recyclerView ;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstFilm = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();

    }

    private void jsonrequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject  = null ;
                for (int i = 0 ; i < response.length(); i++ ) {
//pobieranie danych gdzie String odpowiada temu co jest jako znacznik w jsnonie, ściąga te dane i zapisuje. set z folderu model klasa Film
                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Film film = new Film();
                        film.setTitle(jsonObject.getString("Title"));
                        film.setSummary(jsonObject.getString("summary"));
                        film.setRating(jsonObject.getString("imdb_rating"));
                        film.setTime(jsonObject.getString("runtime"));
                        film.setYear(jsonObject.getInt("movie_year"));
                        film.setLanguage(jsonObject.getString("language"));
                        film.setImage_url(jsonObject.getString("Image URL"));
                        lstFilm.add(film);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(lstFilm);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;
    }

    private void setuprecyclerview(List<Film> lstFilm) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstFilm) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
