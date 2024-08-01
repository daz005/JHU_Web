// QuoteResponse.java
package com.hikequote;

import java.util.List;

/**
 * QuoteResponse class represents the response of a hike quote request.
 * It contains the quote and a list of errors if any.
 */
public class QuoteResponse {
    
    // The calculated quote for the hike
    private double quote;
    
    // List of errors encountered during the quote calculation
    private List<String> errors;

    /**
     * Constructor to initialize QuoteResponse with the given quote and errors.
     * 
     * @param quote The calculated quote
     * @param errors List of errors
     */
    public QuoteResponse(double quote, List<String> errors) {
        this.quote = quote;
        this.errors = errors;
    }

    /**
     * Getter method for the quote.
     * 
     * @return The calculated quote
     */
    public double getQuote() {
        return quote;
    }

    /**
     * Setter method for the quote.
     * 
     * @param quote The calculated quote
     */
    public void setQuote(double quote) {
        this.quote = quote;
    }

    /**
     * Getter method for the list of errors.
     * 
     * @return List of errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Setter method for the list of errors.
     * 
     * @param errors List of errors
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
