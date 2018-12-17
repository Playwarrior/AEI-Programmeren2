package com.avans.database;

import java.util.HashMap;
import java.util.Set;

public class ColumnKey extends Column {

    private HashMap<Action, Response> responses;

    private Key key;

    public ColumnKey(String name, Type type, Key key, int... args) {
        super(name, type, args);
        this.responses = new HashMap<>();
        this.key = key;
    }

    public void addResponse(Action action, Response response){
        if(key != Key.FOREIGN)
            throw new IllegalStateException("You cannot use this method if the key is a primary key!");

        this.responses.put(action, response);
    }

    /* GETTERS */
    Set<Action> getActions(){
        return responses.keySet();
    }

    Response getResponse(Action action){
        return responses.getOrDefault(action, null);
    }

    /* BOOLEANS */
    boolean isPrimaryKey(){
        return key == Key.PRIMARY;
    }

    boolean isForeignKey(){
        return key == Key.FOREIGN;
    }

    /* SUB ENUMS */
    public enum Action {

        ON_DELETE(" ON DELETE"),
        ON_UPDATE(" ON UPDATE");

        private String query;

        Action(String query){
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }

    }

    public enum Response {

        NO_ACTION(" NO ACTION"),
        RESTRICT(" RESTRICT "),
        SET_DEFAULT(" SET DEFAULT"),
        CASCADE(" CASCADE"),
        SET_NULL(" SET NULL");

        private String query;

        Response(String query){
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }

    public enum Key {

        PRIMARY,
        FOREIGN;

    }
}
