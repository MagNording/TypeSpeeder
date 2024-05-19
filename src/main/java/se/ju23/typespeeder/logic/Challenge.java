package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repositories.ResultRepo;
import se.ju23.typespeeder.service.PlayerService;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.Messages;
import se.ju23.typespeeder.util.UserInputService;
import se.ju23.typespeeder.entity.Result;

import java.util.List;

@Component
public class Challenge {

    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    private final UserInputService userInputService;
    private final Menu menu;
    private final ResultRepo resultRepo;
    private final PlayerService playerService;

    private Messages messages;

    @Autowired
    public Challenge(UserInputService userInputService, ResultRepo resultRepo, PlayerService playerService, Menu menu) {
        this.userInputService = userInputService;
        this.resultRepo = resultRepo;
        this.playerService = playerService;
        this.messages = new Messages(menu.getLanguage());
        this.menu = menu;
    }

    public void updateMessages() {
        this.messages = new Messages(menu.getLanguage());
    }

    public void startChallenge(Player player) {
        updateMessages();
        int choice;
        do {
            displayMainMenu();
            choice = userInputService.getIntInput();
            switch (choice) {
                case 1 -> startGame(player);
                case 2 -> startCharacterGame(player);
                case 3 -> System.out.println(messages.get("menu.exit"));
                default -> System.out.println(messages.get("invalid.option"));
            }
        } while (choice != 3);
    }

    private void displayMainMenu() {
        updateMessages();
        System.out.println(messages.get("challenge.menu.main"));
    }

    private String colorize(String text) {
        return Challenge.CYAN + text + Challenge.RESET;
    }

    public String lettersToType(List<String> wordsToType) {
        return String.join(" ", wordsToType).trim();
    }

    public void getStandardGameInstructions() {
        updateMessages();
        System.out.println(messages.get("challenge.instructions.standard"));
    }

    public void getSpecialCharGameInstructions() {
        updateMessages();
        System.out.println(messages.get("challenge.instructions.special"));
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
        updateMessages();
        System.out.println(messages.get("challenge.menu.difficulty"));
    }

    private String generateTargetWords() {
        return menu.getLanguage().equals("svenska")
                ? lettersToType(WordsToType.randomizeWords(WordsToType.words))
                : lettersToType(WordsToType.randomizeWords(WordsToType.englishWords));
    }

    private void displayResults(Player loggedInPlayer, String targetWords, double elapsedTimeInSeconds, int[] accuracy) {
        int accuracyPercentage = accuracy[0];
        int mistakesCount = accuracy[1];
        String formattedAccuracy = String.format("%d", accuracyPercentage);

        System.out.println(messages.get("challenge.results.time").formatted(elapsedTimeInSeconds));
        System.out.println(messages.get("challenge.results.accuracy").formatted(formattedAccuracy));
        System.out.println(messages.get("challenge.results.mistakes").formatted(mistakesCount));

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

        mistakeCount += maxLength - minLength;

        int accuracy = (int) ((double) correctCount / maxLength * 100);
        return new int[]{accuracy, mistakeCount, correctCount};
    }

    public static String removeAnsiCodes(String input) {
        return input.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}
