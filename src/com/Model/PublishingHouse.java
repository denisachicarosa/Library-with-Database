package com.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PublishingHouse {
    private String Name;
    private String address;


    public void readData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Publishing house: ");
            System.out.println("Name : ");
            Name = reader.readLine();
            System.out.println("Address : ");
            address = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        System.out.println("Publishing House Name " + Name);
        System.out.println("Address " + address);
    }
    public PublishingHouse(String name, String address) {
        Name = name;
        this.address = address;
    }

    public PublishingHouse() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String address) {
        this.address = address;
    }
}
