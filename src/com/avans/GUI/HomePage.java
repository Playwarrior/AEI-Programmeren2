package com.avans.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Home screen of the application
 *
 * TODO: Add logo Sources/NFLogo.png to the homescreen
 */

public class HomePage extends JPanel {

    private JPanel homePagePanel;
    private JLabel homePageTitle;
    private JLabel infoAppVersion;

    public HomePage(){
        this.homePagePanel = new JPanel();
        GridLayout layout = new GridLayout(5, 1);
        this.homePagePanel.setLayout(layout);

        this.homePageTitle = new JLabel("Netflix Statistix", SwingConstants.CENTER);
        this.infoAppVersion = new JLabel("Versie 0.9.3\n " +
                                        "Last updated: 23-12-2018", SwingConstants.CENTER);
    }

    public void addComponents(){
        // Adding all components to the side menu panel
        this.homePagePanel.add( new JLabel("")); // Spacing for the home title
        this.homePagePanel.add( this.homePageTitle);
        this.homePagePanel.add( new JLabel("")); // Spacing between home title and App info
        this.homePagePanel.add( this.infoAppVersion);
    }

    public void initializeAesthetics() {
        // Initializing the visual aesthetics of the components inside the home page panel
        this.homePagePanel.setBackground(Color.darkGray);

        this.homePageTitle.setFont( new Font("Helvetica Neue", Font.ITALIC, 40));
        this.homePageTitle.setForeground(Color.red);

        this.infoAppVersion.setFont( new Font("Helvetica Neue", Font.PLAIN, 10));
        this.infoAppVersion.setForeground(Color.lightGray);
    }

    public void initializeHomePage(){
        initializeAesthetics();
        addComponents();
    }

    public JPanel getJPanel(){
        return this.homePagePanel;
    }
}
