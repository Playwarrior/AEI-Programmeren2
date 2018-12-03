package com.avans.database;

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

    }

    public Join(Type type, ColumnKey pk, ColumnKey fk){
        this(type, pk.getTable(), pk, fk.getTable(), fk);
    }

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

    @Override
    public String toString() {
        return String.format("%s %s %s ON (%s.%s = %s.%s)", leftTable, type, rightTable, leftTable, leftColumn, rightTable, rightColumn);
    }
}
