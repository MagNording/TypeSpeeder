package se.ju23.typespeeder.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static String formatPublishDateTime(LocalDateTime publishDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return publishDateTime.format(formatter);
    }
}
