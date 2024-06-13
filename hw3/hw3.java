
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

//javac -cp ".:/dir/commons.jar:/dir/more_jar_files.jar" MyClass.java

// javac -cp ".:./BhcUtils.jar" hw3.java
// java -cp ".:./BhcUtils.jar" hw3


public class hw3 {

    static Rates rate = null;
    static final String[] months = {"January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"};  
    
    
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

    public static void main(String[] args) {

        System.out.println("this is hw3!");

        // Create the frame
        JFrame frame = new JFrame("Drop Down Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        // Create the drop-down list (JComboBox) for hikeTypes:
        HikeType[] hikeTypes = HikeType.values();
        JComboBox<HikeType> comboBoxHikeType = new JComboBox<>(hikeTypes);

        // Create the drop-down list (JComboBox) for duration:
        JComboBox<Integer> comboBoxDuration = new JComboBox<>();

        // Create the drop-down list (JComboBox) for number of hikers:
        JComboBox<Integer> comboBoxNumberHikers = new JComboBox<>();    

        // Create the JComboBox for year
        JComboBox<Integer> comboBoxYear = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = Rates.DEFAULT_MIN_YEAR; i <= Rates.DEFAULT_MAX_YEAR; i++) {
            if(i >=  currentYear) {
                Integer item = Integer.valueOf(i);
                comboBoxYear.addItem(item); 
            }
        }
        
        // Create the JComboBox for Months
        JComboBox<String> comboBoxMonths = new JComboBox<>();

        // Create the JComboBox for days of the month
        JComboBox<Integer> comboBoxDays = new JComboBox<>();

        // Create a label to display the selected item
        JLabel selectedItemLabel = new JLabel("Selected Item: None");

        // Add an action listener to handle item selection
        comboBoxHikeType.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                HikeType selectedItem = (HikeType) comboBoxHikeType.getSelectedItem();
                hw3.rate = new Rates(selectedItem);
                
                //durations:
                int[] intArray = hw3.rate.getDurations();
                comboBoxDuration.removeAllItems();
                for (int i = 0; i < intArray.length; i++) {
                    Integer item = Integer.valueOf(intArray[i]);
                    comboBoxDuration.addItem(item);
                    System.out.println("duration==" + intArray[i]); 
                }  
                
                //number of hikers:
                int maxHikers = hw3.rate.getMaxHikers();
                comboBoxNumberHikers.removeAllItems();
                for (int i = 1; i <= maxHikers; i++) {
                    Integer item = Integer.valueOf(i);
                    comboBoxNumberHikers.addItem(item);
                    //System.out.println("number of hikers==" + intArray[i]); 
                } 



                double costs= hw3.rate.getCost();
                System.out.println("costs=" + costs);
                              
            }
        });


        // Add an action listener to handle item selection
        comboBoxDuration.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Integer selectedItem = (Integer) comboBoxDuration.getSelectedItem();
                if (selectedItem != null)
                {
                    System.out.println("selected duration==" + selectedItem); 
                }   
            }
        });

        // Add an action listener to handle item selection
        comboBoxNumberHikers.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Integer selectedItem = (Integer) comboBoxNumberHikers.getSelectedItem();
                if (selectedItem != null)
                {
                    System.out.println("selected number of hikers=" + selectedItem); 
                }   
            }
        });


        // Add an action listener to handle item selection
        comboBoxYear.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Integer selectedItem = (Integer) comboBoxYear.getSelectedItem();
                if (selectedItem != null)
                {
                    System.out.println("selected number of year=" + selectedItem); 
                    
                    comboBoxMonths.removeAllItems();
                    int startMonth = hw3.rate.getSeasonStartMonth();
                    int endMonth = hw3.rate.getSeasonEndMonth();

                    YearMonth result = YearMonth.now(); 
                    int curr_year = result.getYear();
                    int curr_month = result.getMonthValue();
                    int curr_dayOfMonth = LocalDate.now().getDayOfMonth();

                    for (int i = 0; i < hw3.months.length; i++) {

                        //skip past days:
                        if(selectedItem < curr_year)
                            continue;
                        if(selectedItem == curr_year && (i+1) < curr_month)
                            continue;    

                        if( i+1 >= startMonth && i+1 <= endMonth)
                        {
                            String item = hw3.months[i];
                            comboBoxMonths.addItem(item);
                        }
                    } 

                }   
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
                    for(int i =0; i < hw3.months.length; i++){
                        if (hw3.months[i] == selectedItem){
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
                                
                                if(selectedMonth+1 == hw3.rate.getSeasonStartMonth() && day < hw3.rate.getSeasonStartDay())
                                {
                                    continue;
                                }else if(selectedMonth+1 == hw3.rate.getSeasonEndMonth() && day > hw3.rate.getSeasonEndDay())
                                {
                                    continue;
                                }
                                else if(selectedYear == curr_year && selectedMonth+1 == curr_month && curr_dayOfMonth > day)
                                {
                                    continue;
                                }

                                comboBoxDays.addItem(Integer.valueOf(day));
                            }
                        }
                    }
                }   
            }
        });


        // Add components to the frame
        frame.add(comboBoxHikeType);
        frame.add(selectedItemLabel);
        frame.add(comboBoxDuration);
        frame.add(comboBoxYear);
        frame.add(comboBoxMonths);
        frame.add(comboBoxDays);
        frame.add(comboBoxNumberHikers);

        // Set the frame to be visible
        frame.setVisible(true);






    // In summary, you should have controls for:

    // Selecting one of the available hikes provided by the API
    // String[] hikeNames = HikeType.getHikeNames();
    // for( String hikeName: hikeNames)
    // {
    //     System.out.println(hikeName);
        // Navajo Loop
        // Bryce Canyon Rim
        // Fairyland Loop
        // Mossy Cave Trail
        // Queens Garden Trail

        // HikeType hike = HikeType.valueOf("Navajo Loop");
        // System.out.println(hike);

        // Rates rt = new Rates(hike);
        // int[] dr = rt.getDurations();
        // for( int j = 0; j < dr.length; j++)
        // {
        //     System.out.println(dr[i]);
        // }

    //}

    // apply now() method of YearMonth class 
    YearMonth result = YearMonth.now(); 
    int year = result.getYear();
    int month = result.getMonthValue();
    int dayOfMonth = LocalDate.now().getDayOfMonth();
    System.out.println( "Year: "+ year); 
    System.out.println( "Month: "+ month); 
    System.out.println( "Day: "+ dayOfMonth); 
     

    System.out.println("Rates.DEFAULT_MIN_YEAR=" + Rates.DEFAULT_MIN_YEAR);
    System.out.println("Rates.DEFAULT_MAX_YEAR=" + Rates.DEFAULT_MAX_YEAR);
    
    //HikeType[] hikeTypes = HikeType.values();
    for( HikeType hikeType: hikeTypes)
    {
        System.out.println("******************************");
        System.out.println("hikeType=" + hikeType);

        Rates rate = new Rates(hikeType);
        int[] intArray = rate.getDurations();
        // Convert int[] to Integer[]:
        Integer[] durations = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            durations[i] = Integer.valueOf(intArray[i]);
            System.out.println("duration==" + durations[i]); 
        }

        int startMonth = rate.getSeasonStartMonth();
        System.out.println("startMonth=" + startMonth); 

        int startDay = rate.getSeasonStartDay();
        System.out.println("startDay=" + startDay); 

        int endMonth = rate.getSeasonEndMonth();
        System.out.println("endMonth=" + endMonth); 

        int endDay = rate.getSeasonEndDay();
        System.out.println("endDay=" + endDay); 

        int maxHikers = rate.getMaxHikers();
        System.out.println("maxHikers=" + maxHikers);

        double costs= rate.getCost();
        System.out.println("costs=" + costs);

    }
    

    // Setting the beginning date of the hike
    // Year
    // Month
    // Day

    // Setting the duration of the hike (note that different hikes have different duration options as indicated above)


    // Setting the number of hikers going on the hike


    // Submitting the query (use a JButton)
    // BookingDay bd= new BookingDay(year, month, dayOfMonth);

    // System.out.println(bd.toString());    
    
    // Showing the results (DON'T use a JButton)
    // Total Cost of the Tour For All Hikers

    }

}


