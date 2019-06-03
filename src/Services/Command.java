package Services;

import GUI.AppMainForm;
import Persons.Client;
import Persons.Employee;
import com.Model.Book;
import com.Model.Library;
import com.Model.Loan;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class Command {
    private Library library;
    static private String fileName = "commands.csv";
    public Command(){
        library = new Library();
        //library.readData();

    }


    public void printMenu(){
        System.out.println("------>  Menu  <------");
        System.out.println();
        System.out.println("16. Use GUI");
        System.out.println("1. Get all the information about the library");
        System.out.println("2. Get the books in alphabetically order");
        System.out.println("3. Get all the clients");
        System.out.println("4. Get all the loans");
        System.out.println("5. Create a new loan paper");
        System.out.println("6. Add a new book");
        System.out.println("7. Add a new client");
        System.out.println("8. Add a new employee");
        System.out.println("9. Check if a book exists in the library");
        System.out.println("10. Check if a book is available");
        System.out.println("11. Get the clients that have not returned the books yet");
        System.out.println("12. Get the number of employees");
        System.out.println("13. Get the max salary");
        System.out.println("14. Get all the movies");
        System.out.println("15. Delete object");
        System.out.println("0. Exit menu ");
    }
    public void start() {

        System.out.println(" Read from file or database ?");
        String ans;
        Scanner scanner = new Scanner(System.in);
        ans = scanner.nextLine();
        if (ans.equals("file")) {
            library.readData();
            try {
                library.updateObjects();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            printMenu();
            int command;
            Scanner in = new Scanner(System.in);
            command = in.nextInt();

            while (command != 0) {
                switch (command) {
                    case 16: {
                        AppMainForm appMainForm = new AppMainForm();

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                appMainForm.initFrameWithMenu(library);
                            }
                        });
                        break;
                    }
                    case 1: {
                        addToFile("Get-All");
                        library.printData();
                        break;
                    }
                    case 2: {
                        addToFile("Get-books");
                        library.sortBooks();
                        library.printBooks();
                        break;
                    }
                    case 3: {
                        addToFile("Get-All-Clients");
                        library.printClients();
                        break;
                    }
                    case 4: {
                        addToFile("Get-All-loans");
                        library.printLoans();
                        break;
                    }
                    case 5: {
                        addToFile("Create-loan-paper");
                        library.addLoan();

                        break;
                    }
                    case 6: {
                        addToFile("Add-book");

                        library.addBook();
                        break;
                    }
                    case 7: {
                        addToFile("Add-client");
                        library.addClient();
                        break;
                    }
                    case 8: {
                        addToFile("Add-employee");
                        library.addEmployee();
                        break;
                    }
                    case 9: {
                        addToFile("Check-book's-existance");
                        String name = "", author = "";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            System.out.println("Title : ");
                            name = reader.readLine();
                            System.out.println("Author : ");
                            author = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (library.findBook(name, author) == true)
                            System.out.println("The book exists in our library");
                        else
                            System.out.println("The book doesn't exist in our library");
                        break;
                    }
                    case 10: {
                        addToFile("Check-book-available");
                        String name = "", author = "";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            System.out.println("Title : ");
                            name = reader.readLine();
                            System.out.println("Author : ");
                            author = reader.readLine();

                            System.out.println("Titlu: " + name + " Autor: " + author);

                            boolean available;

                            available = library.check(name, author);
                            if (available)
                                System.out.println("The book is available");
                            else System.out.println("The book is not available");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 11: {
                        addToFile("Get-client-that-didn't-return-the-book");
                        library.printEndedLoans();
                        break;
                    }
                    case 12: {
                        addToFile("Get-number-of-employees");
                        System.out.println(library.nrEmployees());
                        break;
                    }
                    case 13: {
                        addToFile("Get-max-salary");
                        System.out.println(library.getMaxSalary());
                        break;
                    }
                    case 14: {
                        addToFile("Get-All-Movies");
                        library.printMovies();
                        break;
                    }
                    default: {
                        System.out.println("The-command-doesn't-exist");
                    }
                }

                printMenu();
                System.out.println();
                System.out.println("The next command is: ");
                command = in.nextInt();
            }
        }
        else
        { //database
            ReadLibraryFromDatabase.readAll(library);
            try {
                library.updateObjects();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            printMenu();
            int command;
            Scanner in = new Scanner(System.in);
            command = in.nextInt();

            while (command != 0) {
                switch (command) {
                    case 16: {
                        AppMainForm appMainForm = new AppMainForm();

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                appMainForm.initFrameWithMenu(library);
                            }
                        });
                        break;
                    }
                    case 1: {
                        addToFile("Get-All");
                        library.printData();
                        break;
                    }
                    case 2: {
                        addToFile("Get-books");
                        library.sortBooks();
                        library.printBooks();
                        break;
                    }
                    case 3: {
                        addToFile("Get-All-Clients");
                        library.printClients();
                        break;
                    }
                    case 4: {
                        addToFile("Get-All-loans");
                        library.printLoans();
                        break;
                    }
                    case 5: {
                        addToFile("Create-loan-paper");
                        Loan l = new Loan();
                        l.readData();
                        library.addL(l);
                        UpdateDatabase.addLoan(l);

                        break;
                    }
                    case 6: {
                        addToFile("Add-book");
                        Book b = new Book();
                        b.readData();
                        library.addBook(b);
                        UpdateDatabase.addBook(b);
                        break;
                    }
                    case 7: {
                        addToFile("Add-client");
                        Client c = new Client();
                        c.readData();
                        UpdateDatabase.addClient(c);
                        library.addClient(c);
                        break;
                    }
                    case 8: {
                        addToFile("Add-employee");
                        Employee e = new Employee();
                        e.readData();
                        library.addEmployee(e);
                        UpdateDatabase.addEmployee(e);
                        break;
                    }
                    case 9: {
                        addToFile("Check-book's-existance");
                        String name = "", author = "";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            System.out.println("Title : ");
                            name = reader.readLine();
                            System.out.println("Author : ");
                            author = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (library.findBook(name, author) == true)
                            System.out.println("The book exists in our library");
                        else
                            System.out.println("The book doesn't exist in our library");
                        break;
                    }
                    case 10: {
                        addToFile("Check-book-available");
                        String name = "", author = "";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            System.out.println("Title : ");
                            name = reader.readLine();
                            System.out.println("Author : ");
                            author = reader.readLine();

                            System.out.println("Titlu: " + name + " Autor: " + author);

                            boolean available;

                            available = library.check(name, author);
                            if (available)
                                System.out.println("The book is available");
                            else System.out.println("The book is not available");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 11: {
                        addToFile("Get-client-that-didn't-return-the-book");
                        library.printEndedLoans();
                        break;
                    }
                    case 12: {
                        addToFile("Get-number-of-employees");
                        System.out.println(library.nrEmployees());
                        break;
                    }
                    case 13: {
                        addToFile("Get-max-salary");
                        System.out.println(library.getMaxSalary());
                        break;
                    }
                    case 14: {
                        addToFile("Get-All-Movies");
                        library.printMovies();
                        break;
                    }
                    case 15: {
                        System.out.println(" Din ce tabel :");
                        Scanner inn = new Scanner(System.in);
                        String c = inn.nextLine();
                        System.out.println("Ce id: ");
                        Integer id = inn.nextInt();

                        UpdateDatabase.deleteRow(c, id);
                        break;
                    }
                    default: {
                        System.out.println("The-command-doesn't-exist");
                    }
                }

                printMenu();
                System.out.println();
                System.out.println("The next command is: ");
                command = in.nextInt();
            }

        }
    }


    public static void addToFile(String comName) {
        File log = new File(fileName);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(log, true));
            StringBuilder sb = new StringBuilder();

            sb.append(comName);
            sb.append(",");
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            sb.append(formatter.format(date));
            pw.println(sb.toString());
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
