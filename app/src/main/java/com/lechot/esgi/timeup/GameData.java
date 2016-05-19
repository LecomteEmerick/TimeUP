package com.lechot.esgi.timeup;

import java.util.ArrayList;

/**
 * Created by Emerick Lecomte on 19/05/2016.
 */
public class GameData {

    public static ArrayList<Player> TeamA;
    public static int TeamAScore;
    public static ArrayList<Player> TeamB;
    public static int TeamBScore;
    public enum CATEGORIES { Animals, History, Games, Celebrety }

    public static CATEGORIES SelectedCategories;

    public static String[] AnimalsWords = new String[] { "Chat", "Chien", "Poisson", "Girafe", "Dauphin", "Singe", "Pivert", "Moineau", "Pigeons", "Poulet" };
    public static String[] HistoryWords = new String[] { "Hitler", "Charlemagne", "Charles Martel", "Jacques Chirac", "Napolean", "Louis XVI", "Lioret" };
    public static String[] GamesWords  = new String[] { "Darks Souls", "Minecraft", "Uncharted", "Tomb Raider", "Pokemon", "Mario", "Kirby"};
    public static String[] CelebretyWords  = new String[] {"Mickael Jackson", "Madona", "Justin bieber", "Chuck Norris", "Emma Watson"};
}
