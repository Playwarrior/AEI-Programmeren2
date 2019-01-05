package com.avans.handlers.program;

import com.avans.database.Column;
import com.avans.database.Database;
import com.avans.database.Set;
import com.avans.database.Where;
import com.avans.handlers.Removable;

import java.util.Map;

import static com.avans.database.tables.ProgramTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public abstract class Program implements Removable {

    private int id;
    private String title;
    private String genre;

    //constructor for not stored in database Program
    public Program(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    //constructor for already stored in database Program
    public Program(int id) {
        this.id = id;

        if (!Database.get().contains(PROGRAM_TABLE, ID, new Where<>(ID, id)))
            throw new IllegalStateException("Cannot find the right data by this ID: " + id);

        Map<Column, Object> values = Database.get().getValues(PROGRAM_TABLE, new Where<>(ID, id));

        this.title = (String) values.get(TITLE);
        this.genre = (String) values.get(GENRE);
    }

    /**
     * GETTERS
     */
    public int getId() {
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
        Database.get().delete(PROGRAM_TABLE, new Where<>(ID, id));
        return true;
    }

    /**
     * serialize() method
     */
    public void serialize() {
        if (Database.get().contains(PROGRAM_TABLE, ID, new Where<>(ID, getId()))) {
            Database.get().update(PROGRAM_TABLE, new Set[]{
                            new Set<>(TITLE, title),
                            new Set<>(GENRE, genre)
                    },
                    new Where<>(ID, id)
            );
        } else {
            Database.get().insert(PROGRAM_TABLE, String.valueOf(id), String.valueOf(title));
        }

    }
}
