// package com.hw8;

// import java.util.ArrayList;
// import java.util.Calendar;

// public class Util {

//     /**
//      * Helper method to determine the number of days in a given month and year.
//      * 
//      * @param year  The year
//      * @param month The month (1-12)
//      * @return The number of days in the specified month and year
//      */
//     public static int getDaysInMonth(int year, int month) {
//         switch (month) {
//             case 2: // February
//                 if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
//                     return 29; // Leap year
//                 } else {
//                     return 28;
//                 }
//             case 4: case 6: case 9: case 11: // April, June, September, November
//                 return 30;
//             default: // January, March, May, July, August, October, December
//                 return 31;
//         }
//     }  

//     /**
//      * Returns a list of days for a given month and year.
//      * 
//      * @param year  The year
//      * @param month The month (1-12)
//      * @return ArrayList of days in the specified month
//      */
//     public static ArrayList<String> getDayList(int year, int month) {
//         ArrayList<String> days = new ArrayList<>();
//         int maxDay = getDaysInMonth(year, month);
//         for (int i = 1; i <= maxDay; i++) {
//             days.add(String.valueOf(i));
//         }  
//         return days;
//     }

//     /**
//      * Returns a list of months (1-12).
//      * 
//      * @return ArrayList of months
//      */
//     public static ArrayList<String> getMonthList() {
//         ArrayList<String> months = new ArrayList<>();
//         for (int i = 1; i <= 12; i++) {
//             months.add(String.valueOf(i));
//         }  
//         return months;
//     }

//     /**
//      * Returns a list of years from the current year or a specified minimum year to a specified maximum year.
//      * 
//      * @param defaultMinYear The minimum year
//      * @param defaultMaxYear The maximum year
//      * @return ArrayList of years
//      */
//     public static ArrayList<String> getYearList(int defaultMinYear, int defaultMaxYear) {
//         int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//         int minYear = Math.max(defaultMinYear, currentYear);
//         int maxYear = defaultMaxYear;
//         ArrayList<String> years = new ArrayList<>();
//         for (int i = minYear; i <= maxYear; i++) {
//             years.add(String.valueOf(i));
//         }  
//         return years;
//     }

//     /**
//      * Returns a list of integers from start to end.
//      * 
//      * @param start The starting integer
//      * @param end   The ending integer
//      * @return ArrayList of integers from start to end
//      */
//     public static ArrayList<String> getIntegerList(int start, int end) {
//         ArrayList<String> hikerList = new ArrayList<>();
//         for (int i = start; i <= end; i++) {
//             hikerList.add(String.valueOf(i));
//         } 
//         return hikerList;
//     }


//         /**
//      * Checks if a given string can be converted to an integer.
//      * 
//      * @param theStr The string to check
//      * @return true if the string can be converted to an integer, false otherwise
//      */
//     public static boolean isInteger(String theStr) {
//         if (theStr == null) {
//             return false;
//         }
//         try {
//             Integer.parseInt(theStr);
//         } catch (NumberFormatException e) {
//             return false;
//         }
//         return true;
//     }
// }





package com.hw9;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

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
            "MMM dd, yyyy",   // Format with full month names
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

        // If no format matched, throw an exception or return a default value
        throw new IllegalArgumentException("Either bad date or invalid date format: " + dateStr);
    }

    /**
     * Converts the current date to the standard yyyy-MM-dd format.
     *
     * @return The current date as a string in yyyy-MM-dd format.
     */
    public static String getCurrentDateFormatted() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Format the current date to yyyy-MM-dd
        return currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}

