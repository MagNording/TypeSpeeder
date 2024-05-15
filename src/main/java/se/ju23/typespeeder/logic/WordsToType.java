package se.ju23.typespeeder.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordsToType {

    public static final String[] words={
            "Befolkning", "Förklaring", "Myndighet", "Bevittna", "Känslomässig", "Förutsättning",
            "Avdelning", "Förbindelse", "Avbrytare", "Motsvarighet", "Försäkring", "Svårigheter",
            "Arbetsplats", "Trafikljus", "Sammansättning", "Överenskommelse", "Tjänsteman", "Återställa",
            "Konditori", "Invandring", "Förnyelsebar", "Förpackning", "Föroreningar", "Befolkad",
            "Framsteg", "Återvinning", "Avstängning", "Krånglig", "Kvalitetskontroll", "Användarvänlig"
    };

    public static final String[] englishWords={
            "Population", "Explanation", "Authority", "Witness", "Emotional", "Prerequisite", "Department",
            "Connection", "Interrupter", "Equivalent", "Insurance", "Difficulties", "Workplace",
            "Traffic light", "Composition", "Agreement", "Official", "Restore", "Patisserie", "Immigration",
            "Renewable", "Packaging", "Pollution", "Populated", "Progress", "Recycling",
            "Shutdown", "Complicated", "Quality control", "User-friendly"
    };

    public static final String[] characters = {
            "A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N","O",
            "P","Q","R","S","T","U","V","W",
            "X","Y","Z"
    };

    public static List<String> randomizeWords(String[] words) {
        List<String> tempWords = new ArrayList<>(List.of(words));
        Collections.shuffle(tempWords);

        return tempWords.subList(0, 7);
    }
}