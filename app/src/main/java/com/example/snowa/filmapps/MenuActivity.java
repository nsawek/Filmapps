package com.example.snowa.filmapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.snowa.filmapps.activities.MainActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttons=findViewById(R.id.buttons);
        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(MenuActivity.this, ListaF_Activity.class);
                startActivity(intent);}
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);}
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(MenuActivity.this, RuleActivity.class);
                startActivity(intent);}
        });

        Button button5=findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(MenuActivity.this, HelpActivity.class);
                startActivity(intent);}
        });

        Button button6=findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(intent);}
        });

        Button button7=findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
                startActivity(intent);}
        });


    }
}
