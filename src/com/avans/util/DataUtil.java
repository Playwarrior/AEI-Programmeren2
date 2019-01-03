package com.avans.util;

/*
    Created By Robin Egberts On 1/1/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.NFS;
import com.avans.handlers.program.Episode;
import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Profile;
import com.avans.handlers.user.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public static double getPrecentageEpisode(Serie serie, int episode) {
        int precentage = 0;
        int subscribers = 0;

        for (Subscriber s : NFS.getHandler().getSubscribers()) {
            precentage += getPrecentageEpisode(s, serie, episode);
            subscribers++;
        }
        return (double) precentage / subscribers;
    }

    public static double getPrecentageEpisode(Subscriber s, Serie serie, int episode) {
        int minutes = 0;
        int profiles = 0;

        Episode e = serie.getEpisode(episode);

        for (Profile profile : s.getProfiles()) {
            if (s.hasSeenProgram(profile, serie, episode)) {
                minutes += s.getCurrentMinute(profile, serie);
                profiles++;
            }
        }
        return (double) ((minutes / profiles) / e.getDuration()) * 100;
    }

    public static List<Subscriber> getSubscribers(int profileCount) {
        List<Subscriber> subscribers = new ArrayList<>();

        for (Subscriber s : NFS.getHandler().getSubscribers()) {
            if (s.getProfiles().size() == profileCount) {
                subscribers.add(s);
            }
        }

        return subscribers;
    }

    public static Movie getLongestMovie(int ageRestriction){
        Movie movie = null;

        for(Movie m : NFS.getHandler().getPrograms(Movie.class)){
            if(m.getAgeIndication() <= ageRestriction){
                if(movie == null || movie.getDuration() < m.getDuration()){
                    movie = m;
                }
            }
        }
        return movie;
    }
}
