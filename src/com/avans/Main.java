package com.avans;

import com.avans.database.*;
import com.avans.database.tables.AbonneeTable;
import com.avans.database.tables.ProfileTable;
import com.avans.database.tables.TussenTable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class Main {

    public static void main(String[] args) {
        Database database = new Database("localhost", "MSSQL","NetflixStatistics");
        database.openConnection();
        database.setupTables();

        Join join = new Join(Join.Type.INNER_JOIN, AbonneeTable.NAME, ProfileTable.FK_NAME);

        Join innerJoin = new Join(Join.Type.LEFT_JOIN, ProfileTable.PROFILE_NAME, TussenTable.FK_PROFILE_NAME);

        join.addChild(innerJoin);

        String name = database.get(Table.ABONNEE_TABLE, AbonneeTable.NAME, new Where<>(AbonneeTable.POSTCODE, "4707ZG"));
        String bullshit = database.get(join, TussenTable.BULLSHIT, new Where<>(Where.Operator.EQUALS, ProfileTable.FK_NAME, name));

        int count = database.getCount(Table.ABONNEE_TABLE);
        System.out.println(name + " woont op deze postcode: " + bullshit);
        System.out.println("Count: " + count);
    }
}
