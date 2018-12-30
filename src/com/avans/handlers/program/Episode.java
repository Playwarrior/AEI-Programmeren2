package com.avans.handlers.program;

import com.sun.istack.internal.NotNull;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/
public class Episode {

    private int episodeNumber;
    private int nextEpisode;

    public Episode(@NotNull int episodeNumber, int nextEpisode){
        this.episodeNumber = episodeNumber;
        this.nextEpisode = nextEpisode;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public int getNextEpisode() {
        return nextEpisode;
    }
}
