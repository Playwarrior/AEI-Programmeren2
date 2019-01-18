package com.avans.GUI.ActionListeners;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverlayTwoAL implements ActionListener {

    private JTable table;
    JComboBox<String> accounts;
    JComboBox<String> series;
    private DefaultTableModel model;

    public OverlayTwoAL(JTable table, JComboBox<String> accounts, JComboBox<String> series) {
        this.table = table;
        this.accounts = accounts;
        this.series = series;
        this.model = new DefaultTableModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
