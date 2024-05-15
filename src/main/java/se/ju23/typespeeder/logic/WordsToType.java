package se.ju23.typespeeder.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordsToType {

    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

//    // Metod för att färglägga en array av ord
//    public static String[] colorizeWords(String[] words, String colorCode, String resetCode) {
//        String[] coloredWords = new String[words.length];
//        for (int i = 0; i < words.length; i++) {
//            coloredWords[i] = colorCode + words[i] + resetCode;
//        }
//        return coloredWords;
//    }

    // Hårdkodade färglagda ordlistor
    public static final String[] words = {
            CYAN + "Befolkning" + RESET, CYAN + "Förklaring" + RESET, CYAN + "Myndighet" + RESET,
            CYAN + "Bevittna" + RESET, CYAN + "Känslomässig" + RESET, CYAN + "Förutsättning" + RESET,
            CYAN + "Avdelning" + RESET, CYAN + "Förbindelse" + RESET, CYAN + "Avbrytare" + RESET,
            CYAN + "Motsvarighet" + RESET, CYAN + "Försäkring" + RESET, CYAN + "Svårigheter" + RESET,
            CYAN + "Arbetsplats" + RESET, CYAN + "Trafikljus" + RESET, CYAN + "Sammansättning" + RESET,
            CYAN + "Överenskommelse" + RESET, CYAN + "Tjänsteman" + RESET, CYAN + "Återställa" + RESET,
            CYAN + "Konditori" + RESET, CYAN + "Invandring" + RESET, CYAN + "Förnyelsebar" + RESET,
            CYAN + "Förpackning" + RESET, CYAN + "Föroreningar" + RESET, CYAN + "Befolkad" + RESET,
            CYAN + "Framsteg" + RESET, CYAN + "Återvinning" + RESET, CYAN + "Avstängning" + RESET,
            CYAN + "Krånglig" + RESET, CYAN + "Kvalitetskontroll" + RESET, CYAN + "Användarvänlig" + RESET
    };

    public static final String[] englishWords = {
            CYAN + "Population" + RESET, CYAN + "Explanation" + RESET, CYAN + "Authority" + RESET,
            CYAN + "Witness" + RESET, CYAN + "Emotional" + RESET, CYAN + "Prerequisite" + RESET,
            CYAN + "Department" + RESET, CYAN + "Connection" + RESET, CYAN + "Interrupter" + RESET,
            CYAN + "Equivalent" + RESET, CYAN + "Insurance" + RESET, CYAN + "Difficulties" + RESET,
            CYAN + "Workplace" + RESET, CYAN + "Traffic light" + RESET, CYAN + "Composition" + RESET,
            CYAN + "Agreement" + RESET, CYAN + "Official" + RESET, CYAN + "Restore" + RESET,
            CYAN + "Patisserie" + RESET, CYAN + "Immigration" + RESET, CYAN + "Renewable" + RESET,
            CYAN + "Packaging" + RESET, CYAN + "Pollution" + RESET, CYAN + "Populated" + RESET,
            CYAN + "Progress" + RESET, CYAN + "Recycling" + RESET, CYAN + "Shutdown" + RESET,
            CYAN + "Complicated" + RESET, CYAN + "Quality control" + RESET, CYAN + "User-friendly" + RESET
    };

    // Hårdkodade färglagda bokstäver
    public static final String[] characters = {
            CYAN + "A" + RESET, CYAN + "B" + RESET, CYAN + "C" + RESET, CYAN + "D" + RESET,
            CYAN + "E" + RESET, CYAN + "F" + RESET, CYAN + "G" + RESET, CYAN + "H" + RESET,
            CYAN + "I" + RESET, CYAN + "J" + RESET, CYAN + "K" + RESET, CYAN + "L" + RESET,
            CYAN + "M" + RESET, CYAN + "N" + RESET, CYAN + "O" + RESET, CYAN + "P" + RESET,
            CYAN + "Q" + RESET, CYAN + "R" + RESET, CYAN + "S" + RESET, CYAN + "T" + RESET,
            CYAN + "U" + RESET, CYAN + "V" + RESET, CYAN + "W" + RESET, CYAN + "X" + RESET,
            CYAN + "Y" + RESET, CYAN + "Z" + RESET
    };

    // slumpmässigt blanda ord
    public static List<String> randomizeWords(String[] words) {
        List<String> tempWords = new ArrayList<>(List.of(words));
        Collections.shuffle(tempWords);
        return tempWords.subList(0, 7);
    }
}
