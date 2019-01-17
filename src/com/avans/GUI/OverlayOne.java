package com.avans.GUI;

import javax.swing.*;
import java.awt.*;

public class OverlayOne extends JPanel {

    private JPanel eastPanel;
    private JComboBox<String> JCSeries;
    private JTable table;

    public OverlayOne() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);
        addComponents();
    }

    private void addComponents() {
        // Overlay title
        JLabel title = new JLabel("Overzicht 1: Gemiddelde kijktijd van series", SwingConstants.CENTER);
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        title.setForeground(Color.white);

        // Initializes the objects
        setEastPanel();
        setTable();

        this.add(title, BorderLayout.NORTH);
        this.add(this.eastPanel, BorderLayout.EAST);
        this.add(this.table, BorderLayout.CENTER);
        this.add(new JScrollPane(this.table));
    }

    // Initializes the east panel with the layout
    private void setEastPanel() {
        this.eastPanel = new JPanel();
        this.eastPanel.setLayout(new GridLayout(12, 1));
        this.eastPanel.setBackground(Color.gray);
        addComponentsToEastPanel();
    }

    private void addComponentsToEastPanel() {
        // Description for the JComboBox
        JLabel chooseSeries = new JLabel("Kies Serie", SwingConstants.CENTER);
        chooseSeries.setForeground(Color.white);
        chooseSeries.setFont(new Font("Helvetica Neue", Font.BOLD, 12));

        // Initializes the JComboBox
        setJCSeries();

        this.eastPanel.add(chooseSeries);
        this.eastPanel.add(this.JCSeries);
    }

    private void setTable() {
        // need to add getEpisodes of Series via Action listener
        String[] columnNames = {"Serie", "Aflevering no.", "Titel"};
        String[][] data = {
                {"Vikings", "1", "Begin"},
                {"House of Cards", "2", "Eeee"}
        };

        this.table = new JTable(data, columnNames);
        this.table.setBounds(30, 40, 200, 300);
        this.table.setSize(500, 500);

        this.table.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        this.table.setForeground(Color.BLACK);
    }

    private void setJCSeries() {
        // add for-loop to add all series of the database
        String[] seriesNames = new String[]{"Vikings", "House of Cards", "Games of Thrones"};

        this.JCSeries = new JComboBox<>(seriesNames);
        this.JCSeries.setPreferredSize(new Dimension(130, 10));
        this.JCSeries.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
        this.JCSeries.setBackground(Color.white);
        this.JCSeries.setForeground(Color.darkGray);
    }

}
