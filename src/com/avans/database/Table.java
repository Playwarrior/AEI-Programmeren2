package com.avans.database;

import com.avans.database.tables.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public abstract class Table implements From {

    public static List<Table> ALL = new ArrayList<>();

    public static final Table SUBSCRIPTION_TABLE;
    public static final Table PROFILE_TABLE;

    public static final Table PROGRAM_TABLE;
    public static final Table MOVIE_TABLE;
    public static final Table SERIE_TABLE;
    public static final Table EPISODE_TABLE;

    public static final Table BEHAVIOUR_SERIE_TABLE;
    public static final Table BEHAVIOUR_MOVIE_TABLE;

    static {
        SUBSCRIPTION_TABLE = new SubscriptionTable();
        PROFILE_TABLE = new ProfileTable();

        PROGRAM_TABLE = new ProgramTable();
        MOVIE_TABLE = new MovieTable();
        SERIE_TABLE = new SerieTable();
        EPISODE_TABLE = new EpisodeTable();

        BEHAVIOUR_SERIE_TABLE = new BehaviourSerieTable();
        BEHAVIOUR_MOVIE_TABLE = new BehaviourMovieTable();
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
    protected void addConstraint(Constraint constraint) {
        this.constraints.add(constraint);
    }

    /* GETTERS */
    Column[] getColumns() {
        return columns;
    }

    List<Constraint> getConstraints() {
        return constraints;
    }

    boolean contains(Column column) {
        for (Column c : columns) {
            if (c.equals(column))
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
    String values(String... values) {
        StringBuilder sb = new StringBuilder("VALUES (");

        for (int i = 0; i < values.length; i++) {

            String value = values[i];
            boolean isNull = value == null || value.equalsIgnoreCase("null");

            if (i != 0)
                sb.append(",");


            sb.append(isNull ? "" : "'").append(isNull ? "NULL" : value).append(isNull ? "" : "'");
        }

        sb.append(")");

        return sb.toString();
    }

    static Table getTable(Column column) {
        for (Table table : ALL) {
            if (Arrays.asList(table.columns).contains(column)) {
                return table;
            }
        }
        return null;
    }
}
