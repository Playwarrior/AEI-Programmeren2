package com.avans;

import com.avans.database.Database;
import com.avans.GUI.UserInterFace;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
//        Database database = new Database("localhost", 4333, "netflixstatitics", "playwarrior", "password");
////        database.openConnection();
////        database.setupTables();

        SwingUtilities.invokeLater( new UserInterFace());

    }
}
