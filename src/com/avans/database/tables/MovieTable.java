package com.avans.database.tables;

/*
    Created By Robin Egberts On 12/23/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class MovieTable extends Table {

    public static final Column GENRE;
    public static final Column AGE_INDICATION;

    public static final ColumnKey FK_ID;

    static {
        GENRE = new Column("Genre", Column.Type.VARCHAR, 50);
        AGE_INDICATION = new Column("AgeIndication", Column.Type.TINYINT);

        FK_ID = new ColumnKey("ID", Column.Type.INT, ColumnKey.Key.FOREIGN);
    }

    public MovieTable() {
        super("Movie", GENRE, AGE_INDICATION, FK_ID);

        Constraint cs = new Constraint("Movie", Constraint.Type.FOREIGN, ProgramTable.ID, FK_ID);
        cs.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

        this.addConstraint(cs);
    }
}
