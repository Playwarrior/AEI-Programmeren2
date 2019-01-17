package com.avans.GUI.controller;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.NFS;
import com.avans.handlers.program.Episode;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Subscriber;
import com.avans.util.DataUtil;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SubscriberWatchTimeController extends BasicController {

    public ChoiceBox subscribers;
    public ChoiceBox serie;
    public ChoiceBox episodes;

    public Label subscriber;
    public Label sserie;
    public Label episode;
    public Label percentage;

    @FXML
    public void initialize() {
        super.initialize();

        subscribers.setItems(FXCollections.observableList(NFS.getHandler().getSubscribers()));

        subscribers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(!(newValue instanceof Subscriber))
                throw new IllegalStateException("Cannot find subscriber!");

            Episode e = (Episode) episodes.getSelectionModel().getSelectedItem();

            Serie serie = (Serie) this.serie.getSelectionModel().getSelectedItem();

            Subscriber s = (Subscriber) newValue;

            this.updateLabels(s, serie, e);
        });

        serie.setItems(FXCollections.observableList(NFS.getHandler().getPrograms(Serie.class)));

        serie.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (!(newValue instanceof Serie))
                throw new IllegalStateException("Cannot find serie!");

            Serie serie = (Serie) newValue;

            episodes.setItems(FXCollections.observableList(serie.getEpisodes()));

            this.resetLabels();
        }));

        episodes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue instanceof Episode))
                throw new IllegalStateException("Cannot find episode!");

            Episode e = (Episode) newValue;

            Subscriber s = (Subscriber) subscribers.getSelectionModel().getSelectedItem();

            Serie serie = (Serie) this.serie.getSelectionModel().getSelectedItem();

            this.updateLabels(s, serie, e);
        });
    }

    private void updateLabels(Subscriber s, Serie serie, Episode e) {
        if (s != null && serie != null && e != null) {
            double percentage = DataUtil.getPercentageEpisode(s, serie, e.getEpisodeNumber());

            this.subscriber.setText(s.toString());

            this.sserie.setText(serie.getTitle());

            this.episode.setText(String.format("%d (%d min)", e.getEpisodeNumber(), e.getDuration()));

            this.percentage.setText(String.format("%.2f%s", percentage, "%"));

        } else {
            resetLabels();
        }
    }

    private void resetLabels(){
        this.subscriber.setText("");
        this.sserie.setText("");
        this.episode.setText("");
        this.percentage.setText("");
    }
}
