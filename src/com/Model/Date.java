package com.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Date {
    public Integer day;
    public Integer month;
    public Integer year;
    public String today;

    public Date addDays(int days) {
        Date returnDay = new Date();
        days += day;
        if(days < 30) {
            returnDay.day = days;
            returnDay.month = month;
            returnDay.year = year;
        }
        else
        {
            int returnMonths = days / 30;
            if (month + returnMonths <= 12) {
                returnDay.day = days % 30;
                returnDay.month = month + returnMonths;
                returnDay.year = year;
            }
            else {
                returnDay.day = days % 30;
                returnDay.year = year + (returnMonths + month) / 12;
                returnDay.month = (returnMonths + month) % 12;
            }
        }
        return returnDay;
    }

    public void readData() {
        System.out.println("Here I am");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String bd = reader.readLine();
            strToDate(bd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        String m="",d="";
        if(day<10)  d = "0";
        if (month<10) m="0";
        d =d+ day.toString()+'.'+m+month.toString()+'.'+year.toString();
        return d;
    }
    public boolean egal (Date d) {
        if (d.day == day && d.month == month && d.year == year)
            return true;
        return false;
    }

    public void strToDate(String data) {
        String bd = data;
        Integer x;
        x = bd.charAt(0) - '0';
        x = x*10;
        x = x + (bd.charAt(1) - '0');
        this.day = x;

        x = 0;
        x = bd.charAt(3) - '0';
        x = x*10;
        x = x + (bd.charAt(4) - '0');
        this.month = x;

        x = 0;
        x = bd.charAt(6) - '0';
        x = x*10;
        x = x + (bd.charAt(7) - '0');
        x = x*10;
        x = x + (bd.charAt(8) - '0');
        x = x*10;
        x = x + (bd.charAt(9) - '0');
        this.year = x;
    }

    public void printData() {
        System.out.println(day + "." + month + "." + year);
    }

    public Date(Integer day, Integer month, Integer year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date() {
        day = month = year = 0;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean compareDate (Date d) {
        if (d.year < year) return true;
        if (d.year > year) return false;
        if (d.month < month) return true;
        if (d.month > month) return false;
        if (d.day < day) return true;
        if (d.day > day) return false;
        return true;
    }
}
