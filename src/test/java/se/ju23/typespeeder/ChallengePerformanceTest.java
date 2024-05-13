package se.ju23.typespeeder;


import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.logic.Challenge;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ChallengePerformanceTest {
    private static final int MAX_EXECUTION_TIME = 200;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    @Test
    public void testStartChallengePerformance() {
        Challenge challenge = new Challenge();
        long startTime = System.nanoTime();
        challenge.startChallenge(mock());
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Starting a challenge took too long. Execution time: " + duration + " ms.");
    }
    @Test
    public void testLettersToTypePerformance() {
        Challenge challenge = new Challenge();
        long startTime = System.nanoTime();
        challenge.lettersToType(mock());
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Selecting letters to type took too long. Execution time: " + duration + " ms.");
    }
}
