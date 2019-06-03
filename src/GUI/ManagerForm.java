package GUI;

import com.Model.Book;
import com.Model.Date;
import com.Model.Library;
import com.Model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerForm extends JFrame{
    private JLabel managerLabel;
    private JRadioButton bookRadioBtn;
    private JRadioButton movieRadioBtn;
    private JRadioButton employeeRadioBtn;
    private JPanel managerPanel;
    private JLabel addLabel;
    private JButton nextBtn;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JRadioButton clientRadioButton;
    private JTextField textField;
    private JButton doneButton;
    private JButton switchToClientModeButton;
    private ButtonGroup  buttonGroup;
    private ArrayList<String> textes = new ArrayList<>();
    static public int step = 0;
    private String added;
    private ArrayList<String> input = new ArrayList<>();

    public ManagerForm(Library l) {
        setTitle("Manager");
        setContentPane(managerPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(bookRadioBtn);
        buttonGroup.add(movieRadioBtn);
        buttonGroup.add(clientRadioButton);
        buttonGroup.add(employeeRadioBtn);

        bookRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                added = "book";
                JRadioButton source = (JRadioButton)e.getSource();
                if( source.isSelected())
//                    System.out.println("add book");
                addBookText();
            }
        });

        movieRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                added = "movie";
                addMovieText();
            }
        });


        clientRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                added = "client";
                addClientText();
            }
        });

        employeeRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeeText();
                added = "employee";
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(step == 0) { input.clear();}
                input.add(textField.getText());

                if(step < textes.size()) {
                    addLabel.setText(textes.get(step));
                    increaseStep();
                }
            }
        });
        
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(added.equals("book")) addBook(l);
                else if(added.equals("movie")) addMovie(l);
                else if(added.equals("client")) addClient();
                else if(added.equals("employee"))addEmployee();
            }
        });

        switchToClientModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame client = new User(l);
            }
        });

        pack();
        setVisible(true);
    }


    private void addMovieText() {
        step = 0;
        textes.clear();
        textes.add("Add Title");
        textes.add("Add release");
        textes.add("Add genre");
        textes.add("Add rating");
        textes.add("Shelf");
    }
    private void addMovie(Library l) {
//        Integer shelf, boolean available, String title, Date release, String genre, Double rating)
        boolean available;
        String title = input.get(0);
        Date release = new Date();
        release.strToDate(input.get(1));
        String genre = input.get(2);
        Double rating = Double.parseDouble(input.get(3));
        Integer shelf = Integer.parseInt(input.get(3));
        Movie movie = new Movie(shelf,true, title, release, genre, rating);
        l.addMovie(movie);

    }

    private void addClient() {
    }


    private void addEmployee() {
    }
    public void increaseStep() {
        step++;
    }

    private void addBookText() {
        step = 0;
        textes.clear();
        textes.add("Add Title");
        textes.add("Add Author");
        textes.add("Publisher name");
        textes.add("Publisher address");
        textes.add("Shelf");
    }

    private void addBook(Library l){
        String title, author, pname, paddress;
        Integer shelf;
        title = input.get(0);
        author = input.get(1);
        pname = input.get(2);
        paddress = input.get(3);
        shelf = Integer.parseInt(input.get(4));
        Book b = new Book(title,author,shelf,pname,paddress, true);
        l.addBook(b);
    }


    private void addClientText() {
        step = 0;
        textes.clear();
        textes.add("Add first name");
        textes.add("Add last name");
        textes.add("Add birthday");
        textes.add("Add address");
        textes.add("Add contact");

    }

    private void addEmployeeText() {
        step = 0;
        textes.clear();
        textes.add("Add first name");
        textes.add("Add last name");
        textes.add("Add birthday");
        textes.add("Add address");
        textes.add("Add function");
        textes.add("Add salary");
    }





//    public void createUIComponents() {
//        // TODO: place custom component creation code here
//        jPanel2 = new JPanel();
//        jPanel3 = new JPanel();
//        buttonGroup = new ButtonGroup();
//        managerLabel = new JLabel("Do you want to add");
//        managerPanel = new JPanel();
//        addLabel = new JLabel( );
//        nextBtn = new JButton("Next step");
//        textFieldAdd = new JTextField();
//        textFieldAdd.setFont(textFieldAdd.getFont().deriveFont(textFieldAdd.getFont().getSize() * 2));
//        textFieldAdd.setToolTipText("Insert here");
////        textFieldAdd.setMinimumSize(new Dimension(200,300));
//        textFieldAdd.setSize(200,300);
//
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(800, 600));
//        setContentPane(this.managerPanel);
//
//        bookRadioBtn = new JRadioButton("book");
//        bookRadioBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JRadioButton source = (JRadioButton)e.getSource();
//                ManagerForm.step = 0;
//                if( source.isSelected()) {
//                    System.out.println("book button is selected");
//                    nextBtn.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            ManagerForm.step ++;
//                            switch (ManagerForm.step) {
//                                case 0:
//                                    addLabel.setText("Add title");
//                                    break;
//                                case 1:
//                                {
//                                    addLabel.setText("Add author");
//                                    break; }
//                                case 2: {
//                                    addLabel.setText("case2");
//                                    break;
//                                }
//                                default: {addLabel.setText("Default"); break;}
//                            }
//                        }
//                    });
//
//                }
//
//
//                if (!source.isSelected())
//                {
////                    panel1.setBackground(Color.red);
//                }
//
//            }
//        });
//
//
//        movieRadioBtn = new JRadioButton("movie");
//        movieRadioBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JRadioButton source = (JRadioButton)e.getSource();
//                if( source.isSelected()) {
//                    System.out.println("movie button is selected");
//                    addLabel.setText("Add movie name: ");
//                }
//                if (!source.isSelected())
//                {
////                    panel1.setBackground(Color.red);
//                }
//
//            }
//        });
//
//
//        loanRadioBtn = new JRadioButton("loan paper");
//        loanRadioBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JRadioButton source = (JRadioButton)e.getSource();
//                if( source.isSelected()) {
//                    System.out.println("loan button is selected");
//                    addLabel.setText("Add object type");
//                }
//                if (!source.isSelected())
//                {
////                    panel1.setBackground(Color.red);
//                }
//
//            }
//        });
//
//        employeeRadioBtn = new JRadioButton("employee");
//        employeeRadioBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JRadioButton source = (JRadioButton)e.getSource();
//                if( source.isSelected()) {
//                    System.out.println("employee button is selected");
//                    addLabel.setText("Add first name");
//                }
//                if (!source.isSelected())
//                {
////                    panel1.setBackground(Color.red);
//                }
//
//            }
//        });
//
//


////        completeTable("books");
//        add(textFieldAdd);
//        add(nextBtn);
//        add(addLabel);
//        add(managerLabel);
//        add(bookRadioBtn);
//        add(movieRadioBtn);
//        add(loanRadioBtn);
//        add(employeeRadioBtn);
//        pack();
//        setVisible(true);
//
//    }
}
