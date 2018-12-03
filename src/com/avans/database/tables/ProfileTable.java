package com.avans.database.tables;

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class ProfileTable extends Table {

    public static final ColumnKey PROFILE_NAME;

    public static final Column AGE;
    public static final Column PREFERENCE;
    public static final Column BEHAVIOUR;
    public static final Column PROGRAMS;

    public static final ColumnKey FK_NAME;

    static {
        /* initialisation of primary keys */
        PROFILE_NAME = new ColumnKey("ProfileName", Column.Type.VARCHAR, Table.PROFILE_TABLE, ColumnKey.Key.PRIMARY);

        /* initialisation of columns */
        AGE = new Column("Age", Column.Type.TINYINT, 3); //TODO: CHANGE TO BIRTHDAY TO BE MORE EFFICIENT WITH THE DATABASE!
        PREFERENCE = new Column("Preference", Column.Type.VARCHAR); //TODO ASK BRYAN ON HOW TO IMPLEMENT THIS!
        BEHAVIOUR = new Column("Behaviour", Column.Type.VARCHAR); //TODO SAME GOES FOR THIS!
        PROGRAMS = new Column("Programs", Column.Type.VARCHAR); //TODO: MOVE THIS TO A SUB-TABLE!

        /* initialisation of foreign key */
        FK_NAME = new ColumnKey("Name", Column.Type.VARCHAR, Table.PROFILE_TABLE, ColumnKey.Key.FOREIGN);

        /* initialisation of action and response */
        FK_NAME.addResponse(ColumnKey.Action.ON_DELETE, ColumnKey.Response.CASCADE);
        FK_NAME.addResponse(ColumnKey.Action.ON_UPDATE, ColumnKey.Response.UPDATE);
    }

    public ProfileTable() {
        super("Profile", PROFILE_NAME, AGE, PREFERENCE, BEHAVIOUR, PROGRAMS, FK_NAME);

        /* initialisation of constraints */
        this.addConstraint(new Constraint("ProfilePK", Constraint.Type.PRIMARY, PROFILE_NAME));
        this.addConstraint(new Constraint("ProfileFK", Constraint.Type.FOREIGN, AbonneeTable.NAME, FK_NAME));
    }
}
