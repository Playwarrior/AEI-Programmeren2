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
 *
 */

public class OverlayOneAL implements ActionListener {

    private JTable table;
    private JComboBox<String> series;

    public OverlayOneAL(JTable table, JComboBox<String> series) {
        this.table = table;
        this.series = series;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Checks for the data of the selected series
        Serie s = (Serie) NFS.getHandler().getProgram((String) this.series.getSelectedItem());
        setTable(s);
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
            row[3] = String.valueOf(DataUtil.getPercentageEpisode(serie, e.getEpisodeNumber()));

            dataModel.addRow(row);
        }
    }
}
