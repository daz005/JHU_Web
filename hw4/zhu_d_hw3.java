
// package com.jhu.web;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;
import java.time.*; 
import java.time.temporal.*;
import java.text.SimpleDateFormat;
import java.util.*;

// ****** Note ***** 
// zip file name: lastname_firstinit_hw#.zip
// how to compile in linux terminal: javac -cp ".:./BhcUtils.jar" zhu_d_hw3.java helper.java 
// how to run in linux terminal: java -cp ".:./BhcUtils.jar" zhu_d_hw3

public class zhu_d_hw3 {

    protected Rates rateObj = null;
    protected HikeType hikeType = null;
    protected JLabel costLabel;

    private JFrame frameObj = null;
    private JComboBox<HikeType> comboBoxHikeType;
    private JComboBox<Integer> comboBoxDuration;
    private JComboBox<Integer> comboBoxNumberHikers; 
    private JComboBox<Integer> comboBoxYear;
    private JComboBox<String> comboBoxMonths;
    private JComboBox<Integer> comboBoxDays;
    private JButton submitButon;
    private static String lastErr="";

    public zhu_d_hw3()
    {

    }

    public static void main(String[] args) 
    {
        zhu_d_hw3 hw4 = new zhu_d_hw3();
        hw4.createUI();
    }

