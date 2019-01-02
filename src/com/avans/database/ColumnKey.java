package com.avans.database;

import java.util.HashMap;
import java.util.Set;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class ColumnKey extends Column {

    private Key key;

    public ColumnKey(String name, Type type, Key key, int... args) {
        super(name, type, args);
        this.key = key;
    }

    /* BOOLEANS */
    boolean isPrimaryKey(){
        return key == Key.PRIMARY || key == Key.BOTH;
    }

    boolean isForeignKey(){
        return key == Key.FOREIGN || key == Key.BOTH;
    }

    boolean isCandidateKey(){
        return key == Key.CANDIDATE;
    }

    public enum Key {

        PRIMARY,
        FOREIGN,
        BOTH,
        CANDIDATE;

    }
}
