package com.avans.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Side Panel Menu of the Application
 */

public class SidePanelMenu extends JPanel {

    private JPanel sidePanel;

    private JButton seriesAverageWatchTime;
    private JButton accountAndSeriesAverage;
    private JButton moviesWatchedOnAccount;
    private JButton longestMovieUnderSixteen;
    private JButton accountWithOnlyOneProfile;
    private JButton numberOfViewersWatchedCompleteMovie;

    public SidePanelMenu() {

        // JPanel for the sidePanelMenu
        this.sidePanel = new JPanel();
        GridLayout layout = new GridLayout(12, 1);
        this.sidePanel.setLayout(layout);


        // Buttons of the sidePanelMenu
        this.seriesAverageWatchTime = new JButton("Overzicht 1");
        this.accountAndSeriesAverage = new JButton("Overzicht 2");
        this.moviesWatchedOnAccount = new JButton("Overzicht 3");
        this.longestMovieUnderSixteen = new JButton("Overzicht 4");
        this.accountWithOnlyOneProfile = new JButton("Overzicht 5");
        this.numberOfViewersWatchedCompleteMovie = new JButton("Overzicht 6");
    }

    public void addComponents() {
        // Adding all components to the side menu panel
        this.sidePanel.add(new JLabel("", SwingConstants.LEFT)); // Spacer for the top of the side menu
        this.sidePanel.add(this.seriesAverageWatchTime);
        this.sidePanel.add(this.accountAndSeriesAverage);
        this.sidePanel.add(this.moviesWatchedOnAccount);
        this.sidePanel.add(this.longestMovieUnderSixteen);
        this.sidePanel.add(this.accountWithOnlyOneProfile);
        this.sidePanel.add(this.numberOfViewersWatchedCompleteMovie);
    }


    public void initializeAesthetics() {
        // Initializing the visual aesthetics of the components inside the side menu panel
        this.sidePanel.setBackground(Color.lightGray);

        this.seriesAverageWatchTime.setBackground(Color.gray);
        this.accountAndSeriesAverage.setBackground(Color.gray);
        this.moviesWatchedOnAccount.setBackground(Color.gray);
        this.longestMovieUnderSixteen.setBackground(Color.gray);
        this.accountWithOnlyOneProfile.setBackground(Color.gray);
        this.numberOfViewersWatchedCompleteMovie.setBackground(Color.gray);
    }

    public void initializeSidePanelMenu() {
        // Initializing the methods to create the side menu panel
        initializeAesthetics();
        addComponents();
    }

    public JPanel getJPanel() {
        return this.sidePanel;
    }

}
