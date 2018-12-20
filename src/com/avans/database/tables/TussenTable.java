package com.avans.database.tables;

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

/*
    Created By Robin Egberts On 12/19/2018
    Copyrighted By OrbitMines ©2018
*/

public class TussenTable extends Table {

    public static final Column BULLSHIT;

    public static final ColumnKey FK_PROFILE_NAME;

    static {
        BULLSHIT = new Column("Bullshit", Column.Type.VARCHAR, 36);

        FK_PROFILE_NAME = new ColumnKey("ProfileName", Column.Type.VARCHAR, ColumnKey.Key.FOREIGN, 50);

        FK_PROFILE_NAME.addResponse(ColumnKey.Action.ON_DELETE, ColumnKey.Response.CASCADE);
    }


    public TussenTable() {
        super("Tussen", BULLSHIT, FK_PROFILE_NAME);

        this.addConstraint(new Constraint("Tussen", Constraint.Type.FOREIGN, ProfileTable.PROFILE_NAME, FK_PROFILE_NAME));
    }
}
