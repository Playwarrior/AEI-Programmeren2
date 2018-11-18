package com.avans.database;

import com.sun.org.apache.bcel.internal.classfile.ConstantInteger;

import java.util.ArrayList;
import java.util.List;

public class Table {

    public static List<Table> ALL = new ArrayList<>();

    private final String name;
    private final Column[] columns;
    private final List<Constraint> constraints;

    public Table(String name, Column... columns){
        ALL.add(this);

        this.name = name;
        this.columns = columns;
        this.constraints = new ArrayList<>();
    }

    private void addConstraint(Constraint constraint){
        this.constraints.add(constraint);
    }

    public Column[] getColumns() {
        return columns;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public List<Column> getPrimaryKeys(){
        //TODO: CONVERT THIS TO BETTER CODE SINCE NO WIFI!
        List<Column> primaryKeys = new ArrayList<>();

        for(Column column : this.columns){
            if(column.isPrimaryKey()){
                primaryKeys.add(column);
            }
        }

        return primaryKeys;
    }

    public List<ForeignKey> getForeignKeys(){
        List<ForeignKey> foreignKeys = new ArrayList<>();

        for(Column column : columns){
            if(column instanceof ForeignKey){
                foreignKeys.add((ForeignKey) column);
            }
        }

        return foreignKeys;
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
}
