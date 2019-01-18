package com.avans.handlers.program;

import com.avans.database.Database;
import com.avans.database.Set;
import com.avans.database.Where;
import com.sun.istack.internal.NotNull;

import static com.avans.database.Table.EPISODE_TABLE;
import static com.avans.database.tables.EpisodeTable.EPISODE_NUMBER;
import static com.avans.database.tables.EpisodeTable.FK_EPISODE_NUMBER;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

/**
 * TODO: Need to add title for Episode
 */

public class Episode {

    private int episodeNumber;
    private int duration;
    private int nextEpisode;

    public Episode(@NotNull int episodeNumber, int duration, int nextEpisode) {
        this.episodeNumber = episodeNumber;
        this.duration = duration;
        this.nextEpisode = nextEpisode;
    }

    /**
        GETTERS
     */
    public int getDuration() {
        return duration;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public int getNextEpisode() {
        return nextEpisode;
    }

    /**
        SETTERS
     */
    public void setNextEpisode(int nextEpisode){
        this.nextEpisode = nextEpisode;

        Database.get().update(EPISODE_TABLE, new Set<>(FK_EPISODE_NUMBER, nextEpisode), new Where<>(EPISODE_NUMBER, episodeNumber));
    }

    /**
        BOOLEANS
     */
    public boolean hasNextEpisode(){
        return nextEpisode != -1;
    }
}
