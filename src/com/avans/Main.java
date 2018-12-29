package com.avans;

import com.avans.database.*;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class Main {

    public static void main(String[] args) {
        Database database = new Database("localhost", "MSSQL","NetflixStatistics");
        database.openConnection();
        database.setupTables();
    }
}
