// MyServletApp/
// |-- src/
// |   |-- main/
// |       |-- java/
// |           |-- com/
// |               |-- example/
// |                   |-- MyServlet.java
// |-- webapp/
//     |-- index.html
// |-- WEB-INF/
//     |-- web.xml    


package com.example;

    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.Calendar;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    
    import edu.jhu.en605681.HikeType;
    
    

//    @WebServlet("/MyServlet")
    public class MyServlet extends HttpServlet 
    {
        private static final long serialVersionUID = 1L;
        private static final HikeType[] hikeTypes = HikeType.values();
        
               
            /**
         * @see HttpServlet#HttpServlet()
         */
        public MyServlet() 
        {
            super();
            // TODO Auto-generated constructor stub
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {
            // Ensure correct character encoding
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");   	
        	
            String hikeName = request.getParameter("hikeName");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");
            String duration = request.getParameter("duration");
            String hikers = request.getParameter("hikers");

            String message = "<p>doPost!</p>";
            message = message + " hikeName=" + hikeName + " duration=" + duration ;

            FormData formData = new FormData(hikeName, year, month, day, duration, hikers);
            generateForm(response, message, formData);        	
        	
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        	String message = "<p>doGet!</p>";

			generateForm(response, message, null);

        }

        private void generateForm(HttpServletResponse response, String message, FormData formData) throws IOException {
        	
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");  	

            PrintWriter out = response.getWriter();

            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

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

            String test = "<h1>Hike Registration Form " +  (formData != null ? formData.getYear() : currentYear) + "</h1>";
            out.println(test);
            out.println("<h1>Hike Registration Form </h1>");
            out.println(message);
            out.println("<form id=\"hikeForm\" method=\"post\"  enctype=\"application/x-www-form-urlencoded\" action=\"http://localhost:8080/zhu_d_hw6/MyServlet\">");

            out.println("<label for=\"hikeName\">Select a hike:</label>");
            out.println("<select id=\"hikeName\" name=\"hikeName\" required>");
//            out.println("<option value=\"\">--Select a hike--</option>");
            
			for (int i=0; i < hikeTypes.length;i++) 
			{
				String item = hikeTypes[i].toString().replace(' ', '_');
				out.println("<option value=" + item + (formData != null && item.equals(formData.getHikeName()) ? " selected" : "") + ">" + item + "</option>");
			}
//            out.println("<option value=\"Trail A\"" + (formData != null && "Trail A".equals(formData.getHikeName()) ? " selected" : "") + ">Trail A</option>");
//            out.println("<option value=\"Trail B\"" + (formData != null && "Trail B".equals(formData.getHikeName()) ? " selected" : "") + ">Trail B</option>");
//            out.println("<option value=\"Trail C\"" + (formData != null && "Trail C".equals(formData.getHikeName()) ? " selected" : "") + ">Trail C</option>");
            out.println("</select>");

            out.println("<label for=\"year\">Beginning Year:</label>");
            out.println("<input type=\"number\" id=\"year\" name=\"year\" min=\"" + currentYear + "\" value=\"" + (formData != null ? formData.getYear() : currentYear) + "\" required>");

            out.println("<label for=\"month\">Beginning Month:</label>");
            out.println("<input type=\"number\" id=\"month\" name=\"month\" min=\"1\" max=\"12\" value=\"" + (formData != null ? formData.getMonth() : "") + "\" required>");

            out.println("<label for=\"day\">Beginning Day:</label>");
            out.println("<input type=\"number\" id=\"day\" name=\"day\" min=\"1\" max=\"31\" value=\"" + (formData != null ? formData.getDay() : "") + "\" required>");

            out.println("<label for=\"duration\">Duration of the hike (in days):</label>");
            out.println("<input type=\"number\" id=\"duration\" name=\"duration\" min=\"1\" value=\"" + (formData != null ? formData.getDuration() : "") + "\" required>");

            out.println("<label for=\"hikers\">Number of hikers:</label>");
            out.println("<input type=\"number\" id=\"hikers\" name=\"hikers\" min=\"1\" value=\"" + (formData != null ? formData.getHikers() : "") + "\" required>");

            out.println("<button type=\"submit\">Submit</button>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");

            out.close();
        }

        private static class FormData {
            private String hikeName;
            private String year;
            private String month;
            private String day;
            private String duration;
            private String hikers;

            public FormData(String hikeName, String year, String month, String day, String duration, String hikers) {
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
