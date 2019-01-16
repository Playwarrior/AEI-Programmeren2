package com.avans;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.database.Database;
import com.avans.handlers.DataHandler;

import com.avans.handlers.program.Movie;
import com.avans.handlers.user.Subscriber;
import com.avans.util.ScreenState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class NFS extends Application {

    private static DataHandler dataHandler;

    private static Stage window;

    private static ScreenState state;

    public static void main(String[] args) {
        Database database = new Database("localhost", "MSSQL", "NetflixStatistics");
        database.openConnection();
        database.setupTables();

        state = null;

        dataHandler = new DataHandler();

        launch(args);

        dataHandler.serialize();
    }

    public static DataHandler getHandler() {
        return dataHandler;
    }

    @Override
    public void start(Stage s) {
        window = s;

        window.setResizable(false);
        window.requestFocus();

        setState(ScreenState.HOMEPAGE);
    }

    //TODO: FIX THIS MESS!
    public static void setState(ScreenState s) {
        if (s != null && s != state) {
            state = s;

            try {
                FXMLLoader loader = new FXMLLoader();

                AnchorPane root = loader.load(new FileInputStream(s.getFile()));

                Scene scene = new Scene(root);

                window.setScene(scene);

                window.setTitle(s.getTitle());

                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ScreenState getState(){
        return state;
    }
}
