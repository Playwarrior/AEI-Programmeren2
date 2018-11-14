package com.avans.database;

import java.util.ArrayList;
import java.util.List;

public class Table {

    public static List<Table> ALL = new ArrayList<>();

    private final String name;
    private final Column[] columns;

    public Table(String name, Column... columns){
        ALL.add(this);

        this.name = name;
        this.columns = columns;
    }

    public Column[] getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return name;
    }

    public String values(String... values){
        StringBuilder sb = new StringBuilder("VALUES ('");

        for(int i = 0; i < values.length; i++){
            if(i != 0)
                sb.append("','");

            sb.append(values[i]);
        }

        sb.append("')");

        return sb.toString();
    }

    //TODO: INTRODUCE PRIMARY KEY AND FOREIGN KEYS!
}
