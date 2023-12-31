package com.example.vacationsrus.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vacationsrus.entities.Vacation;

import java.util.List;

@Dao
public interface VacationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Vacation vacation);

    @Update
    void update(Vacation vacation);

    @Delete
    void delete(Vacation vacation);

    @Query("SELECT * FROM VACATIONS ORDER BY vacationID ASC")
    List<Vacation> getAllVacations();

    @Query("SELECT * FROM VACATIONS WHERE vacationID = :id")
    Vacation getVacationByID(int id);

    @Query("SELECT vacationTitle FROM VACATIONS ORDER BY vacationID ASC")
    LiveData<List<String>> getAllVacationTitles();

    @Query("SELECT vacationID FROM VACATIONS WHERE vacationTitle = :title")
    int getVacationIDByTitle(String title);
    @Query("SELECT vacationStartDate FROM VACATIONS WHERE vacationID = :selectedVacationID")
    String getVacationStartDate(int selectedVacationID);
    @Query("SELECT vacationEndDate FROM VACATIONS WHERE vacationID = :selectedVacationID")
    String getVacationEndDate(int selectedVacationID);
    @Query("SELECT vacationReminderState FROM VACATIONS WHERE vacationID = :vacationID")
    boolean getVacationReminderState(int vacationID);
}
