package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vacationsrus.R;
import com.example.vacationsrus.database.Repository;
import com.example.vacationsrus.entities.Excursion;
import com.example.vacationsrus.entities.Vacation;

public class ExcursionDetails extends AppCompatActivity {
    String excursionTitle;
    String excursionStartDate;
    String excursionEndDate;
    int excursionID;
    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    Repository repository = new Repository(getApplication());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_details);

        excursionTitle = getIntent().getStringExtra("title");
        excursionStartDate = getIntent().getStringExtra("startDate");
        excursionEndDate = getIntent().getStringExtra("endDate");
        excursionID = getIntent().getIntExtra("id", 1);
        editTitle = findViewById(R.id.editTextExcursionDetailsTitle);
        editTitle.setText(excursionTitle);
        editStartDate = findViewById(R.id.editTextStartDateExcursionDetails);
        editStartDate.setText(excursionStartDate);
        editEndDate = findViewById(R.id.editTextEndDateExcursionDetails);
        editEndDate.setText(excursionEndDate);

        Button excursionsDetailsSaveButton = findViewById(R.id.buttonExcursionDetailsSave);
        excursionsDetailsSaveButton.setOnClickListener(view -> {
            Excursion excursion = repository.getExcursionByID(excursionID);
            excursion.setExcursionTitle(editTitle.getText().toString());
            excursion.setExcursionStartDate(editStartDate.getText().toString());
            excursion.setExcursionEndDate(editEndDate.getText().toString());
            repository.update(excursion);
            Toast.makeText(getApplicationContext(), "Excursion updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExcursionDetails.this, Excursions.class);
            startActivity(intent);
        });

        Button excursionsDetailsDeleteButton = findViewById(R.id.buttonExcursionDetailsDelete);
        excursionsDetailsDeleteButton.setOnClickListener(view -> {
            Excursion excursion = repository.getExcursionByID(excursionID);
            repository.delete(excursion);
            Toast.makeText(getApplicationContext(), "Excursion deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExcursionDetails.this, Excursions.class);
            startActivity(intent);
        });
    }
}
