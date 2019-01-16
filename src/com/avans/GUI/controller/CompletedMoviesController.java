package com.avans.GUI.controller;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.NFS;
import com.avans.handlers.DataHandler;
import com.avans.handlers.program.Movie;
import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class CompletedMoviesController extends BasicController {

    public ChoiceBox sub;
    public ListView movies;

    @FXML
    public void initialize() {
        super.initialize();

        sub.setItems(FXCollections.observableList(NFS.getHandler().getSubscribers()));

        sub.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue instanceof Subscriber) {
                Subscriber s = (Subscriber) newValue;

                List<Movie> movies = DataUtil.getMoviesBySubscriber(s);

                List<String> m = new ArrayList<>();

                int i = 1;

                for(Movie mo : movies){
                    m.add(String.format("%d. %s %s %d", i, mo.getTitle(), mo.getGenre(), mo.getDuration()));
                }

                this.movies.setItems(FXCollections.observableList(m));
            }
        });
    }
}
