package com.example.vacationsrus.database;

import android.app.Application;

import com.example.vacationsrus.dao.ExcursionDAO;
import com.example.vacationsrus.dao.VacationDAO;
import com.example.vacationsrus.entities.Excursion;
import com.example.vacationsrus.entities.Title;
import com.example.vacationsrus.entities.Vacation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private ExcursionDAO mExcursionDAO;
    private VacationDAO mVacationDAO;

    private List<Excursion> mAllExcursions;
    private List<Vacation> mAllVacations;
    private Vacation mOneVacation;
    private List<Title> mAllVacationTitles;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        VacationDBBuilder db = VacationDBBuilder.getDatabase(application);
        mExcursionDAO = db.excursionDAO();
        mVacationDAO = db.vacationDAO();
    }

    public List<Vacation>getmAllVacations() {
        databaseExecutor.execute(()->{
            mAllVacations=mVacationDAO.getAllVacations();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mAllVacations;
    }

    public List<Title> getmAllVacationTitles() {
        databaseExecutor.execute(()->{
            mAllVacationTitles=mVacationDAO.getAllVacationTitles();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mAllVacationTitles;
    }

    public Vacation getVacationByID(int vacationID) {
        databaseExecutor.execute(()->{
            mOneVacation=mVacationDAO.getVacationByID(vacationID);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mOneVacation;
    }

    public List<Excursion>getmAllExcursions() {
        databaseExecutor.execute(()->{
            mAllExcursions=mExcursionDAO.getAllExcursions();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mAllExcursions;
    }

    public void insert(Vacation vacation) {
        databaseExecutor.execute(()->{
            mVacationDAO.insert(vacation);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Excursion excursion) {
        databaseExecutor.execute(()->{
            mExcursionDAO.insert(excursion);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vacation vacation) {
        databaseExecutor.execute(()->{
            mVacationDAO.update(vacation);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Excursion excursion) {
        databaseExecutor.execute(()->{
            mExcursionDAO.update(excursion);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Vacation vacation) {
        databaseExecutor.execute(()->{
            mVacationDAO.delete(vacation);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Excursion excursion) {
        databaseExecutor.execute(()->{
            mExcursionDAO.delete(excursion);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
