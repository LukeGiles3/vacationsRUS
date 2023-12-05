package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationsrus.DateValidation;
import com.example.vacationsrus.DateValidator;
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
    String vacationStartDateText;
    String vacationEndDateText;
    DateValidator dateValidator;
    String dateValidationError;

    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vacations);
        dateValidator = new DateValidation();


        Button vacationsSubmitButton = findViewById(R.id.addVacationFormSubmit);
        vacationsSubmitButton.setOnClickListener(view -> {
            vacationTitle = findViewById(R.id.editTextTitle);
            vacationTitleText = vacationTitle.getText().toString();
            vacationHotel = findViewById(R.id.editTextHotel);
            vacationHotelText = vacationHotel.getText().toString();
            vacationStartDate = findViewById(R.id.editTextStartDate);
            vacationStartDateText = vacationStartDate.getText().toString();
            vacationEndDate = findViewById(R.id.editTextEndDate);
            vacationEndDateText = vacationEndDate.getText().toString();
            if (dateValidator.isValid(vacationStartDateText) && dateValidator.isValid(vacationEndDateText)) {
                Repository repo = new Repository(getApplication());
                Vacation vacation = new Vacation(vacationTitleText, vacationHotelText, vacationStartDateText, vacationEndDateText, 1);
                repo.insert(vacation);

                Intent intent = new Intent(AddVacation.this, Vacations.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Please use the correct date format", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
