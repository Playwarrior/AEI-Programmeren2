package com.avans.GUI;


import javax.swing.*;
import java.awt.*;

/**
 * TODO:
 *
 *  Overview of SELECT series with each episode containing view% of the duration,
 *      each episode shows title and episode number;
 *
 *  Overview of SELECT account and SELECT series that shows average watch time for each episode;
 *
 *  Overview of SELECT account of what films have been watched;
 *
 *  Overview of film under 16 with longest duration;
 *
 *  Overview of accounts with only 1 profile;
 *
 *  Overview of SELECT film with number of watchers watched the whole film.
 */

public class UserInterFace implements Runnable{

    private JFrame frame;
    private HomePage homePage;

    public UserInterFace(){
        this.homePage = new HomePage();

    }

    @Override
    public void run() {
        frame = new JFrame("Netflix Statistix");
        frame.setPreferredSize(new Dimension(1000, 800));

        frame.setIconImage(new ImageIcon("sources//NetflixLogo.png").getImage());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }


    private void createComponents(Container contentPane) {
        BorderLayout layout = new BorderLayout();
        contentPane.setLayout(layout);
        contentPane.setBackground(Color.DARK_GRAY);


        // Home Page
        this.homePage.initializeHomePage();


        // Side Menu Panel
        SidePanelMenu sidePanelMenu = new SidePanelMenu();
        sidePanelMenu.initializeSidePanelMenu();


        contentPane.add(sidePanelMenu.getJPanel(), BorderLayout.WEST);
        contentPane.add(credits(), BorderLayout.SOUTH);
        contentPane.add(this.homePage.getJPanel(), BorderLayout.CENTER);
    }

    private static JPanel credits(){
        // Shows on the bottom of the App the creators of the App
        JPanel creditsPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        creditsPanel.setLayout(layout);
        creditsPanel.setBackground(Color.GRAY);

        JLabel studentNames = new JLabel("Door: Robin Egberts, Bryan Kho, Jur Nagtzaam   ");
        studentNames.setForeground(Color.LIGHT_GRAY);
        creditsPanel.add(studentNames, BorderLayout.EAST);

        return creditsPanel;
    }


    public JFrame getFrame(){
        return this.frame;
    }
}
