package com.avans.database;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Constraint {

    private ColumnKey[] keys;

    private HashMap<Action, Response> responses;

    private Type type;
    private String name;

    public Constraint(String name, Type type, ColumnKey... keys) {
        this.keys = keys;
        this.type = type;
        this.name = name;

        this.responses = new HashMap<>();

        if(keys.length <= 0)
            throw new IllegalArgumentException("Constraint doesn't contain any keys!");
    }

    public void addResponses(Action action, Response response){
        if(type != Type.FOREIGN)
            throw new IllegalStateException("Cannot add action and response to primary constraint!");

        this.responses.put(action, response);
    }

    public String getName() {
        return type.getName() + name;
    }

    /* OVERRIDABLE */
    @Override
    public String toString() {
        StringBuilder cs = new StringBuilder(String.format(" CONSTRAINT %s%s ", type.getName(), name));

        switch (type) {

            /* case when the constraint is a primary key! */
            case PRIMARY: {

                cs.append("PRIMARY KEY (");
                for (int i = 0; i < keys.length; i++) {

                    ColumnKey columnKey = keys[i];

                    if (columnKey.isPrimaryKey()) {

                        if (i != 0)
                            cs.append(",");

                        cs.append(columnKey.toString());

                    }
                }
                cs.append(")");
                break;
            }

            /* case when the constraint is a foreign key! */
            case FOREIGN: {

                //TODO: FIX BOTH KEY!

                List<ColumnKey> primaryKeys = new ArrayList<>();
                List<ColumnKey> foreignKeys = new ArrayList<>();

                for(int i = 0; i < keys.length; i++){

                    ColumnKey key = keys[i];

                    if(key.isPrimaryKey() && i % 2 == 0)
                        primaryKeys.add(key);

                    if(key.isForeignKey() && i % 2 == 1)
                        foreignKeys.add(key);
                }

                if(isSameType(primaryKeys, foreignKeys)) {
                    cs.append(String.format("FOREIGN KEY (%s) REFERENCES %s (%s) %s", toString(foreignKeys), Table.getTable(primaryKeys.get(0)), toString(primaryKeys), toActionResponseString()).trim());
                }
                break;
            }
        }
        return cs.toString();
    }

    /* FOREIGN KEY METHODS */
    private String toString(List<ColumnKey> keys){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < keys.size(); i++){
            if(i != 0)
                sb.append(",");

            sb.append(keys.get(i).toString());
        }

        return sb.toString();
    }

    private String toActionResponseString(){
        StringBuilder sb = new StringBuilder();
        for(Action action : responses.keySet()){
            sb.append(action.toString()).append(" ").append(responses.get(action).toString()).append(" ");
        }
        sb.trimToSize();
        return sb.toString();
    }

    private boolean isSameType(List<ColumnKey> pks, List<ColumnKey> fks){
        if(pks.size() != fks.size())
            throw new IllegalStateException(String.format("Missing some Keys in %s constraint!", name));

        for(int i = 0; i < pks.size(); i++){
            ColumnKey pk = pks.get(i);
            ColumnKey fk = fks.get(i);

            if(pk.getType() != fk.getType())
                throw new IllegalStateException(String.format("The column: %s and %s aren't the same type", pk.toString(), fk.toString()));

            if(pk.getArgs().length != fk.getArgs().length)
                throw new IllegalStateException(String.format("The column: %s and %s don't have the same arguments", pk.toString(), fk.toString()));

            for(int j = 0; j < pk.getArgs().length; j++){
                int argpk = pk.getArgs()[j];
                int argfk = fk.getArgs()[j];

                if(argpk != argfk)
                    throw new IllegalStateException(String.format("The column: %s and %s don't have the same arguments", pk.toString(), fk.toString()));
            }
        }

        return true;
    }

    /* SUB ENUM */
    public enum Type {

        PRIMARY("PK"),
        FOREIGN("FK");

        private String shortName;

        Type(String shortName){
            this.shortName = shortName;
        }

        public String getName(){
            return shortName;
        }
    }

    public enum Action {

        ON_DELETE("ON DELETE"),
        ON_UPDATE("ON UPDATE");

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

        NO_ACTION("NO ACTION"),
        RESTRICT("RESTRICT "),
        SET_DEFAULT("SET DEFAULT"),
        CASCADE("CASCADE"),
        SET_NULL("SET NULL");

        private String query;

        Response(String query){
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }
}
