package com.avans.database.tables;

/*
    Created By Robin Egberts On 12/24/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class BehaviourSerieTable extends Table {

    public static final ColumnKey FK_PROFILE_NAME;

    public static final ColumnKey FK_ID;

    public static final ColumnKey FK_PROGRAM_ID;

    public static final ColumnKey FK_EPISODE_NUMBER;

    public static final Column CURRENT_DURATION;

    static {
        FK_PROFILE_NAME = new ColumnKey("ProfileName", Column.Type.VARCHAR, ColumnKey.Key.BOTH, 50);

        FK_ID = new ColumnKey("ID", Column.Type.VARCHAR, ColumnKey.Key.BOTH, 36);

        FK_PROGRAM_ID = new ColumnKey("ProgramID", Column.Type.VARCHAR, ColumnKey.Key.BOTH, 36);

        FK_EPISODE_NUMBER = new ColumnKey("EpisodeNumber", Column.Type.INT, ColumnKey.Key.BOTH);

        CURRENT_DURATION = new Column("CurrentDuration", Column.Type.INT);
    }

    public BehaviourSerieTable() {
        super("BehaviourSerie", FK_ID, FK_PROFILE_NAME, FK_PROGRAM_ID, FK_EPISODE_NUMBER, CURRENT_DURATION);

        this.addConstraint(new Constraint("BehaviourSerie", Constraint.Type.PRIMARY, FK_ID, FK_PROFILE_NAME, FK_PROGRAM_ID, FK_EPISODE_NUMBER));
        {
            Constraint cs = new Constraint("BehaviourSerieProfile", Constraint.Type.FOREIGN, ProfileTable.PROFILE_NAME, FK_PROFILE_NAME, ProfileTable.FK_ID, FK_ID);
            cs.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

            this.addConstraint(cs);
        }
        {
            Constraint cs = new Constraint("BehaviourSerieEpisode", Constraint.Type.FOREIGN, EpisodeTable.EPISODE_NUMBER, FK_EPISODE_NUMBER, EpisodeTable.FK_ID, FK_PROGRAM_ID);
            cs.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

            this.addConstraint(cs);
        }
    }
}
