package com.avans.handlers.program;

import com.avans.database.Column;
import com.avans.database.Database;
import com.avans.database.Set;
import com.avans.database.Where;
import com.avans.handlers.Removable;

import java.util.Map;
import java.util.UUID;

import static com.avans.database.tables.ProgramTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public abstract class Program implements Removable {

    private UUID id;
    private String title;
    private String genre;

    //constructor for not stored in database Program
    public Program(UUID id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    //constructor for already stored in database Program
    public Program(UUID id) {
        this.id = id;

        if (!Database.get().containKey(ID, id.toString()))
            throw new IllegalStateException("Cannot find the right data by this ID: " + id.toString());

        Map<Column, Object> values = Database.get().getValues(PROGRAM_TABLE, new Where<>(ID, id.toString()));

        this.title = (String) values.get(TITLE);
        this.genre = (String) values.get(GENRE);
    }

    /**
     * GETTERS
     */
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    /**
     * SETTERS
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * delete() method
     */
    @Override
    public boolean delete() {
        Database.get().delete(PROGRAM_TABLE, new Where<>(ID, id.toString()));
        return true;
    }

    /**
     * serialize() method
     */
    public void serialize() {
        if (Database.get().contains(PROGRAM_TABLE, ID, new Where<>(ID, getId().toString()))) {
            Database.get().update(PROGRAM_TABLE, new Set[]{
                            new Set<>(TITLE, title),
                            new Set<>(GENRE, genre)
                    },
                    new Where<>(ID, id.toString())
            );
        } else {
            Database.get().insert(PROGRAM_TABLE, id.toString(), title, genre);
        }
    }

    @Override
    public String toString() {
        return title;
    }
}
