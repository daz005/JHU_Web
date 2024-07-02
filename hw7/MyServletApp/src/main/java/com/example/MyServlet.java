// MyServletApp/
// |-- src/
// |   |-- main/
// |       |-- java/
// |           |-- com/
// |               |-- example/
// |                   |-- MyServlet.java
// |-- webapp/
//     |-- index.html
//     |-- script.js
// |-- WEB-INF/
//     |-- web.xml



package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import org.json.JSONObject;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            buffer.append(line);
        }
        JSONObject jsonObject = new JSONObject(buffer.toString());
        String name = jsonObject.getString("name");

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Hello, " + name + "!");
        out.close();
    }
}

