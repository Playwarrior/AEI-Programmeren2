package com.avans.gui.actionlisteners;

import com.avans.NFS;
import com.avans.handlers.program.Movie;
import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for Overlay three
 */

public class OverlayThreeAL implements ActionListener {

    private JComboBox<Subscriber> accountsName;
    private JTable table;

    public OverlayThreeAL(JComboBox<Subscriber> accountsName, JTable table) {
        this.accountsName = accountsName;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setTable((Subscriber) this.accountsName.getSelectedItem());
    }

    // Returns table of the selected account
    private void setTable(Subscriber s) {
        DefaultTableModel dataModel = new DefaultTableModel();
        String[] columnNames = {"Titel", "Genre", "Leeftijdsindicatie", "Lengte"};
        dataModel.setColumnIdentifiers(columnNames);
        this.table.setModel(dataModel);

        Object[] row = new Object[4];

        for (Movie m : DataUtil.getMoviesBySubscriber(s)) {
            row[0] = m.getTitle();
            row[1] = m.getGenre();
            row[2] = String.valueOf(m.getAgeIndication());
            row[3] = String.valueOf(m.getDuration());

            dataModel.addRow(row);
        }
    }

}
