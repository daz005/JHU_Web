<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hike Registration</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; margin: 0 auto; padding: 10px; border: 1px solid #ccc; border-radius: 5px; }
        label { display: block; margin-top: 10px; }
        input, select, button { width: 100%; padding: 8px; margin-top: 5px; margin-bottom: 10px; }
        button { background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <%
        com.hw8.FormData formData = (com.hw8.FormData) request.getAttribute("formData");
        ArrayList<String> items = null;
    %>

    <h1>Hike Registration Form</h1>
    <h2>Assignment: JSP and MVC2</h2>
         
    <form id="hikeForm" method="post" action="http://localhost:8080/zhu_d_hw8/MyServlet">
        <!-- <label for="hikeName">Select a hike:</label>
        <select id="hikeName" name="hikeName" required>
            <option value="Trail_A">Trail A</option>
            <option value="Trail_B">Trail B</option>
            <option value="Trail_C">Trail C</option>
        </select> -->
  
        <label for="hikeName">Select a hike:</label>
        <select id="hikeName" name="hikeName" required>
            <%
                items = formData.getHikeNames();                
                for (String item : items) {
            %>
                    <option value="<%= item %>"><%= item %></option>
            <%
                }
            %>
        </select>
        

        <!-- <label for="year">Beginning Year:</label>
        <input type="number" id="year" name="year" required> -->
        <select id="year" name="year" required>
            <%
                items = formData.getYears();                
                for (String item : items) {
            %>
                    <option value="<%= item %>"><%= item %></option>
            <%
                }
            %>
        </select>  

        <label for="month">Beginning Month:</label>
        <!-- <input type="number" id="month" name="month" min="1" max="12" required> -->
        <select id="month" name="month" required>
            <%
                items = formData.getMonths();                
                for (String item : items) {
            %>
                    <option value="<%= item %>"><%= item %></option>
            <%
                }
            %>
        </select>  

        <label for="day">Beginning Day:</label>
        <!-- <input type="number" id="day" name="day" min="1" max="31" required> -->
        <select id="day" name="day" required>
            <%
                items = formData.getDays();                
                for (String item : items) {
            %>
                    <option value="<%= item %>"><%= item %></option>
            <%
                }
            %>
        </select>         

        <label for="duration">Duration of the hike (in days):</label>
        <select id="duration" name="duration" required>
            <%
                items = formData.getDurations();                
                for (String item : items) {
            %>
                    <option value="<%= item %>"><%= item %></option>
            <%
                }
            %>
        </select>       

        <label for="hikers">Number of hikers:</label>
        <!-- <input type="number" id="hikers" name="hikers" min="1" required> -->
        <select id="hikers" name="hikers" required>
            <%
                items = formData.getHikerList();                
                for (String item : items) {
            %>
                    <option value="<%= item %>"><%= item %></option>
            <%
                }
            %>
        </select>  

        <button type="submit">Submit</button>
    </form>
</body>
</html>
