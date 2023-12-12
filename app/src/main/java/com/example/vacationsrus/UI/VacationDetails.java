package com.example.vacationsrus.UI;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationsrus.R;
import com.example.vacationsrus.dao.ExcursionDAO;
import com.example.vacationsrus.database.Repository;
import com.example.vacationsrus.entities.Excursion;
import com.example.vacationsrus.entities.Vacation;

import java.util.List;

public class VacationDetails extends AppCompatActivity {
    String vacationTitle;
    String vacationHotel;
    int vacationID;
    String vacationStartDate;
    String vacationEndDate;
    int excursionID;
    EditText editTitle;
    EditText editHotel;
    EditText editStartDate;
    EditText editEndDate;
    Switch reminderSwitch;
    private static final String CHANNEL_ID = "my_channel";
    private static final int NOTIFICATION_ID = 1;
    Repository repository = new Repository(getApplication());
    ExcursionDAO excursionDAO;

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

        Button vacationsDetailsSaveButton = findViewById(R.id.buttonDetailsSave);
        vacationsDetailsSaveButton.setOnClickListener(view -> {
            Vacation vacation = repository.getVacationByID(vacationID);
            vacation.setVacationTitle(editTitle.getText().toString());
            vacation.setVacationHotel(editHotel.getText().toString());
            vacation.setVacationStartDate(editStartDate.getText().toString());
            vacation.setVacationEndDate(editEndDate.getText().toString());
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
                        // There are associated excursions
                        Toast.makeText(getApplicationContext(), "You cannot delete a vacation with excursions", Toast.LENGTH_SHORT).show();
                    } else {
                        // No associated excursions, proceed with deletion
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

//        createNotificationChannel();
//
//        reminderSwitch = findViewById(R.id.vacationDetailsSwitch);
//        reminderSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
//            if(isChecked) {
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                        .setSmallIcon(R.drawable.notification_icon)
//                        .setContentTitle("My Notification Title")
//                        .setContentText("Notification Content")
//                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                        .setContentIntent(pendingIntent)
//                        .setAutoCancel(true);
//                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
//                notificationManager.notify(NOTIFICATION_ID, builder.build());
//            }
//        }));
    }
//    private void createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "My Channel";
//            String description = "My Notification Channel";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }

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


