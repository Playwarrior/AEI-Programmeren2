package com.avans;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/
public enum ScreenState {

    HOMEPAGE("Homepage.fxml"),
    WATCHTIME("WatchTime.fxml"),
    S_WATCHTIME("SubscriberWatchTime.fxml"),
    M_WATCHED("MoviesWatched.fxml"),
    LONGEST_MOVIE("LongestMovie.fxml"),
    ACCOUNTS("Accounts.fxml"),
    COMPLETED_MOVIES("CompletedMovies.fxml");

    private String fileLocation;

    ScreenState(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFile() {
        return "/GUI/" + fileLocation;
    }
}
