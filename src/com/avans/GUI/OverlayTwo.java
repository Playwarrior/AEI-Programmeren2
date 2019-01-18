package com.avans.GUI;

import com.avans.handlers.DataHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Overlay two of the application
 */

public class OverlayTwo extends JPanel {

    private JPanel eastPanel;
    private JTable table;
    private JComboBox<String> JCAccounts;
    private JComboBox<String> JCSeries;
    private DataHandler dataHandler;

    public OverlayTwo(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);
        addComponents();
    }

    private void addComponents() {
        // Initializes the objects
        setEastPanel();
        setTable();

        // Overlay title
        JLabel title = new JLabel("Overzicht 2: Kijkpercentage van accounts", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        title.setForeground(Color.white);

        this.add(title, BorderLayout.NORTH);
        this.add(this.table, BorderLayout.CENTER);
        this.add(this.eastPanel, BorderLayout.EAST);
        this.add(new JScrollPane(this.table));
    }

    // Initializes the layout of the east panel
    private void setEastPanel() {
        this.eastPanel = new JPanel();
        this.eastPanel.setLayout(new GridLayout(12, 1));
        this.eastPanel.setBackground(Color.gray);

        eastPanelComponents();
    }

    private void eastPanelComponents() {
        // General font for the components of the east panel
        Font eastPanelFont = new Font("Helvetica Neue", Font.BOLD, 12);

        // Description for the JComboBox of the accounts
        JLabel selectAccount = new JLabel("Kies Account", SwingConstants.CENTER);
        selectAccount.setForeground(Color.white);
        selectAccount.setFont(eastPanelFont);

        // Description for the JComboBox of the series
        JLabel selectSeries = new JLabel("Kies Serie", SwingConstants.CENTER);
        selectSeries.setForeground(Color.white);
        selectSeries.setFont(eastPanelFont);

        // Initializes the JComboBoxes
        setJCAccount();
        setJCSeries();

        this.eastPanel.add(selectAccount);
        this.eastPanel.add(this.JCAccounts);
        this.eastPanel.add(new JLabel(""));
        this.eastPanel.add(selectSeries);
        this.eastPanel.add(this.JCSeries);
    }

    // Initializes the JCAccount
    private void setJCAccount() {
        String[] exAccounts = {"Alpha", "Bravo", "Charlie", "Delta"}; // Sample values
        Font eastPanelFont = new Font("Helvetica Neue", Font.PLAIN, 10);
        this.JCAccounts = new JComboBox<>(exAccounts);

        this.JCAccounts.setBackground(Color.white);

        this.JCAccounts.setFont(eastPanelFont);
        this.JCAccounts.setPreferredSize(new Dimension(130, 10));
    }

    // Initializes the JCSeries
    private void setJCSeries() {
        String[] exSeries = {"Alpha", "Bravo", "Charlie", "Delta"};
        Font eastPanelFont = new Font("Helvetica Neue", Font.PLAIN, 10);
        this.JCSeries = new JComboBox<>(exSeries);

        this.JCSeries.setBackground(Color.white);
        this.JCSeries.setForeground(Color.darkGray);
        this.JCSeries.setFont(eastPanelFont);
        this.JCSeries.setPreferredSize(new Dimension(130, 10));
    }

    // Initializes the table
    private void setTable() {
        String[] columns = {"Aflevering", "Aflevering No.", "Gemiddelde kijktijd %"}; // Columns of the table
        String[][] data = {
                {"Alpha", "1", "53"} // Sample data
        };
        this.table = new JTable(data, columns);
        this.table.setFont(new Font("Helvetiva Neue", Font.PLAIN, 12));
    }
}
