package com.avans.gui;

import com.avans.gui.actionlisteners.OverlayTwoAL;
import com.avans.NFS;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Subscriber;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Overlay two of the application
 * IMPORTANT: GETTING DATA MAY TAKE SOME TIME
 */

public class OverlayTwo extends JPanel {

    private JPanel eastPanel;
    private JTable table;
    private JComboBox<String> JCAccounts;
    private JComboBox<String> JCSeries;
    private JButton showData;

    public OverlayTwo() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);
        addComponents();
    }

    private void addComponents() {
        // Initializes the objects
        setTable();
        setEastPanel();

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

        // Button for showing the data of the selected values
        this.showData = new JButton("Toon");
        this.showData.setForeground(Color.white);
        this.showData.setBackground(Color.darkGray);
        this.showData.setFont(eastPanelFont);
        OverlayTwoAL tal = new OverlayTwoAL(this.table, this.JCAccounts, this.JCSeries);
        this.showData.addActionListener(tal);

        this.eastPanel.add(selectAccount);
        this.eastPanel.add(this.JCAccounts);
        this.eastPanel.add(new JLabel(""));
        this.eastPanel.add(selectSeries);
        this.eastPanel.add(this.JCSeries);
        this.eastPanel.add(new JLabel(""));
        this.eastPanel.add(this.showData);
    }

    // Initializes the JCAccount
    private void setJCAccount() {
        Font eastPanelFont = new Font("Helvetica Neue", Font.PLAIN, 12);
        this.JCAccounts = new JComboBox<>();

        // Ads all accounts to the JComboBox
        for (Subscriber s : NFS.getHandler().getSubscribers()) {
            this.JCAccounts.addItem(s.getName() + " " + s.getLastName());
        }

        this.JCAccounts.setBackground(Color.white);
        this.JCAccounts.setForeground(Color.black);
        this.JCAccounts.setFont(eastPanelFont);
        this.JCAccounts.setPreferredSize(new Dimension(130, 10));
    }

    // Initializes the JCSeries
    private void setJCSeries() {
        Font eastPanelFont = new Font("Helvetica Neue", Font.PLAIN, 10);
        this.JCSeries = new JComboBox<>();

        // Ads all series to the JCombBox
        for (Serie e : NFS.getHandler().getPrograms(Serie.class)) {
            this.JCSeries.addItem(e.getTitle());
        }

        this.JCSeries.setBackground(Color.white);
        this.JCSeries.setForeground(Color.darkGray);
        this.JCSeries.setFont(eastPanelFont);
        this.JCSeries.setPreferredSize(new Dimension(130, 10));
    }

    // Initializes the table
    private void setTable() {
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"Serie", "Aflevering no.", "Titel", "Gemiddelde kijktijd %"}; // Columns of the table
        model.setColumnIdentifiers(columns);

        this.table = new JTable();
        this.table.setModel(model);
        this.table.setFont(new Font("Helvetiva Neue", Font.PLAIN, 12));
    }
}
