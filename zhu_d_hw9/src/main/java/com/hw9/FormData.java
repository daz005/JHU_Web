/**
 * 
 */
package com.hw8;

import java.util.ArrayList;

/**
 * 
 * JavaBeans: This is a LIGHTWEIGHT container for 
 * storing and passing data between MVC components.  
 * This component should have NO business logic as they 
 * should be lightweight.
 * 
 */
public class FormData {
    // Instance variables to hold form data
    private String hikeName, year, month, day, duration, hikers;
    // Lists to hold possible values for the form fields
    private ArrayList<String> hikeNames, years, months, days, durations, hikersList;

    /**
     * Constructor to initialize the FormData object with form field values and their possible options.
     * 
     * @param hikeName   Selected hike name
     * @param year       Selected year
     * @param month      Selected month
     * @param day        Selected day
     * @param duration   Selected duration
     * @param hikers     Selected number of hikers
     * @param hikeNames  List of possible hike names
     * @param durations  List of possible durations
     * @param years      List of possible years
     * @param months     List of possible months
     * @param days       List of possible days
     * @param hikersList List of possible number of hikers
     */
    public FormData(String hikeName, String year, 
        String month, String day, String duration, String hikers,
        ArrayList<String> hikeNames, ArrayList<String> durations, 
        ArrayList<String> years, ArrayList<String> months, 
        ArrayList<String> days, ArrayList<String> hikersList
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
        this.hikersList = hikersList;
    }

    // Getter methods to retrieve the form field values and their possible options

    /**
     * @return Selected hike name
     */
    public String getHikeName() { return hikeName; }

    /**
     * @return List of possible hike names
     */
    public ArrayList<String> getHikeNames() { return hikeNames; }

    /**
     * @return Selected year
     */
    public String getYear() { return year; }

    /**
     * @return List of possible years
     */
    public ArrayList<String> getYears() { return years; }

    /**
     * @return Selected month
     */
    public String getMonth() { return month; }

    /**
     * @return List of possible months
     */
    public ArrayList<String> getMonths() { return months; }

    /**
     * @return Selected day
     */
    public String getDay() { return day; }

    /**
     * @return List of possible days
     */
    public ArrayList<String> getDays() { return days; }

    /**
     * @return Selected duration
     */
    public String getDuration() { return duration; }

    /**
     * @return List of possible durations
     */
    public ArrayList<String> getDurations() { return durations; }

    /**
     * @return Selected number of hikers
     */
    public String getHikers() { return hikers; }

    /**
     * @return List of possible number of hikers
     */
    public ArrayList<String> getHikersList() { return hikersList; }
}
