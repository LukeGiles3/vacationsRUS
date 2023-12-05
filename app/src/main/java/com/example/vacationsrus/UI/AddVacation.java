package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationsrus.R;
import com.example.vacationsrus.database.Repository;
import com.example.vacationsrus.entities.Vacation;

public class AddVacation extends AppCompatActivity {
    EditText vacationTitle;
    EditText vacationHotel;
    EditText vacationStartDate;
    EditText vacationEndDate;
    String vacationTitleText;
    String vacationHotelText;
    int vacationStartDateInt;
    int vacationEndDateInt;

    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacations);

        Button vacationsSubmitButton = findViewById(R.id.addVacationFormSubmit);
        vacationsSubmitButton.setOnClickListener(view -> {
            vacationTitle = findViewById(R.id.editTextTitle);
            vacationTitleText = vacationTitle.getText().toString();
            vacationHotel = findViewById(R.id.editTextHotel);
            vacationHotelText = vacationHotel.getText().toString();
            vacationStartDate = findViewById(R.id.editTextStartDate);
            vacationStartDateInt = Integer.parseInt(vacationStartDate.getText().toString());
            vacationEndDate = findViewById(R.id.editTextEndDate);
            vacationEndDateInt = Integer.parseInt(vacationEndDate.getText().toString());

            Repository repo = new Repository(getApplication());
            Vacation vacation = new Vacation(5, vacationTitleText, vacationHotelText, vacationStartDateInt, vacationEndDateInt, 1);
            repo.insert(vacation);

            Intent intent = new Intent(AddVacation.this, Vacations.class);
            startActivity(intent);
        });
    }
}
