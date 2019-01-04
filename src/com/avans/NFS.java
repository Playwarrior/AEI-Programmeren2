package com.avans;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/
import com.avans.database.Database;
import com.avans.handlers.DataHandler;

public class NFS {

    private static DataHandler dataHandler;

    public static void main(String[] args) {
        Database database = new Database("localhost", "MSSQL","NetflixStatistics");
        database.openConnection();
        database.setupTables();

        dataHandler = new DataHandler();

//        SwingUtilities.invokeLater(new UserInterFace());

        dataHandler.serialize();
    }

    public static DataHandler getHandler() {
        return dataHandler;
    }
}
