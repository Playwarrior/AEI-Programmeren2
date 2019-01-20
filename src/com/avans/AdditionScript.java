package com.avans;

/*
    Created By Robin Egberts On 1/19/2019
    Copyrighted By OrbitMines ©2019
*/

import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Subscriber;

import static com.avans.handlers.user.Subscriber.DEFAULT_PROFILE;

public class AdditionScript {


    public static void addData(){
        Subscriber a = NFS.getHandler().addSubscriber("Robin", "Egberts");
        a.setAdress("Gripvelden", (short) 82, "4707ZG", "Roosendaal");
        a.addProfile("PROFILE1", 16);

        Subscriber b = NFS.getHandler().addSubscriber("Bryan", "Kho");
        b.setAdress("Steenweg", (short) 42, "4203GT", "Roermond");
        b.addProfile("PROFILE2", 18);

        Subscriber c = NFS.getHandler().addSubscriber("Jur", "Nagtzaam");
        c.setAdress("Titulaerlaan", (short) 9, "4841GS", "Prinsenbeek");
        c.addProfile("PROFILE3", 12);

        Subscriber d = NFS.getHandler().addSubscriber("Niek", "Hoppenbrouwers");
        d.setAdress("Vincent van Goghlaan", (short) 32, "7741DN", "Zundert");
        d.addProfile("PROFILE4", 16);

        Subscriber e = NFS.getHandler().addSubscriber("Sander", "van Zundert");
        e.setAdress("Hudsonstraat", (short) 201, "4758GB", "Breda");
        e.addProfile("PROFILE5", 23);

        Subscriber f = NFS.getHandler().addSubscriber("Willem", "Jansen");
        f.setAdress("Jokerlaan", (short) 49, "3141PI", "Bavel");
        f.addProfile("PROFILE6", 18);

        Subscriber g = NFS.getHandler().addSubscriber("Glenn", "Jacobs");
        g.setAdress("Pastoor van Vessemstraat", (short) 12, "6352KF", "Zundert");
        g.addProfile("PROFILE7", 21);

        Subscriber h = NFS.getHandler().addSubscriber("Avinaash", "Hirasingh");
        h.setAdress("Reeperf", (short) 17, "3643JH", "Breda");
        g.addProfile("PROFILE8", 23);

        Subscriber i = NFS.getHandler().addSubscriber("Jeroen", "Vonk");
        i.setAdress("Hartweg", (short) 3, "7954GV", "Breda");
        i.addProfile("PROFILE9", 22);

        Subscriber j = NFS.getHandler().addSubscriber("Harvey", "van Veltom");
        j.setAdress("Reepstraat", (short) 291, "3252HG", "Rijsbergen");
        j.addProfile("PROFILE10", 37);

        Movie theGodfather = NFS.getHandler().addMovie("The Godfather", 177, "Crime", 18);
        Movie aquaman = NFS.getHandler().addMovie("Aquaman", 143, "Action", 12);
        Movie aQuietPlace = NFS.getHandler().addMovie("A Quiet Place", 90, "Thriller", 18);
        Movie avengersInfinityWar = NFS.getHandler().addMovie("Avengers: Infinity War", 149, "Action", 12);
        Movie ferrisBueller = NFS.getHandler().addMovie("Ferris Bueller's Day Off", 103, "Comedy", 12);
        Movie groundhogDay = NFS.getHandler().addMovie("Groundhog Day", 101, "Comedy", 12);
        Movie jurassicWorldFallenKingdom = NFS.getHandler().addMovie("Jurassic World: Fallen Kingdom", 128, "Action", 12);
        Movie toyStory3 = NFS.getHandler().addMovie("Toy Story 3", 103, "Family", 6);
        Movie cars = NFS.getHandler().addMovie("Cars", 116, "Family", 6);
        Movie scarface = NFS.getHandler().addMovie("Scarface", 170, "Crime", 18);

        Serie theFlash = NFS.getHandler().addSerie("The Flash", "Superheroes");

        NFS.getHandler().addEpisode(theFlash.getTitle(), 1, 41, "A New Beginning", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 2, 41, "The Second One", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 3, 41, "Triple Threat", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 4, 41, "Force of Nature", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 5, 41, "Lands of Fyfe", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 6, 41, "Down With The Sickness", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 7, 41, "Dan Severn", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 8, 41, "Hateful Eight", false);
        NFS.getHandler().addEpisode(theFlash.getTitle(), 9, 41, "Night and Day", false);

        Serie breakingBad = NFS.getHandler().addSerie("Breaking Bad", "Crime");

        NFS.getHandler().addEpisode(breakingBad.getTitle(), 1, 58, "Pilot", false);
        NFS.getHandler().addEpisode(breakingBad.getTitle(), 2, 48, "Cat's in the Bag...", false);
        NFS.getHandler().addEpisode(breakingBad.getTitle(), 3, 48, "...And the Bag's in the River", false);
        NFS.getHandler().addEpisode(breakingBad.getTitle(), 4, 48, "Cancer Man", false);
        NFS.getHandler().addEpisode(breakingBad.getTitle(), 5, 48, "Gray Matter", false);
        NFS.getHandler().addEpisode(breakingBad.getTitle(), 6, 47, "Crazy Handful of Nothin'", false);
        NFS.getHandler().addEpisode(breakingBad.getTitle(), 7, 47, "A No-Rough-Stuff-Type Deal", false);

        Serie boJackHorseman = NFS.getHandler().addSerie("BoJack Horseman", "Animation");

        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 1, 21, "BoJack Horseman: The BoJack Horseman Story, Chapter One", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 2, 21, "BoJack Hates the Troops", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 3, 21, "Prickly-Muffin", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 4, 21, "Zoës and Zeldas", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 5, 21, "Live Fast, Diane Nguyen", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 6, 21, "Our A-Story is a D Story", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 7, 21, "Say Anything", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 8, 21, "The Telescope", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 9, 21, "Horse Majeure", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 10, 21, "One Trick Pony", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 11, 21, "Downer Ending", false);
        NFS.getHandler().addEpisode(boJackHorseman.getTitle(), 12, 21, "Later", false);

