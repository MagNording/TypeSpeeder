package se.ju23.typespeeder.util;

import java.util.Scanner;

public class UserInputServiceImpl implements UserInputService {
    private final Scanner input = new Scanner(System.in);
    private static final String PATTERN = "[-a-zA-ZåäöÅÄÖ0-9@._ ]+";
    private static final String POSITIVE_VALUE_ERROR = "Värdet måste vara positivt, försök igen ";
    private static final String INVALID_INPUT_ERROR = "Felaktig inmatning, försök igen ";
    private static final String RANGE_ERROR = "Värdet måste vara mellan %d och %d, försök igen ";
    private static final String INVALID_FORMAT_ERROR = "Incorrect format, you cannot use special characters!";
    private static final String BLANK_INPUT_ERROR = "Entry cannot be blank.";
    private static final String JA_NEJ_PROMPT = "Ange 'j' för ja eller 'n' för nej: ";
    private static final String YES_NO_PROMPT = "Enter 'y' for yes or 'n' for no: ";
    private static final String INVALID_CHOICE_JA_NEJ = "Ogiltigt val. Ange antingen 'j' eller 'n'.";
    private static final String INVALID_CHOICE_YES_NO = "Invalid choice. Enter either 'y' or 'n'.";

    /**
     * Reads a validated string input from the user.
     * @param prompt The message to prompt the user with
     * @return A validated string
     */
    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        String userInput;
        boolean isUserInputInvalid;
        do {
            userInput = input.nextLine();
            if (!userInput.matches(PATTERN)) {
                System.out.println(INVALID_FORMAT_ERROR);
                isUserInputInvalid = true;
            } else if (userInput.isEmpty()) {
                System.out.println(BLANK_INPUT_ERROR);
                isUserInputInvalid = true;
            } else {
                isUserInputInvalid = false;
            }
        } while (isUserInputInvalid);

        return userInput;
    }

    /**
     * Reads a positive integer value.
     * @param prompt The message to prompt the user with
     * @return A positive integer
     */
    @Override
    public int readInt(String prompt) {
        int intValue;
        while (true) {
            try {
                String inputLine = readString(prompt);
                intValue = Integer.parseInt(inputLine);
                if (intValue < 0) {
                    System.out.println(POSITIVE_VALUE_ERROR);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_ERROR);
            }
        }
        return intValue;
    }

    /**
     * Reads an integer within a specified range.
     * @param prompt The message to prompt the user with
     * @param min    The minimum value (inclusive)
     * @param max    The maximum value (inclusive)
     * @return An integer within the given range
     */
    @Override
    public int readInt(String prompt, int min, int max) {
        int intValue;
        while (true) {
            try {
                String inputLine = readString(prompt);
                intValue = Integer.parseInt(inputLine);
                if (intValue < min || intValue > max) {
                    System.out.printf(RANGE_ERROR, min, max);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_ERROR);
            }
        }
        return intValue;
    }

    /**
     * Reads a positive double value.
     * @param prompt The message to prompt the user with
     * @return A positive double
     */
    @Override
    public double readDouble(String prompt) {
        double doubleValue;
        while (true) {
            String userInput = readString(prompt).replace(",", ".");
            try {
                doubleValue = Double.parseDouble(userInput);
                if (doubleValue < 0) {
                    System.out.println(POSITIVE_VALUE_ERROR);
                } else {
                    return doubleValue;
                }
            } catch (NumberFormatException nfe) {
                System.out.println(INVALID_INPUT_ERROR);
            }
        }
    }

    /**
     * Reads a positive long value.
     * @param prompt The message to prompt the user with
     * @return A positive long
     */
    @Override
    public long readLong(String prompt) {
        long longValue;
        while (true) {
            try {
                String inputLine = readString(prompt);
                longValue = Long.parseLong(inputLine);
                if (longValue < 0) {
                    System.out.println(POSITIVE_VALUE_ERROR);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_ERROR);
            }
        }
        return longValue;
    }

    /**
     * Reads a yes/no response in Swedish (j/n).
     * @return True if 'j', false if 'n'
     */
    @Override
    public boolean readJaNej() {
        while (true) {
            System.out.print(JA_NEJ_PROMPT);
            String userInput = readString("");
            if (userInput.equalsIgnoreCase("j")) {
                return true;
            } else if (userInput.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println(INVALID_CHOICE_JA_NEJ);
            }
        }
    }

    /**
     * Reads a yes/no response in English (y/n).
     * @return True if 'y', false if 'n'
     */
    @Override
    public boolean readYesNo() {
        while (true) {
            System.out.print(YES_NO_PROMPT);
            String userInput = readString("");
            if (userInput.equalsIgnoreCase("y")) {
                return true;
            } else if (userInput.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println(INVALID_CHOICE_YES_NO);
            }
        }
    }
}