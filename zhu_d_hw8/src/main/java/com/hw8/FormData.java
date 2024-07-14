/**
 * 
 */
package com.hw8;

import java.util.ArrayList;

/**
 * 
 */
public class FormData {
    private String hikeName;
    private ArrayList<String> hikeNames;

    private String year;
    private ArrayList<String> years;

    private String month;
    private ArrayList<String> months;

    private String day;
    private ArrayList<String> days;

    private String duration;
    private ArrayList<String>  durations;

    private String hikers;
    private ArrayList<String> hikerList;

    public FormData(String hikeName, String year, String month, String day, String duration, String hikers,
        ArrayList<String> hikeNames, ArrayList<String>  durations, ArrayList<String> years, ArrayList<String> months, ArrayList<String> days, ArrayList<String> hikerList
    ) {
        this.hikeName = hikeName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.duration = duration;
        this.hikers = hikers;  
        this.hikeNames = hikeNames;    
        this.durations = durations;
        this.years = years;
        this.months = months;
        this.days = days;
        this.hikerList = hikerList;
    }

    public String getHikeName() {
        return hikeName;
    }

    public ArrayList<String> getHikeNames() {
        return hikeNames;
    }


    public String getYear() {
        return year;
    }
    public ArrayList<String> getYears() {
        return years;
    }

    public String getMonth() {
        return month;
    }
    public ArrayList<String> getMonths() {
        return months;
    }

    public String getDay() {
        return day;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public String getDuration() {
        return duration;
    }
    
    public ArrayList<String> getDurations() {
        return durations;
    }

    public String getHikers() {
        return hikers;
    }  

    public ArrayList<String> getHikerList() {
        return hikerList;
    }  
    
}
