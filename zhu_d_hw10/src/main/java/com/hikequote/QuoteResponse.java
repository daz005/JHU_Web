// QuoteResponse.java
package com.hikequote;

import java.util.List;

public class QuoteResponse {
    private double quote;
    private List<String> errors;

    public QuoteResponse(double quote, List<String> errors) {
        this.quote = quote;
        this.errors = errors;
    }

    // Getters and setters
    public double getQuote() { return quote; }
    public void setQuote(double quote) { this.quote = quote; }
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}
