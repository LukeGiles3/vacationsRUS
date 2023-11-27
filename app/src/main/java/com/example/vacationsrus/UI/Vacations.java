package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationsrus.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Vacations extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacations);
        FloatingActionButton vacationsFloatingActionButton = findViewById(R.id.vacationsFloatingActionButton);

        vacationsFloatingActionButton.setOnClickListener(new View.OnClickListener(){;
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vacations.this, AddVacation.class);
                startActivity(intent);
            }
        });


    }
}
