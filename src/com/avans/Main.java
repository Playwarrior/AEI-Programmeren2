package com.avans;

import com.avans.database.Column;
import com.avans.database.Database;
import com.avans.database.Table;
import com.avans.database.tables.AbonneeTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Database database = new Database("localhost", 4333, "netflixstatitics", "playwarrior", "password");
        database.openConnection();
        database.setupTables();

        List<Map<Column, Object>> objects = database.getEntries(Table.ABONNEE_TABLE);

        for(Map<Column, Object> object : objects){

        }
    }
}
