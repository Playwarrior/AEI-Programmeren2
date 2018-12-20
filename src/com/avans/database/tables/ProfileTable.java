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
    public static final Column PREFERENCE;
    public static final Column BEHAVIOUR;
    public static final Column PROGRAMS;

    public static final ColumnKey FK_NAME;

    static {
        /* initialisation of primary keys */
        PROFILE_NAME = new ColumnKey("ProfileName", Column.Type.VARCHAR, ColumnKey.Key.PRIMARY, 50);

        /* initialisation of columns */
        AGE = new Column("Age", Column.Type.TINYINT);
        PREFERENCE = new Column("Preference", Column.Type.VARCHAR, 50);
        BEHAVIOUR = new Column("Behaviour", Column.Type.VARCHAR, 50);
        PROGRAMS = new Column("Programs", Column.Type.VARCHAR, 50);

        /* initialisation of foreign key */
        FK_NAME = new ColumnKey("Name", Column.Type.VARCHAR, ColumnKey.Key.FOREIGN, 50);

        /* initialisation of action and response */
        FK_NAME.addResponse(ColumnKey.Action.ON_DELETE, ColumnKey.Response.CASCADE);
    }

    public ProfileTable() {
        super("Profile", PROFILE_NAME, AGE, PREFERENCE, BEHAVIOUR, PROGRAMS, FK_NAME);

        /* initialisation of constraints */
        this.addConstraint(new Constraint("ProfilePK", Constraint.Type.PRIMARY, PROFILE_NAME));
        this.addConstraint(new Constraint("ProfileFK", Constraint.Type.FOREIGN, AbonneeTable.NAME, FK_NAME));
    }
}
