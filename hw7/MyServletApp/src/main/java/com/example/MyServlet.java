// MyServletApp/
// |-- src/
// |   |-- main/
// |       |-- java/
// |           |-- com/
// |               |-- hw6/
// |                   |-- MyServlet.java
// |-- webapp/
//     |-- index.html
// |-- WEB-INF/
//     |-- web.xml    


package com.hw7;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.*; 
import java.time.temporal.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;
    

//  @WebServlet("/MyServlet")
public class MyServlet extends HttpServlet 
{
        private static final long serialVersionUID = 1L;

               
            /**
         * @see HttpServlet#HttpServlet()
         */
        public MyServlet() 
        {
            super();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {
            String hikeName = request.getParameter("hikeName")!=null? URLDecoder.decode(request.getParameter("hikeName"),StandardCharsets.UTF_8.name()):null;
            String year = request.getParameter("year")!=null?URLDecoder.decode(request.getParameter("year"), StandardCharsets.UTF_8.name()):null;
            String month = request.getParameter("month")!=null?URLDecoder.decode(request.getParameter("month"), StandardCharsets.UTF_8.name()):null;
            String day = request.getParameter("day")!=null?URLDecoder.decode(request.getParameter("day"), StandardCharsets.UTF_8.name()):null;
            String duration = request.getParameter("duration")!=null?URLDecoder.decode(request.getParameter("duration"), StandardCharsets.UTF_8.name()):null;
            String hikers = request.getParameter("hikers")!=null?URLDecoder.decode(request.getParameter("hikers"), StandardCharsets.UTF_8.name()):null;
            FormData formData = new FormData(hikeName, year, month, day, duration, hikers);
            generateHTML(response, formData, true);        		
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String hikeName = request.getParameter("hikeName")!=null? URLDecoder.decode(request.getParameter("hikeName"),StandardCharsets.UTF_8.name()):null;
            String year = request.getParameter("year")!=null?URLDecoder.decode(request.getParameter("year"), StandardCharsets.UTF_8.name()):null;
            String month = request.getParameter("month")!=null?URLDecoder.decode(request.getParameter("month"), StandardCharsets.UTF_8.name()):null;
            String day = request.getParameter("day")!=null?URLDecoder.decode(request.getParameter("day"), StandardCharsets.UTF_8.name()):null;
            String duration = request.getParameter("duration")!=null?URLDecoder.decode(request.getParameter("duration"), StandardCharsets.UTF_8.name()):null;
            String hikers = request.getParameter("hikers")!=null?URLDecoder.decode(request.getParameter("hikers"), StandardCharsets.UTF_8.name()):null;
            FormData formData = new FormData(hikeName, year, month, day, duration, hikers);
            generateHTML(response, formData, false);  
        }

        private void generateHTML(HttpServletResponse response, FormData formData, Boolean shouldCalculateCost) throws IOException {
        	
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");  	

            PrintWriter out = response.getWriter(); 
            HikeType[] hikeTypes = HikeType.values();
            Rates rateObj = null;
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int min_year = Rates.DEFAULT_MIN_YEAR>currentYear? Rates.DEFAULT_MIN_YEAR: currentYear;
            int max_year = Rates.DEFAULT_MAX_YEAR;
            String selected_hike = formData.getHikeName();
            String selected_year = formData.getYear();
            String selected_month = formData.getMonth();
            String selected_day = formData.getDay();
            String selected_duration = formData.getDuration();
            String selected_hikers = formData.getHikers();

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Hike Registration</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println("form { max-width: 400px; margin: 0 auto; padding: 10px; border: 1px solid #ccc; border-radius: 5px; }");
            out.println("label { display: block; margin-top: 10px; }");
            out.println("input, select, button { width: 100%; padding: 8px; margin-top: 5px; margin-bottom: 10px; }");
            out.println("button { background-color: #4CAF50; color: white; border: none; cursor: pointer; }");
            out.println("button:hover { background-color: #45a049; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Assignment: Web Forms and Servlets</h1>");
            out.println("<h2>Student Name: Derek Zhu</h2>");
            out.println("<form id=\"hikeForm\" method=\"post\"  enctype=\"application/x-www-form-urlencoded\" action=\"https://web3.jhuep.com/zhu_d_hw7/MyServlet\">");

            // Select a hike:
            ArrayList<String> hikeTypes_list = new ArrayList<String>();
            for(int i=0; i < hikeTypes.length; i++)
            {
                hikeTypes_list.add(hikeTypes[i].toString());
            }
            selected_hike = buildHTMLList(out, "<label for=\"hikeName\">Select a hike:</label>", "<select id=\"hikeName\" name=\"hikeName\" required onchange=\"onHikeNameChanged()\">", formData.getHikeName(), hikeTypes_list);  
            rateObj = new Rates(getHikeTypeFromName(selected_hike));

            // Select Beginning Year:
            ArrayList<String> year_list = new ArrayList<String>();
            for(int i= min_year; i <=max_year; i++)
            {
                year_list.add(Integer.toString(i));
            }            
            selected_year = buildHTMLList(out, "<label for=\"year\">Select Beginning Year:</label>", "<select id=\"year\" name=\"year\" required onchange=\"onHikeNameChanged()\">", formData.getYear(), year_list);  
        
            // Select Beginning Month:
            ArrayList<String> month_list = new ArrayList<String>();
            for(int i= 1; i <= 12; i++)
            {
                month_list.add(Integer.toString(i));
            }             
            selected_month = buildHTMLList(out, "<label for=\"month\">Select Beginning Month:</label>", "<select id=\"month\" name=\"month\" required onchange=\"onHikeNameChanged()\">", formData.getMonth(), month_list);              

            // Select Beginning Day:
            int daysInMonth = 31;
            try{
                daysInMonth = getDaysInMonth(Integer.parseInt(selected_year), 
                Integer.parseInt(selected_month));
            }
            catch(NumberFormatException ex){
                System.out.println("------ error 1:" + ex.getMessage());
            }
            ArrayList<String> day_list = new ArrayList<String>();
            for(int i= 1; i <= daysInMonth; i++)
            {
                day_list.add(Integer.toString(i));
            }            
            selected_day = buildHTMLList(out, "<label for=\"day\">Select Beginning Day:</label>", "<select id=\"day\" name=\"day\" required onchange=\"onHikeNameChanged()\">", formData.getDay(), day_list);           

            //Select durations:
            int[] intArray = rateObj.getDurations();
            ArrayList<String> duration_list = new ArrayList<String>();
            for(int i= 0; i < intArray.length; i++)
            {
                duration_list.add(Integer.toString(intArray[i]));
            }            
            selected_duration = buildHTMLList(out, "<label for=\"duration\">Select Duration of the hike (in days):</label>", "<select id=\"duration\" name=\"duration\" required onchange=\"onHikeNameChanged()\">", formData.getDuration(), duration_list); 
            
            //Select Number of hikers:
            ArrayList<String> hikers_list = new ArrayList<String>();
            for(int i= 1; i <= rateObj.getMaxHikers(); i++)
            {
                hikers_list.add(Integer.toString(i));
            }             
            selected_hikers = buildHTMLList(out, "<label for=\"hikers\">Select Number of hikers:</label>", "<select id=\"hikers\" name=\"hikers\" required onchange=\"onHikeNameChanged()\">", formData.getHikers(), hikers_list);

            // verify data:
            try
            {
                int duration =  Integer.parseInt(selected_duration); 
                int year =  Integer.parseInt(selected_year);
                int month =  Integer.parseInt(selected_month);
                int day =  Integer.parseInt(selected_day); 
                int hikers =  Integer.parseInt(selected_hikers); 
                verifyData(out,rateObj, duration, year, month, day, hikers, shouldCalculateCost);
            }
            catch(NumberFormatException ex){
                System.out.println("------ error:" + ex.getMessage());
            }

            //JavaScript:
            String script =generateJavascript();
            out.println(script);

            out.println("</body>");
            out.println("</html>");

            out.close();
        }

        private static String generateJavascript(){
            String script = "<script>"
            + "function onHikeNameChanged() { "
            + "   var hikeName = encodeURIComponent(document.getElementById('hikeName').value);"
            + "   var year = encodeURIComponent(document.getElementById('year').value);"
            + "   var month = encodeURIComponent(document.getElementById('month').value);"
            + "   var day = encodeURIComponent(document.getElementById('day').value);"
            + "   var duration = encodeURIComponent(document.getElementById('duration').value);"
            + "   var hikers = encodeURIComponent(document.getElementById('hikers').value);"
            + "   var url = 'https://web3.jhuep.com/zhu_d_hw7/MyServlet?hikeName=' + hikeName + '&year=' + year + '&month=' + month + '&day=' + day + '&duration=' + duration + '&hikers=' + hikers;"
            + "   window.location.href = url;"
            + "};"
            + "</script>";
            
            return script;
        }

        // Helper method to update the Rates object and check if there is any error:
        private static void updateRates( Rates rateObj,int duration,int startYear, int startMonth,int startDay,int numHikers)
        {
            BookingDay beginBookingDay = new BookingDay(startYear,startMonth, startDay);
            rateObj.setBeginDate(beginBookingDay);
            rateObj.setDuration(duration);
            rateObj.setNumberHikers(numHikers);

            // the following code for myself to debug:
            // BookingDay endBookingDay = rateObj.getEndBookingDay();
            // System.out.println("beginBookingDay.isValidDate()=" + beginBookingDay.isValidDate());
            // System.out.println("endBookingDay.isValidDate()=" + endBookingDay.isValidDate());
            // System.out.println("rateObj.isValidDates()=" + rateObj.isValidDates());
            // System.out.println("rateObj.isDurationValid()=" + rateObj.isDurationValid());
            // System.out.println("rateObj.numberHikersValid()=" + rateObj.numberHikersValid());
            // GregorianCalendar beginDate = rateObj.getBeginDate();
            // GregorianCalendar endDate = rateObj.getEndDate();
            // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");           
            // String formattedDate = dateFormat.format(beginDate.getTime());
            // System.out.println("begin date: " + formattedDate);
            // formattedDate = dateFormat.format(endDate.getTime());
            // System.out.println("end date: " + formattedDate);
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

        // Helper method to remove duplicated string
        private static List<String> removeDuplicates(List<String> listWithDuplicates) 
        {    
            List<String> result = new ArrayList<>();
            for(int i=0; i<listWithDuplicates.size(); i++)
            {
                if( result.size()==0 )
                {
                    result.add(listWithDuplicates.get(i));
                }
                else{
                    for(int j=0; i<result.size(); j++)
                    {
                        if(result.get(j).compareTo(listWithDuplicates.get(i)) !=0)
                        {
                            result.add(listWithDuplicates.get(i));
                            break;
                        }
                    }
                }

            }

            return result;
        }   

        // Helper method to build a HTML list
        private static String buildHTMLList(PrintWriter out, String label, String select_id_name, String para, ArrayList<String> list)
        {
            Boolean found = false;
            String selected_item = list.get(0);
    
            out.println(label);
            out.println(select_id_name);

            for(int i = 0; i< list.size(); i++) {
                String item= list.get(i);//Integer.toString(i);
                if( !found && para !=null && item.equalsIgnoreCase(para))             
                {
                    System.out.println("--- para: " + para + "--- item: " + item );

                    found = true;
                    selected_item = item;
                    out.println("<option value=" + item  + " selected >" + item + "</option>");
                }else
                {
                    out.println("<option value=" + item  + ">" + item + "</option>");
                }	            	
            }
            out.println("</select>"); 

            return selected_item;


        }

        // Helper method to verify if there is any data format problems:
        private static Boolean verifyData(PrintWriter out, Rates rateObj, int selected_duration, int selected_year, int selected_month, int selected_day, int selected_hikers, Boolean shouldCalculateCost)
        {
            updateRates(rateObj, selected_duration, selected_year, selected_month, selected_day, selected_hikers);
            
    
            if (rateObj.isValidDates() && rateObj.isDurationValid() && rateObj.numberHikersValid())
            {
                //Submit button:
                out.println("<button type=\"submit\">Submit</button>");   
                out.println("</form>");

                if (shouldCalculateCost){
                    out.println("<h2 style=\"color:Green;\">Cost: $" + rateObj.getCost() + "</h2>");
                }
                return true;
            }else{

                String err= removeDuplicates(rateObj.getDetails()).toString();
                if(err!=null && !err.isEmpty())
                {
                    out.println("<h2 style=\"color:Tomato;\">Error: " + err + "</h2>");
                } 
                
                 //Submit button:
                 out.println("<button style=\"color:Yellow;\" type=\"submit\">please check the error!</button>");   
                 out.println("</form>");                
             
            }

            return false;
        }

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
        
        
        // Helper class for a form data
        private static class FormData {
                private String hikeName;
                private String year;
                private String month;
                private String day;
                private String duration;
                private String hikers;

                public FormData(String hikeName, String year, String month, String day, String duration, String hikers) {
                    HikeType[] hikeTypes = HikeType.values();
                    if(hikeName==null){
                        hikeName = hikeTypes[0].toString();
                    }else
                    {
                        for(int i=0; i< hikeTypes.length; i++){
                            if(hikeTypes[i].toString().startsWith(hikeName))
                            {
                                hikeName = hikeTypes[i].toString();
                                break;
                            }
                        }
                    }    

                    this.hikeName = hikeName;
                    this.year = year;
                    this.month = month;
                    this.day = day;
                    this.duration = duration;
                    this.hikers = hikers;
                }

                public String getHikeName() {
                    return hikeName;
                }

                public String getYear() {
                    return year;
                }

                public String getMonth() {
                    return month;
                }

                public String getDay() {
                    return day;
                }

                public String getDuration() {
                    return duration;
                }

                public String getHikers() {
                    return hikers;
                }
            }
}
