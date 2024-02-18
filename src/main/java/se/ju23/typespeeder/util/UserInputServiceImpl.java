package se.ju23.typespeeder.util;// magnus nording, magnus.nording@iths.se
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class UserInputServiceImpl implements UserInputService {

    private final Scanner input;

    public UserInputServiceImpl() {
        this.input = new Scanner(System.in);
    }

    public String readString() {
        String stringValue;
        // Define a regex pattern to match the allowed characters
        String pattern = "[-a-zA-ZåäöÅÄÖ0-9@._ ]+";
        do {
            System.out.print("Enter your input: ");
            stringValue = input.nextLine().trim();
            if (stringValue.isBlank()) {
                System.out.println("No input made, try again.\n > ");
            } else if (!stringValue.matches(pattern)) {
                // If input doesn't match the pattern, inform the user and prompt again
                System.out.println("Invalid input. Only letters, numbers, spaces, '-', ',', '@', '.', and '_' are allowed.\n > ");
            }
        } while (stringValue.isBlank() || !stringValue.matches(pattern));
        return stringValue;
    }

    public int readInt() {
        int intValue;
        while (true) {
            try {
                String inputLine = readString();
                intValue = Integer.parseInt(inputLine);
                if (intValue < 0) {
                    System.out.println("Värdet måste vara positivt, försök igen.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Felaktig inmatning, försök igen.");
            }
        }
        return intValue;
    }

    public int readInt(int min, int max) {
    int intValue;
    while (true) {
        try {
            String inputLine = readString();
            intValue = Integer.parseInt(inputLine);
            if (intValue < min || intValue > max) {
                System.out.printf("Värdet måste vara mellan %d och %d, försök igen.\n", min, max);
            } else {
                break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Felaktig inmatning, försök igen.");
        }
    }
    return intValue;
    }


    public double readDouble() {
        double doubleValue;
        while (true) {
            String userInput = readString();
            userInput = userInput.replace(",", ".");
            try {
                doubleValue = Double.parseDouble(userInput);
                if (doubleValue < 0) {
                    System.out.println("Värdet måste vara positivt, försök igen.");
                } else {
                    return doubleValue;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Felaktig inmatning, försök igen.");
            }
        }
    }

    public long readLong() {
        long longValue;
        while (true) {
            try {
                if (input.hasNextLong()) {
                    longValue = input.nextLong();
                    input.nextLine();
                    break;
                } else {
                    System.out.println("Felaktig inmatning, försök igen.");
                    input.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Felaktig inmatning, försök igen.");
                input.nextLine();
            }
        }
        return longValue;
    }

    public boolean readJaNej() {
        while (true) {
            System.out.print("Ange 'j' för ja eller 'n' för nej: ");
            String userInput = readString();
            if (userInput.equalsIgnoreCase("j")) {
                return true;
            } else if (userInput.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Ogiltigt val. Ange antingen 'j' eller 'n'.");
            }
        }
    }

    public boolean readYesNo() {
        while (true) {
            System.out.print("Enter 'y' for yes or 'n' for no: ");
            String userInput = readString();
            if (userInput.equalsIgnoreCase("y")) {
                return true;
            } else if (userInput.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid choice. Enter either 'y' or 'n'.");
            }
        }
    }

    public static String capitalize(String originalString) {
        if (originalString.isEmpty()) {
            return originalString;
        }
        return originalString.substring(0, 1).toUpperCase() +
                originalString.substring(1);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }

}
