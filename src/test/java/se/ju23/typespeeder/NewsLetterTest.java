package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.ju23.typespeeder.ui.Menu;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;


public class NewsLetterTest {

    @Test
    public void testNewsLetterClassExists() {
        try {
            Class.forName("se.ju23.typespeeder.NewsLetter");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("NewsLetter class should exist.", e);
        }
    }
    @Test
    public void testNewsLetterContentLength() {
        try {
            Class<?> newsLetterClass = Class.forName("se.ju23.typespeeder.NewsLetter");

            Field contentField = newsLetterClass.getDeclaredField("content");
            assertNotNull(contentField, "Field 'content' should exist in NewsLetter.");

            assertTrue(contentField.getType().equals(String.class), "Field 'content' should be of type String.");

            // Mocka Menu, skapa instans av NewsLetter
            Menu mockMenu = Mockito.mock(Menu.class);
            Mockito.when(mockMenu.getLanguage()).thenReturn("en");
            Object instance = newsLetterClass.getDeclaredConstructor(Menu.class).newInstance(mockMenu);

            Field field = newsLetterClass.getDeclaredField("content");
            field.setAccessible(true);
            String contentValue = (String) field.get(instance);

            assertTrue(contentValue.length() >= 100, "Content field length should be at least 100 characters.");
            assertTrue(contentValue.length() <= 200, "Content field length should be at most 200 characters.");

        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail("Error occurred while testing NewsLetter content field length.", e);
        }
    }

    @Test
    public void testNewsLetterPublishDateTime() {
        try {
            Class<?> someClass = Class.forName("se.ju23.typespeeder.NewsLetter");

            Field publishDateTime = someClass.getDeclaredField("publishDateTime");
            assertNotNull(publishDateTime, "Field 'publishDateTime' should exist in NewsLetter class.");

            assertTrue(publishDateTime.getType().equals(LocalDateTime.class), "Field 'publishDateTime' should be of type LocalDateTime.");

            // Mock Menu and create instance of NewsLetter
            Menu mockMenu = Mockito.mock(Menu.class);
            Mockito.when(mockMenu.getLanguage()).thenReturn("en");
            Object instance = someClass.getDeclaredConstructor(Menu.class).newInstance(mockMenu);

            LocalDateTime dateTimeValue = (LocalDateTime) publishDateTime.get(instance);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTimeValue.format(formatter);
            assertEquals("2024-05-22 14:15:15", formattedDateTime, "'publishDateTime' field " +
                    "should have format 'yyyy-MM-dd HH:mm:ss'.");

            Method getterMethod = someClass.getDeclaredMethod("getPublishDateTime");
            assertNotNull(getterMethod, "Getter method for the field 'publishDateTime' should exist.");

        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            fail("Error occurred while testing properties of NewsLetter.", e);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
