package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.ui.MenuService;
import se.ju23.typespeeder.util.UserInputService;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        outContent.reset();
    }

    @Test
    public void testClassExists() {
        try {
            Class<?> clazz = Class.forName("se.ju23.typespeeder.ui.Menu");
            assertNotNull(clazz, "The class 'Menu' should exist.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' does not exist.", e);
        }
    }

    @Test
    public void testMethodExists() {
        try {
            Class<?> clazz = Class.forName("se.ju23.typespeeder.ui.Menu");
            Method method = clazz.getMethod("displayMenu");
            assertNotNull(method, "The method 'displayMenu()' should exist in the class 'Menu'.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' does not exist.", e);
        } catch (NoSuchMethodException e) {
            fail("The method 'displayMenu()' does not exist in the class 'Menu'.", e);
        }
    }

    @Test
    public void testMenuImplementsInterface() {
        try {
            Class<?> menuClass = Class.forName("se.ju23.typespeeder.ui.Menu");
            boolean implementsInterface = false;

            Class<?>[] interfaces = menuClass.getInterfaces();
            for (Class<?> iface : interfaces) {
                if (iface.equals(MenuService.class)) {
                    implementsInterface = true;
                    break;
                }
            }
            assertTrue(implementsInterface, "The class 'Menu' should implement the interface 'MenuService'.");
        } catch (ClassNotFoundException e) {
            fail("The class 'Menu' could not be found", e);
        }
    }

    @Test
    public void testDisplayMenuCallsGetMenuOptionsAndReturnsAtLeastFive() {
        UserInputService userInputServiceMock = mock(UserInputService.class);
        when(userInputServiceMock.nextLine()).thenReturn("svenska");
        Menu menu = new Menu(userInputServiceMock);
        Menu menuMock = Mockito.spy(menu);
        menuMock.displayMenu();
        verify(menuMock, times(1)).getMenuOptions();
        assertTrue(menuMock.getMenuOptions().size() >= 5, "'getMenuOptions()' should return at least 5 alternatives.");
    }

    @Test
    public void menuShouldHaveAtLeastFiveOptions() {
        UserInputService userInputServiceMock = mock(UserInputService.class);
        when(userInputServiceMock.nextLine()).thenReturn("svenska");
        Menu menu = new Menu(userInputServiceMock);
        List<String> options = menu.getMenuOptions();
        assertTrue(options.size() >= 5, "The menu should contain at least 5 alternatives.");
    }

    @Test
    public void menuShouldPrintAtLeastFiveOptions() {
        UserInputService userInputServiceMock = mock(UserInputService.class);
        when(userInputServiceMock.nextLine()).thenReturn("svenska");
        Menu menu = new Menu(userInputServiceMock);
        menu.displayMenu();
        long count = outContent.toString().lines().count();
        assertTrue(count >= 5, "The menu should print out at least 5 alternatives.");
    }

    @Test
    public void testUserCanChooseSwedishLanguage() {
        String input = "svenska\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        UserInputService userInputService = new UserInputService(mock(Menu.class));
        Menu menu = new Menu(userInputService);
        menu.displayMenu();

        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Välj språk (svenska/engelska):"), "Menu should prompt for language selection.");
        assertTrue(consoleOutput.contains("Svenska valt."), "Menu should confirm Swedish language selection.");
    }
}