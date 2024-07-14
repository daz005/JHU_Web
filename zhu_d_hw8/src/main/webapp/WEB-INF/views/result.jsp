<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hike Registration Result</title>
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
    <h1>Hike Registration Result</h1>
    <h3>Assignment: JSP and MVC2</h3>
    <h3>Student Name: Derek Zhu</h3>   

    <p>Hike Name: ${formData.hikeName}</p>
    <p>Year: ${formData.year}</p>
    <p>Month: ${formData.month}</p>
    <p>Day: ${formData.day}</p>
    <p>Duration: ${formData.duration}</p>
    <p>Number of Hikers: ${formData.hikers}</p>
    <h2 style="color:Green;">${result}</h2>

    <form action="http://localhost:8080/zhu_d_hw8/MyServlet" method="get">
        <button type="submit">Go Back</button>
    </form>
</body>
</html>