    public void createUI()
    {
        // Create the frame
        frameObj = new JFrame("Quotes for Bryce Canyon Hikes");
        frameObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameObj.setLayout(new GridLayout(8, 2, 10, 10));

        // Create the drop-down list (JComboBox) for hikeTypes, duration, number of hikers, 
        // year, Months, days of the month:
        HikeType[] hikeTypes = HikeType.values();
        comboBoxHikeType = new JComboBox<>(hikeTypes);

        comboBoxDuration = new JComboBox<>();
        comboBoxDuration.setEditable(true);

        comboBoxNumberHikers = new JComboBox<>(); 
        comboBoxNumberHikers.setEditable(true);

        comboBoxYear = new JComboBox<>();
        comboBoxMonths = new JComboBox<>();
        comboBoxDays = new JComboBox<>();
        submitButon = new JButton("Submit");
        costLabel = new JLabel("");

        // Add components to the frame
        JLabel label= new JLabel("Please select hike:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides
        frameObj.add(comboBoxHikeType);

        label= new JLabel("Please select the duration:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides
        frameObj.add(comboBoxDuration);

        label= new JLabel("Please select the start year:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides       
        frameObj.add(comboBoxYear);

        label= new JLabel("Plese select the start month:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides        
        frameObj.add(comboBoxMonths);

        label= new JLabel("Please select the start day of the month:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides         
        frameObj.add(comboBoxDays);

        label= new JLabel("Please select the number of hikers:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides            
        frameObj.add(comboBoxNumberHikers);

        label= new JLabel("Click Submit button to get quote:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides 
        frameObj.add(submitButon);

        label= new JLabel("Cost:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides         
        frameObj.add(costLabel);

        addActionListeners();

        if(comboBoxHikeType.getItemCount()>0){
            comboBoxHikeType.setSelectedIndex(0);
        }

        // Pack the frame to fit the components
        frameObj.pack();

        // Center the frame on the screen
        frameObj.setLocationRelativeTo(null);

        // Set the frame to be visible
        frameObj.setVisible(true);      
    }

    private void addActionListeners()
    {
        JFrame frameObj = this.frameObj;

        // Add an action listener to handle item selection
        comboBoxHikeType.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                hikeType = (HikeType) comboBoxHikeType.getSelectedItem();
                rateObj = new Rates(hikeType);
                
                //durations:
                int[] intArray = rateObj.getDurations();
                comboBoxDuration.removeAllItems();
                for (int i = 0; i < intArray.length; i++) {
                    Integer item = Integer.valueOf(intArray[i]);
                    comboBoxDuration.addItem(item);
                    System.out.println("duration==" + intArray[i]); 
                }  
                if(comboBoxDuration.getItemCount()>0){
                    comboBoxDuration.setSelectedIndex(0);
                }               
                
                //number of hikers:
                int maxHikers = rateObj.getMaxHikers();
                comboBoxNumberHikers.removeAllItems();
                for (int i = 1; i <= maxHikers; i++) {
                    Integer item = Integer.valueOf(i);
                    comboBoxNumberHikers.addItem(item);
                } 
                if(comboBoxNumberHikers.getItemCount()>0){
                    comboBoxNumberHikers.setSelectedIndex(0);
                }

                comboBoxYear.removeAllItems();
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                for (int i = Rates.DEFAULT_MIN_YEAR; i <= Rates.DEFAULT_MAX_YEAR; i++) {
                    if(i >=  currentYear) {
                        Integer item = Integer.valueOf(i);
                        comboBoxYear.addItem(item); 
                    }
                }
                if(comboBoxYear.getItemCount()>0){
                    comboBoxYear.setSelectedIndex(0);
                } 
                
                costLabel.setText("");
            }
        });


        // Add an action listener to handle item selection
        comboBoxDuration.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    Integer selectedItem = (Integer) comboBoxDuration.getSelectedItem();
                    if (selectedItem != null)
                    {
                        System.out.println("selected duration=" + selectedItem); 
                        HikeType hikeType = (HikeType)comboBoxHikeType.getSelectedItem();
                        Integer duration = (Integer)comboBoxDuration.getSelectedItem();
                        Integer startYear = (Integer)comboBoxYear.getSelectedItem(); 
                        Integer startMonth = helper.getMonthFromString((String)comboBoxMonths.getSelectedItem()); 
                        Integer startDay = (Integer)comboBoxDays.getSelectedItem();
                        Integer numHikers = (Integer)comboBoxNumberHikers.getSelectedItem();

                        if(!updateCost(hikeType, duration, startYear, startMonth, startDay, numHikers)){
                            //System.out.println("rateObj.getDetails()=" + rateObj.getDetails());
                            String err= helper.removeDuplicates(rateObj.getDetails()).toString();
                        
                            comboBoxDuration.setSelectedIndex(0);

                            if(err!=null && !err.isEmpty() && err.length()>2)
                            {
                                displayErrorMessage(err);
                            }                           
                        }                          
                    } 
                } catch (ClassCastException ex) {
                    System.out.println("Invalid number format: " + comboBoxDuration.getSelectedItem());
                    // Show a message dialog
                    //displayErrorMessage("\""+ comboBoxDuration.getSelectedItem() + "\"" + " is not an integer number, please input an integer number!");
                    if(comboBoxDuration.getItemCount()>0)
                    {
                        comboBoxDuration.setSelectedIndex(0);
                    }
                }

                costLabel.setText(""); 
            }
        });

        // Add an action listener to handle item selection
        comboBoxNumberHikers.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    Integer selectedItem = (Integer) comboBoxNumberHikers.getSelectedItem();
                    if (selectedItem != null)
                    {
                        System.out.println("selected number of hikers=" + selectedItem); 
                    } 
                } catch (ClassCastException ex) {
                    System.out.println("Invalid number format: " + comboBoxNumberHikers.getSelectedItem());

                    // Show a message dialog
                    displayErrorMessage("\""+ comboBoxNumberHikers.getSelectedItem() + "\"" +  " is not an integer number, please input an integer number!");

                    if(comboBoxNumberHikers.getItemCount()>0)
                    {
                        comboBoxNumberHikers.setSelectedIndex(0);
                    }
                }

                costLabel.setText("");  
            }
        });


        // Add an action listener to handle item selection
        comboBoxYear.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {

                try {
                    Integer selectedItem = (Integer) comboBoxYear.getSelectedItem();
                    if (selectedItem != null)
                    {
                        System.out.println("selected number of year=" + selectedItem); 

                        comboBoxMonths.removeAllItems();
                        for (int i = 0; i < helper.months.length; i++) {
                            String item = helper.months[i];
                            comboBoxMonths.addItem(item);
                            comboBoxMonths.setSelectedIndex(comboBoxMonths.getItemCount()-1);   
                            if(comboBoxDays.getItemCount()==0){
                                comboBoxMonths.removeItemAt(comboBoxMonths.getItemCount() -1 );
                            }                         
                        } 
    
                        if(comboBoxMonths.getItemCount()>0){
                            comboBoxMonths.setSelectedIndex(0);
                        }             
                    } 
                } catch (ClassCastException ex) {
                    System.out.println("Invalid number format: " + comboBoxYear.getSelectedItem());

                    // Show a message dialog
                    displayErrorMessage("\""+ comboBoxYear.getSelectedItem() + "\"" +  " is not an integer number, please input an integer number!");

                    if(comboBoxYear.getItemCount()>0)
                    {
                        comboBoxYear.setSelectedIndex(0);
                    }
                }

                costLabel.setText("");  
            }
        });

        
        // Add an action listener to handle item selection
        comboBoxMonths.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String selectedItem = (String) comboBoxMonths.getSelectedItem();
                if (selectedItem != null)
                {
                    System.out.println("selected Month=" + selectedItem); 
                    for(int i =0; i < helper.months.length; i++){
                        if (helper.months[i] == selectedItem){
                            int selectedMonth = i;
                            System.out.println("selected Month=" + selectedMonth);  
                            Integer selectedYear = (Integer) comboBoxYear.getSelectedItem();
                            int daysInMonth = helper.getDaysInMonth(selectedYear, selectedMonth);

                            YearMonth result = YearMonth.now(); 
                            int curr_year = result.getYear();
                            int curr_month = result.getMonthValue();
                            int curr_dayOfMonth = LocalDate.now().getDayOfMonth();

                            comboBoxDays.removeAllItems();
                            for (int day = 1; day <= daysInMonth; day++) {
                                comboBoxDays.addItem(Integer.valueOf(day));

                                HikeType hikeType = (HikeType)comboBoxHikeType.getSelectedItem();
                                Integer duration = (Integer)comboBoxDuration.getSelectedItem();
                                Integer startYear = (Integer)comboBoxYear.getSelectedItem(); 
                                Integer startMonth = helper.getMonthFromString((String)comboBoxMonths.getSelectedItem()); 
                                Integer startDay = Integer.valueOf(day);//(Integer)comboBoxDays.getSelectedItem();
                                Integer numHikers = (Integer)comboBoxNumberHikers.getSelectedItem();
                
                                //double costs = updateCost(hikeType, duration, startYear, startMonth, startDay, numHikers);  
                                if(!updateCost(hikeType, duration, startYear, startMonth, startDay, numHikers)){
                                    comboBoxDays.removeItemAt(comboBoxDays.getItemCount()-1);
                                    //System.out.println("rateObj.getDetails()=" + rateObj.getDetails());
                                }    
                            }

                            if(comboBoxDays.getItemCount()>0){
                                comboBoxDays.setSelectedIndex(0);
                            }
                        }
                    }                      
                } 
                costLabel.setText("");  
            }
        });

        // Add an action listener to handle item selection
        comboBoxDays.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    Integer selectedItem = (Integer) comboBoxDays.getSelectedItem();
                    if (selectedItem != null)
                    {
                        System.out.println("selected Day=" + selectedItem); 
                    } 
                } catch (ClassCastException ex) {
                    System.out.println("Invalid number format: " + comboBoxDays.getSelectedItem());

                    // Show a message dialog
                    displayErrorMessage("\""+ comboBoxDays.getSelectedItem() + "\"" + " is not an integer number, please input an integer number!");

                    if(comboBoxDays.getItemCount()>0)
                    {
                        comboBoxDays.setSelectedIndex(0);
                    }
                }                
     
                costLabel.setText("");  
            }
        });
        

        // Add action listener to the submitButon
        submitButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                costLabel.setText("");

                HikeType hikeType = (HikeType)comboBoxHikeType.getSelectedItem();
                Integer duration = (Integer)comboBoxDuration.getSelectedItem();
                Integer numHikers = (Integer)comboBoxNumberHikers.getSelectedItem();
                Integer startYear = (Integer)comboBoxYear.getSelectedItem(); 
                Integer startMonth = helper.getMonthFromString((String)comboBoxMonths.getSelectedItem());
                if(startMonth<1 || startMonth > helper.months.length)
                {
                    if(comboBoxMonths.getItemCount() > 0)
                    {
                        // Show a message dialog
                        displayErrorMessage("no such Month name: " + "\"" + comboBoxMonths.getSelectedItem() + "\"");

                        comboBoxMonths.setSelectedIndex(0);

                        return;
                    }
                }
                
                Integer startDay = (Integer)comboBoxDays.getSelectedItem();
                //double costs = updateCost(hikeType, duration, startYear, startMonth, startDay, numHikers); 

                if(!updateCost(hikeType, duration, startYear, startMonth, startDay, numHikers)){
                    // Show a message dialog
                    displayErrorMessage(helper.removeDuplicates(rateObj.getDetails()).toString());
                    
                    if(!rateObj.numberHikersValid()){

                        if(comboBoxNumberHikers.getItemCount()>0)
                        {
                            comboBoxNumberHikers.setSelectedIndex(0);
                        }  
                    } 

                    if(!rateObj.isDurationValid()){

                        if(comboBoxDuration.getItemCount()>0)
                        {
                            comboBoxDuration.setSelectedIndex(0);
                        }                   
                    } 
                    
                    if(!rateObj.isValidDates()){
                        if(comboBoxYear.getItemCount()>0)
                        {
                            comboBoxYear.setSelectedIndex(0);
                        } 
    
                        if(comboBoxMonths.getItemCount()>0)
                        {
                            comboBoxMonths.setSelectedIndex(0);
                        }   

                        if(comboBoxDays.getItemCount()>0)
                        {
                            comboBoxDays.setSelectedIndex(0);
                        }                         

                    }                   
                    
                }
                else{
                    //costLabel.setText(String.valueOf(getCost()));
                    displayCost();
                }
            }
        });

    }

    protected void displayCost()
    {
        costLabel.setText(String.valueOf(rateObj.getCost()));
    }

    protected void displayErrorMessage(String msg)
    {
        if(lastErr.compareToIgnoreCase(msg) != 0){
            lastErr= msg;
            // Show a message dialog
            JOptionPane.showMessageDialog(frameObj, msg, 
            "Error Message", JOptionPane.INFORMATION_MESSAGE);
            lastErr="";
        }
    }

    private boolean updateCost( HikeType hikeType,
        Integer duration,
        Integer startYear, 
        Integer startMonth,
        Integer startDay,
        Integer numHikers
    ){
        if(hikeType==null || duration==null || 
        startYear==null || startMonth==null || startDay==null || 
        numHikers==null)
        {
            System.out.println("something wrong here!!!");
            //return 0.02;
            return false;
        }

        hikeType = (HikeType) comboBoxHikeType.getSelectedItem();
        rateObj = new Rates(hikeType);        

        BookingDay beginBookingDay = new BookingDay(startYear,startMonth, startDay);
        System.out.println("beginBookingDay.isValidDate()=" + beginBookingDay.isValidDate());
        rateObj.setBeginDate(beginBookingDay);
        rateObj.setDuration(duration);
        rateObj.setNumberHikers(numHikers);

        BookingDay endBookingDay = rateObj.getEndBookingDay();
        System.out.println("endBookingDay.isValidDate()=" + endBookingDay.isValidDate());
        System.out.println("rateObj.isValidDates()=" + rateObj.isValidDates());
        System.out.println("rateObj.isDurationValid()=" + rateObj.isDurationValid());
        System.out.println("rateObj.numberHikersValid()=" + rateObj.numberHikersValid());
        
        GregorianCalendar beginDate = rateObj.getBeginDate();
        GregorianCalendar endDate = rateObj.getEndDate();
        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format the date
        String formattedDate = dateFormat.format(beginDate.getTime());
        // Print the formatted date
        System.out.println("begin date: " + formattedDate);
        formattedDate = dateFormat.format(endDate.getTime());
        System.out.println("end date: " + formattedDate);

        //System.out.println("rateObj.getDetails()=" + rateObj.getDetails());

        //return rateObj.getCost();

        return rateObj.isValidDates() && rateObj.isDurationValid() && rateObj.numberHikersValid();
    }

}

