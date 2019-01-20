package com.avans.gui.actionlisteners;

import com.avans.NFS;
import com.avans.handlers.program.Movie;
import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for Overlay three
 */

public class OverlayThreeAL implements ActionListener {

    private JComboBox<String> accounts;
    private JTable table;

    public OverlayThreeAL(JComboBox<String> accounts, JTable table) {
        this.accounts = accounts;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Subscriber s : NFS.getHandler().getSubscribers()) {
            if ((s.getName() + " " + s.getLastName()).equals(this.accounts.getSelectedItem())) {
                setTable(s);
                break;
            }
        }
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
            row[2] = Integer.toString(m.getAgeIndication());
            row[3] = Integer.toString(m.getDuration());

            dataModel.addRow(row);
        }
    }

}
