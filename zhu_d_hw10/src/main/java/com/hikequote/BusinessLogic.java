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

    // Instance of Rates class from the edu.jhu.en605681 package
    private edu.jhu.en605681.Rates rates = null;
    
    // Cost initialized to -0.01 indicating an uncalculated state
    private double cost = -0.01;

    // List to store any errors encountered during processing
    List<String> errors = new ArrayList<>();

    /**
     * Constructor to initialize BusinessLogic with the given parameters.
     * 
     * @param hikeName Selected hike name
     * @param dateString Selected date in "yyyy-MM-dd" format
     * @param duration Selected duration
     * @param hikers Selected number of hikers
     */
    public BusinessLogic(String hikeName, String dateString, String duration, String hikers) {
        this.rates = null;
        setRateAndData(hikeName, dateString, duration, hikers);
    }

    // Default constructor
    public BusinessLogic() {}

    // Getter method to retrieve the result (cost)
    public double getResult() {
        return this.cost;
    }
    
    // Getter method to retrieve the list of errors
    public List<String> getErrors() {
        return this.errors;
    }

    /**
     * Sets the rate and form data based on the input parameters.
     * 
     * @param hikeName Selected hike name
     * @param dateString Selected date in "yyyy-MM-dd" format
     * @param duration Selected duration
     * @param hikers Selected number of hikers
     */
    private void setRateAndData(String hikeName, String dateString, String duration, String hikers) {
        this.cost = -0.01;
        this.errors.clear();;

        // Validate the hike name
        boolean isValidHikeName = false;
        ArrayList<String> hikeNames = getHikeNameList();

        // Check if hikeName is provided
        if (hikeName == null || hikeName.trim().isEmpty()) {
            errors.add("No input provided for: hike");
        } else {
            // Validate the hikeName against the list of valid hike names
            for (String item : hikeNames) {
                if (item.compareTo(hikeName) == 0) {
                    isValidHikeName = true;
                }
            }
        }

        // Set rates object if hike name is valid
        if (isValidHikeName) {
            this.rates = new Rates(getHikeTypeFromName(hikeName));
        } else {
            this.errors.add("Input could not be parsed for field: hike");
            return;
        }

        // Get the current date information
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1; // zero-based
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        BookingDay startDay = null; // Initialize BookingDay

        // Validate the date
        if (dateString == null || dateString.trim().isEmpty()) {
            errors.add("No input provided for: date");
        } else {
            dateString = dateString.trim();
            if (dateString.indexOf("/") >= 0 || dateString.indexOf(",") >= 0) {
                try {
                    String test = Util.convertToDate(dateString);
                    dateString = test;
                } catch (IllegalArgumentException ex1) {
                    this.errors.add(ex1.getMessage());
                }
            }

            // Split the date string using the hyphen "-" as the delimiter
            String[] dateParts = dateString.split("-");
            if (dateParts.length != 3) {
                this.errors.add("Input could not be parsed for field: date");
            } else if (!Util.isInteger(dateParts[0]) || !Util.isInteger(dateParts[1]) || !Util.isInteger(dateParts[2])) {
                this.errors.add("Input could not be parsed for field: date");
            } else {
                year = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                day = Integer.parseInt(dateParts[2]);
                // Set the start date for the Rates object
                startDay = new BookingDay(year, month, day);
            }
        }

        // Set the start date in Rates if valid
        if (startDay != null && this.rates.getDetails().size() == 0) {
            this.rates.setBeginDate(startDay);
        }

        // Validate the duration
        if (duration == null || duration.trim().isEmpty()) {
            errors.add("No input provided for: duration");
        } else if (!Util.isInteger(duration)) {
            this.errors.add("Input could not be parsed for field: duration");
        } else if (startDay != null && this.rates.getDetails().size() == 0) {
            this.rates.setDuration(Integer.parseInt(duration));
        }

        // Validate the number of hikers
        if (hikers == null || hikers.trim().isEmpty()) {
            errors.add("No input provided for: numHikers");
        } else if (!Util.isInteger(hikers)) {
            this.errors.add("Input could not be parsed for field: numHikers");
        } else if (this.rates.getDetails().size() == 0) {
            this.rates.setNumberHikers(Integer.parseInt(hikers));
        }

        // Calculate the cost if all data is valid
        if (this.rates.getDetails().size() == 0 
            && this.errors.size() == 0 
            && this.rates.isValidDates() 
            && this.rates.isDurationValid() 
            && rates.numberHikersValid()) {
            this.cost = this.rates.getCost();
            this.errors.add("Quoted Rate");
        } else {
            // Add any rate details to the errors list
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
        return (hikeTypes[0]);
    }
}
