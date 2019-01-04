package com.avans.GUI;


import javax.swing.*;
import java.awt.*;

/**
 * TODO:
 * <p>
 * Overview of SELECT series with each episode containing view% of the duration,
 * each episode shows title and episode number;
 * <p>
 * Overview of SELECT account and SELECT series that shows average watch time for each episode;
 * <p>
 * Overview of SELECT account of what films have been watched;
 * <p>
 * Overview of film under 16 with longest duration;
 * <p>
 * Overview of accounts with only 1 profile;
 * <p>
 * Overview of SELECT film with number of watchers watched the whole film.
 */

public class UserInterFace implements Runnable {

    private JFrame frame;
    private HomePage homePage;

    public UserInterFace() {
        this.homePage = new HomePage();

    }

    @Override
    public void run() {
        frame = new JFrame("Netflix Statistix");
        frame.setPreferredSize(new Dimension(1000, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }


    private void createComponents(Container contentPane) {
        BorderLayout layout = new BorderLayout();
        contentPane.setLayout(layout);
        contentPane.setBackground(Color.darkGray);


        // Home Page
        this.homePage.initializeHomePage();


        // Side Menu Panel
        SidePanelMenu sidePanelMenu = new SidePanelMenu();
        sidePanelMenu.initializeSidePanelMenu();


        contentPane.add(sidePanelMenu.getJPanel(), BorderLayout.WEST);
        contentPane.add(credits(), BorderLayout.SOUTH);
        contentPane.add(this.homePage.getJPanel(), BorderLayout.CENTER);
    }

    private static JPanel credits() {
        // Shows on the bottom of the App the creators of the App
        JPanel creditsPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        creditsPanel.setLayout(layout);
        creditsPanel.setBackground(Color.gray);

        JLabel studentNames = new JLabel("Door: Robin Egberts, Bryan Kho, Jur Nagtzaam   ");
        studentNames.setForeground(Color.lightGray);
        creditsPanel.add(studentNames, BorderLayout.EAST);

        return creditsPanel;
    }


    public JFrame getFrame() {
        return this.frame;
    }
}
