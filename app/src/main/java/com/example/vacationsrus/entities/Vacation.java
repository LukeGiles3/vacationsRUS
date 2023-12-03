package com.example.vacationsrus.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vacations")
public class Vacation {
    @PrimaryKey(autoGenerate = true)
    private int vacationID;
    private String vacationTitle;
    private String vacationHotel;

    private int vacationStartDate;

    private int vacationEndDate;
    private int excursionID;

    public Vacation(int vacationID, String vacationTitle, String vacationHotel, int vacationStartDate, int vacationEndDate, int excursionID) {
        this.vacationID = vacationID;
        this.vacationTitle = vacationTitle;
        this.vacationHotel = vacationHotel;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
        this.excursionID = excursionID;
    }

    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public String getVacationTitle() {
        return vacationTitle;
    }

    public void setVacationTitle(String vacationTitle) {
        this.vacationTitle = vacationTitle;
    }

    public String getVacationHotel() {
        return vacationHotel;
    }

    public void setVacationHotel(String vacationHotel) {
        this.vacationHotel = vacationHotel;
    }

    public int getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(int vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public int getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(int vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

    public int getExcursionID() {
        return excursionID;
    }

    public void setExcursionID(int excursionID) {
        this.excursionID = excursionID;
    }
}
