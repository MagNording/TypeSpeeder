package se.ju23.typespeeder.util;

public interface UserInputService {

    String readString(String prompt);

    String readString();

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    int readInt(int min, int max);

    double readDouble(String prompt);

    long readLong(String prompt);

    boolean readJaNej();

    boolean readYesNo();
}
