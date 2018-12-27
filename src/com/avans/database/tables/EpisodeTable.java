package com.avans.database.tables;

/*
    Created By Robin Egberts On 12/24/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class EpisodeTable extends Table {

    public static final ColumnKey EPISODE_NUMBER;

    public static final ColumnKey FK_EPISODE_NUMBER;

    public static final ColumnKey FK_ID;

    static {
        EPISODE_NUMBER = new ColumnKey("EpisodeNumber", Column.Type.INT, ColumnKey.Key.PRIMARY);

        FK_EPISODE_NUMBER = new ColumnKey("NextEpisode", Column.Type.INT, ColumnKey.Key.FOREIGN);

        FK_ID = new ColumnKey("ID", Column.Type.INT, ColumnKey.Key.FOREIGN);
    }

    public EpisodeTable() {
        super("Episode", EPISODE_NUMBER, FK_EPISODE_NUMBER, FK_ID);

        this.addConstraint(new Constraint("Episode", Constraint.Type.PRIMARY, EPISODE_NUMBER));
        this.addConstraint(new Constraint("Episode", Constraint.Type.FOREIGN, EPISODE_NUMBER, FK_EPISODE_NUMBER));

        {
            Constraint cs = new Constraint("EpisodeSerie", Constraint.Type.FOREIGN, SerieTable.ID, FK_ID);
            cs.addResponses(Constraint.Action.ON_DELETE, Constraint.Response.CASCADE);

            this.addConstraint(cs);
        }
    }
}
