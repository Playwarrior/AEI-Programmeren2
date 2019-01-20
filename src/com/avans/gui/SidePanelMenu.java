package com.avans.gui;

import com.avans.handlers.DataHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Side Panel Menu of the Application
 * TODO: Add action listeners for all overlays
 */

public class SidePanelMenu extends JTabbedPane {

    public SidePanelMenu() {
        this.setBackground(Color.gray);
        this.setForeground(Color.white);
        this.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        addComponents();
    }

    private void addComponents(){
        // Adding all components to the side menu panel
        HomePage homePage = new HomePage();
        OverlayOne overlayOne = new OverlayOne();
        OverlayTwo overlayTwo = new OverlayTwo();
        OverlayThree overlayThree = new OverlayThree();
        OverlayFour overlayFour = new OverlayFour();
        OverlayFive overlayFive = new OverlayFive();

        this.addTab("Home", homePage);
        this.addTab("Overzicht 1", overlayOne);
        this.addTab("Overzicht 2", overlayTwo);
        this.addTab("Overzicht 3", overlayThree);
        this.addTab("Overzicht 4", overlayFour);
        this.addTab("Overzicht 5", overlayFive);
    }



}
