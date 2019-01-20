package com.avans.gui;

import com.avans.gui.actionlisteners.OverlayOneAL;
import com.avans.NFS;
import com.avans.handlers.program.Serie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Overlay one of the application
 */

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
        setTable();
        setEastPanel();

        this.add(title, BorderLayout.NORTH);
        this.add(this.table, BorderLayout.CENTER);
        this.add(this.eastPanel, BorderLayout.EAST);
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
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Serie", "Aflevering no.", "Titel", "Gemiddelde kijktijd %"};
        model.setColumnIdentifiers(columnNames);

        this.table = new JTable();
        this.table.setModel(model);
        this.table.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
        this.table.setForeground(Color.BLACK);
    }

    private void setJCSeries() {
        this.JCSeries = new JComboBox<>();

        // Ads all series to the JCombBox
        for (Serie s : NFS.getHandler().getPrograms(Serie.class)){
            this.JCSeries.addItem(s.toString());
        }

        // Adds action listener OverlayOneAL to JCSeries
        OverlayOneAL oal = new OverlayOneAL(this.table, this.JCSeries);
        this.JCSeries.addActionListener(oal);

        this.JCSeries.setPreferredSize(new Dimension(130, 10));
        this.JCSeries.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
        this.JCSeries.setBackground(Color.white);
        this.JCSeries.setForeground(Color.darkGray);
    }

}
