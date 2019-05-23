package Persons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Person {
    private String firstName;
    private String lastName;
    private String birthday;
    private String address;

    public Person() {
        firstName = lastName = address = birthday ="";
        //birthday = new Date();
    }

    public void readData(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("firstName : ");
            firstName = reader.readLine();
            System.out.println("lastName : ");
            lastName = reader.readLine();
            System.out.println("address : ");
            address = reader.readLine();
            System.out.println("birthday : ");
            birthday = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Birthday ");
//        birthday.readData();
    }

    public  void printData(){
        System.out.println("firstName : " + firstName);
        System.out.println("lastName : " + lastName);
        System.out.println("birthday : " + birthday);
        //birthday.printData();
        System.out.println("address : " + address);
    }

    public Person(String firstName, String lastName, String birthday, String adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = adress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }
}
