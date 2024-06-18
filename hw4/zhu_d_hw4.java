import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;
import java.time.*; 
import java.time.temporal.*;  
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.*;

// ****** Note ***** 
// zip file name: lastname_firstinit_hw#.zip
// how to compile in linux terminal: javac -cp ".:./BhcUtils.jar" zhu_d_hw4.java
// how to run in linux terminal: java -cp ".:./BhcUtils.jar" zhu_d_hw4

public class zhu_d_hw4 {

    static JFrame frame = null;
    static Rates rate = null;
    static final String[] months = {"January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"};  
    
    // Helper method to remove duplicated string
    public static List<String> removeDuplicates(List<String> listWithDuplicates) {    
        // Remove duplicates using HashSet
        Set<String> set = new HashSet<>(listWithDuplicates);
        List<String> listWithoutDuplicates = new ArrayList<>(set);
        return listWithoutDuplicates;
    }
    
    // Helper method to determine the number of days in a given month and year
    private static int getDaysInMonth(int year, int month) {
        switch (month) {
            case 1: // February
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29; // Leap year
                } else {
                    return 28;
                }
            case 3: case 5: case 8: case 10: // April, June, September, November
                return 30;
            default: // January, March, May, July, August, October, December
                return 31;
        }
    }

    private static Integer getMonthFromString(String monthStringName){
        for(int i =0; i < zhu_d_hw3.months.length; i++)
        {
            if (zhu_d_hw3.months[i].equalsIgnoreCase(monthStringName))
            {
                return Integer.valueOf(i + 1);
            }
        }

        System.out.println("monthStringName=" + monthStringName);

        return Integer.valueOf(-1);
    }

    private static double updateCost( 
        HikeType hikeType,Integer duration,Integer startYear, Integer startMonth,Integer startDay,Integer numHikers
    ){
        if(hikeType==null || duration==null || 
        startYear==null || startMonth==null || startDay==null || 
        numHikers==null)
        {
            System.out.println("something wrong here!!!");
            return 0.02;
        }

        //Rates rate = new Rates(hikeType);
        rate = zhu_d_hw3.rate;
        BookingDay beginBookingDay = new BookingDay(startYear,startMonth, startDay);
        System.out.println("beginBookingDay.isValidDate()=" + beginBookingDay.isValidDate());
        rate.setBeginDate(beginBookingDay);
        rate.setDuration(duration);
        rate.setNumberHikers(numHikers);

        BookingDay endBookingDay = rate.getEndBookingDay();
        System.out.println("endBookingDay.isValidDate()=" + endBookingDay.isValidDate());
        System.out.println("rate.isValidDates()=" + rate.isValidDates());
        System.out.println("rate.isDurationValid()=" + rate.isDurationValid());
        System.out.println("rate.numberHikersValid()=" + rate.numberHikersValid());
        
        GregorianCalendar beginDate = rate.getBeginDate();
        GregorianCalendar endDate = rate.getEndDate();
        // Define the date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format the date
        String formattedDate = dateFormat.format(beginDate.getTime());
        // Print the formatted date
        System.out.println("begin date: " + formattedDate);
        formattedDate = dateFormat.format(endDate.getTime());
        System.out.println("end date: " + formattedDate);

        System.out.println("rate.getDetails()=" + rate.getDetails());

        return rate.getCost();
    }

    public static void main(String[] args) {

        System.out.println("this is zhu_d_hw3!");

        // Create the frame
        JFrame frame = new JFrame("Quotes for Bryce Canyon Hikes");
        zhu_d_hw3.frame =frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 2, 10, 10));

        // Create the drop-down list (JComboBox) for hikeTypes:
        HikeType[] hikeTypes = HikeType.values();
        JComboBox<HikeType> comboBoxHikeType = new JComboBox<>(hikeTypes);

        // Create the drop-down list (JComboBox) for duration:
        JComboBox<Integer> comboBoxDuration = new JComboBox<>();
        // Make the JComboBox editable
        comboBoxDuration.setEditable(true);

        // Create the drop-down list (JComboBox) for number of hikers:
        JComboBox<Integer> comboBoxNumberHikers = new JComboBox<>(); 
         // Make the JComboBox editable
         comboBoxNumberHikers.setEditable(true);          

        // Create the JComboBox for year
        JComboBox<Integer> comboBoxYear = new JComboBox<>();
        // Make the JComboBox editable
        comboBoxYear.setEditable(true); 

        // Create the JComboBox for Months
        JComboBox<String> comboBoxMonths = new JComboBox<>();
        // Make the JComboBox editable
        comboBoxMonths.setEditable(true); 

        // Create the JComboBox for days of the month
        JComboBox<Integer> comboBoxDays = new JComboBox<>();
        // Make the JComboBox editable
        comboBoxDays.setEditable(true); 

        JButton submitButon = new JButton("Submit");

        // Create a label to display the cost
        JLabel costLabel = new JLabel("");

        // Add an action listener to handle item selection
        comboBoxHikeType.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                HikeType selectedItem = (HikeType) comboBoxHikeType.getSelectedItem();
                zhu_d_hw3.rate = new Rates(selectedItem);
                
                //durations:
                int[] intArray = zhu_d_hw3.rate.getDurations();
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
                int maxHikers = zhu_d_hw3.rate.getMaxHikers();
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
                    } 
                } catch (ClassCastException ex) {
                    System.out.println("Invalid number format: " + comboBoxDuration.getSelectedItem());

                    // Show a message dialog
                    JOptionPane.showMessageDialog(zhu_d_hw3.frame, 
                    "\""+ comboBoxDuration.getSelectedItem() + "\"" + " is not an integer number, please input an integer number!", 
                    "Error Message", JOptionPane.INFORMATION_MESSAGE);

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
                    JOptionPane.showMessageDialog(zhu_d_hw3.frame, 
                    "\""+ comboBoxNumberHikers.getSelectedItem() + "\"" +  " is not an integer number, please input an integer number!", 
                    "Error Message", JOptionPane.INFORMATION_MESSAGE);

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
                        for (int i = 0; i < zhu_d_hw3.months.length; i++) {
                            String item = zhu_d_hw3.months[i];
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
                    JOptionPane.showMessageDialog(zhu_d_hw3.frame, 
                    "\""+ comboBoxYear.getSelectedItem() + "\"" +  " is not an integer number, please input an integer number!", 
                    "Error Message", JOptionPane.INFORMATION_MESSAGE);

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
                    for(int i =0; i < zhu_d_hw3.months.length; i++){
                        if (zhu_d_hw3.months[i] == selectedItem){
                            int selectedMonth = i;
                            System.out.println("selected Month=" + selectedMonth);  
                            Integer selectedYear = (Integer) comboBoxYear.getSelectedItem();
                            int daysInMonth = getDaysInMonth(selectedYear, selectedMonth);

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
                                Integer startMonth = getMonthFromString((String)comboBoxMonths.getSelectedItem()); 
                                Integer startDay = Integer.valueOf(day);//(Integer)comboBoxDays.getSelectedItem();
                                Integer numHikers = (Integer)comboBoxNumberHikers.getSelectedItem();
                
                                double costs = updateCost(hikeType, duration, startYear, startMonth, startDay, numHikers);  
                                if(costs < 0){
                                    comboBoxDays.removeItemAt(comboBoxDays.getItemCount()-1);
                                    System.out.println("rate.getDetails()=" + zhu_d_hw3.rate.getDetails());
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
                    JOptionPane.showMessageDialog(zhu_d_hw3.frame, 
                    "\""+ comboBoxDays.getSelectedItem() + "\"" + " is not an integer number, please input an integer number!", 
                    "Error Message", JOptionPane.INFORMATION_MESSAGE);

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
                Integer startMonth = getMonthFromString((String)comboBoxMonths.getSelectedItem());
                if(startMonth<1 || startMonth > months.length)
                {
                    if(comboBoxMonths.getItemCount() > 0)
                    {
                        // Show a message dialog
                        JOptionPane.showMessageDialog(zhu_d_hw3.frame, 
                        "no such Month name: " + "\"" + comboBoxMonths.getSelectedItem() + "\"" , 
                        "Error Message", JOptionPane.INFORMATION_MESSAGE);

                        comboBoxMonths.setSelectedIndex(0);

                        return;
                    }
                }
                
                Integer startDay = (Integer)comboBoxDays.getSelectedItem();
                double costs = updateCost(hikeType, duration, startYear, startMonth, startDay, numHikers); 

                if(!zhu_d_hw3.rate.isDurationValid() || !zhu_d_hw3.rate.numberHikersValid() || !zhu_d_hw3.rate.isValidDates()){
                    // Show a message dialog
                    JOptionPane.showMessageDialog(frame, removeDuplicates(zhu_d_hw3.rate.getDetails()), "Error Message", JOptionPane.INFORMATION_MESSAGE);
                
                    if(!zhu_d_hw3.rate.numberHikersValid()){

                        if(comboBoxNumberHikers.getItemCount()>0)
                        {
                            comboBoxNumberHikers.setSelectedIndex(0);
                        }  
                    } 

                    if(!zhu_d_hw3.rate.isDurationValid()){

                        if(comboBoxDuration.getItemCount()>0)
                        {
                            comboBoxDuration.setSelectedIndex(0);
                        }                   
                    } 
                    
                    if(!zhu_d_hw3.rate.isValidDates()){
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
                    costLabel.setText(String.valueOf(costs));
                }
            }
        });

        // Add components to the frame
        JLabel label= new JLabel("Please select hike:");
        frame.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides
        frame.add(comboBoxHikeType);

        label= new JLabel("Please select or input the duration:");
        frame.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides
        frame.add(comboBoxDuration);

        label= new JLabel("Please select or input the start year:");
        frame.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides       
        frame.add(comboBoxYear);

        label= new JLabel("Plese select the start month:");
        frame.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides        
        frame.add(comboBoxMonths);

      
        label= new JLabel("Please select or input the start day of the month:");
        frame.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides         
        frame.add(comboBoxDays);

        label= new JLabel("Please select or input the number of hikers:");
        frame.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides            
        frame.add(comboBoxNumberHikers);

        label= new JLabel("Click Submit button to get quote:");
        frame.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides 
        frame.add(submitButon);

        label= new JLabel("Cost:");
        frame.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides         
        frame.add(costLabel);

        if(comboBoxHikeType.getItemCount()>0){
            comboBoxHikeType.setSelectedIndex(0);
        }

        // Pack the frame to fit the components
        frame.pack();

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Set the frame to be visible
        frame.setVisible(true);

    }

}

