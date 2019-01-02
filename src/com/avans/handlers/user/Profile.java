package com.avans.handlers.user;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public class Profile {

    private String name;
    private int age;

    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * GETTERS
     */
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
