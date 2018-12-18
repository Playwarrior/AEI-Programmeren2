package com.avans.database;

public class Constraint {

    private Table primaryTable;

    private ColumnKey[] keys;

    private Type type;
    private String name;

    public Constraint(Table primaryTable, String name, Type type, ColumnKey... keys) {
        this.keys = keys;
        this.type = type;
        this.name = name;

        this.primaryTable = primaryTable;

        if(keys.length <= 0)
            throw new IllegalArgumentException("Constraint doesn't contain any keys!");
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
                int nonPrimaryKeys = 1;

                cs.append("PRIMARY KEY (");
                for (int i = 0; i < keys.length; i++) {

                    ColumnKey columnKey = keys[i];

                    if (columnKey.isPrimaryKey()) {

                        if (i != 0 || i != keys.length - nonPrimaryKeys)
                            cs.append(",");

                        cs.append(columnKey.toString());

                    } else {
                        nonPrimaryKeys++;
                    }
                }
                cs.append(") ");
                break;
            }

            /* case when the constraint is foreign key! */
            case FOREIGN: {
                if (keys.length != 2)
                    throw new IllegalStateException("Too many keys have been found in the constraint!");


                ColumnKey fk = keys[0].isForeignKey() ? keys[0] : keys[1].isForeignKey() ? keys[1] : null;
                ColumnKey pk = keys[0].isPrimaryKey() ? keys[0] : keys[1].isPrimaryKey() ? keys[1] : null;

                if (fk == null || pk == null)
                    throw new IllegalStateException("Either the Foreign Key doesn't exist or the Primary Key doesn't exist!");

                if(!pk.toTypeString(false).equals(fk.toTypeString(false)))
                    throw new IllegalStateException("Primary Key isn't the same type of the Foreign Key!");

                cs.append(String.format("FOREIGN KEY (%s) REFERENCES %s (%s)", fk.toString(), primaryTable.toString(), pk.toString()));

                for (ColumnKey.Action action : fk.getActions()) {
                    ColumnKey.Response response = fk.getResponse(action);

                    if (response == null)
                        throw new IllegalStateException("Response couldn't be found!");

                    cs.append(String.format("%s %s", action.toString(), response.toString()));
                }

                break;
            }
        }
        return cs.toString();
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
}
