package com.avans;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/
import com.avans.database.Database;
import com.avans.handlers.DataHandler;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Profile;
import com.avans.handlers.user.Subscriber;

public class NFS {

    private static DataHandler dataHandler;

    public static void main(String[] args) {
        Database database = new Database("localhost", "MSSQL","NetflixStatistics");
        database.openConnection();
        database.setupTables();

        dataHandler = new DataHandler();

        Subscriber s = dataHandler.getSubscriber("Robin", "Egberts");

        Profile profile = s.getProfile("Robin");

        s.watch(profile, (Serie) dataHandler.getProgram("ShadowHunter"), 1, 31);

//        SwingUtilities.invokeLater(new UserInterFace());

        dataHandler.serialize();
    }

    public static DataHandler getHandler() {
        return dataHandler;
    }
}
