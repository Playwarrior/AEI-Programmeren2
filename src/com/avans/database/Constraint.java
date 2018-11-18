package com.avans.database;

public class Constraint {

    private Table primaryTable;
    private Table foreignTable;

    private Column primaryKey;
    private ForeignKey foreignKey;

    public Constraint(Table primaryTable, Column primaryKey, Table foreignTable, ForeignKey foreignKey){
        this.primaryTable = primaryTable;
        this.primaryKey = primaryKey;
        this.foreignTable = foreignTable;
        this.foreignKey = foreignKey;

        if(!(primaryKey.isPrimaryKey() && primaryTable.getPrimaryKeys().contains(primaryKey)))
            throw new IllegalStateException("Primary key is not a primary key or the table doesn't contain it!");

        if(!foreignTable.getForeignKeys().contains(foreignKey))
            throw new IllegalStateException("Foreign key is not contained in the table!");

        if(foreignKey.getType() != primaryKey.getType())
            throw new IllegalStateException("Primary Key and Foreign Key aren't the same type!");
    }

    @Override
    public String toString() {
        return String.format("CONSTRAINT `FK_%s%s` ", primaryTable.toString(), foreignTable.toString()) +
                String.format("FOREIGN KEY (%s) ", foreignKey.toString()) +
                String.format("REFERENCES %s(%s) ", primaryTable.toString(), primaryKey.toString()) +
                String.format("%s %s", foreignKey.getAction().toString(), foreignKey.getResponse());
    }
}
