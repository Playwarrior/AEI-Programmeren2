package com.avans.gui;


import com.avans.handlers.DataHandler;

import javax.swing.*;
import java.awt.*;

/**
 *  Runnable user interface class
 */

public class UserInterFace implements Runnable{

    private JFrame frame;

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
        contentPane.setBackground(Color.lightGray);

        contentPane.add(new SidePanelMenu(), BorderLayout.CENTER);   // Add a new side menu
        contentPane.add(credits(), BorderLayout.SOUTH);
    }


    private static JPanel credits(){
        // Shows on the bottom of the App the creators of the App
        JPanel creditsPanel = new JPanel();
        BorderLayout layout = new BorderLayout();

        creditsPanel.setLayout(layout);
        creditsPanel.setBackground(Color.gray);

        JLabel appName = new JLabel("   Netflix Statistix");
        JLabel studentNames = new JLabel("Informatica | 2019 | Robin Egberts, Bryan Kho, Jur Nagtzaam   ");
        studentNames.setForeground(Color.lightGray);
        appName.setForeground(Color.lightGray);

        creditsPanel.add(appName, BorderLayout.WEST);
        creditsPanel.add(studentNames, BorderLayout.EAST);

        return creditsPanel;
    }

}
