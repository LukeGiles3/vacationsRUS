package com.example.vacationsrus.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vacationsrus.dao.ExcursionDAO;
import com.example.vacationsrus.dao.VacationDAO;
import com.example.vacationsrus.entities.Excursion;
import com.example.vacationsrus.entities.Vacation;

@Database(entities = {Excursion.class, Vacation.class}, version = 15, exportSchema = false)
public abstract class VacationDBBuilder extends RoomDatabase {
    public abstract VacationDAO vacationDAO();
    public abstract ExcursionDAO excursionDAO();
    public static volatile VacationDBBuilder INSTANCE;

    static VacationDBBuilder getDatabase(final Context context) {
        if (INSTANCE==null) {
            synchronized (VacationDBBuilder.class) {
                if(INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VacationDBBuilder.class, "MyVacationDB").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
