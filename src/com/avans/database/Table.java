package com.avans.database;

import com.avans.database.tables.AbonneeTable;
import com.avans.database.tables.ProfileTable;

import java.util.ArrayList;
import java.util.List;

public class Table implements From {

    public static List<Table> ALL = new ArrayList<>();

    public static final Table ABONNEE_TABLE;
    public static final Table PROFILE_TABLE;

    static {
        ABONNEE_TABLE = new AbonneeTable();
        PROFILE_TABLE = new ProfileTable();
    }

    private final String name;
    private final Column[] columns;
    private final List<ColumnKey> primaryKeys;
    private final List<ColumnKey> foreignKeys;
    private final List<Constraint> constraints;

    public Table(String name, Column... columns) {
        ALL.add(this);

        this.name = name;
        this.columns = columns;
        this.primaryKeys = new ArrayList<>();
        this.foreignKeys = new ArrayList<>();
        this.constraints = new ArrayList<>();

        for(Column column : columns){
            if(column instanceof ColumnKey){
                ColumnKey columnKey = (ColumnKey) column;
                if(columnKey.isPrimaryKey()){
                    this.primaryKeys.add(columnKey);
                } else {
                    this.foreignKeys.add(columnKey);
                }
            }
        }
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

    public ColumnKey[] getPrimaryKeys(){
        return (ColumnKey[]) primaryKeys.toArray();
    }

    public ColumnKey[] getForeignKeys() {
        return (ColumnKey[]) foreignKeys.toArray();
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
}
