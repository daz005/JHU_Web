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
    private double cost = -0.01;
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

    public BusinessLogic() {}

    // Getter method to retrieve the result (cost)
    public double getResult() {
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
        this.cost = -0.01;
        this.errors.clear();;

        // Validate the hike name
        boolean isValidHikeName = false;
        ArrayList<String> hikeNames = getHikeNameList();

        if (hikeName == null||hikeName.trim().isEmpty())
        {
            errors.add( "No input provided for: hike" );
        }
        else
        {
            for (String item : hikeNames) 
            {
                if (item.compareTo(hikeName) == 0) 
                {
                    isValidHikeName = true;
                }
            }
        }

        if (isValidHikeName)
        {
            this.rates = new Rates(getHikeTypeFromName(hikeName));
        }
        else
        {
            this.errors.add("Input could not be parsesd for field: hike");
            return;
        } 

        // Get the current date information
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1; //zero based.
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);    
        BookingDay startDay = null;//new BookingDay(year, month, day);

        if(dateString==null || dateString.trim().isEmpty())
        {
            errors.add( "No input provided for: date" );
        }
        else
        {
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
            }
            else if(!Util.isInteger(dateParts[0]) 
                ||!Util.isInteger(dateParts[1]) 
                || !Util.isInteger(dateParts[2]))
            {
                this.errors.add("Input could not be parsesd for field: date");
            }
            else 
            {
                year = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                day = Integer.parseInt(dateParts[2]);
                // Set the start date, duration, and number of hikers for the Rates object
                startDay = new BookingDay(year, month, day);
            }   
        }

        if(startDay!=null && this.rates.getDetails().size()==0)
        {
            this.rates.setBeginDate(startDay);
        }

        if(duration==null ||duration.trim().isEmpty())
        {
            errors.add( "No input provided for: duration" );
        }
        else if(!Util.isInteger(duration))
        {
            this.errors.add("Input could not be parsesd for field: duration");
        }
        else if(startDay!=null && this.rates.getDetails().size()==0)
        {
            this.rates.setDuration(Integer.parseInt(duration));  
        }

        if(hikers==null||hikers.trim().isEmpty())
        {
            errors.add( "No input provided for: numHikers" );
        }
        else if(!Util.isInteger(hikers))
        {
            this.errors.add("Input could not be parsesd for field: numHikers");
        }
        else if(this.rates.getDetails().size()==0)
        {
            System.out.println("----" + hikers + "=" + Integer.parseInt(hikers));
            this.rates.setNumberHikers(Integer.parseInt(hikers)); 
        }

        // Calculate the cost if all data is valid
        if (this.rates.getDetails().size()==0 
        && this.errors.size()==0 
        && this.rates.isValidDates() 
        && this.rates.isDurationValid() 
        && rates.numberHikersValid()) 
        {
            this.cost =  this.rates.getCost();
            this.errors.add("Quoted Rate");
        } 
        else 
        {
            List<String> details = this.rates.getDetails();
            for (String item : details) {
                this.errors.add(item);
            } 
        }                

    }

    /**
     * Returns a list of hike names.
     * 
     * @return ArrayList of hike names
     */
    private ArrayList<String> getHikeNameList() 
    {
        ArrayList<String> hikeNames = new ArrayList<>();
        HikeType[] hikeTypes = HikeType.values();

        for (HikeType hikeType : hikeTypes) 
        {
            hikeNames.add(hikeType.toString());
        }

        return hikeNames;
    }

    /**
     * Returns a list of durations.
     * 
     * @return ArrayList of durations as Strings
     */
    private ArrayList<String> getDurationsList() 
    {
        ArrayList<String> durations = new ArrayList<>();
        int[] intArray = this.rates.getDurations();

        for (int duration : intArray) 
        {
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
    private HikeType getHikeTypeFromName(String name) 
    {
        HikeType[] hikeTypes = HikeType.values();

        for (HikeType hikeType : hikeTypes) 
        {
            if (hikeType.toString().compareTo(name) == 0) 
            {
                return hikeType;
            }
        }
        return (hikeTypes[0] );
    }
}
