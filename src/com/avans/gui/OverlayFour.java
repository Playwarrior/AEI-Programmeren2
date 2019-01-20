package com.avans.gui;

import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Overlay four of the application
 * TODO: Need to test table
 */

public class OverlayFour extends JPanel {

    private JTable accountTable;
    private JPanel eastPanel;

    public OverlayFour() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);
        addComponents();
    }

    private void addComponents() {
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
    private void setAccountTable() {
        // Columns for the table
        DefaultTableModel model = new DefaultTableModel();
        this.accountTable = new JTable();
        String[] columns = {"Naam", "Achternaam", "Adres"};
        model.setColumnIdentifiers(columns);

        this.accountTable = new JTable();
        this.accountTable.setModel(model);

        Object[] row = new Object[4];

        // Checks for subscribers/accounts with only one profile
        for (Subscriber s : DataUtil.getSubscribersByCount(1)) {

            row[0] = s.getId().toString();
            row[1] = s.getName();
            row[2] = s.getLastName();
            row[3] = s.getAdress();

            model.addRow(row);  // Adds the subscribers/accounts to the table

        }

        this.accountTable.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
    }

    // Initializes the east panel
    private void setEastPanel() {
        this.eastPanel = new JPanel();
        this.eastPanel.setLayout(new GridLayout(5, 1));
        this.eastPanel.setBackground(Color.gray);

        addComponentsToEastPanel();
    }

    private void addComponentsToEastPanel() {
        // Description for the overlay
        JLabel description = new JLabel("<html>Alle accounts/abonnee's met<br>één profiel worden " +
                "hier getoond</html>", SwingConstants.CENTER);
        description.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
        description.setForeground(Color.white);

        this.eastPanel.add(description);
    }
}
