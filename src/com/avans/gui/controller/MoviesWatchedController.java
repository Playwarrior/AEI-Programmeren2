package com.avans.gui.controller;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.NFS;
import com.avans.handlers.program.Movie;
import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class MoviesWatchedController extends BasicController {

    public ListView s;
    public ChoiceBox movies;

    @FXML
    public void initialize() {
       super.initialize();

        movies.setItems(FXCollections.observableList(NFS.getHandler().getPrograms(Movie.class)));

        movies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof Movie) {
                Movie movie = (Movie) newValue;

                List<Subscriber> subscribers = DataUtil.getSubscribersByMovie(movie);

                List<String> sub = new ArrayList<>();

                int i = 1;

                for(Subscriber s : subscribers){
                    sub.add(String.format("%d. %s", i, s.toString()));
                }

                this.s.setItems(FXCollections.observableList(sub));
            }
        });
    }
}
