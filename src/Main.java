
import GUI.AppMainForm;
import GUI.User;
import com.Model.Book;
import com.Model.Date;
import com.Model.Library;
import com.Model.ObjectLoaned;
import Services.Command;
import Services.ReadLibraryFromDatabase;

import javax.swing.*;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {

//        Date d = new Date();
//        d.readData();
//        d.printData();

//        Library l = new Library();
//        ReadLibraryFromDatabase.readAll(l);
//        l.printBooks();
//        l.printMovies();
////        l.printClients();
//        l.printEmployees();
//        Command c = new Command();
////        c.start();


        Library library = new Library();
        ReadLibraryFromDatabase.readAll(library);
        try {
            library.updateObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AppMainForm appMainForm = new AppMainForm();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                appMainForm.initFrameWithMenu(library);
            }
        });
    }
}
