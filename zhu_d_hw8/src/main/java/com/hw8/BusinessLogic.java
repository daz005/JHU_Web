package com.hw8;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;

public class BusinessLogic {

    // Instance variables to hold form data, rates, and calculated cost
    private FormData formData = null;
    private edu.jhu.en605681.Rates rates = null;
    private String cost = null;

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
    public BusinessLogic(String hikeName, String year, String month, String day, String duration, String hikers, Boolean isPost) {
        this.rates = null;
        setRateAndData(hikeName, year, month, day, duration, hikers, isPost);
    }

    // Getter method to retrieve form data
    public FormData getFormData() {
        return this.formData;
    }

    // Getter method to retrieve the result (cost)
    public String getResult() {
        return this.cost;
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
    private void setRateAndData(String hikeName, String year, String month, String day, String duration, String hikers, Boolean isPost) {
        
        System.out.println("--------isPost=" + isPost);
        System.out.println("--------hikeName=" + hikeName);
        System.out.println("--------year=" + year);

        ArrayList<String> errors = new  ArrayList<String>();
        // Get the current date information
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        
        // Set the minimum and maximum year based on current year and default values
        int minYear = Math.max(Rates.DEFAULT_MIN_YEAR, currentYear);
        int maxYear = Rates.DEFAULT_MAX_YEAR;


        // Validate the hike name
        boolean isValidHikeName = false;
        ArrayList<String> hikeNames = getHikeNameList();
        if (hikeName != null) {
            for (String item : hikeNames) {
                if (item.compareTo(hikeName) == 0) {
                    isValidHikeName = true;
                }
            }
        }

        if (!isValidHikeName){
            errors.add("the Hikename is wrong: " + hikeName );
        } 
        if(!Util.isInteger(year)){
            errors.add("the Year is not an integer: " + year );
        }

        if(!Util.isInteger(month)){
            errors.add("The Month is not an integer: " + month );
        }

        if(!Util.isInteger(day)){
            errors.add( "The Day is not an integer: " + day );
        }

        if(!Util.isInteger(duration)){
            errors.add( "The Duration is not an integer: " + duration );
        }

        if(!Util.isInteger(hikers)){
            errors.add( "The Hikers is not an integer: " + hikers );
        }   

        if(!isPost){
            // If the hike name is not valid, set it to the first hike name in the list
            if (!isValidHikeName) {
                hikeName = hikeNames.get(0);
                isValidHikeName = true;
            }
            // Initialize Rates with the valid hike type
            this.rates = new Rates(getHikeTypeFromName(hikeName));

            // Set default values for year, month, day, duration, and hikers if they are not provided
            year = Util.isInteger(year)? year: Integer.toString(minYear);
            month = Util.isInteger(month)? month: Integer.toString(currentMonth);
            day = Util.isInteger(day)? day: Integer.toString(currentDay);
            duration = Util.isInteger(duration)? duration: String.valueOf(this.rates.getDurations()[0]);
            hikers = Util.isInteger(hikers)? hikers: "1";
        }

        if(this.rates==null){
            this.rates = new Rates(getHikeTypeFromName(hikeName));
        }  

        // Create a new FormData object with the validated and default values
        this.formData = new FormData(hikeName, year, month, day, duration, hikers, 
                getHikeNameList(), getDurationsList(), 
                Util.getYearList(Rates.DEFAULT_MIN_YEAR, Rates.DEFAULT_MAX_YEAR), 
                Util.getMonthList(), 
                Util.getDayList(Util.isInteger(year)?Integer.parseInt(year):minYear, Util.isInteger(month)?Integer.parseInt(month): currentMonth), 
                Util.getIntegerList(1, this.rates.getMaxHikers()));
        
        if (errors.size() == 0){
            // Set the start date, duration, and number of hikers for the Rates object
            BookingDay startDay = new BookingDay(Util.isInteger(year)?Integer.parseInt(year):minYear, 
                Util.isInteger(month)?Integer.parseInt(month): currentMonth, 
                Util.isInteger(month)?Integer.parseInt(day): currentDay);

            this.rates.setBeginDate(startDay);
            this.rates.setDuration(Integer.parseInt(formData.getDuration()));
            this.rates.setNumberHikers(Integer.parseInt(formData.getHikers()));

            // Calculate the cost if all data is valid
            if (this.rates.isValidDates() && this.rates.isDurationValid() && rates.numberHikersValid()) {
                this.cost = "cost is $" + String.valueOf(this.rates.getCost());
            } else {
                this.cost = this.rates.getDetails().toString();
            }
        }else{
            this.cost = errors.toString();
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
