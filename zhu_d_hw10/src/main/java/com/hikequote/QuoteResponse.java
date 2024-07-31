// QuoteResponse.java
package com.hikequote;

import java.util.List;

public class QuoteResponse {
    private String quote;
    private List<String> errors;

    public QuoteResponse(String quote, List<String> errors) {
        this.quote = quote;
        this.errors = errors;
    }

    // Getters and setters
    public String getQuote() { return quote; }
    public void setQuote(String quote) { this.quote = quote; }
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}
