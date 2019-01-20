package com.avans.handlers.program;

import com.avans.database.Database;
import com.avans.database.Set;
import com.avans.database.Where;
import com.avans.database.tables.EpisodeTable;
import com.sun.istack.internal.NotNull;

import java.util.UUID;

import static com.avans.database.Table.EPISODE_TABLE;
import static com.avans.database.tables.EpisodeTable.*;

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
    private String title;

    public Episode(@NotNull int episodeNumber, int duration, String title, int nextEpisode) {
        this.episodeNumber = episodeNumber;
        this.duration = duration;
        this.title = title;
        this.nextEpisode = nextEpisode;
    }

    /**
     * GETTERS
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

    public String getTitle() {
        return title;
    }

    /**
     * SETTERS
     */
    void setNextEpisode(UUID id, int nextEpisode) {
        this.nextEpisode = nextEpisode;

        Database.get().update(EPISODE_TABLE, new Set[]{
                        new Set<>(FK_EPISODE_NUMBER, nextEpisode),
                        new Set<>(FK_FK_ID, id.toString())
                },
                new Where<>(EPISODE_NUMBER, episodeNumber),
                new Where<>(EpisodeTable.FK_ID, id.toString())
        );
    }

    /**
     * BOOLEANS
     */
    public boolean hasNextEpisode() {
        return nextEpisode != -1;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", getEpisodeNumber(), getTitle());
    }
}
