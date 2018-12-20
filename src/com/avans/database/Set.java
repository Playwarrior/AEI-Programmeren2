package com.avans.database;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class Set<T> {

    protected final Column column;
    protected final T value;

    public Set(Column column, T t){
        this.column = column;
        this.value = t;
    }

    /* GETTERS */
    public Column getColumn() {
        return column;
    }

    public T getValue() {
        return value == null ? (T) "NULL" : value;
    }

    /* OVERRIDABLE */
    @Override
    public String toString() {
        return String.format("%s='%s'", column, value.toString());
    }
}
