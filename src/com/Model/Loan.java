package com.Model;

import Persons.Client;
import Services.FileAdd;


import java.io.*;

public class Loan implements FileAdd {
    private Client client;
    private ObjectLoaned object;
    //private Book book;
    private Date loanDate;
    private Date returnDate;

    public Loan(Client client, ObjectLoaned object, Date loanDate, Date returnDate) {
        this.client = client;
        this.object = object;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public ObjectLoaned getObject() {
        return object;
    }

    public void setObject(ObjectLoaned object) {
        this.object = object;
    }


    public Loan() {
        this.client = client;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.object.equals(object);
    }

    public Loan(String[] values) {
//        System.out.println("values[1] = " + values[0] + " .");
//        System.out.println(values.length);
        Integer idClient = Integer.parseInt(values[0]);
        String firstName = values[1];
        String lastName = values[2];
        String birthday = values[3];
        String address = values[4];
        String contact = values[5];
        Integer day = Integer.parseInt(values[6]);
        Integer month = Integer.parseInt(values[7]);
        Integer year = Integer.parseInt(values[8]);
        client = new Client(idClient, firstName, lastName, birthday, address, contact);
        this.loanDate = new Date(day, month, year);
        returnDate = new Date();
        returnDate = loanDate.addDays(14);
        if (values.length == 16)
        { // book loaned
            Integer idBook = Integer.parseInt(values[9]);
            String title = values[10];
            String author = values[11];
            Integer shelf = Integer.parseInt(values[12]);
            String pname = values[13];
            String pAddress = values[14];
            Boolean available = Boolean.parseBoolean(values[15]);
            object = new Book(idBook, title, author, shelf, pname, pAddress, available);
        }
        else { //movie loaned
            Integer idMovie = Integer.parseInt(values[9]);
            Integer shelf = Integer.parseInt(values[10]);
            boolean available = Boolean.parseBoolean(values[11]);
            String title = values[12];
            Integer rday = Integer.parseInt(values[13]);
            Integer rmonth = Integer.parseInt(values[14]);
            Integer ryear = Integer.parseInt(values[15]);
            String genre = values[16];
            Double rating = Double.parseDouble(values[17]);
            object = new Movie(idMovie,shelf, available, title, rday, rmonth, ryear,genre, rating);
        }
    }

    public Loan(String firstName, String lastName, String birthday, String address, String contact, Integer day, Integer month, Integer year, String title, String author, Integer shelf, String pname, String pAddress, boolean available) {
        client = new Client(firstName, lastName, birthday, address, contact);
        object = new Book(title, author, shelf, pname, pAddress, available);
        this.loanDate = new Date(day, month, year);
        returnDate = new Date();
        returnDate = loanDate.addDays(14);
    }

    public Loan(String firstName, String lastName, String birthday, String address, String contact, Integer day, Integer month, Integer year, Integer shelf, boolean available, String title, Integer rday, Integer rmonth, Integer ryear, String genre, Double rating ) {
        client = new Client(firstName, lastName, birthday, address, contact);
        object = new Movie(shelf, available, title, rday, rmonth, ryear,genre, rating);
        this.loanDate = new Date(day, month, year);
        returnDate = new Date();
        returnDate = loanDate.addDays(14);
    }

    private void setReturnDate() {
        returnDate = (Date) loanDate.addDays(14);
    }

    public Loan(Client client, ObjectLoaned book) {
        this.client = new Client();
        loanDate = new Date();
        returnDate = new Date();

        System.out.println("Do you want to loan a book or a movie? ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String obj = "";

        try {
            obj = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (obj) {
            case "book": {
                object = new Book();
                break;
            }
            case "movie": {

                object = new Movie();
                break;
            }

        }
    }

    public Loan(ObjectLoaned o, Client c, String dateString)
    {
        object = o;
        client = c;

        loanDate = new Date(dateString);
        setReturnDate();
    }

    public void readData() {

        System.out.println("Client : ");
//        System.out.println("Client ID : ");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        Integer id = 0;
//        try {
//            id = Integer.parseInt(reader.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        client.readData();
        //client.setID(id);
//        System.out.println("Object ID : ");
//        try {
//            id = Integer.parseInt(reader.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        object.readData();
        //object.setID(id);
        System.out.println("loanDate : ");
        loanDate.readData();
        setReturnDate();
    }

    public void printData() {
        System.out.println("The person who loaned the object is:");
        client.printData();
        System.out.println("Loan Date:");
        loanDate.printData();
        System.out.println("Return Date:");
        returnDate.printData();
        object.printData();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getLoanDate() {
        return loanDate;
    }


    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public void addToFile(String fileName) {
        File log = new File(fileName);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(log, true));
            StringBuilder sb = new StringBuilder();

            sb.append(client.getID().toString());
            sb.append(",");
            sb.append(client.getFirstName());
            sb.append(",");
            sb.append(client.getLastName());
            sb.append(",");
            sb.append(client.getBirthday());
            sb.append(",");
            sb.append(client.getAdress());
            sb.append(",");
            sb.append(client.getContact());
            sb.append(",");
            sb.append(loanDate.getDay().toString());
            sb.append(",");
            sb.append(loanDate.getMonth().toString());
            sb.append(",");
            sb.append(loanDate.getYear().toString());
            sb.append(",");

            sb.append(object.getID().toString());
            sb.append(",");

            if (object.getClass().getName() == "com.Model.Book") {

                sb.append(object.getTitle());
                sb.append(",");
                sb.append(object.getAuthor());
                sb.append(",");
                sb.append(object.getShelf().toString());
                sb.append(",");
                sb.append(object.getPublisherName());
                sb.append(",");
                sb.append(object.getPublisherAddress());
                sb.append(",");
                sb.append(object.isAvailable());

            }
            else {
                System.out.println(" file name is " + fileName);
                sb.append(object.getShelf().toString());
                sb.append(",");
                sb.append(object.isAvailable());
                sb.append(",");
                sb.append(object.getTitle());
                sb.append(",");
                sb.append(object.getReleaseDay().toString());
                sb.append(",");
                sb.append(object.getReleaseMonth().toString());
                sb.append(",");
                sb.append(object.getReleaseYear().toString());
                sb.append(",");
                sb.append(object.getGenre());
                sb.append(",");
                sb.append(object.getRating().toString());
            }

//            sb.append("\r\n");

            pw.println(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

