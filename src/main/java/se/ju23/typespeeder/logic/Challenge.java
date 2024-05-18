package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repositories.ResultRepo;
import se.ju23.typespeeder.service.PlayerService;
import se.ju23.typespeeder.ui.ChallengeMenu;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInputService;
import se.ju23.typespeeder.entity.Result;

import java.util.List;

@Component
public class Challenge {

    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    @Autowired
    ChallengeMenu challengeMenu;

    @Autowired
    UserInputService userInputService;

    @Autowired
    Menu menu;

    @Autowired
    ResultRepo resultRepo;

    @Autowired
    PlayerService playerService;

    public void startChallenge(Player player) {
        int choice;
        do {
            displayMainMenu();
            choice = userInputService.getIntInput();
            switch (choice) {
                case 1 -> startGame(player);
                case 2 -> startCharacterGame(player);
                case 3 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 3);
    }

    private void displayMainMenu() {
        if (menu.getLanguage().equals("svenska")) {
            System.out.println("""
                1. Starta ett Ordspel
                2. Starta ett Bokstavsspel
                3. Åter till spelmenyn
                """);
        } else {
            System.out.println("""
                1. Start a words game
                2. Start a single character game
                3. Back to game menu
                """);
        }
    }

    private String colorize(String text) {
        return Challenge.CYAN + text + Challenge.RESET;
    }

    public String lettersToType(List<String> wordsToType) {
        return String.join(" ", wordsToType).trim();
    }

    public void getStandardGameInstructions() {
        if (menu.getLanguage().equals("svenska")) {
            System.out.println("""
                Ett antal ord kommer att visas, skriv av orden.
                När du skrivit klart, tryck ENTER.
                Tryck på ENTER när du är redo att starta
                eller tryck '0' och ENTER för att avbryta!
                """);
        } else {
            System.out.println("""
                A number of words will be displayed.
                Type the words, when you're finished, press Enter.
                Press Enter when ready to start
                or press '0' and Enter to exit!
                """);
        }
    }

    public void getSpecialCharGameInstructions() {
        if (menu.getLanguage().equals("svenska")) {
            System.out.println("""
                Skriv in de tecken som visas samt mellanrum.
                När du skrivit klart, tryck ENTER!
                Tryck på ENTER när du är redo att starta!
                """);
        } else {
            System.out.println("""
                Enter the characters as they appear,
                including the spaces. When done, press ENTER.
                Press ENTER when ready to start!
                """);
        }
    }

    public void startGame(Player loggedInPlayer) {
        do {
            displayDifficultyMenu();
            int difficulty = userInputService.getIntInput();
            if (difficulty == 0) break;

            getStandardGameInstructions();
            String wait = userInputService.nextLine(); // Wait for user to press enter
            if (wait.equals("0")) break;

            String targetWords = generateTargetWords();
            targetWords = colorize(targetWords);
            System.out.println(targetWords);

            double startTime = System.currentTimeMillis();
            String playerWords = userInputService.nextLine();
            double elapsedTimeInSeconds = (System.currentTimeMillis() - startTime) / 1000;

            int[] accuracy = (difficulty == 1) ? calcAccuracy(targetWords, playerWords) : calcAccuracyForCaseSensitive(targetWords, playerWords);

            displayResults(loggedInPlayer, targetWords, elapsedTimeInSeconds, accuracy);

        } while (true);
    }

    private void displayDifficultyMenu() {
        if (menu.getLanguage().equals("svenska")) {
            System.out.println("""
                1.Starta lätt spel
                2.Starta svårt spel(skiftlägeskänsligt)
                0.Avsluta
                """);
        } else {
            System.out.println("""
                1.Start easy game
                2.Start hard game(case sensitive)
                0.Exit
                """);
        }
    }

    private String generateTargetWords() {
        if (menu.getLanguage().equals("svenska")) {
            return lettersToType(WordsToType.randomizeWords(WordsToType.words));
        } else {
            return lettersToType(WordsToType.randomizeWords(WordsToType.englishWords));
        }
    }

    private void displayResults(Player loggedInPlayer, String targetWords, double elapsedTimeInSeconds, int[] accuracy) {
        int accuracyPercentage = accuracy[0];
        int mistakesCount = accuracy[1];
        String formattedAccuracy = String.format("%d", accuracyPercentage);

        if (menu.getLanguage().equals("svenska")) {
            System.out.println("Det tog " + elapsedTimeInSeconds + " sekunder att skriva klart!\n" +
                    "Du hade en precision på " + formattedAccuracy + "%");
            System.out.println("Du hade " + mistakesCount + " fel");
        } else {
            System.out.println("It took " + elapsedTimeInSeconds + " seconds to finish!\n" +
                    "Your precision was " + formattedAccuracy + "%");
            System.out.println("You made " + mistakesCount + " mistakes");
        }

        int points = calcTotalPoints(accuracyPercentage, elapsedTimeInSeconds);
        saveResults(loggedInPlayer, elapsedTimeInSeconds, accuracyPercentage, points);
    }

    private void saveResults(Player loggedInPlayer, double elapsedTimeInSeconds, int accuracyPercentage, int points) {
        Result result = new Result();
        result.setTimeResult(elapsedTimeInSeconds);
        result.setAmountResult(accuracyPercentage);
        result.setResult(points);
        result.setUser(loggedInPlayer);
        resultRepo.save(result);
        playerService.updateXPandLevel(loggedInPlayer, points);
    }

    public void startCharacterGame(Player loggedInPlayer) {
        do {
            Result result = new Result();
            getSpecialCharGameInstructions();
            String wait = userInputService.nextLine();
            if (wait.equals("0")) break;

            String targetWords = lettersToType(WordsToType.randomizeWords(WordsToType.characters));
            targetWords = colorize(targetWords);
            System.out.println(targetWords);

            double startTime = System.currentTimeMillis();
            String playerWords = userInputService.nextLine();
            double elapsedTimeInSeconds = (System.currentTimeMillis() - startTime) / 1000;

            int[] accuracy = calcAccuracy(targetWords, playerWords);
            displayResults(loggedInPlayer, targetWords, elapsedTimeInSeconds, accuracy);

        } while (true);
    }

    public int calcTotalPoints(int accuracy, double elapsedTime) {
        int points = 100;

        if (elapsedTime > 20) {
            double extraTime = elapsedTime - 20;
            points -= (int) extraTime; // Deduct 1 point for each second over 20 seconds
        }
        if (accuracy < 100) {
            int mistakes = 100 - accuracy;
            points -= mistakes; // Deduct 1 point for each mistake made
        }
        return Math.max(points, 0); // Ensure total points are non-negative
    }

    public int[] calcAccuracy(String targetWords, String playerWords) {
        targetWords = removeAnsiCodes(targetWords);
        playerWords = removeAnsiCodes(playerWords);

        int correctCount = 0;
        int mistakeCount = 0;
        int minLength = Math.min(targetWords.length(), playerWords.length());
        int maxLength = Math.max(targetWords.length(), playerWords.length());

        for (int i = 0; i < minLength; i++) {
            if (Character.toLowerCase(targetWords.charAt(i)) == Character.toLowerCase(playerWords.charAt(i))) {
                correctCount++;
            } else {
                mistakeCount++;
            }
        }

        // Räkna de extra tecknen i spelarinmatningen som felaktiga
        mistakeCount += maxLength - minLength;

        int accuracy = (int) ((double) correctCount / maxLength * 100);
        return new int[]{accuracy, mistakeCount, correctCount};
    }

    public int[] calcAccuracyForCaseSensitive(String targetWords, String playerWords) {
        targetWords = removeAnsiCodes(targetWords);
        playerWords = removeAnsiCodes(playerWords);

        int correctCount = 0;
        int mistakeCount = 0;
        int minLength = Math.min(targetWords.length(), playerWords.length());
        int maxLength = Math.max(targetWords.length(), playerWords.length());

        for (int i = 0; i < minLength; i++) {
            if (targetWords.charAt(i) == playerWords.charAt(i)) {
                correctCount++;
            } else {
                mistakeCount++;
            }
        }

        // Räkna de extra tecknen i spelarinmatningen som felaktiga
        mistakeCount += maxLength - minLength;

        int accuracy = (int) ((double) correctCount / maxLength * 100);
        return new int[]{accuracy, mistakeCount, correctCount};
    }

    // Metod för att ta bort ANSI-koder från en sträng
    public static String removeAnsiCodes(String input) {
        return input.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}