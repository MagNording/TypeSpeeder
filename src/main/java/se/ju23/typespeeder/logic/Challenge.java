package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.ui.ChallangeMenu;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInputService;
import se.ju23.typespeeder.entity.Result;

import java.util.List;

@Component
public class Challenge {

    @Autowired
    ChallangeMenu challangeMenu;

    @Autowired
    UserInputService userInputService;

    @Autowired
    Menu menu;

    public void startChallenge(Player player) {
        int choice = 0;

        do {
            challangeMenu.displayMenu();
            choice = userInputService.getIntInput();

            switch (choice) {
                case 1 -> {
                    startGame(player);
                }
                case 2 -> {
//                    startStandardGameInEnglish(player);
                }
                case 3 -> {
//                    startSpecialCharacterGame(player);
                }
                case 4 -> {
//                    startHighlightedCharacterGame(player);
                }
            }
        } while (choice != 0);
    }

    public String lettersToType(List<String> wordsToType) {
        StringBuilder generatedLetters = new StringBuilder();
        for (String word : wordsToType) {
            generatedLetters.append(word).append(" ");
        }
        return generatedLetters.toString().trim();
    }

    public void getStandardGameInstructions() {
        if (menu.getLanguage().equals("svenska")) {
            System.out.println("""
                    Ett antal ord kommer visas på skärmen, skriv av orden, när du skrivit klart. Tryck Enter
                    Tryck på Enter när du är redo att starta Eller tryck '0' och Enter för att avbryta!

                    """);
        } else {
            System.out.println("""
                    A number of words will be displayed on the screen. Type the words, and when you're finished typing, press Enter.
                    Press Enter when ready to start or press '0' and Enter to exit!

                    """);
        }
    }

    public void startGame(Player foundPlayer) {
        String wait;

        do {

            if (menu.getLanguage().equals("svenska")) {
                System.out.println("""
                        1.Starta normalt spel
                        2.Starta svårt spel(skriftlägeskänsligt)
                        """);
            } else {
                System.out.println("""
                        1.Starta normal game
                        2.Start hard game(case sensitive)
                        """);
            }
            int difficulty = userInputService.getIntInput();

            Result result = new Result();

            getStandardGameInstructions();

            wait = userInputService.nextLine(); // väntar in att användarn läst instruktioner, bekräftar innan spelet startas
            if (wait.equals("0")) {
                break;
            }
            String targetWords;
            if (menu.getLanguage().equals("svenska")) {
                targetWords = lettersToType(WordsToType.randomizeWords(WordsToType.words));
                System.out.println(targetWords);
            } else {
                targetWords = lettersToType(WordsToType.randomizeWords(WordsToType.englishWords));
                System.out.println(targetWords);
            }

            double startTime = System.currentTimeMillis();

            String playerWords = userInputService.nextLine();

            double endTime = System.currentTimeMillis();
            double elapsedTimeInSeconds = (endTime - startTime) / 1000;

            int[] accuracy;

            if (difficulty == 1){
                accuracy = calcAccuracy(targetWords, playerWords);
            }else {
                accuracy = calcAccuracyForCaseSensitive(targetWords, playerWords);
            }

            int accuracyPercentage = accuracy[0];
            int misstakesCount = accuracy[1];
            int correctCount = accuracy[2];

            String formattedAccuracy = String.format("%d", accuracyPercentage);

            if (menu.getLanguage().equals("svenska")) {
                System.out.println("Det tog " + elapsedTimeInSeconds + "Sekunder att skriva klart!\n" +
                        "du hade en precision på " + formattedAccuracy + "%");
                System.out.println("du hade " + misstakesCount + " fel");
            } else {
                System.out.println("It took " + elapsedTimeInSeconds + "seconds to finish!\n" +
                        "with a precision of " + formattedAccuracy + "%");
                System.out.println("You made " + misstakesCount + " misstakes");
            }

        } while (!wait.equals("0"));
    }

    public int[] calcAccuracy(String targetWords, String playerWords) {

        int correctCount = 0;
        int mistakeCount = 0;

        int minLength = Math.min(targetWords.length(), playerWords.length());
        int accuracy = 0;

        for (int i = 0; i < minLength; i++) {
            char goalChar = targetWords.charAt(i);
            char playerChar = playerWords.charAt(i);

            if (Character.toLowerCase(goalChar) == Character.toLowerCase(playerChar)) {
                correctCount++;
            } else {
                mistakeCount++;
            }
            accuracy = (int) ((double) correctCount / Math.max(targetWords.length(), playerWords.length()) * 100);
        }

        return new int[]{accuracy,mistakeCount,correctCount};
    }

    public int[] calcAccuracyForCaseSensitive(String targetWords, String playerWords) {

            int correctCount = 0;
            int mistakeCount = 0;

            int minLength = Math.min(targetWords.length(), playerWords.length());
            int accuracy = 0;

            for (int i = 0; i < minLength; i++) {
                char goalChar = targetWords.charAt(i);
                char playerChar = playerWords.charAt(i);

                if (goalChar == playerChar) {
                    correctCount++;
                } else {
                    mistakeCount++;
                }
                accuracy = (int) ((double) correctCount / Math.max(targetWords.length(), playerWords.length()) * 100);
            }

            return new int[]{accuracy,mistakeCount,correctCount};
        }
}
