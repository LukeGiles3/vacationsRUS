package com.example.vacationsrus.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vacationsrus.entities.Excursion;

import java.util.List;

@Dao
public interface ExcursionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Excursion excursion);

    @Update
    void update(Excursion excursion);

    @Delete
    void delete(Excursion excursion);

    @Query("SELECT * FROM Excursions ORDER BY excursionID ASC")
    List<Excursion> getAllExcursions();
    @Query("SELECT * FROM EXCURSIONS WHERE vacationID = :mVacationID")
    LiveData<List<Excursion>> getAllExcursionsForVacation(int mVacationID);

    @Query("SELECT * FROM EXCURSIONS WHERE excursionID = :id")
    Excursion getExcursionByID(int id);
}
