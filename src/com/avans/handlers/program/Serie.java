package com.avans.handlers.program;

import com.avans.database.Column;
import com.avans.database.Database;
import com.avans.database.Join;
import com.avans.database.Where;
import com.avans.database.Set;
import com.avans.database.tables.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.avans.database.tables.SerieTable.*;
import static com.avans.database.tables.EpisodeTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public class Serie extends Program {

    private static Join EPISODE_JOIN = new Join(Join.Type.INNER_JOIN, SerieTable.ID, EpisodeTable.FK_ID);

    private List<Episode> episodes;

    public Serie(UUID id) {
        super(id);

        this.initEpisodes();
    }

    public Serie(UUID id, String title, String genre) {
        super(id, title, genre);

        this.initEpisodes();

        this.serialize();
    }

    /**
     * GETTERS
     */
    public List<Episode> getEpisodes() {
        return episodes;
    }

    public Episode getEpisode(int episodeNumber) {
        for (Episode episode : episodes) {
            if (episode.getEpisodeNumber() == episodeNumber) {
                return episode;
            }
        }
        return null;
    }

    /**
     * addEpisode(int, int) METHOD
     */
    public boolean addEpisode(int episodeNumber, int duration, boolean nextEpisode) {
        if (!isEpisode(episodeNumber)) {
            int numberNextEpisode = nextEpisode ? (episodeNumber + 1) : -1;

            this.episodes.add(new Episode(episodeNumber, duration, numberNextEpisode));

            Database.get().insert(EPISODE_TABLE, String.valueOf(episodeNumber), String.valueOf(duration), numberNextEpisode != -1 ? String.valueOf(numberNextEpisode) : "NULL", String.valueOf(getId()));

            int previousEpisode = episodeNumber - 1;

            if (isEpisode(previousEpisode)) {
                Episode e = getEpisode(previousEpisode);

                if (e != null && !e.hasNextEpisode()) {
                    e.setNextEpisode(this.getId(), episodeNumber);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * BOOLEANS
     */
    public boolean isEpisode(int episodeNumber) {
        return getEpisode(episodeNumber) != null;
    }

    /**
     * initialization method
     */
    private void initEpisodes() {
        this.episodes = new ArrayList<>();

        List<Map<Column, Integer>> values = Database.get().getEntry(EPISODE_JOIN, new Column[]{EPISODE_NUMBER, DURATION, FK_EPISODE_NUMBER}, new Where<>(SerieTable.ID, getId()));

        for (Map<Column, Integer> episodes : values) {
            int nextEpisode = episodes.get(FK_EPISODE_NUMBER) == null ? -1 : episodes.get(FK_EPISODE_NUMBER);

            this.episodes.add(new Episode(episodes.get(EPISODE_NUMBER), episodes.get(DURATION), nextEpisode));
        }
    }

    @Override
    public void serialize() {
        super.serialize();

        for (Episode ep : episodes) {
            if (!Database.get().contains(EPISODE_JOIN, EPISODE_NUMBER, new Where<>(EPISODE_NUMBER, ep.getEpisodeNumber()))) {

                String nextEpisode;

                if (ep.hasNextEpisode()) {
                    nextEpisode = String.valueOf(ep.getNextEpisode());
                } else {
                    nextEpisode = "NULL";
                }

                Database.get().insert(EPISODE_TABLE, String.valueOf(ep.getEpisodeNumber()), String.valueOf(ep.getDuration()), nextEpisode, getId().toString());
            }
        }
    }
}
