package com.avans.GUI.controller;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.NFS;
import com.avans.handlers.program.Episode;
import com.avans.handlers.program.Serie;
import com.avans.util.DataUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class WatchTimeController extends BasicController {

    public ChoiceBox series;
    public ChoiceBox episodes;

    public Label serie;
    public Label episode;
    public Label percentage;

    @FXML
    public void initialize() {
        super.initialize();

        series.setItems(FXCollections.observableList(NFS.getHandler().getPrograms(Serie.class)));

        series.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue instanceof Serie))
                throw new IllegalStateException("Cannot find serie!");

            Serie s = (Serie) newValue;

            episodes.setItems(FXCollections.observableList(s.getEpisodes()));

            this.clearLabels();
        });

        episodes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue instanceof Episode))
                throw new IllegalStateException("Cannot find episode!");

            Episode e = (Episode) newValue;

            Serie s = (Serie) series.getSelectionModel().getSelectedItem();

            double percentage = DataUtil.getPercentageEpisode(s, e.getEpisodeNumber());

            this.serie.setText(s.getTitle());

            this.episode.setText(String.valueOf(e.getEpisodeNumber()));

            this.percentage.setText(String.format("%.2f", percentage));
        });


    }

    private void clearLabels() {
        this.serie.setText("");
        this.episode.setText("");
        this.percentage.setText("");
    }
}
