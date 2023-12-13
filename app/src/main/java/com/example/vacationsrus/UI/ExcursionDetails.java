package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.vacationsrus.R;
import com.example.vacationsrus.database.Repository;
import com.example.vacationsrus.entities.Excursion;
import com.example.vacationsrus.entities.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExcursionDetails extends AppCompatActivity {
    String excursionTitle;
    String excursionDate;
    int excursionVacation;
    int excursionID;
    EditText editTitle;
    EditText editDate;
    Spinner excursionVacationID;
    int selectedVacationID;
    boolean excursionReminderState;
    Switch reminderSwitch;
    Repository repository = new Repository(getApplication());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_details);

        excursionVacationID = findViewById(R.id.dropDownAddVacationDetails);
        repository.getmAllVacationTitles().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> vacationTitles) {
                ArrayAdapter<String> ad = new ArrayAdapter<>(ExcursionDetails.this, android.R.layout.simple_spinner_item, vacationTitles);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                excursionVacationID.setAdapter(ad);
            }

        });
        excursionVacationID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedVacationTitle = parentView.getItemAtPosition(position).toString();
                selectedVacationID = repository.getVacationIdByTitle(selectedVacationTitle);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        excursionTitle = getIntent().getStringExtra("title");
        excursionDate = getIntent().getStringExtra("startDate");
        excursionID = getIntent().getIntExtra("id", 1);
        excursionVacation = getIntent().getIntExtra("vacationID", 1);
        editTitle = findViewById(R.id.editTextExcursionDetailsTitle);
        editTitle.setText(excursionTitle);
        editDate = findViewById(R.id.editTextStartDateExcursionDetails);
        editDate.setText(excursionDate);
        reminderSwitch = findViewById(R.id.excursionDetailsSwitch);
        reminderSwitch.setChecked(repository.getExcursionReminderState(excursionID));
        reminderSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                excursionReminderState = true;
                reminderSwitch.setChecked(true);
                setReminders();
            } else {
                excursionReminderState = false;
                reminderSwitch.setChecked(false);
            }
        }));

        Button excursionsDetailsSaveButton = findViewById(R.id.buttonExcursionDetailsSave);
        excursionsDetailsSaveButton.setOnClickListener(view -> {
            Excursion excursion = repository.getExcursionByID(excursionID);
            excursion.setExcursionTitle(editTitle.getText().toString());
            excursion.setExcursionDate(editDate.getText().toString());
            excursion.setVacationID(selectedVacationID);
            excursion.setExcursionReminderState(excursionReminderState);
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
    private void setReminders() {
        Calendar todayCalendar = Calendar.getInstance();
        Date today = todayCalendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(excursionDate);
            if (isSameDay(today, startDate)) {
                Toast.makeText(getApplicationContext(), "Your excursion is starting today!", Toast.LENGTH_SHORT).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}
