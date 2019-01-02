package com.avans.handlers.program;

import com.avans.database.Database;
import com.avans.database.Where;
import com.avans.handlers.Removable;

import static com.avans.database.tables.ProgramTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public abstract class Program implements Removable {

    private int id;
    private String title;

    //constructor for not stored in database Program
    public Program(int id, String title) {
        this.id = id;
        this.title = title;
    }

    //constructor for already stored in database Program
    public Program(int id) {
        this.id = id;

        if (!Database.get().contains(PROGRAM_TABLE, ID, new Where<>(ID, id)))
            throw new IllegalStateException("Cannot find the right data by this ID: " + id);

        this.title = Database.get().get(PROGRAM_TABLE, TITLE, new Where<>(ID, id));
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
        if (!Database.get().contains(PROGRAM_TABLE, ID, new Where<>(ID, getId())))
            Database.get().insert(PROGRAM_TABLE, String.valueOf(id), String.valueOf(title));
    }
}
