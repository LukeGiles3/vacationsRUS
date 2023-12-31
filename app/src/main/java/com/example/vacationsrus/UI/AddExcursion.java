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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.vacationsrus.DateValidation;
import com.example.vacationsrus.DateValidator;
import com.example.vacationsrus.R;
import com.example.vacationsrus.database.Repository;
import com.example.vacationsrus.entities.Excursion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddExcursion extends AppCompatActivity {
    EditText excursionTitle;
    EditText excursionDate;
    Spinner excursionVacationID;
    String excursionTitleText;
    String excursionDateText;
    int selectedVacationID;
    Date excursionDateDate;
    String vacationStartDateText;
    String vacationEndDateText;
    Date vacationStartDateDate;
    Date vacationEndDateDate;
    DateValidator dateValidator;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excursion);

        excursionVacationID = findViewById(R.id.dropDownAddVacation);
        repository = new Repository(getApplication());
        repository.getmAllVacationTitles().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> vacationTitles) {
                ArrayAdapter<String> ad = new ArrayAdapter<>(AddExcursion.this, android.R.layout.simple_spinner_item, vacationTitles);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                excursionVacationID.setAdapter(ad);
                excursionVacationID.setSelection(0);
            }

        });
        excursionVacationID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedVacationTitle = parentView.getItemAtPosition(position).toString();
                selectedVacationID = repository.getVacationIdByTitle(selectedVacationTitle);
                vacationStartDateText = repository.getVacationStartDate(selectedVacationID);
                vacationEndDateText = repository.getVacationEndDate(selectedVacationID);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        dateValidator = new DateValidation();
        Button excursionSubmitButton = findViewById(R.id.addExcursionFormSubmit);
        excursionSubmitButton.setOnClickListener(view -> {
            excursionTitle = findViewById(R.id.excursionEditTextTitle);
            excursionTitleText = excursionTitle.getText().toString();
            excursionDate = findViewById(R.id.excursionEditTextStartDate);
            excursionDateText = excursionDate.getText().toString();

            if (dateValidator.isValid(excursionDateText)) {
                try {
                    excursionDateDate = new SimpleDateFormat("yyyy-MM-dd").parse(excursionDateText);
                    vacationStartDateDate = new SimpleDateFormat("yyyy-MM-dd").parse(vacationStartDateText);
                    vacationEndDateDate = new SimpleDateFormat("yyyy-MM-dd").parse(vacationEndDateText);

                        if (excursionDateDate.compareTo(vacationStartDateDate) < 0 || excursionDateDate.compareTo(vacationEndDateDate) > 0) {
                            Toast.makeText(getApplicationContext(), "Excursion date must be within the selected vacation's dates", Toast.LENGTH_SHORT).show();
                        } else {
                            Repository repo = new Repository(getApplication());
                            Excursion excursion = new Excursion(excursionTitleText, excursionDateText, selectedVacationID, false);
                            repo.insert(excursion);

                            Intent intent = new Intent(AddExcursion.this, Excursions.class);
                            startActivity(intent);
                        }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please use the correct date format", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
