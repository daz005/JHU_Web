// HikeRequest.java
package com.hikequote;

/**
 * HikeRequest class represents a request for a hike quote.
 * It contains fields for hike name, date, duration, and number of hikers.
 */
public class HikeRequest {
    
    // Name of the hike
    private String hike;
    
    // Date of the hike in "yyyy-MM-dd" format
    private String date;
    
    // Duration of the hike in days
    private String duration;
    
    // Number of hikers
    private String numHikers;

    // Getter method for the hike name
    public String getHike() {
        return hike;
    }

    // Setter method for the hike name
    public void setHike(String hike) {
        this.hike = hike;
    }

    // Getter method for the hike date
    public String getDate() {
        return date;
    }

    // Setter method for the hike date
    public void setDate(String date) {
        this.date = date;
    }

    // Getter method for the hike duration
    public String getDuration() {
        return duration;
    }

    // Setter method for the hike duration
    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Getter method for the number of hikers
    public String getNumHikers() {
        return numHikers;
    }

    // Setter method for the number of hikers
    public void setNumHikers(String numHikers) {
        this.numHikers = numHikers;
    }
}
