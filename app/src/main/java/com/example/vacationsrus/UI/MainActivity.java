package com.example.vacationsrus.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vacationsrus.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button vacationsButton = findViewById(R.id.goToVacationsButton);
        Button excursionsButton = findViewById(R.id.goToExcursionsButton);
        vacationsButton.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Vacations.class);
            startActivity(intent);
        }
        });

        excursionsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Excursions.class);
                startActivity(intent);
            }
        });
    }
}