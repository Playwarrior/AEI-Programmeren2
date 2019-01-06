package com.avans;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.database.Database;
import com.avans.handlers.DataHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class NFS extends Application {

    private static DataHandler dataHandler;

    private static FXMLLoader loader;
    private static Stage stage;

    private static ScreenState state;

    public static void main(String[] args) {
        Database database = new Database("localhost", "MSSQL", "NetflixStatistics");
        database.openConnection();
        database.setupTables();

        state = null;

        dataHandler = new DataHandler();

        Application.launch(args);

        dataHandler.serialize();
    }

    public static DataHandler getHandler() {
        return dataHandler;
    }

    @Override
    public void start(Stage s) throws Exception {
        loader = new FXMLLoader();
        stage = s;

        stage.setTitle("Your mom");

        setState(ScreenState.HOMEPAGE);
    }

    public static void setState(ScreenState s) {
        if (s != null && s != state) {
            state = s;

            try {
                loader.load(new FileInputStream(s.getFile()));

                AnchorPane root = loader.load(new FileInputStream(s.getFile()));

                Scene scene = new Scene(root);

                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ScreenState getState(){
        return state;
    }
}
