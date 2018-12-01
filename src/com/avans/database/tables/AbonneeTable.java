package com.avans.database.tables;

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class AbonneeTable extends Table {

    public static final ColumnKey NAME;

    public static final Column LAST_NAME;
    public static final Column STREET;
    public static final Column HOUSE_NUMBER;
    public static final Column POSTCODE;
    public static final Column CITY;

    static {
        /* initialisation of primary keys */
        NAME = new ColumnKey("Name", Column.Type.VARCHAR, Table.ABONNEE_TABLE, ColumnKey.Key.PRIMARY);

        /* initialisation of columns */
        LAST_NAME = new Column("LastName", Column.Type.VARCHAR);
        STREET = new Column("Street", Column.Type.VARCHAR);
        HOUSE_NUMBER = new Column("HouseNumber", Column.Type.TINYINT,3);
        POSTCODE = new Column("Postcode", Column.Type.VARCHAR, 6);
        CITY = new Column("City", Column.Type.VARCHAR);

        /* initialisation of foreign keys if any!*/

        /* initialisation of responses! */
    }



    public AbonneeTable() {
        super("Abonnee", NAME, LAST_NAME, STREET, HOUSE_NUMBER, POSTCODE, CITY);

        this.addConstraint(new Constraint("Abonnee", Constraint.Type.PRIMARY, NAME));
    }
}
