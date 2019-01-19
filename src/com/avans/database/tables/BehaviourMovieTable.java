package com.avans.database.tables;

/*
    Created By Robin Egberts On 1/18/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class BehaviourMovieTable extends Table {

    public static final ColumnKey FK_ID;
    public static final ColumnKey FK_PROFILE;
    public static final ColumnKey FK_PROGRAM_ID;
    public static final Column CURRENT_DURATION;

    static {
        FK_ID = new ColumnKey("ID", Column.Type.VARCHAR, ColumnKey.Key.BOTH, 36);

        FK_PROFILE = new ColumnKey("ProfileName", Column.Type.VARCHAR, ColumnKey.Key.BOTH, 50);

        FK_PROGRAM_ID = new ColumnKey("ProgramID", Column.Type.VARCHAR, ColumnKey.Key.BOTH, 36);

        CURRENT_DURATION = new Column("CurrentDuration", Column.Type.INT);
    }



    public BehaviourMovieTable() {
        super("BehaviourMovie", FK_ID, FK_PROFILE, FK_PROGRAM_ID, CURRENT_DURATION);

        this.addConstraint(new Constraint("BehaviourMovie", Constraint.Type.PRIMARY, FK_ID, FK_PROFILE, FK_PROGRAM_ID));

        Constraint cs = new Constraint("BehaviourMovieProfile", Constraint.Type.FOREIGN, ProfileTable.PROFILE_NAME, FK_PROFILE, ProfileTable.FK_ID, FK_ID);
        cs.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

        this.addConstraint(cs);

        Constraint cs1 = new Constraint("BehaviourMovieMovie", Constraint.Type.FOREIGN, MovieTable.FK_ID, FK_PROGRAM_ID);
        cs1.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

        this.addConstraint(cs1);
    }
}
