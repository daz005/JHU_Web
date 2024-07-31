package com.hikequote;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BusinessLogic {

    private edu.jhu.en605681.Rates rates = null;
    private String cost = null;
    List<String> errors =  new ArrayList<>();

    /**
     * Constructor to initialize BusinessLogic with the given parameters.
     * 
     * @param hikeName Selected hike name
     * @param year     Selected year
     * @param month    Selected month
     * @param day      Selected day
     * @param duration Selected duration
     * @param hikers   Selected number of hikers
     */
    public BusinessLogic(String hikeName, String dateString, String duration, String hikers) {
        this.rates = null;
        setRateAndData(hikeName, dateString, duration, hikers);
    }

    // Getter method to retrieve the result (cost)
    public String getResult() {
        return this.cost;
    }
    
     // Getter method to retrieve the result (cost)
     public List<String> getErrors() {
        return this.errors;
    }

    /**
     * Sets the rate and form data based on the input parameters.
     * 
     * @param hikeName Selected hike name
     * @param year     Selected year
     * @param month    Selected month
     * @param day      Selected day
     * @param duration Selected duration
     * @param hikers   Selected number of hikers
     */
    private void setRateAndData(String hikeName, String dateString, String duration, String hikers) {
        
        this.cost = "-0.01";
        this.errors = new  ArrayList<String>();
        //        No input provided for: field-name (field-name is one of date, duration, numHikers)

        // Validate the hike name
        boolean isValidHikeName = false;
        ArrayList<String> hikeNames = getHikeNameList();

        if (hikeName == null||hikeName.trim().isEmpty()) {
            errors.add( "No input provided for: hike" );
        }else{
            for (String item : hikeNames) {
                if (item.compareTo(hikeName) == 0) {
                    isValidHikeName = true;
                }
            }
        }

        if (isValidHikeName){
            this.rates = new Rates(getHikeTypeFromName(hikeName));
        }else{
            this.errors.add("Input could not be parsesd for field: hike");
        } 

        int year = 0;
        int month = 0;
        int day = 0;     
        BookingDay startDay = null;  
        if(dateString==null||dateString.trim().isEmpty()){
            errors.add( "No input provided for: date" );
        }else{

            dateString = dateString.trim(); 
            if(dateString.indexOf("/") >=0 || dateString.indexOf(",") >= 0)
            {
                try{
                    String test = Util.convertToDate(dateString);
                    dateString = test;
                }catch(IllegalArgumentException ex1)
                {
                    this.errors.add(ex1.getMessage());   
                }
            }
            

            // Split the date string using the hyphen "-" as the delimiter
            String[] dateParts = dateString.split("-");
            if (dateParts.length != 3){
                this.errors.add("Input could not be parsesd for field: date");
            }else if(!Util.isInteger(dateParts[0]) ||!Util.isInteger(dateParts[1]) || !Util.isInteger(dateParts[2])){
                this.errors.add("Input could not be parsesd for field: date");
            }else if(this.rates!=null)
            {
                year = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                day = Integer.parseInt(dateParts[2]);
                // Set the start date, duration, and number of hikers for the Rates object
                startDay = new BookingDay(year, month, day);
                this.rates.setBeginDate(startDay);
                // if (!this.rates.isValidDates()){
                //     List<String> details = this.rates.getDetails();
                //     for (String item : details) {
                //         this.errors.add(item);
                //     }                    
                // }
            }

            // try {
            //     // Define the formatter
            //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
            //     // Parse the string to LocalDate
            //     LocalDate date = LocalDate.parse(dateString, formatter);
                
            //     // Extract year, month, and day
            //     year = date.getYear();
            //     month = date.getMonthValue();
            //     day = date.getDayOfMonth();
            // } catch (DateTimeParseException e) {
            //     //this.errors.add(e.getMessage());
            //     this.errors.add("Input could not be parsesd for field: date");
            // }   
        }

        if(duration==null ||duration.trim().isEmpty()){
            errors.add( "No input provided for: duration" );
        }else if(!Util.isInteger(duration)){
            this.errors.add("Input could not be parsesd for field: duration");
        }else if(startDay!=null && this.rates!=null){
            this.rates.setDuration(Integer.parseInt(duration));  
        }

        if(hikers==null||hikers.trim().isEmpty()){
            errors.add( "No input provided for: numHikers" );
        }else if(!Util.isInteger(hikers)){
            this.errors.add("Input could not be parsesd for field: numHikers");
        }else if(startDay!=null && this.rates!=null){
            this.rates.setNumberHikers(Integer.parseInt(hikers)); 
        }

        if (this.rates!=null)
        {
            // // Set the start date, duration, and number of hikers for the Rates object
            // BookingDay startDay = new BookingDay(year, month, day);
            // this.rates.setBeginDate(startDay);
            //this.rates.setDuration(Integer.parseInt(duration));
            //this.rates.setNumberHikers(Integer.parseInt(hikers));

            // Calculate the cost if all data is valid
            if (this.rates.isValidDates() && this.rates.isDurationValid() && rates.numberHikersValid()) {
                this.cost =  String.valueOf(this.rates.getCost());
                //this.errors.clear();
                this.errors.add("Quoted Rate");
            } else {
                List<String> details = this.rates.getDetails();
                for (String item : details) {
                    this.errors.add(item);
                } 
            }                
        }
    }

    /**
     * Returns a list of hike names.
     * 
     * @return ArrayList of hike names
     */
    private ArrayList<String> getHikeNameList() {
        ArrayList<String> hikeNames = new ArrayList<>();
        HikeType[] hikeTypes = HikeType.values();
        for (HikeType hikeType : hikeTypes) {
            hikeNames.add(hikeType.toString());
        }
        return hikeNames;
    }

    /**
     * Returns a list of durations.
     * 
     * @return ArrayList of durations as Strings
     */
    private ArrayList<String> getDurationsList() {
        ArrayList<String> durations = new ArrayList<>();
        int[] intArray = this.rates.getDurations();
        for (int duration : intArray) {
            durations.add(String.valueOf(duration));
        }
        return durations;
    }

    /**
     * Helper method to get HikeType enum from String name.
     * 
     * @param name Name of the hike type
     * @return HikeType enum corresponding to the name
     */
    private HikeType getHikeTypeFromName(String name) {
        HikeType[] hikeTypes = HikeType.values();
        for (HikeType hikeType : hikeTypes) {
            if (hikeType.toString().compareTo(name) == 0) {
                return hikeType;
            }
        }
        return (hikeTypes[0] );
    }
}
