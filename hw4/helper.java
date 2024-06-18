
import java.util.List;
import java.util.*;

public class helper {

    public static final String[] months = {"January", "February", "March", "April", "May", "June",
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
    public static int getDaysInMonth(int year, int month) 
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

    public static Integer getMonthFromString(String monthStringName){
        for(int i =0; i < helper.months.length; i++)
        {
            if (helper.months[i].equalsIgnoreCase(monthStringName))
            {
                return Integer.valueOf(i + 1);
            }
        }

        System.out.println("monthStringName=" + monthStringName);

        return Integer.valueOf(-1);
    }

}