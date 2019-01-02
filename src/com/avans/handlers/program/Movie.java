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
    private int duration;

    public Movie(int id, String title, int duration, String genre, int ageIndication) {
        super(id, title);

        this.duration = duration;
        this.genre = genre;
        this.ageIndication = ageIndication;

        this.serialize();
    }

    public Movie(int id) {
        super(id);

        this.duration = Database.get().get(MOVIE_TABLE, DURATION, new Where<>(FK_ID, this.getId()));

        this.genre = Database.get().get(MOVIE_TABLE, GENRE, new Where<>(MovieTable.FK_ID, this.getId()));

        this.ageIndication = Short.toUnsignedInt(Database.get().get(MOVIE_TABLE, AGE_INDICATION, new Where<>(MovieTable.FK_ID, this.getId())));
    }

    /**
     * GETTERS
     */
    public String getGenre() {
        return genre;
    }

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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * serialize() method
     */
    @Override
    public void serialize() {
        super.serialize();

        if (Database.get().contains(MOVIE_TABLE, FK_ID, new Where<>(FK_ID, getId()))) {
            Database.get().update(MOVIE_TABLE, new Set[]{
                            new Set<>(GENRE, genre),
                            new Set<>(AGE_INDICATION, ageIndication)
                    },
                    new Where<>(FK_ID, getId())
            );
        } else {
            Database.get().insert(MOVIE_TABLE,  String.valueOf(genre), String.valueOf(ageIndication), String.valueOf(duration), String.valueOf(getId()));
        }
    }
}
