package com.avans.handlers;

import com.avans.database.*;
import com.avans.database.tables.AbonneeTable;
import com.avans.database.tables.SerieTable;
import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Program;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.avans.database.tables.AbonneeTable.LAST_NAME;
import static com.avans.database.tables.AbonneeTable.NAME;
import static com.avans.database.tables.MovieTable.*;
import static com.avans.database.tables.ProgramTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/
public class DataHandler {

    private static Join MOVIE_JOIN = new Join(Join.Type.INNER_JOIN, ID, FK_ID);
    private static Join SERIE_JOIN = new Join(Join.Type.INNER_JOIN, ID, SerieTable.ID);

    private List<Subscriber> subscribers;
    private List<Program> programs;

    public DataHandler() {
        this.subscribers = new ArrayList<>();
        this.programs = new ArrayList<>();

        this.init();
    }

    /**
        GETTERS
     */
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public Subscriber getSubscriber(String name, String lastName){
        for(Subscriber s : subscribers){
            if(s.getName().equalsIgnoreCase(name) && s.getLastName().equalsIgnoreCase(lastName)){
                return s;
            }
        }
        return null;
    }

    public Subscriber getSubscriber(int id){
        for(Subscriber s : subscribers){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public Program getProgram(String name){
        for(Program p : programs){
            if(p.getTitle().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }

    public Program getProgram(int id){
        for(Program p : programs){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    /**
        BOOLEANS
     */
    public boolean isProgram(String name){
        return getProgram(name) != null;
    }

    public boolean isSubscriber(String name, String lastName){
        return getSubscriber(name, lastName) != null;
    }

    /**
        init() method
     */
    private void init() {
        for (Map<Column, Object> values : getEntry(MOVIE_JOIN, ID, TITLE, DURATION))
            programs.add(new Movie((int) values.get(ID), (String) values.get(TITLE), (int) values.get(DURATION)));

        for (Map<Column, Object> values : getEntry(SERIE_JOIN, ID, TITLE, DURATION))
            programs.add(new Serie((int) values.get(ID), (String) values.get(TITLE), (int) values.get(DURATION)));

        for(Map<Column, Object> values : getEntry(ABONNEE_TABLE, AbonneeTable.ID, AbonneeTable.NAME, AbonneeTable.LAST_NAME))
            subscribers.add(new Subscriber((int) values.get(AbonneeTable.ID), (String) values.get(NAME), (String) values.get(LAST_NAME)));
    }

    private List<Map<Column, Object>> getEntry(From from, Column... columns) {
        return Database.get().getEntry(from, columns);
    }

    /**
        serialize() method
     */
    public void serialize(){
        for(Subscriber s : subscribers)
            s.serialize();

        for(Program p : programs)
            p.serialize();
    }
}
