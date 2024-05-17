package se.ju23.typespeeder.util;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.ui.Menu;

import java.util.Scanner;

@Service
public class UserInputService {

    private static final Scanner input = new Scanner(System.in);

    @Lazy
    private final Menu menu;

    public UserInputService(@Lazy Menu menu) {
        this.menu = menu;
    }

    public String nextLine() {
        return input.nextLine();
    }

    public void intro() {
        Messages messages = new Messages(menu.getLanguage());
        System.out.println(messages.get("welcome.message"));
    }

    public void invalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }

    public int getIntInput() {
        while (true) {
            String line = input.nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public void outtro() {
        Messages messages = new Messages(menu.getLanguage());
        System.out.println(messages.get("thankyou.message"));
    }
}