package com.avans;

import com.avans.database.Column;
import com.avans.database.Database;
import com.avans.database.Table;
import com.avans.database.Where;
import com.avans.database.tables.AbonneeTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Database database = new Database("localhost", "NetflixStatistics");
        database.openConnection();
        database.setupTables();

        String name = database.get(Table.ABONNEE_TABLE, AbonneeTable.NAME, new Where<>(Where.Operator.EQUALS, AbonneeTable.POSTCODE, "4707ZG"));

        System.out.printf("This is %s, %s is busy", name, name);
    }
}
