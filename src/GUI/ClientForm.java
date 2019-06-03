package GUI;

import com.Model.Book;
import com.Model.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientForm extends JFrame
{
    private JPanel panel1;
    private JRadioButton listMoviesRadioButton;
    private JRadioButton listBooksRadioButton;
    private JScrollPane scrollPaneBooks;
    private JTable objects;
    private ButtonGroup buttonGroup ;

    private void completeTable(String object ) {

        if (object.equals("books")) {System.out.println("I'm in");
            String[] columns = {"Title", "Author", "Publishing House Name", "Publishing House address"};
            String[][] data = {{"Strainul de langa mine", "Irina Binder", "Art", "Bucuresti"}};
            objects = new JTable(data, columns){
                public boolean isCellEditable(int data, int columns)
                {
                    return false;
                }
            };
            objects.setPreferredScrollableViewportSize(new Dimension(450, 200));
            objects.setFillsViewportHeight(true);

            JScrollPane jps = new JScrollPane(objects);
            add(jps);
//            panel1.repaint();
        }
        else if (object.equals("movies")) {
            String[] columns = {"Title", "Release", "Genre", "Rating"};
            String[][] data = {{"Green Mile", "2017", "Drama","8.3"}, {"Harry Potter", "2014", "Fantasy", "9.1"}};
            objects = new JTable(data,columns) {
                public boolean isCellEditable(int data, int columns) {
                    return false;
                }
            };
            objects.setPreferredScrollableViewportSize(new Dimension(450, 200));
            objects.setFillsViewportHeight(true);
            JScrollPane jps = new JScrollPane(objects);
            add(jps);
//            panel1.repaint();
        }
    }

    public void createUIComponents() {
        // TODO: place custom component creation code here

        buttonGroup = new ButtonGroup();

        panel1 = new JPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setContentPane(this.panel1);
        listBooksRadioButton = new JRadioButton("Books");
        listBooksRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton source = (JRadioButton)e.getSource();
                if( source.isSelected())
                    System.out.println("book button is selected");
                    completeTable("books");

                if (!source.isSelected())
                {
                    panel1.setBackground(Color.red);
                }
                panel1.repaint();
            }
        });

        listMoviesRadioButton = new JRadioButton("Movies");
        listMoviesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton source = (JRadioButton)e.getSource();
                if( source.isSelected())
                    completeTable("movies");
                if (!source.isSelected())
                {
                    panel1.setBackground(Color.red);
                }
                panel1.repaint();
            }
        });

        buttonGroup.add(listBooksRadioButton);
        buttonGroup.add(listMoviesRadioButton);
//        completeTable("books");
        add(listBooksRadioButton);
        add(listMoviesRadioButton);
        pack();
        setVisible(true);

    }
}
