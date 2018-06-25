package com.example.snowa.filmapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.snowa.filmapps.adapters.RecyclerViewAdapter;
import com.example.snowa.filmapps.model.ListaFilmowfull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Films_main extends AppCompatActivity {

    private final String JSON_URL="https://hydramovies.com/api-v2/?source=http://hydramovies.com/api-v2/current-Movie-Data.csv";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<ListaFilmowfull> lstListaFilmowfull;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_main);
        lstListaFilmowfull = new ArrayList<>();
        recyclerView=findViewById(R.id.re_id);
        jsonrequest();
        
    }

    private void jsonrequest() {

        request=new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for(int i=0;1<response.length();i++){
                    try{
                        jsonObject=response.getJSONObject(i);
                        ListaFilmowfull listaFilmowfull=new ListaFilmowfull();
                        listaFilmowfull.setTitle(jsonObject.getString("Title"));
                        listaFilmowfull.setSummary(jsonObject.getString("summary"));
                        listaFilmowfull.setCategory(jsonObject.getString("Categories"));
                        listaFilmowfull.setRating(jsonObject.getString("imdb_rating"));
                        listaFilmowfull.setLanguage(jsonObject.getString("language"));
                        listaFilmowfull.setYear(jsonObject.getInt("movie_year"));
                        listaFilmowfull.setImage_url(jsonObject.getString("Image URL"));
                        lstListaFilmowfull.add(listaFilmowfull);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
setuprecyclerview(lstListaFilmowfull);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue= Volley.newRequestQueue(Films_main.this);
        requestQueue.add(request);
    }



    private void setuprecyclerview(List<ListaFilmowfull> lstListaFilmowfull) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstListaFilmowfull);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
