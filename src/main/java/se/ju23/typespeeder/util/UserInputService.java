package se.ju23.typespeeder.util;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserInputService {
    private static final Scanner input = new Scanner(System.in);

    public String nextLine() {
        return input.nextLine();
    }

    public int getIntInput() {
        String line = input.nextLine();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return getIntInput();
        }
    }

    public boolean hasNextInt() {
        return input.hasNextInt();
    }

    public void consumeNextLine() {
        if (input.hasNextLine()) {
            input.nextLine();
        }
    }
}