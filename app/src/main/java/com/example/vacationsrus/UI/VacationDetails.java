package com.example.vacationsrus.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationsrus.R;

import com.example.vacationsrus.database.Repository;

public class VacationDetails extends AppCompatActivity {
    String title;
    int vacationID;
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details);
        title = getIntent().getStringExtra("title");
        vacationID = getIntent().getIntExtra("id", 1);
        repository = new Repository(getApplication());
    }
}


