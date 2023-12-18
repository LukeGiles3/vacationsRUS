package com.example.vacationsrus.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationsrus.R;
import com.example.vacationsrus.database.Repository;
import com.example.vacationsrus.entities.Excursion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Excursions extends AppCompatActivity {
    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursions);
        FloatingActionButton excursionsFloatingActionButton = findViewById(R.id.excursionsFloatingActionButton);
        excursionsFloatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(Excursions.this, AddExcursion.class);
            startActivity(intent);
        });

        repository = new Repository(getApplication());
        List<Excursion> allExcursions = repository.getmAllExcursions();
        RecyclerView recyclerView = findViewById(R.id.excursionRecyclerView);
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        excursionAdapter.setExcursions(allExcursions);
    }
    @Override
    protected void onResume() {

        super.onResume();
        List<Excursion> allExcursions = repository.getmAllExcursions();
        RecyclerView recyclerView = findViewById(R.id. excursionRecyclerView);
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        excursionAdapter.setExcursions(allExcursions);
    }
}
