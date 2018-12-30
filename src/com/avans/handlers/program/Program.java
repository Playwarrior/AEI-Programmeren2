package com.avans.handlers.program;

import com.avans.database.Database;
import com.avans.database.Set;
import com.avans.database.Where;

import java.util.ArrayList;
import java.util.List;

import static com.avans.database.tables.ProgramTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public abstract class Program {

    private static List<Program> PROGRAMS = new ArrayList<>();

    private int id, duration;
    private String title;

    public Program(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;

        PROGRAMS.add(this);
    }

    /**
     * GETTERS
     */
    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    /**
     * ABSTRACT METHODS
     */
    protected void serialize() {
        if (Database.get().contains(PROGRAM_TABLE, ID, new Where<>(ID, getId()))) {
            Database.get().update(PROGRAM_TABLE,
                    new Set[]{
                            new Set<>(ID, id),
                            new Set<>(TITLE, title),
                            new Set<>(DURATION, duration)
                    },
                    new Where<>(ID, id)
            );
        } else {
            Database.get().insert(PROGRAM_TABLE, String.valueOf(id), String.valueOf(title), String.valueOf(duration));
        }
    }

    /**
     * STATIC METHODS
     */
    public static Program getProgram(int id) {
        for (Program program : PROGRAMS) {
            if (program.id == id) {
                return program;
            }
        }
        return null;
    }

    public static Program getProgram(String title){
        for(Program program : PROGRAMS){
            if(program.title.equalsIgnoreCase(title)){
                return program;
            }
        }
        return null;
    }
}
