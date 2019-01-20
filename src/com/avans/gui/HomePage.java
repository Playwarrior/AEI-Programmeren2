package com.avans.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Home screen of the application
 */

public class HomePage extends JPanel {

    public HomePage() {
        GridLayout layout = new GridLayout(2, 1);
        this.setLayout(layout);
        this.setBackground(Color.DARK_GRAY);
        addComponents();
    }

    private void addComponents() {
        // Title of the application
        JLabel appTitleLabel = new JLabel("Netflix Statistix", SwingConstants.CENTER);
        appTitleLabel.setFont(new Font("Helvetica Neue", Font.ITALIC, 40));
        appTitleLabel.setForeground(Color.red);

        // Update info of the application
        JLabel appInfoLabel = new JLabel("Version x.x.x | Last updated: 12-1-2019", SwingConstants.CENTER);
        appInfoLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        appInfoLabel.setForeground(Color.white);

        this.add(appTitleLabel);
        this.add(appInfoLabel);
    }

}
