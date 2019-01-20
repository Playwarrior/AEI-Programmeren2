package com.avans.GUI.ActionListeners;
import com.avans.NFS;
import com.avans.handlers.program.Movie;
import com.avans.util.DataUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverlayFiveAL implements ActionListener {

    private JComboBox<String> films;
    private JLabel showViewersCount;

    public OverlayFiveAL(JComboBox<String> films, JLabel showViewersCount) {
        this.films = films;
        this.showViewersCount = showViewersCount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Movie m = (Movie) NFS.getHandler().getProgram(this.films.getSelectedItem().toString());
        this.showViewersCount.setText(String.valueOf(DataUtil.getSubscribersByMovie(m).size()));
    }

}
