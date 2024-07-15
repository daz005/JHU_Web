<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hike Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <%
        com.hw8.FormData formData = (com.hw8.FormData) request.getAttribute("formData");
        ArrayList<String> items = null;
        String selected_item = "";
    %>

    <h1>Hike Registration Form</h1>
    <h2>Assignment: JSP and MVC2</h2>
    <h3>Student Name: Derek Zhu</h3>
         
    <form id="hikeForm" method="post" action="${pageContext.request.contextPath}/MyServlet">
        <label for="hikeName">Select a hike:</label>
        <select id="hikeName" name="hikeName" required onchange="onUIDataChanged()">
            <%
                selected_item = formData.getHikeName();
                items = formData.getHikeNames();                
                for (String item : items) {
                    if(item.equals(selected_item)) {
            %>   
                        <option value="<%= item %>" selected ><%= item %></option>
            <%
                    } else {
            %>
                        <option value="<%= item %>"><%= item %></option>
            <%
                    }
                }
            %>
        </select>
        

        <label for="year">Beginning Year:</label>
        <select id="year" name="year" required onchange="onUIDataChanged()">
            <%
                selected_item = formData.getYear();
                items = formData.getYears();                
                for (String item : items) {
                    if(item.equals(selected_item)) {
            %>   
                        <option value="<%= item %>" selected ><%= item %></option>
            <%
                    } else {
            %>
                        <option value="<%= item %>"><%= item %></option>
            <%
                    }
                }
            %>
        </select> 

        <label for="month">Beginning Month:</label>
        <select id="month" name="month" required onchange="onUIDataChanged()">
            <%
                selected_item = formData.getMonth();
                items = formData.getMonths();                
                for (String item : items) {
                    if(item.equals(selected_item)) {
            %>   
                        <option value="<%= item %>" selected ><%= item %></option>
            <%
                    } else {
            %>
                        <option value="<%= item %>"><%= item %></option>
            <%
                    }
                }
            %>
        </select>  

        <label for="day">Beginning Day:</label>
        <select id="day" name="day" required onchange="onUIDataChanged()">
            <%
                selected_item = formData.getDay();
                items = formData.getDays();                
                for (String item : items) {
                    if(item.equals(selected_item)) {
            %>   
                        <option value="<%= item %>" selected ><%= item %></option>
            <%
                    } else {
            %>
                        <option value="<%= item %>"><%= item %></option>
            <%
                    }
                }
            %>
        </select>         

        <label for="duration">Duration of the hike (in days):</label>
        <select id="duration" name="duration" required onchange="onUIDataChanged()">
            <%
                selected_item = formData.getDuration();
                items = formData.getDurations();                
                for (String item : items) {
                    if(item.equals(selected_item)) {
            %>   
                        <option value="<%= item %>" selected ><%= item %></option>
            <%
                    } else {
            %>
                        <option value="<%= item %>"><%= item %></option>
            <%
                    }
                }
            %>
        </select>       

        <label for="hikers">Number of hikers:</label>
        <select id="hikers" name="hikers" required onchange="onUIDataChanged()">
            <%
                selected_item = formData.getHikers();
                items = formData.getHikersList();                
                for (String item : items) {
                    if(item.equals(selected_item)) {
            %>   
                        <option value="<%= item %>" selected ><%= item %></option>
            <%
                    } else {
            %>
                        <option value="<%= item %>"><%= item %></option>
            <%
                    }
                }
            %>
        </select>  

        <p style="color:Green;">${result}</p>
        
        <button type="submit">Submit</button>
    </form>

    <script>
        function onUIDataChanged() {
            var hikeName = encodeURIComponent(document.getElementById('hikeName').value);
            var year = encodeURIComponent(document.getElementById('year').value);
            var month = encodeURIComponent(document.getElementById('month').value);
            var day = encodeURIComponent(document.getElementById('day').value);
            var duration = encodeURIComponent(document.getElementById('duration').value);
            var hikers = encodeURIComponent(document.getElementById('hikers').value);
            var url = '${pageContext.request.contextPath}/MyServlet?hikeName=' + hikeName + '&year=' + year + '&month=' + month + '&day=' + day + '&duration=' + duration + '&hikers=' + hikers;
            window.location.href = url;
        }
    </script>
</body>
</html>
