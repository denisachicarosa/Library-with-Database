package com.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectLoaned {

    private Integer ID;
    private Integer shelf;
    private boolean available;
    private static final AtomicInteger count = new AtomicInteger(0);

    public ObjectLoaned(Integer shelf, boolean available) {
        this.available = available;
        ID = count.incrementAndGet();
        this.shelf = shelf;
    }

    public ObjectLoaned(Integer ID, Integer shelf, boolean available) {
        this.available = available;
        this.ID = ID;
        this.shelf = shelf;
    }

    public ObjectLoaned() {
        ID = count.incrementAndGet();
        available = true;
        shelf = 0;
    }

    public void readData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("ID : ");
            ID = Integer.parseInt(reader.readLine());
            System.out.println("Shelf : ");
            shelf = Integer.parseInt(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGenre(){
        return "";
    }

    public void printData() {
        System.out.println("ID : " + ID);
        System.out.println("shelf : " + shelf);
        System.out.println("available : " + available);
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getShelf() {
        return shelf;
    }

    public void setShelf(Integer shelf) {
        this.shelf = shelf;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getTitle() {
        return "";
    }

    public String getAuthor() {
        return "";
    }

    public Date getRelease() {
        return new Date();
    }

    public String getPublisherName() {
        return "";
    }

    public String getPublisherAddress() {
        return "";
    }


    public Double getRating() {
        return 0.0  ;
    }


    public Integer getReleaseDay() {
        return 0;
    }


    public Integer getReleaseMonth() {
        return 0;
    }


    public Integer getReleaseYear() {
        return 0;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
