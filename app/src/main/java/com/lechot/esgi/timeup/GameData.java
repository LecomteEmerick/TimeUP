package com.lechot.esgi.timeup;

import java.util.ArrayList;
import com.lechot.esgi.timeup.Player;

/**
 * Created by Emerick Lecomte on 19/05/2016.
 */
/*
    Ouch, ça pique.
    La méthode propre consiste à utiliser le pattern singleton.
    http://thecodersbreakfast.net/index.php?post/2008/02/25/26-de-la-bonne-implementation-du-singleton-en-java
 */
public class GameData {

    public static int TimerValue = 30000;

    public static ArrayList<Player> PlayerList = new ArrayList<Player>();
    public static ArrayList<Player> TeamA = new ArrayList<Player>();
    public static int TeamAScore=0;
    public static ArrayList<Player> TeamB = new ArrayList<Player>();
    public static int TeamBScore=0;
    public enum CATEGORIES { Animals, History, Games, Celebrety, Null }

    public static CATEGORIES SelectedCategories = CATEGORIES.Null;

    public static String[] AnimalsWords = new String[] { "Chat", "Chien", "Poisson", "Girafe", "Dauphin", "Singe", "Pivert", "Moineau", "Pigeons", "Poulet", "Serpent", "Kangouroux" };
    public static String[] HistoryWords = new String[] { "Hitler", "Charlemagne", "Charles Martel", "Jacques Chirac", "Napolean", "Louis XVI", "Joseph Staline", "Robespierre", "Cléopatre", "César" };
    public static String[] GamesWords  = new String[] { "Darks Souls", "Minecraft", "Uncharted", "Tomb Raider", "Pokemon", "Mario", "Kirby"};
    public static String[] CelebretyWords  = new String[] {"Mickael Jackson", "Madona", "Justin bieber", "Chuck Norris", "Emma Watson"};
}
