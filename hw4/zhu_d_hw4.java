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
// how to compile in linux terminal: javac -cp ".:./BhcUtils.jar" zhu_d_hw4.java
// how to run in linux terminal: java -cp ".:./BhcUtils.jar" zhu_d_hw4

public class zhu_d_hw4 {

    private Rates rateObj = null;

    private JFrame frameObj = null;
    private JComboBox<HikeType> comboBoxHikeType;
    private JComboBox<Integer> comboBoxDuration;
    private JComboBox<Integer> comboBoxNumberHikers; 
    private JComboBox<Integer> comboBoxYear;
    private JComboBox<String> comboBoxMonths;
    private JComboBox<Integer> comboBoxDays;
    private JButton submitButon;
    private JLabel costLabel;

    public zhu_d_hw4()
    {

    }

    public static void main(String[] args) 
    {
        zhu_d_hw4 hw4 = new zhu_d_hw4();
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
        comboBoxNumberHikers = new JComboBox<>(); 
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

        label= new JLabel("Please select or input the duration:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides
        frameObj.add(comboBoxDuration);

        label= new JLabel("Please select or input the start year:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides       
        frameObj.add(comboBoxYear);

        label= new JLabel("Plese select the start month:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides        
        frameObj.add(comboBoxMonths);

        label= new JLabel("Please select or input the start day of the month:");
        frameObj.add(label);
        label.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10px margins on all sides         
        frameObj.add(comboBoxDays);

        label= new JLabel("Please select or input the number of hikers:");
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
                HikeType selectedItem = (HikeType) comboBoxHikeType.getSelectedItem();
                rateObj = new Rates(selectedItem);
                
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
                    } 
                } catch (ClassCastException ex) {
                    System.out.println("Invalid number format: " + comboBoxDuration.getSelectedItem());

                    // Show a message dialog
                    JOptionPane.showMessageDialog(frameObj, 
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
                    JOptionPane.showMessageDialog(frameObj, 
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
                        for (int i = 0; i < zhu_d_hw4.months.length; i++) {
                            String item = zhu_d_hw4.months[i];
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
                    JOptionPane.showMessageDialog(frameObj, 
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
                    for(int i =0; i < zhu_d_hw4.months.length; i++){
                        if (zhu_d_hw4.months[i] == selectedItem){
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
                                    System.out.println("rateObj.getDetails()=" + rateObj.getDetails());
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
                    JOptionPane.showMessageDialog(frameObj, 
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
                        JOptionPane.showMessageDialog(frameObj, 
                        "no such Month name: " + "\"" + comboBoxMonths.getSelectedItem() + "\"" , 
                        "Error Message", JOptionPane.INFORMATION_MESSAGE);

                        comboBoxMonths.setSelectedIndex(0);

                        return;
                    }
                }
                
                Integer startDay = (Integer)comboBoxDays.getSelectedItem();
                double costs = updateCost(hikeType, duration, startYear, startMonth, startDay, numHikers); 

                if(!rateObj.isDurationValid() || !rateObj.numberHikersValid() || !rateObj.isValidDates()){
                    // Show a message dialog
                    JOptionPane.showMessageDialog(frameObj, removeDuplicates(rateObj.getDetails()), "Error Message", JOptionPane.INFORMATION_MESSAGE);
                
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
                    costLabel.setText(String.valueOf(costs));
                }
            }
        });

    }


    static final String[] months = {"January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"};  
    
    // Helper method to remove duplicated string
    public static List<String> removeDuplicates(List<String> listWithDuplicates) 
    {    
        // Remove duplicates using HashSet
        Set<String> set = new HashSet<>(listWithDuplicates);
        List<String> listWithoutDuplicates = new ArrayList<>(set);

        return listWithoutDuplicates;
    }
    
    // Helper method to determine the number of days in a given month and year
    private static int getDaysInMonth(int year, int month) 
    {
        switch (month) 
        {
            case 1: // February
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) 
                {
                    return 29; // Leap year
                } 
                else 
                {
                    return 28;
                }
            case 3: case 5: case 8: case 10: // April, June, September, November
                return 30;
            default: // January, March, May, July, August, October, December
                return 31;
        }
    }

    private static Integer getMonthFromString(String monthStringName){
        for(int i =0; i < zhu_d_hw4.months.length; i++)
        {
            if (zhu_d_hw4.months[i].equalsIgnoreCase(monthStringName))
            {
                return Integer.valueOf(i + 1);
            }
        }

        System.out.println("monthStringName=" + monthStringName);

        return Integer.valueOf(-1);
    }

    private double updateCost( HikeType hikeType,
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
            return 0.02;
        }

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

        System.out.println("rateObj.getDetails()=" + rateObj.getDetails());

        return rateObj.getCost();
    }



}

