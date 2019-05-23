package Services;

import Persons.Employee;
import com.Model.*;
import Persons.Client;
import com.mysql.jdbc.PreparedStatement;

import javax.xml.crypto.Data;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReadLibraryFromDatabase {

    static public void readAll(Library l) {
        readBooks(l);
        readMovies(l);
        readClients(l);
        readLoans(l);
        readEmployees(l);
    }

    static void readBooks (Library l) {
        ArrayList<String> args = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);
            Statement stmt = DatabaseConnection.connection.createStatement();
            String qrySQL = "SELECT * FROM books";
            ResultSet rs = stmt.executeQuery(qrySQL);
            //Integer ID, String title, String author, Integer shelf, String pname, String pAddress, boolean available
            while ( rs.next()) {
                args.add(rs.getString("ID"));
                args.add(rs.getString("title"));
                args.add(rs.getString("author"));
                args.add(rs.getString("shelf"));
                args.add(rs.getString("available"));
                args.add(rs.getString("pname"));
                args.add(rs.getString("paddress"));
                boolean av;
                if (args.get(4).equals('1')) av = true;
                else av = false;

                Book b = new Book(Integer.parseInt(args.get(0)), args.get(1), args.get(2), Integer.parseInt(args.get(3)), args.get(5), args.get(6), av);
                args.clear();
                l.addBook(b);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void readMovies (Library l) {
        ArrayList<String> args = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url, DatabaseConnection.userName, DatabaseConnection.password);
            Statement stmt = DatabaseConnection.connection.createStatement();
            String qrySQL = "SELECT * FROM movies";
            ResultSet rs = stmt.executeQuery(qrySQL);
            //Integer ID, Integer shelf, boolean available, String title, Integer day, Integer month, Integer year, String genre, Double rating
            while (rs.next()) {
                args.add(rs.getString("ID"));
                args.add(rs.getString("shelf"));
                args.add(rs.getString("available"));
                args.add(rs.getString("title"));
                args.add(rs.getString("day"));
                args.add(rs.getString("month"));
                args.add(rs.getString("year"));
                args.add(rs.getString("genre"));
                args.add(rs.getString("rating"));
                boolean av;
                if (args.get(2).equals('1')) av = true;
                else av = false;
                Movie movie = new Movie(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)), av, args.get(3), Integer.parseInt(args.get(4)), Integer.parseInt(args.get(5)), Integer.parseInt(args.get(6)), args.get(7), Double.parseDouble(args.get(8)));
                args.clear();
                l.addMovie(movie);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void readClients (Library l) {
        ArrayList<String> args = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);
            Statement stmt = DatabaseConnection.connection.createStatement();
            String qrySQL = "SELECT * FROM clients";
            ResultSet rs = stmt.executeQuery(qrySQL);
            //Integer id, String firstName, String lastName, String birthday, String adress, String contact
            while ( rs.next()) {
                args.add(rs.getString("ID"));
                args.add(rs.getString("firstname"));
                args.add(rs.getString("lastname"));
                args.add(rs.getString("birthday"));
                args.add(rs.getString("address"));
                args.add(rs.getString("contact"));
                Client client = new Client (Integer.parseInt(args.get(0)),args.get(1), args.get(2), args.get(3), args.get(4), args.get(5));
                args.clear();
                l.addClient(client);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void readLoans(Library l) {
        ArrayList<String> args = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);
            Statement stmt = DatabaseConnection.connection.createStatement();
            String qrySQL = "SELECT * FROM loans";
            ResultSet rs = stmt.executeQuery(qrySQL);
            //Integer id, String firstName, String lastName, String birthday, String adress, String contact
            while ( rs.next()) {
                args.add(rs.getString("ID"));
                args.add(rs.getString("idclient"));
                args.add(rs.getString("idobject"));
                args.add(rs.getString("type"));
                args.add(rs.getString("loandate"));
                args.add(rs.getString("returndate"));
                int oidx = 0;

                int idxClient = l.getClientIndex(Integer.parseInt(args.get(1)));
                Client c = l.returnClient(idxClient);
                Loan loan = null;
                boolean done = false;
                Date ld = new Date();
                ld.strToDate(args.get(4));
                Date rd = new Date();
                rd.strToDate(args.get(5));

                if (args.get(3).equals("book")) {
                    oidx = l.getBookIndex(Integer.parseInt(args.get(2)));
                    if(oidx != -1) {
                        done = true;
                        Book b =  l.returnBook(oidx);
                        loan = new Loan(c,b,ld, rd);
                    }

                }
                else {
                    oidx = l.getMovieIndex(Integer.parseInt(args.get(2)));
                    if(oidx != -1) {
                        done = true;
                        Movie m = l.returnMovie(oidx);
                        loan = new Loan(c,m,ld,rd);
                    }
                }


                if(done)
                    l.addL(loan);
                args.clear();

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void readEmployees(Library l) {
        ArrayList<String> args = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);
            Statement stmt = DatabaseConnection.connection.createStatement();
            String qrySQL = "SELECT * FROM employees";
            ResultSet rs = stmt.executeQuery(qrySQL);
            //Integer id, String firstName, String lastName, String birthday, String adress, String contact
            while ( rs.next()) {
                args.add(rs.getString("ID"));
                args.add(rs.getString("firstname"));
                args.add(rs.getString("lastname"));
                args.add(rs.getString("address"));
                args.add(rs.getString("function"));
                args.add(rs.getString("salary"));
                args.add(rs.getString("birthday"));
                Employee employee = new Employee (args.get(1), args.get(2), args.get(6), args.get(3), args.get(4), Double.parseDouble(args.get(5)));
                args.clear();
                l.addEmployee(employee);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}


