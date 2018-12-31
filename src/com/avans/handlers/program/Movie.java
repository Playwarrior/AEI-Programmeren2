package com.avans.handlers.program;

import com.avans.database.*;
import com.avans.database.tables.MovieTable;

import static com.avans.database.tables.MovieTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public class Movie extends Program {

    private String genre;
    private int ageIndication;

    public Movie(int id, String title, int duration) {
        super(id, title, duration);

        this.genre = Database.get().get(MOVIE_TABLE, GENRE, new Where<>(MovieTable.FK_ID, this.getId()));
        this.ageIndication = Short.toUnsignedInt(Database.get().get(MOVIE_TABLE, AGE_INDICATION, new Where<>(MovieTable.FK_ID, this.getId())));
    }

    public String getGenre() {
        return genre;
    }

    public int getAgeIndication() {
        return ageIndication;
    }

    @Override
    public void serialize() {
        super.serialize();

        if(Database.get().contains(MOVIE_TABLE, FK_ID, new Where<>(FK_ID, getId()))) {
            Database.get().update(MOVIE_TABLE, new Set[]{
                            new Set<>(FK_ID, getId()),
                            new Set<>(GENRE, genre),
                            new Set<>(AGE_INDICATION, ageIndication)
                    },
                    new Where<>(FK_ID, getId())
            );
        } else {
            Database.get().insert(MOVIE_TABLE, String.valueOf(getId()), String.valueOf(ageIndication), String.valueOf(genre));
        }
    }
}
