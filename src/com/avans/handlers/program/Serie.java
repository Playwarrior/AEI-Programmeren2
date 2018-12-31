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

import static com.avans.database.Table.SERIE_TABLE;
import static com.avans.database.tables.SerieTable.*;
import static com.avans.database.tables.EpisodeTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/

public class Serie extends Program {

    private static Join EPISODE_JOIN = new Join(Join.Type.INNER_JOIN, SerieTable.ID, EpisodeTable.FK_ID);

    private List<Episode> episodes;

    private String genre;

    public Serie(int id, String title, int duration) {
        super(id, title, duration);

        this.genre = Database.get().get(SERIE_TABLE, SerieTable.GENRE, new Where<>(ID, this.getId()));
        this.episodes = new ArrayList<>();

        this.initEpisodes();
    }

    /**
     * GETTERS
     */
    public String getGenre() {
        return genre;
    }

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
    public void addEpisode(int episodeNumber) {
        this.episodes.add(new Episode(episodeNumber, (episodeNumber + 1)));
    }

    /**
     * INIT METHOD
     */
    private void initEpisodes() {
        List<Map<Column, Integer>> values = Database.get().getEntry(EPISODE_JOIN, new Column[]{EPISODE_NUMBER, FK_EPISODE_NUMBER}, new Where<>(SerieTable.ID, getId()));

        for (Map<Column, Integer> episodes : values) {
            this.episodes.add(new Episode(episodes.get(EPISODE_NUMBER), episodes.get(FK_EPISODE_NUMBER)));
        }
    }

    @Override
    public void serialize() {
        super.serialize();

        if (Database.get().contains(SERIE_TABLE, SerieTable.ID, new Where<>(SerieTable.ID, getId()))) {
            Database.get().update(SERIE_TABLE, new Set[]{
                            new Set<>(ID, getId()),
                            new Set<>(GENRE, genre)
                    },
                    new Where<>(ID, getId())
            );
        } else {
            Database.get().insert(SERIE_TABLE, String.valueOf(getId()), genre);
        }

        for (Episode ep : episodes) {
            if (Database.get().contains(EPISODE_JOIN, EPISODE_NUMBER, new Where<>(EPISODE_NUMBER, ep.getEpisodeNumber()))) {
                Database.get().update(EPISODE_TABLE, new Set[]{
                                new Set<>(EPISODE_NUMBER, ep.getEpisodeNumber()),
                                new Set<>(FK_EPISODE_NUMBER, ep.getNextEpisode()),
                                new Set<>(FK_ID, getId())
                        },
                        new Where<>(EPISODE_NUMBER, ep.getEpisodeNumber())
                );
            } else {
                Database.get().insert(EPISODE_TABLE, String.valueOf(ep.getEpisodeNumber()), String.valueOf(ep.getNextEpisode()), String.valueOf(getId()));
            }
        }
    }
}
