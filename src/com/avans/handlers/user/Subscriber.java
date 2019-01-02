package com.avans.handlers.user;

import com.avans.database.*;
import com.avans.database.tables.BehaviourTable;
import com.avans.database.tables.ProfileTable;
import com.avans.database.tables.ProgramTable;
import com.avans.handlers.Removable;
import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Program;
import com.avans.handlers.program.Serie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.avans.database.Table.ABONNEE_TABLE;
import static com.avans.database.tables.AbonneeTable.*;
import static com.avans.database.tables.BehaviourTable.*;
import static com.avans.database.tables.ProfileTable.*;
import static com.avans.database.tables.ProfileTable.FK_ID;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public class Subscriber implements Removable {

    private static Join JOIN = new Join(Join.Type.INNER_JOIN, PROFILE_NAME, BehaviourTable.FK_PROFILE_NAME);

    private int id;
    private short houseNumber;
    private String name, lastName, street, postalCode, city;

    private List<Profile> profiles;

    public Subscriber(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;

        if (Database.get().contains(ABONNEE_TABLE, new Column[]{STREET, HOUSE_NUMBER, POSTCODE, CITY}, new Where<>(ID, id))) {

            this.street = Database.get().get(ABONNEE_TABLE, STREET, new Where<>(ID, this.id));
            this.houseNumber = Database.get().get(ABONNEE_TABLE, HOUSE_NUMBER, new Where<>(ID, this.id));
            this.postalCode = Database.get().get(ABONNEE_TABLE, POSTCODE, new Where<>(ID, this.id));
            this.city = Database.get().get(ABONNEE_TABLE, CITY, new Where<>(ID, this.id));
        }

        this.profiles = new ArrayList<>();

        this.initProfiles();
    }

    /**
     * SETTERS
     */
    public void setAdress(String street, short houseNumber, String postalCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public boolean addProfile(String name, int age) {
        if (profiles.size() > 5)
            return false;

        if (isProfile(name))
            return false;

        profiles.add(new Profile(name, age));

        return true;
    }

    /**
     * watch(profile, program, [episode], int)
     */
    public boolean watch(Profile profile, Movie movie, int currentDuration) {
        if (!profiles.contains(profile))
            return false;

        if (movie.getDuration() < currentDuration)
            return false;

        if (Database.get().contains(BEHAVIOUR_TABLE, FK_PROGRAM_ID, new Where<>(BehaviourTable.FK_ID, id), new Where<>(FK_PROFILE_NAME, profile.getName()), new Where<>(FK_PROGRAM_ID, movie.getId()))) {
            Database.get().update(BEHAVIOUR_TABLE, new Set[]{
                            new Set<>(FK_PROGRAM_ID, movie.getId()),
                            new Set<>(CURRENT_DURATION, currentDuration)
                    },
                    new Where<>(FK_ID, id),
                    new Where<>(FK_PROFILE_NAME, profile.getName()),
                    new Where<>(FK_PROGRAM_ID, movie.getId())
            );
        } else {
            Database.get().insert(BEHAVIOUR_TABLE, String.valueOf(id), profile.getName(), String.valueOf(movie.getId()), "NULL", String.valueOf(currentDuration));
        }
        return true;
    }

    public boolean watch(Profile profile, Serie serie, int episode, int currentDuration) {
        if (serie == null)
            return false;

        if (!profiles.contains(profile))
            return false;

        if (!serie.isEpisode(episode))
            return false;

        if (serie.getEpisode(episode).getDuration() < currentDuration)
            return false;

        if (Database.get().contains(BEHAVIOUR_TABLE, FK_PROGRAM_ID, new Where<>(BehaviourTable.FK_ID, id), new Where<>(BehaviourTable.FK_PROFILE_NAME, profile.getName()), new Where<>(BehaviourTable.FK_PROGRAM_ID, serie.getId()))) {
            Database.get().update(BEHAVIOUR_TABLE, new Set[]{
                            new Set<>(BehaviourTable.FK_EPISODE_NUMBER, episode),
                            new Set<>(BehaviourTable.CURRENT_DURATION, currentDuration)
                    },
                    new Where<>(BehaviourTable.FK_ID, id),
                    new Where<>(BehaviourTable.FK_PROFILE_NAME, profile.getName()),
                    new Where<>(BehaviourTable.FK_PROGRAM_ID, serie.getId())
            );
        } else {
            Database.get().insert(BEHAVIOUR_TABLE, profile.getName(), String.valueOf(id), String.valueOf(serie.getId()), String.valueOf(episode), String.valueOf(currentDuration));
        }
        return true;
    }

    /**
     * GETTERS
     */
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdress() {
        return String.format("%s %s %s %d", city, postalCode, street, houseNumber);
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public Profile getProfile(String name) {
        for (Profile profile : profiles) {
            if (profile.getName().equalsIgnoreCase(name)) {
                return profile;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public int getCurrentMinute(Profile profile, Program program) {
        if (!hasSeenProgram(profile, program))
            return -1;

        return Database.get().get(BEHAVIOUR_TABLE, BehaviourTable.CURRENT_DURATION,
                new Where<>(ID, id),
                new Where<>(PROFILE_NAME, profile.getName()),
                new Where<>(ProgramTable.ID, program.getId())
        );
    }

    public int getCurrentEpisode(Profile profile, Serie serie) {
        if (!hasSeenProgram(profile, serie))
            return -1;

        return Database.get().get(BEHAVIOUR_TABLE, BehaviourTable.FK_EPISODE_NUMBER,
                new Where<>(ID, id),
                new Where<>(PROFILE_NAME, profile.getName()),
                new Where<>(ProgramTable.ID, serie.getId())
        );
    }

    /**
     * BOOLEANS
     */
    public boolean isProfile(String name) {
        return getProfile(name) != null;
    }

    public boolean hasSeenProgram(Profile p, Program program) {
        if (!profiles.contains(p))
            return false;

        return Database.get().contains(JOIN,
                FK_PROGRAM_ID,
                new Where<>(BehaviourTable.FK_ID, id),
                new Where<>(BehaviourTable.FK_PROFILE_NAME, p.getName()),
                new Where<>(FK_PROGRAM_ID, program.getId())
        );
    }

    public boolean hasSeenProgram(Profile p, Serie serie, int episode) {
        if (!profiles.contains(p))
            return false;

        return Database.get().contains(JOIN,
                FK_PROGRAM_ID,
                new Where<>(BehaviourTable.FK_ID, id),
                new Where<>(BehaviourTable.FK_PROFILE_NAME, p.getName()),
                new Where<>(FK_PROGRAM_ID, serie.getId()),
                new Where<>(Where.Operator.GREATER_THAN_OR_EQUAL, BehaviourTable.FK_EPISODE_NUMBER, episode)
        );
    }

    /**
     * delete() method
     */
    @Override
    public boolean delete() {
        Database.get().delete(ABONNEE_TABLE, new Where<>(ID, id));
        return true;
    }

    public boolean deleteProfile(String profileName) {
        if (!isProfile(profileName))
            return false;

        Database.get().delete(PROFILE_TABLE, new Where<>(PROFILE_NAME, profileName), new Where<>(FK_ID, getId()));

        return true;
    }

    /**
     * initProfiles() method
     */
    private void initProfiles() {
        if (Database.get().getCount(Table.PROFILE_TABLE, new Where<>(FK_ID, this.id)) != 0) {
            List<Map<Column, Object>> values = Database.get().getEntry(PROFILE_TABLE, new Where<>(FK_ID, this.getId()));

            for (Map<Column, Object> v : values) {
                String name = String.valueOf(v.get(PROFILE_NAME));
                int age = Integer.parseInt(String.valueOf(v.get(AGE)));

                this.profiles.add(new Profile(name, age));
            }
        } else {
            this.profiles.add(new Profile("Kids", 15));
        }
    }

    /**
     * serialize() method
     */
    public void serialize() {
        if (Database.get().contains(ABONNEE_TABLE, ID, new Where<>(ID, id))) {

            Database.get().update(ABONNEE_TABLE, new Set[]{
                            new Set<>(STREET, street),
                            new Set<>(HOUSE_NUMBER, houseNumber),
                            new Set<>(POSTCODE, postalCode),
                            new Set<>(CITY, city)
                    },
                    new Where<>(ID, id)
            );
        } else {
            Database.get().insert(ABONNEE_TABLE,
                    String.valueOf(id),
                    name,
                    lastName,
                    street,
                    String.valueOf(houseNumber),
                    postalCode,
                    city
            );
        }

        for (Profile profile : profiles) {
            if (Database.get().contains(PROFILE_TABLE, FK_ID, new Where<>(FK_ID, id), new Where<>(PROFILE_NAME, profile.getName()))) {

                Database.get().update(PROFILE_TABLE, new Set[]{
                                new Set<>(PROFILE_NAME, profile.getName()),
                                new Set<>(ProfileTable.AGE, profile.getAge())
                        },
                        new Where<>(PROFILE_NAME, profile.getName()),
                        new Where<>(FK_ID, id)
                );
            } else {
                Database.get().insert(PROFILE_TABLE,
                        profile.getName(),
                        String.valueOf(profile.getAge()),
                        String.valueOf(id)
                );
            }
        }
    }
}
