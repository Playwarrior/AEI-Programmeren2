package com.avans.database;

import java.util.Arrays;

public class Join implements From {

    private Type type;

    private Table leftTable;
    private Column leftColumn;

    private Table rightTable;
    private Column rightColumn;

    public Join(Type type, Table leftTable, Column leftColumn, Table rightTable, Column rightColumn) {
        this.type = type;

        this.leftTable = leftTable;
        this.leftColumn = leftColumn;

        this.rightTable = rightTable;
        this.rightColumn = rightColumn;

        if(!Arrays.asList(leftTable.getColumns()).contains(leftColumn))
            throw new IllegalStateException("Left Table doesn't contain the left column!");

        if(!Arrays.asList(rightTable.getColumns()).contains(rightColumn))
            throw new IllegalStateException("Right Table doesn't contain the right right column!");

        if(!leftColumn.toTypeString(false).equals(rightColumn.toTypeString(false)))
            throw new IllegalStateException("The left column isn't the same type as the right column!");

    }

    public Join(Type type, ColumnKey pk, ColumnKey fk){
        this(type, pk.getTable(), pk, fk.getTable(), fk);
    }

    /* OVERRIDABLE METHODS */
    @Override
    public String toString() {
        return String.format("%s %s %s ON (%s.%s = %s.%s)", leftTable, type, rightTable, leftTable, leftColumn, rightTable, rightColumn);
    }

    /* SUB-ENUM */
    public enum Type {

        INNER_JOIN("JOIN"),
        LEFT_JOIN("LEFT JOIN"),
        RIGHT_JOIN("RIGHT JOIN"),
        FULL_OUTER_JOIN("FULL OUTER JOIN");

        private String query;

        Type(String query){
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }
}
