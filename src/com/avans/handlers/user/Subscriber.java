package com.avans.handlers.user;

import com.avans.database.*;
import com.avans.database.Set;
import com.avans.database.tables.BehaviourTable;
import com.avans.database.tables.ProfileTable;
import com.avans.database.tables.ProgramTable;
import com.avans.handlers.Removable;
import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Program;
import com.avans.handlers.program.Serie;

import java.util.*;

import static com.avans.database.Table.SUBSCRIPTION_TABLE;
import static com.avans.database.tables.SubscriptionTable.*;
import static com.avans.database.tables.BehaviourTable.*;
import static com.avans.database.tables.ProfileTable.*;
import static com.avans.database.tables.ProfileTable.FK_ID;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public class Subscriber implements Removable {

    private static Join JOIN = new Join(Join.Type.INNER_JOIN, PROFILE_NAME, BehaviourTable.FK_PROFILE_NAME);

    public static String DEFAULT_PROFILE = "kids";

    private UUID id;
    private short houseNumber;
    private String name, lastName, street, postalCode, city;

    private List<Profile> profiles;

    public Subscriber(UUID id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;

        if (Database.get().contains(SUBSCRIPTION_TABLE, new Column[]{STREET, HOUSE_NUMBER, POSTCODE, CITY}, new Where<>(ID, id.toString()))) {

            Map<Column, Object> values = Database.get().getValues(SUBSCRIPTION_TABLE, new Column[]{STREET, HOUSE_NUMBER, POSTCODE, CITY}, new Where<>(ID, id.toString()));

            this.street = (String) values.get(STREET);
            this.houseNumber = (short) values.get(HOUSE_NUMBER);
            this.postalCode = (String) values.get(POSTCODE);
            this.city = (String) values.get(CITY);
        }

        this.profiles = new ArrayList<>();

        this.initProfiles();

        this.serialize();
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

        if (Database.get().contains(BEHAVIOUR_TABLE, FK_PROGRAM_ID, new Where<>(BehaviourTable.FK_ID, id.toString()), new Where<>(FK_PROFILE_NAME, profile.getName()), new Where<>(FK_PROGRAM_ID, movie.getId()))) {
            Database.get().update(BEHAVIOUR_TABLE, new Set[]{
                            new Set<>(CURRENT_DURATION, currentDuration)
                    },
                    new Where<>(FK_ID, id),
                    new Where<>(FK_PROFILE_NAME, profile.getName()),
                    new Where<>(FK_PROGRAM_ID, movie.getId())
            );
        } else {
            Database.get().insert(BEHAVIOUR_TABLE, profile.getName(), String.valueOf(id), String.valueOf(movie.getId()), "NULL", String.valueOf(currentDuration));
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

        if (Database.get().contains(BEHAVIOUR_TABLE, FK_PROGRAM_ID, new Where<>(BehaviourTable.FK_ID, id.toString()), new Where<>(BehaviourTable.FK_PROFILE_NAME, profile.getName()), new Where<>(BehaviourTable.FK_PROGRAM_ID, serie.getId()), new Where<>(FK_EPISODE_NUMBER, episode))) {
            Database.get().update(BEHAVIOUR_TABLE, new Set[]{
                            new Set<>(BehaviourTable.CURRENT_DURATION, currentDuration)
                    },
                    new Where<>(BehaviourTable.FK_ID, id.toString()),
                    new Where<>(BehaviourTable.FK_PROFILE_NAME, profile.getName()),
                    new Where<>(BehaviourTable.FK_PROGRAM_ID, serie.getId()),
                    new Where<>(BehaviourTable.FK_EPISODE_NUMBER, episode)
            );
        } else {
            Database.get().insert(BEHAVIOUR_TABLE, profile.getName(), id.toString(), String.valueOf(serie.getId()), String.valueOf(episode), String.valueOf(currentDuration));
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
        if (street == null || street.equalsIgnoreCase("null"))
            return null;

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

    public UUID getId() {
        return id;
    }

    public int getCurrentMinute(Profile profile, Program program) {
        if (!hasSeenProgram(profile, program))
            return 0;

        return Database.get().get(BEHAVIOUR_TABLE, BehaviourTable.CURRENT_DURATION,
                new Where<>(BehaviourTable.FK_ID, id.toString()),
                new Where<>(BehaviourTable.FK_PROFILE_NAME, profile.getName()),
                new Where<>(BehaviourTable.FK_PROGRAM_ID, program.getId())
        );
    }

    public int getCurrentEpisode(Profile p, Serie s) {
        if (!hasSeenProgram(p, s))
            return 0;


        return Database.get().get(BEHAVIOUR_TABLE, BehaviourTable.FK_EPISODE_NUMBER,
                new Where<>(BehaviourTable.FK_ID, id.toString()),
                new Where<>(BehaviourTable.FK_PROFILE_NAME, p.getName()),
                new Where<>(BehaviourTable.FK_PROGRAM_ID, s.getId())
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
                BehaviourTable.FK_PROGRAM_ID,
                new Where<>(BehaviourTable.FK_ID, id.toString()),
                new Where<>(BehaviourTable.FK_PROFILE_NAME, p.getName()),
                new Where<>(BehaviourTable.FK_PROGRAM_ID, program.getId())
        );
    }

    public boolean hasSeenProgram(Profile p, Serie serie, int episode) {
        if (!profiles.contains(p))
            return false;

        return Database.get().contains(JOIN,
                FK_PROGRAM_ID,
                new Where<>(BehaviourTable.FK_ID, id.toString()),
                new Where<>(BehaviourTable.FK_PROFILE_NAME, p.getName()),
                new Where<>(FK_PROGRAM_ID, serie.getId()),
                new Where<>(BehaviourTable.FK_EPISODE_NUMBER, episode)
        );
    }

    public boolean hasSeenProgram(Program program) {
        for (Profile p : profiles) {
            if (hasSeenProgram(p, program)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSeenProgram(Serie serie, int episode) {
        for (Profile p : profiles) {
            if (hasSeenProgram(p, serie, episode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * delete() method
     */
    @Override
    public boolean delete() {
        Database.get().delete(SUBSCRIPTION_TABLE, new Where<>(ID, id.toString()));
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
        if (Database.get().getCount(Table.PROFILE_TABLE, new Where<>(FK_ID, this.id.toString())) != 0) {
            List<Map<Column, Object>> values = Database.get().getEntry(PROFILE_TABLE, new Where<>(FK_ID, this.getId()));

            for (Map<Column, Object> v : values) {
                String name = String.valueOf(v.get(PROFILE_NAME));
                int age = Integer.parseInt(String.valueOf(v.get(AGE)));

                this.profiles.add(new Profile(name, age));
            }
        } else {
            this.profiles.add(new Profile(DEFAULT_PROFILE, 15));
        }
    }

    /**
     * serialize() method
     */
    public void serialize() {
        if (Database.get().contains(SUBSCRIPTION_TABLE, ID, new Where<>(ID, id))) {

            Database.get().update(SUBSCRIPTION_TABLE, new Set[]{
                            new Set<>(STREET, street),
                            new Set<>(HOUSE_NUMBER, houseNumber),
                            new Set<>(POSTCODE, postalCode),
                            new Set<>(CITY, city)
                    },
                    new Where<>(ID, id.toString())
            );
        } else {
            Database.get().insert(SUBSCRIPTION_TABLE,
                    id.toString(),
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
                        new Where<>(FK_ID, id.toString())
                );
            } else {
                Database.get().insert(PROFILE_TABLE,
                        profile.getName(),
                        String.valueOf(profile.getAge()),
                        id.toString()
                );
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, lastName);
    }
}
