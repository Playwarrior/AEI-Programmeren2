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

    public Episode getEpisode(String title){
        for(Episode e : episodes){
            if(e.getTitle().equalsIgnoreCase(title)){
                return e;
            }
        }
        return null;
    }

    /**
     * addEpisode(int, int) METHOD
     */
    public boolean addEpisode(int episodeNumber, int duration, String title, boolean nextEpisode) {
        if (!isEpisode(episodeNumber)) {
            int numberNextEpisode = nextEpisode ? (episodeNumber + 1) : -1;

            this.episodes.add(new Episode(episodeNumber, duration, title, numberNextEpisode));

            Database.get().insert(EPISODE_TABLE,
                    getId().toString(),
                    String.valueOf(episodeNumber),
                    String.valueOf(duration),
                    title,
                    numberNextEpisode != -1 ? getId().toString() : "NULL",
                    numberNextEpisode != -1 ? String.valueOf(numberNextEpisode) : "NULL"
            );

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

        List<Map<Column, Object>> values = Database.get().getEntry(EPISODE_JOIN, new Column[]{EPISODE_NUMBER, DURATION, TITLE, FK_EPISODE_NUMBER}, new Where<>(SerieTable.ID, getId()));

        for (Map<Column, Object> episodes : values) {
            int nextEpisode = episodes.get(FK_EPISODE_NUMBER) == null ? -1 : (int) episodes.get(FK_EPISODE_NUMBER);

            this.episodes.add(new Episode((int) episodes.get(EPISODE_NUMBER), (int) episodes.get(DURATION), (String) episodes.get(TITLE), nextEpisode));
        }
    }

    @Override
    public void serialize() {
        super.serialize();

        if(!Database.get().containKey(ID, getId().toString())){
            Database.get().insert(SERIE_TABLE, getId().toString());
        }

        for (Episode ep : episodes) {
            if (!Database.get().contains(EPISODE_JOIN, EPISODE_NUMBER, new Where<>(EPISODE_NUMBER, ep.getEpisodeNumber()))) {

                String nextEpisode;
                String id;

                if (ep.hasNextEpisode()) {
                    nextEpisode = String.valueOf(ep.getNextEpisode());
                    id = getId().toString();
                } else {
                    nextEpisode = "NULL";
                    id = "NULL";
                }

                Database.get().insert(EPISODE_TABLE, getId().toString(), String.valueOf(ep.getEpisodeNumber()), String.valueOf(ep.getDuration()), String.valueOf(ep.getTitle()), id, nextEpisode);
            }
        }
    }
}
