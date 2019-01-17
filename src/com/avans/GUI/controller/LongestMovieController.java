package com.avans.GUI.controller;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.handlers.program.Movie;
import com.avans.util.DataUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LongestMovieController extends BasicController {

    public Label title;
    public Label genre;
    public Label duration;

    @FXML
    public void initialize() {
        super.initialize();

        updateLabels();
    }

    private void updateLabels(){
        Movie movie = DataUtil.getLongestMovie(16);

        if(movie != null) {
            title.setText(movie.getTitle());
            genre.setText(movie.getGenre());
            duration.setText(String.format("%d min", movie.getDuration()));
        }
    }
}
