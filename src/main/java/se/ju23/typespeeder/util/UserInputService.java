package se.ju23.typespeeder.util;

public interface UserInputService {
    String readString();
    int readInt();
    int readInt(int min, int max);
    double readDouble();
    long readLong();
    boolean readJaNej();
    boolean readYesNo();
}
