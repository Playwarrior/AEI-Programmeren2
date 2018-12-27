package com.avans.database.tables;

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class ProfileTable extends Table {

    public static final ColumnKey PROFILE_NAME;

    public static final Column AGE;

    public static final ColumnKey FK_ID;

    static {
        /* initialisation of primary keys */
        PROFILE_NAME = new ColumnKey("ProfileName", Column.Type.VARCHAR, ColumnKey.Key.PRIMARY, 50);

        /* initialisation of columns */
        AGE = new Column("Age", Column.Type.TINYINT);

        /* initialisation of foreign key */
        FK_ID = new ColumnKey("ID", Column.Type.INT, ColumnKey.Key.BOTH);
    }

    public ProfileTable() {
        super("Profile", PROFILE_NAME, AGE, FK_ID);

        /* initialisation of constraints */
        this.addConstraint(new Constraint("ProfilePK", Constraint.Type.PRIMARY, PROFILE_NAME, FK_ID));

        Constraint cs = new Constraint("ProfileFK", Constraint.Type.FOREIGN, AbonneeTable.ID, FK_ID);
        cs.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

        this.addConstraint(cs);
    }
}
