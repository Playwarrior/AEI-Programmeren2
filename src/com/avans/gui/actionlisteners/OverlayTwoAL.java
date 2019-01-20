package com.avans.gui.actionlisteners;

import com.avans.NFS;
import com.avans.handlers.program.Episode;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for overlay two
 *
 */

public class OverlayTwoAL implements ActionListener {

    private JTable table;
    private JComboBox<String> accounts;
    private JComboBox<String> series;
    private DefaultTableModel dataModel;

    public OverlayTwoAL(JTable table, JComboBox<String> accounts, JComboBox<String> series) {
        this.table = table;
        this.accounts = accounts;
        this.series = series;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Subscriber sub : NFS.getHandler().getSubscribers()){   // Checks for matching subscriber

            for (Serie s : NFS.getHandler().getPrograms(Serie.class)) {  // Checks for matching serie

                if ((sub.getName() + " " + sub.getLastName()).equals(this.accounts.getSelectedItem().toString()) &&
                        (s.getTitle().equals(this.series.getSelectedItem().toString()))){
                    setTable(s, sub);
                    break;
                }
            }
        }

    }

    // Return a table with data
    private void setTable(Serie serie, Subscriber subscriber) {
        DefaultTableModel dataModel = new DefaultTableModel();
        String[] columnNames = {"Serie", "Aflevering no.", "Titel", "Gemiddelde kijktijd %"};
        dataModel.setColumnIdentifiers(columnNames);
        this.table = new JTable();
        this.table.setModel(dataModel);

        Object[] row = new Object[4];

        for (Episode e : serie.getEpisodes()){
            row[0] = serie.getTitle();
            row[1] = Integer.toString(e.getEpisodeNumber());
            row[2] = e.getTitle();
            row[3] = DataUtil.getPercentageEpisode(subscriber, serie, e.getEpisodeNumber());

            dataModel.addRow(row);
        }

    }

}
