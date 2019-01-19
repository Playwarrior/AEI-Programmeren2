package com.avans.handlers;

import com.avans.database.*;
import com.avans.database.tables.SubscriptionTable;
import com.avans.database.tables.SerieTable;
import com.avans.handlers.program.Episode;
import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Program;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.avans.database.tables.SubscriptionTable.LAST_NAME;
import static com.avans.database.tables.SubscriptionTable.NAME;
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
     * ADD METHODS
     */
    public Subscriber addSubscriber(String name, String lastName) {
        UUID id = UUID.randomUUID();

        while (isSubscriber(id))
            id = UUID.randomUUID();

        Subscriber s = new Subscriber(id, name, lastName);
        this.subscribers.add(s);
        return s;
    }

    public Serie addSerie(String title, String genre) {
        UUID id = UUID.randomUUID();

        if (isProgram(title))
            return null;

        while (isProgram(id))
            id = UUID.randomUUID();

        Serie s = new Serie(id, title, genre);

        this.programs.add(new Serie(id, title, genre));

        return s;
    }

    public Episode addEpisode(String title, int episode, int duration, String episodeTitle, boolean nextEpisode) {
        if (!isProgram(title))
            return null;

        Serie serie = (Serie) getProgram(title);

        if (serie.isEpisode(episode))
            return null;

        serie.addEpisode(episode, duration, episodeTitle, nextEpisode);

        return serie.getEpisode(episode);
    }

    public Movie addMovie(String title, int duration, String genre, int ageIndication) {
        UUID id = UUID.randomUUID();

        if (isProgram(title))
            return null;

        while (isProgram(id))
            id = UUID.randomUUID();


        Movie m = new Movie(id, title, duration, ageIndication, genre);

        programs.add(m);

        return m;
    }

    /**
     * GETTERS
     */

    //subscribers
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public Subscriber getSubscriber(UUID id) {
        for (Subscriber s : subscribers) {
            if (s.getId().compareTo(id) == 0) {
                return s;
            }
        }
        return null;
    }

    public List<Subscriber> getSubscribers(String name, String lastName) {
        List<Subscriber> subscribers = new ArrayList<>();

        for (Subscriber s : this.subscribers) {
            if (s.getName().equalsIgnoreCase(name) && s.getLastName().equalsIgnoreCase(lastName)) {
                subscribers.add(s);
            }
        }
        return subscribers;
    }

    //program
    public Program getProgram(String name) {
        for (Program p : programs) {
            if (p.getTitle().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public Program getProgram(UUID id) {
        for (Program p : programs) {
            if (p.getId().compareTo(id) == 0) {
                return p;
            }
        }
        return null;
    }

    public <T extends Program> List<T> getPrograms(Class<T> type) {
        if (type == Program.class)
            return (List<T>) programs;

        List<T> programs = new ArrayList<>();

        for (Program p : this.programs) {
            if (p.getClass() == type) {
                programs.add((T) p);
            }
        }
        return programs;
    }

    /**
     * BOOLEANS
     */
    public boolean isProgram(String name) {
        return getProgram(name) != null;
    }

    public boolean isProgram(UUID id) {
        return getProgram(id) != null;
    }

    public boolean isSubscriber(UUID id) {
        return getSubscriber(id) != null;
    }

    /**
     * delete() method
     */
    public boolean delete(Removable removable) {
        boolean deleted = removable.delete();

        if (deleted) {
            if (removable instanceof Program)
                this.programs.remove(removable);

            if (removable instanceof Subscriber)
                this.subscribers.remove(removable);
        }
        return deleted;
    }

    /**
     * init() method
     */
    private void init() {
        for (Map<Column, Object> values : getEntry(MOVIE_JOIN, ID, TITLE, DURATION))
            programs.add(new Movie(UUID.fromString(values.get(ID).toString())));

        for (Map<Column, Object> values : getEntry(SERIE_JOIN, ID, TITLE))
            programs.add(new Serie(UUID.fromString(values.get(ID).toString())));

        for (Map<Column, Object> values : getEntry(SUBSCRIPTION_TABLE, SubscriptionTable.ID, SubscriptionTable.NAME, SubscriptionTable.LAST_NAME))
            subscribers.add(new Subscriber(UUID.fromString(values.get(SubscriptionTable.ID).toString()), (String) values.get(NAME), (String) values.get(LAST_NAME)));
    }

    private List<Map<Column, Object>> getEntry(From from, Column... columns) {
        return Database.get().getEntry(from, columns);
    }

    /**
     * serialize() method
     */
    public void serialize() {
        for (Subscriber s : subscribers)
            s.serialize();

        for (Program p : programs)
            p.serialize();
    }
}
