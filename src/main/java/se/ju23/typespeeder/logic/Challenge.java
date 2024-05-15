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
        int choice = 0;

        do {
            if (menu.getLanguage().equals("svenska")) {
                System.out.println("""
                    1. Starta ett ord-spel
                    2. Starta ett bokstavs-spel
                    3. Åter till meny
                    """);
            } else {
                System.out.println("""
                    1. Start a words game
                    2. Start a single character game
                    3. Back to main menu
                    """);
            }
            choice = userInputService.getIntInput();

            if (choice == 3){
                break;
            }
            if (choice == 1){
                startGame(player);
            }else {
                startCharacterGame(player);
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
                    Ett antal ord kommer visas, skriv av orden. När du skrivit klart, tryck Enter.
                    Tryck på Enter när du är redo att starta Eller tryck '0' och Enter för att avbryta!

                    """);
        } else {
            System.out.println("""
                    A number of words will be displayed. Type the words, and when you're finished, press Enter.
                    Press Enter when ready to start or press '0' and Enter to exit!

                    """);
        }
    }

    public void getSpecialCharGameInstructions() {
        if (menu.getLanguage().equals("svenska")) {
            System.out.println("""
                    Skriv in de tecken som visas med mellanrum där de visas.
                    När du skrivit klart, tryck Enter!
                    
                    Tryck på Enter när du är redo att starta!

                    """);
        } else {
            System.out.println("""
                    Enter the characters as they appear,
                    including spaces where they appear.
                    when done, press Enter.
                    
                    Press Enter when ready to start!

                    """);
        }
    }

    public void startGame(Player loggedInPlayer) {
        String wait; // Wait for user to press enter

        do {
            if (menu.getLanguage().equals("svenska")) {
                System.out.println("""
                        1.Starta lätt spel
                        2.Starta svårt spel(skiftlägeskänsligt)
                        """);
            } else {
                System.out.println("""
                        1.Starta easy game
                        2.Start hard game(case sensitive)
                        """);
            }
            int difficulty = userInputService.getIntInput();

            Result result = new Result();
            getStandardGameInstructions();

            wait = userInputService.nextLine(); // Wait for user to press enter

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
            int mistakesCount = accuracy[1];
            int correctCount = accuracy[2];

            String formattedAccuracy = String.format("%d", accuracyPercentage);

            if (menu.getLanguage().equals("svenska")) {
                System.out.println("Det tog " + elapsedTimeInSeconds + "sekunder att skriva " +
                        "klart!\n" +
                        "Du hade en precision på " + formattedAccuracy + "%");
                System.out.println("du hade " + mistakesCount + " fel");
            } else {
                System.out.println("It took " + elapsedTimeInSeconds + "seconds to finish!\n" +
                        "Your precision was " + formattedAccuracy + "%");
                System.out.println("You made " + mistakesCount + " mistakes");
            }

            int points = calcTotalPoints(accuracyPercentage,elapsedTimeInSeconds);

            result.setTimeResult(elapsedTimeInSeconds);
            result.setAmountResult(accuracyPercentage);
            result.setResult(points);
            result.setUser(loggedInPlayer);

            resultRepo.save(result);

            playerService.updateXPandLevel(loggedInPlayer,points);

        } while (!wait.equals("0"));
    }


    public void startCharacterGame(Player loggedInPlayer) {
        String wait;
        do {
            Result result = new Result();
            getSpecialCharGameInstructions();
            wait = userInputService.nextLine();

            if (wait.equals("0")) {
                break;
            }

            String targetWords = lettersToType(WordsToType.randomizeWords(WordsToType.characters));
            System.out.println(targetWords);

            double startTime = System.currentTimeMillis();

            String playerWords = userInputService.nextLine();

            double endTime = System.currentTimeMillis();
            double elapsedTimeInSeconds = (endTime - startTime) / 1000;

            int[] accuracy = calcAccuracy(targetWords,playerWords);

            int accuracyPercentage = accuracy[0];
            int mistakesCount = accuracy[1];
            int correctCount = accuracy[2];

            String formattedAccuracy = String.format("%d", accuracyPercentage);

            if (menu.getLanguage().equals("svenska")) {
                System.out.println("Det tog " + elapsedTimeInSeconds + "sekunder att skriva " +
                        "klart!\n" +
                        "Du hade en precision på " + formattedAccuracy + "%");
                System.out.println("Du hade " + mistakesCount + " fel");
            } else {
                System.out.println("It took " + elapsedTimeInSeconds + "seconds to finish!\n" +
                        "Your precision was " + formattedAccuracy + "%");
                System.out.println("You made " + mistakesCount + " mistakes");
            }

            int points = calcTotalPoints(accuracyPercentage,elapsedTimeInSeconds);

            result.setTimeResult(elapsedTimeInSeconds);
            result.setAmountResult(accuracyPercentage);
            result.setResult(points);
            result.setUser(loggedInPlayer);
            resultRepo.save(result);

            playerService.updateXPandLevel(loggedInPlayer,points);
        } while (!wait.equals("0"));
    }

    public int calcTotalPoints(int accuracy, double elapsedTime){
        int point = 100;

        if (elapsedTime > 20) {
            double extraTime = elapsedTime - 20;
            point -= extraTime; // Deduct 1 point for each second over 20 seconds
        }
        // Deduct points based on accuracy
        if (accuracy < 100) {
            int mistakes = 100 - accuracy;
            point -= mistakes; // Deduct 1 point for each mistake made
        }
        // Ensure total points are non-negative
        point = Math.max(point, 0);
        return point;
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
            accuracy = (int) ((double) correctCount / Math.max(targetWords.length(),
                    playerWords.length()) * 100);
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
            accuracy = (int) ((double) correctCount / Math.max(targetWords.length(),
                    playerWords.length()) * 100);
        }
        return new int[]{accuracy,mistakeCount,correctCount};
    }
}
