package com.avans;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.database.Database;
import com.avans.handlers.DataHandler;
import com.avans.util.ScreenState;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

        //TODO: FIX IF DATA IS INSERTED DO NOT INSERT DATA AGAIN!
        AdditionScript.addData();

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

        window.getIcons().add(new Image("com/avans/gui/css/NetflixLogo.png"));

        setState(ScreenState.HOMEPAGE);
    }

    public static void setState(ScreenState s) {
        if (s != null && s != state) {
            state = s;

            try {
                FXMLLoader loader = new FXMLLoader();

                Scene scene = new Scene(loader.load(new FileInputStream(s.getFile())));

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
