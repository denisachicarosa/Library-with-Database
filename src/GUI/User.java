package GUI;

import Persons.Client;
import Services.FindStuff;
import com.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static Services.FindStuff.*;

public class User extends JFrame{
    private JTable objects;
    private JPanel panel1;
    private JButton moviesBtn;
    private JButton booksBtn;
    private JTable moviesTable;
    private JTextField insertTitleTextField;
    private JTextField insertAuthorTextField;
    private JButton checkButton;
    private JLabel answerLabel;
    private JTextField insertLastNameTextField;
    private JTextField insertFirstNameTextField;
    private JTextField insertObjectIDTextField;
    private JTextField insertObjectTypeTextField;
    private JButton doneButton;
    private JLabel loanPaperLabel;
    private JTextField insertLoanDateTextField;

    public User(Library l) {
//        createUIComponents();
//        panel1 = new JPanel();
//        objects = new JTable(){
//            public boolean isCellEditable(int data, int columns)
//            {
//                return false;
//            }
//        };
//        moviesTable = new JTable(){
//            public boolean isCellEditable(int data, int columns)
//            {
//                return false;
//            }
//        };

        FindStuff fs = new FindStuff(l);

//        booksBtn = new JButton("List all the books" );
        booksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<ArrayList<Book>, String> stringSwingWorker = new SwingWorker<ArrayList<Book>, String>() {
                    @Override
                    protected ArrayList<Book> doInBackground() throws Exception {

                        return l.getBooks();
                    }
                    @Override
                    protected  void done(){

                        try {
                            ArrayList<Book> result = get();

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    DefaultTableModel defaultTableModel = new DefaultTableModel();
                                    defaultTableModel.addColumn("ID");
                                    defaultTableModel.addColumn("Title");
                                    defaultTableModel.addColumn("Author");
                                    defaultTableModel.addColumn("shelf");

                                    for(Book b : result) {
                                        defaultTableModel.addRow(new Object[] {b.getID(),b.getTitle(), b.getAuthor(), b.getShelf()});
                                    }

                                    objects.setModel(defaultTableModel);
                                }
                            });


                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        } catch (ExecutionException ex) {
                            ex.printStackTrace();
                        }
                    }


                };
                stringSwingWorker.execute();
            }

        });

//        moviesBtn = new JButton("List all the movies" );
        moviesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<ArrayList<Movie>, String> stringSwingWorker = new SwingWorker<ArrayList<Movie>, String>() {
                    @Override
                    protected ArrayList<Movie> doInBackground() throws Exception {

                        return l.getMovies();
                    }
                    @Override
                    protected  void done(){

                        try {
                            ArrayList<Movie> result = get();

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    DefaultTableModel defaultTableModel = new DefaultTableModel();
                                    defaultTableModel.addColumn("ID");
                                    defaultTableModel.addColumn("Title");
                                    defaultTableModel.addColumn("Release Date");
                                    defaultTableModel.addColumn("Genre");
                                    defaultTableModel.addColumn("Rating");
                                    for(Movie b : result) {
                                        defaultTableModel.addRow(new Object[] {b.getID(), b.getTitle(), b.getRelease(), b.getGenre(), b.getRating()});
                                    }

                                    moviesTable.setModel(defaultTableModel);

                                }
                            });


                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        } catch (ExecutionException ex) {
                            ex.printStackTrace();
                        }
                    }


                };
                stringSwingWorker.execute();
            }

        });


        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = insertTitleTextField.getText();
                String author = insertAuthorTextField.getText();

                boolean available = l.check(title,author);
                if (available)  answerLabel.setText("The book is available");
                else answerLabel.setText("The book is not available");
            }
        });


        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = insertObjectIDTextField.getText();
                String type = insertObjectTypeTextField.getText();
                String firstName = insertFirstNameTextField.getText();
                String lastName = insertLastNameTextField.getText();
                String date = insertLoanDateTextField.getText();
                Loan loan = null;
                Client client = findClientByName(firstName, lastName);
                if (!client.getFirstName().equals("Nu exista"))
                {
                    if(type.equals("book"))
                    {
                        ObjectLoaned book= findBookByIndex(Integer.parseInt(ID));
                        if(!book.getTitle().equals("Nu exista")) {

                            loan = new Loan(book, client, date);
                            l.addL(loan);
                            loanPaperLabel.setText("Done. The return date is "+loan.getReturnDate().toString());

                        }
                        else loanPaperLabel.setText("The book is not in our library");
                    }
                    else if(type.equals("movie")) {
                        ObjectLoaned movie= findMovieByIndex(Integer.parseInt(ID));
                        if(!movie.getTitle().equals( "Nu exista")) {
                            loan = new Loan(movie, client, date);
                            l.addL(loan);
                            loanPaperLabel.setText("Done. The return date is "+loan.getReturnDate().toString());
                        }
                        else loanPaperLabel.setText("The movie id not in our library");
                }
                }
                else loanPaperLabel.setText("The client doesn't exist in our database");
            }

        });



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500 , 700));
        setContentPane(this.panel1);
//        add(objects);
//        add(booksBtn);
//        add(moviesBtn);
        pack();
        setVisible(true);

    }

    public void createUIComponents() {
        // TODO: place custom component creation code here


//        panel1 = new JPanel();
//        objects = new JTable();
//        booksBtn = new JButton("List all the books" );
//
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(800, 600));
//        setContentPane(this.panel1);
//        add(objects);
//        add(booksBtn);
//        pack();
//        setVisible(true);

    }
}
