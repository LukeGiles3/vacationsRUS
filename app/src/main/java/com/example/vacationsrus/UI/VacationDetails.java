package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationsrus.R;
import com.example.vacationsrus.database.Repository;
import com.example.vacationsrus.entities.Excursion;
import com.example.vacationsrus.entities.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VacationDetails extends AppCompatActivity {
    String vacationTitle;
    String vacationHotel;
    int vacationID;
    String vacationStartDate;
    String vacationEndDate;
    boolean vacationReminderState;
    EditText editTitle;
    EditText editHotel;
    EditText editStartDate;
    EditText editEndDate;
    Switch reminderSwitch;
    Repository repository = new Repository(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details);

        vacationTitle = getIntent().getStringExtra("title");
        vacationID = getIntent().getIntExtra("id", 1);
        vacationHotel = getIntent().getStringExtra("hotel");
        vacationStartDate = getIntent().getStringExtra("startDate");
        vacationEndDate = getIntent().getStringExtra("endDate");
        editTitle = findViewById(R.id.editTextTitle1);
        editTitle.setText(vacationTitle);
        editHotel = findViewById(R.id.editTextHotel1);
        editHotel.setText(vacationHotel);
        editStartDate = findViewById(R.id.editTextStartDate1);
        editStartDate.setText(vacationStartDate);
        editEndDate = findViewById(R.id.editTextEndDate1);
        editEndDate.setText(vacationEndDate);
        reminderSwitch = findViewById(R.id.vacationDetailsSwitch);
        reminderSwitch.setChecked(repository.getVacationReminderState(vacationID));
        reminderSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                vacationReminderState = true;
                reminderSwitch.setChecked(true);
                setReminders();
            } else {
                vacationReminderState = false;
                reminderSwitch.setChecked(false);
            }
        }));

        Button vacationsDetailsSaveButton = findViewById(R.id.buttonDetailsSave);
        vacationsDetailsSaveButton.setOnClickListener(view -> {
            Vacation vacation = repository.getVacationByID(vacationID);
            vacation.setVacationTitle(editTitle.getText().toString());
            vacation.setVacationHotel(editHotel.getText().toString());
            vacation.setVacationStartDate(editStartDate.getText().toString());
            vacation.setVacationEndDate(editEndDate.getText().toString());
            vacation.setVacationReminderState(vacationReminderState);
            repository.update(vacation);
            Toast.makeText(getApplicationContext(), "Vacation updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(VacationDetails.this, Vacations.class);
            startActivity(intent);
        });

        Button vacationsDetailsDeleteButton = findViewById(R.id.buttonDetailsDelete);
        vacationsDetailsDeleteButton.setOnClickListener(view -> {
            repository.getmAllExcursionsForVacation(vacationID).observe(VacationDetails.this, new Observer<List<Excursion>>() {
                public void onChanged(List<Excursion> excursions) {
                    if (excursions != null && excursions.size() > 0) {
                        Toast.makeText(getApplicationContext(), "You cannot delete a vacation with excursions", Toast.LENGTH_SHORT).show();
                    } else {
                        Vacation vacation = repository.getVacationByID(vacationID);
                        repository.delete(vacation);
                        Toast.makeText(getApplicationContext(), "Vacation deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VacationDetails.this, Vacations.class);
                        startActivity(intent);
                    }
                }
            });
        });

        RecyclerView recyclerView = findViewById(R.id.excursionsForVacationRecyclerView);
        final ExcurionsForVacationsAdapter excurionsForVacationsAdapter = new ExcurionsForVacationsAdapter(this);
        repository.getmAllExcursionsForVacation(vacationID).observe(VacationDetails.this, new Observer<List<Excursion>>() {
            public void onChanged(List<Excursion> excursions) {
                excurionsForVacationsAdapter.setExcursionsForVacations(excursions);
            }
        });
        recyclerView.setAdapter(excurionsForVacationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setReminders() {
        Calendar todayCalendar = Calendar.getInstance();
        Date today = todayCalendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(vacationStartDate);
            Date endDate = dateFormat.parse(vacationEndDate);
            if (isSameDay(today, startDate)) {
                Toast.makeText(getApplicationContext(), "Your trip is starting today!", Toast.LENGTH_SHORT).show();
            }
            if (isSameDay(today, endDate)) {
                Toast.makeText(getApplicationContext(), "Your trip is ending today!", Toast.LENGTH_SHORT).show();            }
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.shareVacation) {
            Vacation vacation = repository.getVacationByID(vacationID);
            String shareContent = "Vacation Details:\n\n" +
                    "Title: " + vacation.getVacationTitle() + "\n" +
                    "Hotel: " + vacation.getVacationHotel() + "\n" +
                    "Start Date: " + vacation.getVacationStartDate() + "\n" +
                    "End Date: " + vacation.getVacationEndDate();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
            startActivity(Intent.createChooser(shareIntent, "Share via"));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {

        super.onResume();
        final ExcurionsForVacationsAdapter excurionsForVacationsAdapter = new ExcurionsForVacationsAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.excursionsForVacationRecyclerView);
        repository.getmAllExcursionsForVacation(vacationID).observe(VacationDetails.this, new Observer<List<Excursion>>() {
            public void onChanged(List<Excursion> excursions) {
                excurionsForVacationsAdapter.setExcursionsForVacations(excursions);
            }
        });
        recyclerView.setAdapter(excurionsForVacationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}


