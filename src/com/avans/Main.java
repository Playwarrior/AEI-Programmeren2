package com.avans;

import com.avans.database.Database;

import javax.xml.crypto.Data;

public class Main {

    public static void main(String[] args) {
        Database database = new Database("localhost", 4333, "netflixstatitics", "playwarrior", "password");
        database.openConnection();
        database.setupTables();
    }

}
