package com.Model;

import Services.FileAdd;

import java.io.*;

public class Book  extends  ObjectLoaned implements FileAdd, Comparable<Book> {

    private String title;
    private String author;
    private PublishingHouse publisher;

    public Book(){
        super();
        publisher = new PublishingHouse();
    }

    public Book(String title, String author, Integer shelf, String pname, String pAddress, boolean available) {
        super(shelf, available);
        this.title = title;
        this.author = author;
        this.publisher = new PublishingHouse(pname, pAddress);

    }

    public Book(Integer ID, String title, String author, Integer shelf, String pname, String pAddress, boolean available) {
        super(ID, shelf, available);
        this.title = title;
        this.author = author;
        this.publisher = new PublishingHouse(pname, pAddress);

    }

    public Book( String title, String author, Integer shelf, PublishingHouse publisher, boolean available) {
        //this.ID = ID;

        super(shelf, available);
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public Book(String[] values) {
        super(Integer.parseInt(values[2]),Boolean.parseBoolean(values[5]));
        this.title = values[0];
        this.author = values[1];
        this.publisher = new PublishingHouse(values[3],values[4]);
    }

    @Override
    public String getPublisherName() {
        return publisher.getName();
    }

    @Override
    public String getPublisherAddress() {
        return publisher.getAdress();
    }


    @Override
    public void readData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            super.readData();

            System.out.println("Title : ");
            title = reader.readLine();
            System.out.println("Author : ");
            author = reader.readLine();
            publisher.readData();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printData(){
        super.printData();
        System.out.println("Title : " + title );
        System.out.println("Author : " + author);
        System.out.println("Publishing House");
        publisher.printData();
    }


    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public PublishingHouse getPublisher() {
        return publisher;
    }

    public void setPublisher(PublishingHouse publisher) {
        this.publisher = publisher;
    }

    public int compareTo(Book a) {
        return this.getTitle().compareTo(a.getTitle());
    }

    @Override
    public void addToFile(String fileName) {

        File log = new File(fileName);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(log, true));
            StringBuilder sb = new StringBuilder();

            sb.append(title);
            sb.append(",");
            sb.append(author);
            sb.append(",");
            sb.append(getShelf().toString());
            sb.append(",");
            sb.append(publisher.getName());
            sb.append(",");
            sb.append(publisher.getAdress());
            sb.append(",");
            sb.append(isAvailable());

            pw.println(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean egal (Book b) {
        System.out.println("in egal book");
        if (title.equals(b.title) && author.equals(b.author) && getShelf().equals( b.getShelf()) && publisher.getName().equals(b.publisher.getName()) && b.publisher.getAdress().equals(publisher.getAdress())) {
            System.out.println("return true book");
            return true;
        }
        return false;
    }

    public void setID() {
    }
}
