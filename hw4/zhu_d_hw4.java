// package com.jhu.web;

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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


// ****** Note ***** 
// zip file name: lastname_firstinit_hw#.zip
// how to compile in linux terminal: javac -cp ".:./BhcUtils.jar" zhu_d_hw3.java zhu_d_hw4.java helper.java main.java
// how to run in linux terminal: java -cp ".:./BhcUtils.jar" main

public class zhu_d_hw4 extends zhu_d_hw3{

    final static String delimiter = "&";
    final static String serverAddress = "web6.jhuep.com";
    final static int port = 20025;

    public zhu_d_hw4()
    {

    }
    
    @Override
    protected double getCost()
    {
        double cost = 0;

        GregorianCalendar startDate = rateObj.getBeginDate();
        GregorianCalendar endDate = rateObj.getEndDate();

        // Get the year, month, and day of the month
        int year = startDate.get(Calendar.YEAR);
        int month = startDate.get(Calendar.MONTH); // Note: Months are 0-based in Java Calendar (0 = January, 1 = February, ..., 11 = December)
        int dayOfMonth = startDate.get(Calendar.DAY_OF_MONTH);
        int duration_in_days = helper.calculateDifferenceInDays(startDate, endDate) + 1;

        // Data to be sent to the server
        String hikeId = "0";
        HikeType[] hikeTypes = HikeType.values();
        for(int i=0; i< hikeTypes.length; i++)
        {
            if(hikeTypes[i] == hikeType)
            {
                hikeId = String.valueOf(i);
                break;
            }
        }

        String beginMonth = String.valueOf(month + 1);
        String beginDay = String.valueOf(dayOfMonth);;
        String beginYear = String.valueOf(year);
        String duration = String.valueOf(duration_in_days);
        String numberHikers = String.valueOf(rateObj.getNumberHikers());
        String dataToSend = hikeId + "&" + beginMonth + "&" + beginDay + "&" + beginYear + "&" + duration + "&" + numberHikers;

        System.out.println("hikeId= " + hikeId);
        System.out.println("duration= " + duration);
        System.out.println("beginYear= " + beginYear);
        System.out.println("beginMonth= " + beginMonth);
        System.out.println("beginDay= " + beginDay);
        System.out.println("numberHikers= " + numberHikers);

        try (
            Socket socket = new Socket(serverAddress, port);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) 
            {
                // Send data to the server
                out.println(dataToSend);
    
                // Read response from the server
                String response = in.readLine();
    
                System.out.println("Response from server: " + response);

                // Split the string by the delimiter
                String[] parts = response.split(delimiter);

                // Print the results
                for (int i=0; i <parts.length;i++) 
                {
                    System.out.println(parts[i]);
                }

                cost = Double.parseDouble(parts[0]);
    
            } 
            catch (Exception e) 
            {
                System.out.println("*****************************************************: ");
                e.printStackTrace();

                cost = - 0.01;
            }
        return cost;
    }
}

