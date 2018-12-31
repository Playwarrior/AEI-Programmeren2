package com.avans.database;

import java.util.ArrayList;
import java.util.List;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class Join implements From {

    private List<Join> children;

    private Type type;

    private Table leftTable;
    private Column leftColumn;

    private Table rightTable;
    private Column rightColumn;

    public Join(Type type, Column leftColumn, Column rightColumn) {
        this.type = type;

        this.leftTable = Table.getTable(leftColumn);
        this.leftColumn = leftColumn;

        this.rightTable = Table.getTable(rightColumn);
        this.rightColumn = rightColumn;

        if(leftTable == null)
            throw new IllegalStateException("The left table doesn't exist!");

        if(rightTable == null)
            throw new IllegalStateException("The right table doesn't exist!");

        if(rightTable.equals(leftTable))
            throw new IllegalStateException("Cannot use the same table twice in a join!");

        if(leftColumn.equalsArgs(rightColumn))
            throw new IllegalStateException("The left column isn't the same type as the right column!");

        this.children = new ArrayList<>();
    }

    /* OVERRIDABLE METHODS */
    @Override
    public String toString() {
        return String.format("%s %s %s%s %s", leftTable, type, rightTable, type != Type.CROSS_JOIN ? String.format(" ON (%s.%s = %s.%s)", leftTable, leftColumn, rightTable, rightColumn) : "", getChildren()).trim();
    }

    /* SETTERS */
    public void addChild(Join join){
        this.children.add(join);
    }

    /* GETTERS */
    private String getChildren(){
        StringBuilder sb = new StringBuilder();

        for(Join join : children){

            Table commonTable = join.leftTable.equals(leftTable) ? join.leftTable :
                    join.leftTable.equals(rightTable) ? join.leftTable :
                            join.rightTable.equals(rightTable) ? join.rightTable :
                                    join.rightTable.equals(leftTable) ? join.rightTable : null;


            if(commonTable == null)
                throw new IllegalStateException("One of the joins doesn't have anything in common with the parent Join!");

            Column commonColumn = commonTable.contains(join.leftColumn) ? join.leftColumn : join.rightColumn;

            Table unusedTable = !commonTable.equals(leftTable) ? join.rightTable : join.leftTable;
            Column unusedColumn = !commonColumn.equals(leftColumn) ? join.rightColumn : join.leftColumn;

            sb.append(String.format("%s %s%s ", join.type, unusedTable, join.type != Type.CROSS_JOIN ? String.format(" ON (%s.%s = %s.%s)", commonTable, commonColumn, unusedTable, unusedColumn) : ""));
            sb.append(join.getChildren()).trimToSize();
        }

        return sb.toString();
    }

    /* SUB-ENUM */
    public enum Type {

        INNER_JOIN("INNER JOIN"),
        LEFT_JOIN("LEFT JOIN"),
        LEFT_OUTER_JOIN("LEFT OUTER JOIN"),
        RIGHT_JOIN("RIGHT JOIN"),
        RIGHT_OUTER_JOIN("RIGHT OUTER JOIN"),
        CROSS_JOIN("CROSS JOIN"),
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
