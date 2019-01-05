package com.avans.database.tables;

/*
    Created By Robin Egberts On 12/24/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class SerieTable extends Table {

    public static final ColumnKey ID;

    static {
        ID = new ColumnKey("ID", Column.Type.INT, ColumnKey.Key.BOTH);
    }

    public SerieTable() {
        super("Serie", ID);

        this.addConstraint(new Constraint("Serie", Constraint.Type.PRIMARY, ID));

        Constraint cs = new Constraint("Serie", Constraint.Type.FOREIGN, ProgramTable.ID, ID);
        cs.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

        this.addConstraint(cs);
    }
}
