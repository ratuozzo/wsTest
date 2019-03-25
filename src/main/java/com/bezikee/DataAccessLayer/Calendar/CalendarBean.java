package com.bezikee.DataAccessLayer.Calendar;

import java.sql.Date;
import java.sql.Timestamp;

public class CalendarBean {

    private int id;
    private String StartDate;
    private String EndDate;
    private String name;

    public CalendarBean(String startDate, String endDate, String name) {
        StartDate = startDate;
        EndDate = endDate;
        this.name = name;
    }

    public CalendarBean(int id, String startDate, String endDate, String name) {
        this.id = id;
        StartDate = startDate;
        EndDate = endDate;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
