package com.example.vacationsrus.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationsrus.R;

import com.example.vacationsrus.database.Repository;

public class VacationDetails extends AppCompatActivity {
    String vacationTitle;
    String vacationHotel;
    int vacationID;
    int vacationStartDate;
    int vacationEndDate;
    int excursionID;
    EditText editTitle;
    EditText editHotel;
    EditText editStartDate;
    EditText editEndDate;
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details);
        vacationTitle = getIntent().getStringExtra("title");
        vacationID = getIntent().getIntExtra("id", 1);
        vacationHotel = getIntent().getStringExtra("hotel");
        vacationStartDate = getIntent().getIntExtra("startDate", 01/01/2023);
        vacationEndDate = getIntent().getIntExtra("endDate", 01/01/2023);
        editTitle = findViewById(R.id.editTextTitle1);
        editTitle.setText(vacationTitle);
        editHotel = findViewById(R.id.editTextHotel1);
        editHotel.setText(vacationHotel);
        editStartDate = findViewById(R.id.editTextStartDate1);
        editStartDate.setText(Integer.toString(vacationStartDate));
        editEndDate = findViewById(R.id.editTextEndDate1);
        editEndDate.setText(Integer.toString(vacationEndDate));

        repository = new Repository(getApplication());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_details, menu);
        return true;
    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//
//    }
}


