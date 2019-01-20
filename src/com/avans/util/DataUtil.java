package com.avans.util;

import com.avans.NFS;
import com.avans.handlers.program.Episode;
import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Profile;
import com.avans.handlers.user.Subscriber;

import java.util.ArrayList;
import java.util.List;

/*
    Created By Robin Egberts On 1/1/2019
    Copyrighted By OrbitMines ©2019
*/
public class DataUtil {

    public static double getPercentageEpisode(Serie serie, int episode) {
        int precentage = 0;
        int subscribers = 0;

        for (Subscriber s : NFS.getHandler().getSubscribers()) {
            precentage += getPercentageEpisode(s, serie, episode);
            subscribers++;
        }
        return (double) precentage / subscribers;
    }

    public static double getPercentageEpisode(Subscriber s, Serie serie, int episode) {
        int minutes = 0;
        int profiles = s.getProfiles().size();

        Episode e = serie.getEpisode(episode);

        for (Profile profile : s.getProfiles()) {
            if (s.hasSeenProgram(profile, serie, episode)) {
                int currentEpisode = s.getCurrentEpisode(profile, serie);

                if (currentEpisode == episode)
                    minutes += s.getCurrentMinute(profile, serie);

                else if (currentEpisode > episode)
                    minutes += e.getDuration();

            }
        }

        return (double) (((minutes / profiles)) * 100) / e.getDuration();
    }

    public static List<Subscriber> getSubscribersByCount(int profileCount) {
        List<Subscriber> subscribers = new ArrayList<>();

        for (Subscriber s : NFS.getHandler().getSubscribers()) {
            if (s.getProfiles().size() == profileCount) {
                subscribers.add(s);
            }
        }

        return subscribers;
    }

    public static Movie getLongestMovie(int ageRestriction) {
        Movie movie = null;

        for (Movie m : NFS.getHandler().getPrograms(Movie.class)) {
            if (m.getAgeIndication() <= ageRestriction) {
                if (movie == null || movie.getDuration() < m.getDuration()) {
                    movie = m;
                }
            }
        }
        return movie;
    }

    public static List<Subscriber> getSubscribersByMovie(Movie movie) {
        List<Subscriber> seenSubscribers = new ArrayList<>();

        for (Subscriber s : NFS.getHandler().getSubscribers()) {
            if (s.hasSeenProgram(movie)) {
                seenSubscribers.add(s);
            }
        }

        return seenSubscribers;
    }

    public static List<Movie> getMoviesBySubscriber(Subscriber s) {
        List<Movie> movies = new ArrayList<>();

        for (Movie m : NFS.getHandler().getPrograms(Movie.class)) {
            if (s.hasSeenProgram(m)) {
                movies.add(m);
            }
        }

        return movies;
    }
}
