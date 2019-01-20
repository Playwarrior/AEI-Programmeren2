package com.avans;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.gui.UserInterFace;
import com.avans.database.Database;
import com.avans.handlers.DataHandler;

import javax.swing.*;

public class NFS {

    private static DataHandler dataHandler;

    public static void main(String[] args) {
        Database database = new Database("localhost", "MSSQL", "NetflixStatistics");
        database.openConnection();
        database.setupTables();

        dataHandler = new DataHandler();

        AdditionScript.addData();

        SwingUtilities.invokeLater(new UserInterFace());

        dataHandler.serialize();
    }

    public static DataHandler getHandler() {
        return dataHandler;
    }

}
