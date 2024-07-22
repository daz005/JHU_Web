package com.hw9;

import java.sql.Date;

public class Reservation {

    // Fields representing the reservation details
    private Date startDay = null;               // The start date of the reservation
    private int numberOfDays = 0;               // The number of days the reservation is for
    private String location = null;             // The location of the reservation
    private String guideFirstName = null;       // The first name of the guide for the reservation
    private String guideLastName = null;        // The last name of the guide for the reservation
    private String reservationFirstName = null; // The first name of the person who made the reservation
    private String reservationLastName = null;  // The last name of the person who made the reservation

    // Default constructor
    public Reservation() {}

    // Getters and Setters

    // Returns the start date of the reservation
    public Date getStartDay() { 
        return startDay; 
    }

    // Sets the start date of the reservation
    public void setStartDay(Date startDay) { 
        this.startDay = startDay; 
    }

    // Returns the number of days the reservation is for
    public int getNumberOfDays() { 
        return numberOfDays; 
    }

    // Sets the number of days the reservation is for
    public void setNumberOfDays(int numberOfDays) { 
        this.numberOfDays = numberOfDays; 
    }

    // Returns the location of the reservation
    public String getLocation() { 
        return location; 
    }

    // Sets the location of the reservation
    public void setLocation(String location) { 
        this.location = location; 
    }

    // Returns the first name of the guide for the reservation
    public String getGuideFirstName() { 
        return guideFirstName; 
    }

    // Sets the first name of the guide for the reservation
    public void setGuideFirstName(String guideFirstName) { 
        this.guideFirstName = guideFirstName; 
    }

    // Returns the last name of the guide for the reservation
    public String getGuideLastName() { 
        return guideLastName; 
    }

    // Sets the last name of the guide for the reservation
    public void setGuideLastName(String guideLastName) { 
        this.guideLastName = guideLastName; 
    }

    // Returns the first name of the person who made the reservation
    public String getReservationFirstName() { 
        return reservationFirstName; 
    }

    // Sets the first name of the person who made the reservation
    public void setReservationFirstName(String reservationFirstName) { 
        this.reservationFirstName = reservationFirstName; 
    }

    // Returns the last name of the person who made the reservation
    public String getReservationLastName() { 
        return reservationLastName; 
    }

    // Sets the last name of the person who made the reservation
    public void setReservationLastName(String reservationLastName) { 
        this.reservationLastName = reservationLastName; 
    }
}
