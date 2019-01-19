package com.avans.database;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class Set<T> {

    protected final Column column;
    protected final T value;

    public Set(Column column, T t) {
        this.column = column;
        this.value = t;
    }

    /* GETTERS */
    public Column getColumn() {
        return column;
    }

    public T getValue() {
        return value;
    }

    /* OVERRIDABLE */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if(value != null)
            s.append("'");

        String value = this.value == null ? "NULL" : this.value.toString();

        s.append(value);

        if(value != null)
            s.append("'");


        return String.format("%s=%s", column, s.toString());
    }
}
