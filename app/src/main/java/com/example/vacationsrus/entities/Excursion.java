package com.example.vacationsrus.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "excursions")
public class Excursion {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="excursionID")
    private int excursionID;
    @ColumnInfo(name="excursionTitle")
    private String excursionTitle;
    @ColumnInfo(name="excursionStartDate")
    private String excursionStartDate;
    @ColumnInfo(name="excursionEndDate")
    private String excursionEndDate;
    @ColumnInfo(name="vacationID")
    private int vacationID;
    private boolean excursionReminderState;

    public Excursion(String excursionTitle, String excursionStartDate, String excursionEndDate, int vacationID, boolean excursionReminderState) {
        this.excursionTitle = excursionTitle;
        this.excursionStartDate = excursionStartDate;
        this.excursionEndDate = excursionEndDate;
        this.vacationID = vacationID;
        this.excursionReminderState = false;
    }

    public int getExcursionID() {
        return excursionID;
    }

    public void setExcursionID(int excursionID) {
        this.excursionID = excursionID;
    }

    public String getExcursionTitle() {
        return excursionTitle;
    }

    public void setExcursionTitle(String excursionTitle) {
        this.excursionTitle = excursionTitle;
    }

    public String getExcursionStartDate() {
        return excursionStartDate;
    }

    public void setExcursionStartDate(String excursionStartDate) {
        this.excursionStartDate = excursionStartDate;
    }

    public String getExcursionEndDate() {
        return excursionEndDate;
    }

    public void setExcursionEndDate(String excursionEndDate) {
        this.excursionEndDate = excursionEndDate;
    }

    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public boolean getExcursionReminderState() {
        return excursionReminderState;
    }

    public void setExcursionReminderState(boolean excursionReminderState) {
        this.excursionReminderState = excursionReminderState;
    }
}
