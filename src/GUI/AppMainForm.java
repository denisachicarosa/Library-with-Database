package GUI;

import Persons.Client;
import com.Model.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.DomainCombiner;

public class AppMainForm {
    private JPanel mainPanel;

    public void initFrameWithMenu(Library l) {
        JFrame jFrame = new JFrame("Library Manager");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(800, 600));
        jFrame.setContentPane(this.mainPanel);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");


        JMenuItem clientMenuItem = new JMenuItem("Use as a client");
        clientMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                ClientForm clientForm = new ClientForm();
                User user = new User(l);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
//                        user.createUIComponents();
                        JFrame user = new User(l);
                    }
                });
            }
        });


        JMenuItem managerMenuItem = new JMenuItem("Use as a manager");
        managerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
//                        managerForm.createUIComponents();
                        JFrame man = new ManagerForm(l);

                    }
                });
//                System.exit(0);
            }
        });


        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(clientMenuItem);
        fileMenu.add(managerMenuItem);
        fileMenu.add(exitMenuItem);
        jMenuBar.add(fileMenu);

        jFrame.setJMenuBar(jMenuBar);

        jFrame.pack();
        jFrame.setVisible(true);
    }

}
