package com.avans.database;

import com.avans.database.tables.AbonneeTable;
import com.avans.database.tables.ProfileTable;
import com.avans.database.tables.TussenTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public abstract class Table implements From {

    public static List<Table> ALL = new ArrayList<>();

    public static final Table ABONNEE_TABLE;
    public static final Table PROFILE_TABLE;
    public static final Table TUSSEN_TABLE;

    static {
        ABONNEE_TABLE = new AbonneeTable();
        PROFILE_TABLE = new ProfileTable();
        TUSSEN_TABLE = new TussenTable();
    }

    private final String name;
    private final Column[] columns;
    private final List<Constraint> constraints;

    public Table(String name, Column... columns) {
        ALL.add(this);

        this.name = name;
        this.columns = columns;
        this.constraints = new ArrayList<>();
    }

    /* SETTERS */
    public void addConstraint(Constraint constraint) {
        this.constraints.add(constraint);
    }

    /* GETTERS */
    public Column[] getColumns() {
        return columns;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public boolean contains(Column column){
        for(Column c : columns){
            if(c.equals(column))
                return true;
        }
        return false;
    }

    /* OVERRIDABLE */
    @Override
    public String toString() {
        return name;
    }

    /* VALUES */
    public String values(String... values) {
        StringBuilder sb = new StringBuilder("VALUES ('");

        for (int i = 0; i < values.length; i++) {
            if (i != 0)
                sb.append("','");

            sb.append(values[i]);
        }

        sb.append("')");

        return sb.toString();
    }

    public static Table getTable(Column column){
        for(Table table : ALL){
            if(Arrays.asList(table.columns).contains(column)){
                return table;
            }
        }
        return null;
    }
}
