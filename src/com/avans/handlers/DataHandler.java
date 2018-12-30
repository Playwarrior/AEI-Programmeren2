package com.avans.handlers;

import com.avans.database.Join;
import com.avans.database.tables.SerieTable;
import com.avans.handlers.program.Program;
import com.avans.handlers.user.Subscriber;

import java.util.ArrayList;
import java.util.List;

import static com.avans.database.tables.MovieTable.FK_ID;
import static com.avans.database.tables.ProgramTable.ID;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/
public class DataHandler {

    private static Join MOVIE_JOIN = new Join(Join.Type.INNER_JOIN, ID, FK_ID);
    private static Join SERIE_JOIN = new Join(Join.Type.INNER_JOIN, ID, SerieTable.ID);

    private List<Subscriber> subscribers;
    private List<Program> programs;

    public DataHandler(){
        this.subscribers = new ArrayList<>();
        this.programs = new ArrayList<>();

        this.init();
    }


    private void init(){

    }
}
