package com.avans.gui.actionlisteners;
import com.avans.NFS;
import com.avans.handlers.program.Movie;
import com.avans.util.DataUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener of Overlay five
 */

public class OverlayFiveAL implements ActionListener {

    private JComboBox<String> films;
    private JLabel showViewersCount;

    public OverlayFiveAL(JComboBox<String> films, JLabel showViewersCount) {
        this.films = films;
        this.showViewersCount = showViewersCount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Converts the selected item of the JComboBox films to a Movie Object
        Movie m = (Movie) NFS.getHandler().getProgram((String) this.films.getSelectedItem());

        // Checks if the there are any films and counts it
        if (!DataUtil.getSubscribersByMovie(m).isEmpty()) {
            this.showViewersCount.setText(String.valueOf(DataUtil.getSubscribersByMovie(m).size()));

        }else {
            this.showViewersCount.setText("Geen informatie beschikbaar");

        }
    }

}
