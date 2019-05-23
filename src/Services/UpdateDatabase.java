package Services;

import Persons.Client;
import Persons.Employee;
import com.Model.Book;
import com.Model.Loan;
import com.Model.Movie;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDatabase {
    public static void addLoan(Loan l) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);

            String query = " insert into loans (idclient, idobject, type, loandate, returndate)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) DatabaseConnection.connection.prepareStatement(query);

            String type;

            if (l.getObject().getClass().getName().equals("com.Model.Book")) type = "book";
            else type = "movie";


            preparedStmt.setInt (1, l.getClient().getID());
            preparedStmt.setInt (2,l.getObject().getID());
            preparedStmt.setString (3, type);
            preparedStmt.setString(4, l.getLoanDate().toString());
            preparedStmt.setString   (5, l.getReturnDate().toString());


            // execute the preparedstatement
            preparedStmt.execute();
            System.out.println("done");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void addBook(Book b) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);

            String qu = "select max(id)  from books";
            Statement stmt = DatabaseConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(qu);
            b.setID(Integer.parseInt(rs.getString("max(id)"))+1);
            String query = " insert into books (title, author, shelf, available, pname, paddress)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) DatabaseConnection.connection.prepareStatement(query);
            String av;
            if (b.isAvailable()) av ="true";
            else av = "false";
            preparedStmt.setString (1, b.getTitle());
            preparedStmt.setString (2, b.getAuthor());
            preparedStmt.setInt   (3, b.getShelf());
            preparedStmt.setString(4, av);
            preparedStmt.setString   (5, b.getPublisherName());
            preparedStmt.setString   (6, b.getPublisherAddress());

            // execute the preparedstatement
            preparedStmt.execute();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void addClient(Client c ) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);
            String qu = "select max(id)  from clients";
            Statement stmt = DatabaseConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(qu);
            c.setID(Integer.parseInt(rs.getString("max(id)"))+1);

            String query = " insert into clients ( firstname, lastname, birthday, address, contact)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) DatabaseConnection.connection.prepareStatement(query);


            preparedStmt.setString (1, c.getFirstName());
            preparedStmt.setString (2,c.getLastName());
            preparedStmt.setString (3, c.getBirthday());
            preparedStmt.setString(4,c.getAdress());
            preparedStmt.setString   (5,c.getContact());


            // execute the preparedstatement
            preparedStmt.execute();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void addEmployee(Employee e) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);

            String query = " insert into clients ( firstname, lastname, address, function, salary, birthday)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) DatabaseConnection.connection.prepareStatement(query);


            preparedStmt.setString (1, e.getFirstName());
            preparedStmt.setString (2, e.getLastName());
            preparedStmt.setString (3, e.getAdress());
            preparedStmt.setString (4, e.getFunction());
            preparedStmt.setDouble (5, e.getSalary());
            preparedStmt.setString (6, e.getBirthday());


            // execute the preparedstatement
            preparedStmt.execute();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void AddMovie(Movie m) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url,DatabaseConnection.userName,DatabaseConnection.password);

            String qu = "select max(id)  from movies";
            Statement stmt = DatabaseConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(qu);
            m.setID(Integer.parseInt(rs.getString("max(id)"))+1);
            String query = " insert into movies (shelf, available, title, day, month, year, genre, rating)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) DatabaseConnection.connection.prepareStatement(query);
            String av;
            if (m.isAvailable()) av ="true";
            else av = "false";
            preparedStmt.setInt     (1, m.getShelf());
            preparedStmt.setString  (2, av);
            preparedStmt.setString  (3, m.getTitle());
            preparedStmt.setInt     (4, m.getReleaseDay());
            preparedStmt.setInt     (5, m.getReleaseMonth());
            preparedStmt.setInt     (6, m.getReleaseYear());
            preparedStmt.setString  (7, m.getGenre());
            preparedStmt.setDouble  (8, m.getRating());

            // execute the preparedstatement
            preparedStmt.execute();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void updateAvailable (String tableName, Integer idObject,String available) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if ( idObject > 0 ) {
            DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url, DatabaseConnection.userName, DatabaseConnection.password);
            String querry = null;
            if( tableName.equals("movies"))
                querry = "update movies set available = ?  where id = ?";
            else if (tableName.equals("books"))
                querry = "update books set available = ?  where id = ?";

            PreparedStatement preparedStatement = (PreparedStatement) DatabaseConnection.connection.prepareStatement(querry);
            preparedStatement.setString(1, available);
            preparedStatement.setInt(2, idObject);
            preparedStatement.execute();
            System.out.println("update " + tableName + " " + available + " " + idObject);
        }
    }

    public static void deleteRow(String tableName, Integer idObject) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if ( idObject > 0 ) {
            try {
                DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url, DatabaseConnection.userName, DatabaseConnection.password);
                String querry = null;
                querry = "delete from "+tableName+ " where id = ?" ;

                PreparedStatement preparedStatement = (PreparedStatement) DatabaseConnection.connection.prepareStatement(querry);
                preparedStatement.setInt(1, idObject);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (tableName.equals("clients") ){
            try {
                DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url, DatabaseConnection.userName, DatabaseConnection.password);
                String querry = null;
                querry = "delete from loans where idclient = ?" ;

                PreparedStatement preparedStatement = (PreparedStatement) DatabaseConnection.connection.prepareStatement(querry);
                preparedStatement.setInt(1, idObject);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (tableName.equals("movies") || tableName.equals("books")) {
            try {
                DatabaseConnection.connection = DriverManager.getConnection(DatabaseConnection.url, DatabaseConnection.userName, DatabaseConnection.password);
                String querry = null;
                querry = "delete from loans where idobject = ?" ;

                PreparedStatement preparedStatement = (PreparedStatement) DatabaseConnection.connection.prepareStatement(querry);
                preparedStatement.setInt(1, idObject);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
