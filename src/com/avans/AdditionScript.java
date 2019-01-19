package com.avans;

/*
    Created By Robin Egberts On 1/19/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Subscriber;

import static com.avans.handlers.user.Subscriber.DEFAULT_PROFILE;

public class AdditionScript {


    public static void addData(){
        Subscriber s = NFS.getHandler().addSubscriber("Robin", "Egberts");
        s.setAdress("Gripvelden", (short) 82, "4707ZG", "Roosendaal");

        Movie theGodFather = NFS.getHandler().addMovie("GodFather", 120, "Criminals", 18);

        Serie theFlash = NFS.getHandler().addSerie("The Flash", "Superheroes");

        NFS.getHandler().addEpisode(theFlash.getTitle(), 1, 41, "A new Begging", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 2, 41, "A new Begging", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 3, 41, "A new Begging", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 4, 41, "A new Begging", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 5, 41, "A new Begging", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 6, 41, "A new Begging", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 7, 41, "A new Begging", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 8, 41, "A new Begging", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 9, 41, "A new Begging", false);



        s.watch(s.getProfile(DEFAULT_PROFILE), theFlash, 1, 40);

        s.watch(s.getProfile(DEFAULT_PROFILE), theGodFather, 120);
    }

}
