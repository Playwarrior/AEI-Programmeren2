package com.avans.gui;

import com.avans.gui.actionlisteners.OverlayThreeAL;
import com.avans.NFS;
import com.avans.handlers.user.Subscriber;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Overlay three of the application
 */

public class OverlayThree extends JPanel {

    private JPanel mainPanel; // Specific panel in the Center to separate
    private JPanel choosePanel;
    private JComboBox<Subscriber> JCAccountsName;
    private JButton showData;
    private JTable filmTable;

    public OverlayThree() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);
        addComponents();
    }

    private void addComponents() {
        // Title of the overlay
        JLabel title = new JLabel("Overzicht 3: Films bekeken", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        title.setForeground(Color.white);
        setMainPanel();

        this.add(this.mainPanel, BorderLayout.CENTER);
        this.add(title, BorderLayout.NORTH);
    }

    // Initializes the main panel
    private void setMainPanel() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridLayout(1, 2));
        this.mainPanel.setBackground(Color.gray);

        addComponentsToMainPanel();
    }

    private void addComponentsToMainPanel() {
        setTable();
        setChoosePanel();

        this.mainPanel.add(this.choosePanel);
        this.mainPanel.add(this.filmTable);
        this.mainPanel.add(new JScrollPane(this.filmTable));
    }

    // Initializes a panel for the JCAccounts
    private void setChoosePanel() {
        this.choosePanel = new JPanel();
        this.choosePanel.setLayout(new GridLayout(8, 1));
        this.choosePanel.setBackground(Color.gray);
        addComponentsToChoosePanel();
    }

    private void addComponentsToChoosePanel() {
        // Description for the JCAccounts JComboBox
        JLabel chooseAccount = new JLabel("Kies naam", SwingConstants.CENTER);
        chooseAccount.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        chooseAccount.setForeground(Color.white);

        setJCAccounts();

        // Initializes the show data button
        this.showData = new JButton("Toon");
        this.showData.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        this.showData.setForeground(Color.white);
        this.showData.setBackground(Color.darkGray);
        OverlayThreeAL tal = new OverlayThreeAL(this.JCAccountsName, this.filmTable);
        this.showData.addActionListener(tal);

        this.choosePanel.add(new JLabel(" "));
        this.choosePanel.add(new JLabel(" "));
        this.choosePanel.add(chooseAccount);
        this.choosePanel.add(this.JCAccountsName);
        this.choosePanel.add(new JLabel(" "));
        this.choosePanel.add(this.showData);
    }

    private void setJCAccounts() {
        Font font = new Font("Helvetica Neue", Font.PLAIN, 18);
        Dimension dimension = new Dimension(130, 20);

        // Select account name
        this.JCAccountsName = new JComboBox<>();
        for (Subscriber s : NFS.getHandler().getSubscribers()) {
            this.JCAccountsName.addItem(s);
        }
        this.JCAccountsName.setPreferredSize(new Dimension(130, 20));
        this.JCAccountsName.setFont(font);
        this.JCAccountsName.setPreferredSize(dimension);
    }

    private void setTable() {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnsNames = {"Titel", "Genre", "Leeftijdsindicatie", "Lengte"};
        model.setColumnIdentifiers(columnsNames);

        this.filmTable = new JTable();
        this.filmTable.setModel(model);
        this.filmTable.setFont(new Font("Helvetica Neue", Font.PLAIN, 9));
        this.filmTable.setForeground(Color.black);
    }

}
