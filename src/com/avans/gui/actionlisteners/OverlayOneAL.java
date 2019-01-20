package com.avans.gui.actionlisteners;

import com.avans.NFS;
import com.avans.handlers.program.Episode;
import com.avans.handlers.program.Serie;
import com.avans.util.DataUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener for overlay one.
 * TODO: Need to test.
 */

public class OverlayOneAL implements ActionListener {

    private JTable table;

    public OverlayOneAL(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Checks for the data of the selected series
        for (Serie s : NFS.getHandler().getPrograms(Serie.class)) {
            if (e.getActionCommand().equals(s.toString())) {
                setTable(s);
                break;
            }
        }
    }

    // Return a table with data
    private void setTable(Serie serie) {
        DefaultTableModel dataModel = new DefaultTableModel();
        String[] columnNames = {"Serie", "Aflevering no.", "Titel", "Gemiddelde kijktijd %"};
        dataModel.setColumnIdentifiers(columnNames);
        this.table.setModel(dataModel);

        Object[] row = new Object[4];

        for (Episode e : serie.getEpisodes()){
            row[0] = serie.getTitle();
            row[1] = Integer.toString(e.getEpisodeNumber());
            row[2] = e.getTitle();
            row[3] = DataUtil.getPercentageEpisode(serie, e.getEpisodeNumber());

            dataModel.addRow(row);
        }
    }
}
