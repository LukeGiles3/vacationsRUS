package com.example.vacationsrus.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "excursions")
public class Excursion {
    @PrimaryKey(autoGenerate = true)
    private int excursionID;
    private String excursionTitle;
    private String excursionStartDate;
    private String excursionEndDate;
    private int productID;

    public Excursion(String excursionTitle, String excursionStartDate, String excursionEndDate, int productID) {
        this.excursionTitle = excursionTitle;
        this.excursionStartDate = excursionStartDate;
        this.excursionEndDate = excursionEndDate;
        this.productID = productID;
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

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
