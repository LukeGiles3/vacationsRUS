package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationsrus.DateValidation;
import com.example.vacationsrus.DateValidator;
import com.example.vacationsrus.R;
import com.example.vacationsrus.database.Repository;
import com.example.vacationsrus.entities.Excursion;
import com.example.vacationsrus.entities.Title;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddExcursion extends AppCompatActivity {
    EditText excursionTitle;
    EditText excursionStartDate;
    EditText excursionEndDate;
    Spinner excursionVacationID;
    String excursionTitleText;
    String excursionStartDateText;
    String excursionEndDateText;
    List<Title> vacationsArray;
    int excursionVacationIDInt;
    Date excursionStartDateDate;
    Date excursionEndDateDate;
    DateValidator dateValidator;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excursion);

        excursionVacationID = findViewById(R.id.dropDownAddVacation);
        repository = new Repository(getApplication());
        vacationsArray = repository.getmAllVacationTitles();
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vacationsArray);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        excursionVacationID.setAdapter(ad);

        dateValidator = new DateValidation();
        Button excursionSubmitButton = findViewById(R.id.addExcursionFormSubmit);
        excursionSubmitButton.setOnClickListener(view -> {
            excursionTitle = findViewById(R.id.excursionEditTextTitle);
            excursionTitleText = excursionTitle.getText().toString();
            excursionStartDate = findViewById(R.id.excursionEditTextStartDate);
            excursionStartDateText = excursionStartDate.getText().toString();
            excursionEndDate = findViewById(R.id.excursionEditTextEndDate);
            excursionEndDateText = excursionEndDate.getText().toString();
            excursionVacationID.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

            if (dateValidator.isValid(excursionStartDateText) && dateValidator.isValid(excursionEndDateText)) {
                try {
                    excursionStartDateDate = new SimpleDateFormat("yyyy-MM-dd").parse(excursionStartDateText);
                    excursionEndDateDate = new SimpleDateFormat("yyyy-MM-dd").parse(excursionEndDateText);

                    if (excursionStartDateDate.compareTo(excursionEndDateDate) > 0) {
                        Toast.makeText(getApplicationContext(), "The start date must be before the end date", Toast.LENGTH_SHORT).show();
                    } else {
                        Repository repo = new Repository(getApplication());
                        Excursion excursion = new Excursion(excursionTitleText, excursionStartDateText, excursionEndDateText, excursionVacationIDInt);
                        repo.insert(excursion);

                        Intent intent = new Intent(AddExcursion.this, Excursions.class);
                        startActivity(intent);
                    }
                } catch (ParseException e) {

                }
            } else {
                Toast.makeText(getApplicationContext(), "Please use the correct date format", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
         parent.getItemAtPosition(pos);
    }
}
