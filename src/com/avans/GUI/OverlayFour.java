package com.avans.GUI;

import com.avans.handlers.DataHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Overlay four of the application
 */

public class OverlayFour extends JPanel {

    private JTable accountTable;
    private JPanel eastPanel;
    private DataHandler dataHandler;

    public OverlayFour(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);
        addComponents();
    }

    public void addComponents() {
        // Title for the overlay
        JLabel title = new JLabel("Overzicht 4: Accounts met 1 profiel", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        title.setForeground(Color.white);
        setAccountTable();
        setEastPanel();

        this.add(title, BorderLayout.NORTH);
        this.add(this.accountTable, BorderLayout.CENTER);
        this.add(new JScrollPane(this.accountTable));
        this.add(this.eastPanel, BorderLayout.EAST);
    }

    // Initializes the table
    public void setAccountTable() {
        // Columns for the table
        String[] columns = {"AbonneeID", "Naam", "Achternaam", "Straat", "HuisNr", "Postcode", "Stad"};
        String[][] data = {
                {"1", "Henk", "Opoa", "Molenstraat", "1", "1234AB", "Roosendaal"} // Sample data
        };
        this.accountTable = new JTable(data, columns);
        this.accountTable.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
    }

    // Initializes the east panel
    public void setEastPanel () {
        this.eastPanel = new JPanel();
        this.eastPanel.setLayout(new GridLayout(5, 1));
        this.eastPanel.setBackground(Color.gray);

        addComponentsToEastPanel();
    }

    public void addComponentsToEastPanel() {
        // Description for the overlay
        JLabel description = new JLabel("<html>Alle accounts/abonnee's met<br>één profiel worden " +
                "hier getoond</html>", SwingConstants.CENTER);
        description.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
        description.setForeground(Color.white);

        this.eastPanel.add(description);
    }
}
