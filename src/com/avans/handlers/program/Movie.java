package com.avans.handlers.program;

import com.avans.database.*;
import com.avans.database.tables.MovieTable;

import java.util.Map;

import static com.avans.database.tables.MovieTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public class Movie extends Program {

    private int ageIndication;
    private int duration;

    public Movie(int id, String title, int duration, int ageIndication, String genre) {
        super(id, title, genre);

        this.duration = duration;
        this.ageIndication = ageIndication;

        this.serialize();
    }

    public Movie(int id) {
        super(id);

        Map<Column, Object> values = Database.get().getValues(MOVIE_TABLE, new Where<>(FK_ID, getId()));

        this.duration = (int) values.get(DURATION);

        this.ageIndication = Short.toUnsignedInt((short) values.get(AGE_INDICATION));
    }

    /**
     * GETTERS
     */
    public int getAgeIndication() {
        return ageIndication;
    }

    public int getDuration() {
        return duration;
    }

    /**
     * SETTERS
     */
    public void setAgeIndication(int ageIndication) {
        this.ageIndication = ageIndication;
    }

    /**
     * serialize() method
     */
    @Override
    public void serialize() {
        super.serialize();

        if (Database.get().contains(MOVIE_TABLE, FK_ID, new Where<>(FK_ID, getId()))) {
            Database.get().update(MOVIE_TABLE, new Set[]{
                            new Set<>(AGE_INDICATION, ageIndication)
                    },
                    new Where<>(FK_ID, getId())
            );
        } else {
            Database.get().insert(MOVIE_TABLE, String.valueOf(ageIndication), String.valueOf(duration), String.valueOf(getId()));
        }
    }
}
