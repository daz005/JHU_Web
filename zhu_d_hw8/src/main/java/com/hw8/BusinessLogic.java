package com.hw8;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;

public class BusinessLogic  {

    private FormData formData = null;
    private Rates rates = null;
    private String result = null;

    public BusinessLogic(String hikeName, String year, String month, String day, String duration, String hikers){
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int min_year = Rates.DEFAULT_MIN_YEAR>currentYear? Rates.DEFAULT_MIN_YEAR: currentYear;
        int max_year = Rates.DEFAULT_MAX_YEAR;      
        
        if(hikeName==null || year==null || month==null || day==null || duration ==null || hikers==null)
        {
            hikeName = HikeType.values()[0].toString();
            this.rates = new Rates(getHikeTypeFromName(hikeName));

            year = Integer.toString(min_year);
            month = Integer.toString(currentMonth);
            day = Integer.toString(currentDay);
            hikers="1";
            int[] intArray = this.rates.getDurations();
            duration = String.valueOf(intArray[0]);
        }
        
        this.rates = new Rates(getHikeTypeFromName(hikeName));

        ArrayList<String> durations = new ArrayList<>();
        int[] intArray = this.rates.getDurations();
        for(int i=0; i<intArray.length;i++ ){
            durations.add(String.valueOf(intArray[i]));
        }

        ArrayList<String> hikeNames = new ArrayList<>();
        HikeType[] hikeTypes = HikeType.values();
        for(int i=0; i<hikeTypes.length;i++ ){
            hikeNames.add(hikeTypes[i].toString());
        }

        ArrayList<String> years = new ArrayList<>();
        for(int i= min_year; i<=max_year;i++ ){
            years.add(String.valueOf(i));
        }      

        ArrayList<String> months = new ArrayList<>();
        for(int i= 1; i<=12;i++ ){
            months.add(String.valueOf(i));
        }  

        ArrayList<String> days = new ArrayList<>();
        int maxDay = getDaysInMonth(Integer.parseInt(year), Integer.parseInt(month));
        for(int i= 1; i<=maxDay;i++ ){
            days.add(String.valueOf(i));
        }  

        ArrayList<String> hikerList = new ArrayList<>();
        int maxHikers = this.rates.getMaxHikers();
        for(int i= 1; i<=maxHikers;i++ ){
            hikerList.add(String.valueOf(i));
        } 

        this.formData =  new FormData(hikeName, year, month, day, duration, hikers, hikeNames, durations, years, months, days, hikerList);
        BookingDay startDay = new BookingDay(Integer.parseInt(formData.getYear()), Integer.parseInt(formData.getMonth()), Integer.parseInt(formData.getDay()));
        this.rates.setBeginDate(startDay);
        this.rates.setDuration(Integer.parseInt(formData.getDuration()));
        this.rates.setNumberHikers(Integer.parseInt(formData.getHikers()));

        if (this.rates.isValidDates() && this.rates.isDurationValid() && rates.numberHikersValid()) {
            //return String.valueOf(this.rates.getCost());
            this.result = "cost is $" + String.valueOf(this.rates.getCost());
        } else {
            //return (this.rates.getDetails()).toString();
            this.result = (this.rates.getDetails()).toString();
        }
    }

    public FormData getFormData(){
        return this.formData;
    }

    public String getResult(){
        return this.result;
    }

    // public ArrayList<String> getHikeNameList() {
    //     return hikeNameList;
    // }

    // public ArrayList<String> getYearList() {
    //     return yearList;
    // }

    // public ArrayList<String> getMonthList() {
    //     return monthList;
    // }

    // public ArrayList<String> getDayList() {
    //     return dayList;
    // }

    // public ArrayList<String> getDurationsList() {
    //     return durationList;
    // }

    // public ArrayList<String> getHikersList() {
    //     return hikersList;
    // }      

    // public String getCost() {
    	
    //     //HikeType hikeType = HikeType.valueOf(hikeName);
    //     HikeType hikeType = getHikeTypeFromName(hikeName);
    //     Rates rates = new Rates(hikeType);

    //     int duration = Integer.parseInt(formData.getDuration());
    //     int year = Integer.parseInt(formData.getYear());
    //     int month = Integer.parseInt(formData.getMonth());
    //     int day = Integer.parseInt(formData.getDay());
    //     int hikers = Integer.parseInt(formData.getHikers());

    //     BookingDay startDay = new BookingDay(year, month, day);
    //     rates.setBeginDate(startDay);
    //     rates.setDuration(duration);
    //     rates.setNumberHikers(hikers);

    //     if (rates.isValidDates() && rates.isDurationValid() && rates.numberHikersValid()) {
    //         return String.valueOf(rates.getCost());
    //     } else {
    //         return (rates.getDetails()).toString();
    //     }
    // }
    
    // Helper method to get HikeType enum from String name
    private static HikeType getHikeTypeFromName(String name){
        HikeType[] hikeTypes = HikeType.values();
        for(int i=0; i<hikeTypes.length;i++ ){
            if(hikeTypes[i].toString().compareTo(name)==0)
            {
                return hikeTypes[i];
            }
        }
        return hikeTypes[0];
    }

    // Helper method to determine the number of days in a given month and year
    private static int getDaysInMonth(int year, int month) 
    {
        switch (month) 
        {
            case 2: // February
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) 
                {
                    return 29; // Leap year
                } 
                else 
                {
                    return 28;
                }
            case 4: case 6: case 9: case 11: // April, June, September, November
                return 30;
            default: // January, March, May, July, August, October, December
                return 31;
        }
    }      
}
