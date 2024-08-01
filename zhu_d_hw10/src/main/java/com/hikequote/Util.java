package com.hikequote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Utility class containing helper methods for date conversion and integer validation.
 */
public class Util {

    /**
     * Converts a date string from various possible formats to the standard yyyy-MM-dd format.
     *
     * @param dateStr The input date string in any supported format.
     * @return The formatted date string in yyyy-MM-dd format.
     * @throws IllegalArgumentException If the input date string does not match any supported format.
     */
    public static String convertToDate(String dateStr) {
        // Define an array of possible date formats
        String[] dateFormats = {
            "yyyy-MM-dd",     // Standard format
            "dd/MM/yyyy",     // Common European format
            "MM/dd/yyyy",     // Common US format
            "dd-MMM-yyyy",    // Format with abbreviated month names
            "MMM dd, yyyy"    // Format with full month names
            // Add more formats as needed
        };

        // Iterate through the array of date formats
        for (String format : dateFormats) {
            try {
                // Create a DateTimeFormatter with the current format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
                
                // Parse the date string to a LocalDate
                LocalDate date = LocalDate.parse(dateStr, formatter);
                
                // Format the LocalDate to the desired yyyy-MM-dd format
                return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
            }
        }

        // If no format matched, throw an exception
        throw new IllegalArgumentException("Input could not be parsed for field: date");
    }

    /**
     * Checks if a given string can be converted to an integer.
     * 
     * @param theStr The string to check
     * @return true if the string can be converted to an integer, false otherwise
     */
    public static boolean isInteger(String theStr) {
        if (theStr == null) {
            return false;
        }
        try {
            Integer.parseInt(theStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