        Serie rickAndMorty = NFS.getHandler().addSerie("Rick and Morty", "Animation");

        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 1, 21, "Pilot", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 2, 21, "Lawnmower Dog", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 3, 21, "Anatomy Park", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 4, 21, "M. Night Shaym-Aliens!", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 5, 21, "Meeseeks and Destroy", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 6, 21, "Rick Potion #9", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 7, 21, "Raising Gazorpazorp", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 8, 21, "Rixty Minutes", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 9, 21, "Something Ricked This Way Comes", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 10, 21, "Close Rick-counters of the Rick Kind", false);
        NFS.getHandler().addEpisode(rickAndMorty.getTitle(), 11, 21, "Ricksy Business", false);

        Serie narcos = NFS.getHandler().addSerie("Narcos", "Crime");

        NFS.getHandler().addEpisode(narcos.getTitle(), 1, 51, "Descenso", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 2, 51, "The Sword of Simôn Bolivar", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 3, 51, "The Men of Always", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 4, 51, "The Palace in Flames", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 5, 51, "There Will Be a Future", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 6, 51, "Explosivos", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 7, 51, "You Will Cry Tears of Blood", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 8, 51, "La Gran Mentira", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 9, 51, "La Catedral", false);
        NFS.getHandler().addEpisode(narcos.getTitle(), 10, 51, "Despegue", false);

        Serie friends = NFS.getHandler().addSerie("Friends", "Comedy");

        NFS.getHandler().addEpisode(friends.getTitle(), 1, 21, "The Pilot", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 2, 21, "The One with the Sonogram at the End", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 3, 21, "The One with the Thumb", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 4, 21, "The One with George Stephanopoulos", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 5, 21, "The One with the East German Laundry Detergent", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 6, 21, "The One with the Butt", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 7, 21, "The One with the Blackout", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 8, 21, "The One Where Nana Dies Twice", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 9, 21, "The One Where Underdog Gets Away", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 10, 21, "The One with the Monkey", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 11, 21, "The One with Mrs. Bing", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 12, 21, "The One with the Dozen Lasagnas", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 13, 21, "The One with the Boobies", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 14, 21, "The One with the Candy Hearts", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 15, 21, "The One with the Stoned Guy", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 16, 21, "The One with Two Parts: Part 1", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 17, 21, "The One with Two Parts: Part 2", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 18, 21, "The One with All the Poker", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 19, 21, "The One Where the Monkey Gets Away", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 20, 21, "The One with the Evil Orthodontist", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 21, 21, "The One with the Fake Monica", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 22, 21, "The One with the Ick Factor", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 23, 21, "The One with the Birth", false);
        NFS.getHandler().addEpisode(friends.getTitle(), 24, 21, "The One Where Rachel Finds Out", false);

        Serie houseOfCards = NFS.getHandler().addSerie("House of Cards", "Drama");

        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 1, 49, "Chapter 1", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 2, 49, "Chapter 2", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 3, 49, "Chapter 3", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 4, 49, "Chapter 4", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 5, 49, "Chapter 5", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 6, 49, "Chapter 6", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 7, 49, "Chapter 7", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 8, 49, "Chapter 8", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 9, 49, "Chapter 9", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 10, 49, "Chapter 10", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 11, 49, "Chapter 11", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 12, 49, "Chapter 12", false);
        NFS.getHandler().addEpisode(houseOfCards.getTitle(), 13, 49, "Chapter 13", false);

        Serie blackMirror = NFS.getHandler().addSerie("Black Mirror", "Science Fiction");

        NFS.getHandler().addEpisode(blackMirror.getTitle(), 1, 52, "The National Anthem", false);
        NFS.getHandler().addEpisode(blackMirror.getTitle(), 2, 52, "Fifteen Million Merits", false);
        NFS.getHandler().addEpisode(blackMirror.getTitle(), 3, 52, "The Entire History of You", false);
        NFS.getHandler().addEpisode(blackMirror.getTitle(), 4, 52, "Be Right Back", false);
        NFS.getHandler().addEpisode(blackMirror.getTitle(), 5, 52, "White Bear", false);
        NFS.getHandler().addEpisode(blackMirror.getTitle(), 6, 52, "The Waldo Moment", false);
        NFS.getHandler().addEpisode(blackMirror.getTitle(), 7, 74, "White Christmas", false);

        a.watch(a.getProfile("PROFILE5"), theFlash, 1, 40);
        a.watch(a.getProfile("PROFILE5"), theGodfather, 120);

        b.watch(b.getProfile("PROFILE2"), boJackHorseman, 2, 17);
        b.watch(b.getProfile("PROFILE2"), jurassicWorldFallenKingdom, 68);

        c.watch(c.getProfile("PROFILE3"), friends, 7, 12);
        c.watch(c.getProfile("PROFILE3"), cars, 43);

        d.watch(d.getProfile("PROFILE4"), friends, 7, 12);
        d.watch(d.getProfile("PROFILE4"), cars, 43);

        e.watch(e.getProfile("PROFILE5"), rickAndMorty, 1, 17);
        e.watch(e.getProfile("PROFILE5"), scarface, 120);

        f.watch(f.getProfile("PROFILE6"), narcos, 1, 40);
        f.watch(f.getProfile("PROFILE6"), avengersInfinityWar, 23);
        f.watch(f.getProfile("PROFILE6"), theGodfather, 42);

        g.watch(g.getProfile("PROFILE7"), houseOfCards, 1, 23);
        g.watch(g.getProfile("PROFILE7"), aQuietPlace, 76);

        h.watch(h.getProfile("PROFILE8"), theFlash, 1, 20);
        h.watch(h.getProfile("PROFILE8"), breakingBad, 5, 40);
        h.watch(h.getProfile("PROFILE8"), friends, 17, 17);
        h.watch(h.getProfile("PROFILE8"), theGodfather, 95);

        i.watch(i.getProfile("PROFILE9"), friends, 17, 17);
        i.watch(i.getProfile("PROFILE9"), avengersInfinityWar, 84);

        j.watch(j.getProfile("PROFILE10"), blackMirror, 4, 39);
        j.watch(j.getProfile("PROFILE10"), theGodfather, 17);
    }

}
