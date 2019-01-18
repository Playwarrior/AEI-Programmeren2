package com.avans.GUI.ActionListeners;

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
    private DefaultTableModel model;

    public OverlayOneAL(JTable table) {
        this.table = table;
        this.model = new DefaultTableModel();
        String[] columnNames = {"Serie", "Aflevering no.", "Titel", "Gemiddelde kijktijd %"};
        this.model.setColumnIdentifiers(columnNames);
        this.table.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Serie s : NFS.getHandler().getPrograms(Serie.class)) {
            if (e.getActionCommand().equals(s.toString())) {
                this.table = setTable(s);
                break;
            }
        }
    }

    private JTable setTable(Serie serie) {
        Object[] row = new Object[4];

        for (Episode e : serie.getEpisodes()){
            row[0] = serie.getTitle();
            row[1] = Integer.toString(e.getEpisodeNumber());
            // row[2] = e.getTitle;
            row[3] = DataUtil.getPercentageEpisode(serie, e.getEpisodeNumber());

            this.model.addRow(row);
        }

        return this.table;
    }
}
