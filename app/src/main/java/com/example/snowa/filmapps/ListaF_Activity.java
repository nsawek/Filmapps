package com.example.snowa.filmapps;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ListaF_Activity extends AppCompatActivity {
TextView tvYear,tvActors,tvDirector,tvCountry,tvLanguage,tvPlot;
ImageView ivPoster;
EditText edMName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_f_);

        edMName=findViewById(R.id.edMName);
        tvYear=findViewById(R.id.tvYear);
        tvActors=findViewById(R.id.tvActors);
        tvDirector=findViewById(R.id.tvDirector);
        tvCountry=findViewById(R.id.tvCountry);
        tvLanguage=findViewById(R.id.tvLanguage);
        tvPlot=findViewById(R.id.tvPlot);
        ivPoster=findViewById(R.id.ivPoster);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void search(View view) {
        String mName=edMName.getText().toString();
        if(mName.isEmpty()){
            edMName.setError("Sorry, ale ten film nie istnieje szukaj dalej.");
            return;
        }

        String url = "http://www.omdbapi.com/?t=" + mName + "&plot=full&apikey=9259e2f8";
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject movie=new JSONObject(response);

                            String result =movie.getString("Response");
                            if(result.equals("True"))
                            {
                                String year=movie.getString("Year");
                                tvYear.setText("Rok: " + year);
                                String director=movie.getString("Director");
                                tvDirector.setText("Reżyszer: " + director);
                                String actors=movie.getString("Actors");
                                tvActors.setText("Aktorzy: "+actors);
                                String country=movie.getString("Country");
                                tvCountry.setText("Kraj powstania: "+ country);
                                String language=movie.getString("Language");
                                tvLanguage.setText("Język: "+language );
                                String plot=movie.getString("Plot");
                                tvPlot.setText("Opis: "+ plot);

                                String posterUrl=movie.getString("Poster");
                                if(posterUrl.equals("N/A"))
                                {

                                }
                                else
                                {
                                    Picasso.get().load(posterUrl).into(ivPoster);
                                 }


                            }
                            else{
                                Toast.makeText(ListaF_Activity.this, "Sorry, ale tego filmu nie ma w bazie.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(request);
    }
}