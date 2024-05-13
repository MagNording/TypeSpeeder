package se.ju23.typespeeder;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.logic.Challenge;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInputService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChallengePerformanceTest {
    private static final int MAX_EXECUTION_TIME = 200;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    private PrintStream originalOut;

    @BeforeEach
    public void setUpStreams() {
        originalOut = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testStartChallengePerformance() throws Exception {
        Menu mockMenu = mock(Menu.class);
        UserInputService mockUserInputService = mock(UserInputService.class);
        Player mockPlayer = mock(Player.class);

        when(mockMenu.getLanguage()).thenReturn("English");
        when(mockUserInputService.getIntInput()).thenReturn(3);

        Challenge challenge = new Challenge();

        setField(challenge, "menu", mockMenu);
        setField(challenge, "userInputService", mockUserInputService);

        // Start timing
        long startTime = System.nanoTime();
        challenge.startChallenge(mockPlayer);
        long endTime = System.nanoTime();

        // Calculate duration
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Starting a challenge took too long. Execution time: " + duration + " ms.");
    }
    private void setField(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true); // Bypass the access check
        field.set(object, value);
    }


    @Test
    public void testLettersToTypePerformance() {
        Challenge challenge = new Challenge();
        List<String> mockWords = Arrays.asList("hello", "world");
        long startTime = System.nanoTime();
        challenge.lettersToType(mockWords);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;
        assertTrue(duration <= MAX_EXECUTION_TIME, "Selecting letters to type took too long. Execution time: " + duration + " ms.");
    }
}
