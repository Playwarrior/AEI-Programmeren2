package com.avans.handlers.user;

import com.avans.database.*;
import com.avans.database.tables.BehaviourTable;
import com.avans.database.tables.ProfileTable;
import com.avans.handlers.program.Program;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.avans.database.Table.ABONNEE_TABLE;
import static com.avans.database.tables.AbonneeTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public class Subscriber {

    private static Join JOIN = new Join(Join.Type.INNER_JOIN, ProfileTable.PROFILE_NAME, BehaviourTable.FK_PROFILE_NAME);

    private int id, houseNumber;
    private String name, lastName, street, postalCode, city;

    private List<Profile> profiles;

    public Subscriber(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;

        this.street = Database.get().get(ABONNEE_TABLE, STREET, new Where<>(ID, this.id));
        this.houseNumber = Database.get().get(ABONNEE_TABLE, HOUSE_NUMBER, new Where<>(ID, this.id));
        this.postalCode = Database.get().get(ABONNEE_TABLE, POSTCODE, new Where<>(ID, this.id));
        this.city = Database.get().get(ABONNEE_TABLE, CITY, new Where<>(ID, this.id));

        this.profiles = new ArrayList<>();

        this.initProfiles();
    }

    /**
     * SETTERS
     */
    public void setAdress(String street, int houseNumber, String postalCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public boolean addProfile(String name, int age){
        if(profiles.size() > 5)
            return false;

        if(isProfile(name))
            return false;

        profiles.add(new Profile(name, age));

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

    /**
     * BOOLEANS
     */
    public boolean isProfile(String name) {
        return getProfile(name) != null;
    }

    public boolean hasSeenProgram(Profile p, Program program) {
        if(!profiles.contains(p))
            return false;

        return Database.get().contains(JOIN,
                BehaviourTable.FK_PROGRAM_ID,
                new Where<>(BehaviourTable.FK_ID, id),
                new Where<>(BehaviourTable.FK_PROFILE_NAME, p.getName()),
                new Where<>(BehaviourTable.FK_PROGRAM_ID, program.getId())
        );
    }

    /**
     * initProfiles() method
     */
    private void initProfiles() {
        if (Database.get().getCount(Table.PROFILE_TABLE, new Where<>(ID, this.id)) != 0) {
            List<Map<Column, Object>> values = Database.get().getEntry(PROFILE_TABLE, new Where<>(ID, this.getId()));

            for (Map<Column, Object> v : values) {
                String name = String.valueOf(v.get(ProfileTable.PROFILE_NAME));
                int age = Integer.parseInt(String.valueOf(v.get(ProfileTable.PROFILE_NAME)));

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
                            new Set<>(ID, id),
                            new Set<>(NAME, name),
                            new Set<>(LAST_NAME, lastName),
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
            if (Database.get().contains(PROFILE_TABLE, ProfileTable.FK_ID, new Where<>(ProfileTable.FK_ID, id), new Where<>(ProfileTable.PROFILE_NAME, profile.getName()))) {

                Database.get().update(PROFILE_TABLE, new Set[]{
                                new Set<>(ProfileTable.PROFILE_NAME, profile.getName()),
                                new Set<>(ProfileTable.FK_ID, id),
                                new Set<>(ProfileTable.AGE, profile.getAge())
                        },
                        new Where<>(ProfileTable.PROFILE_NAME, profile.getName()),
                        new Where<>(ProfileTable.FK_ID, id)
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
