package com.avans.util;

/*
    Created By Robin Egberts On 1/6/2019
    Copyrighted By OrbitMines ©2019
*/
public enum ScreenState {

    HOMEPAGE("Homepage.fxml", "Homepage"),
    WATCHTIME("WatchTime.fxml", "WatchTime"),
    S_WATCHTIME("SubscriberWatchTime.fxml", "Subscriber WatchTime"),
    M_WATCHED("MoviesWatched.fxml", "Movies Watched"),
    LONGEST_MOVIE("LongestMovie.fxml", "Longest Movie"),
    ACCOUNTS("Accounts.fxml", "Accounts"),
    COMPLETED_MOVIES("CompletedMovies.fxml", "Completed Movies");

    private String fileLocation;
    private String title;

    ScreenState(String fileLocation, String title) {
        this.fileLocation = fileLocation;
        this.title = title;
    }

    public String getFile() {
        return "src//com//avans//gui//" + fileLocation;
    }

    public String getTitle() {
        return String.format("Netflix Statistics - %s", title);
    }
}
