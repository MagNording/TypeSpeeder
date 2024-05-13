package se.ju23.typespeeder;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewsLetter {
    private String content;
    public LocalDateTime publishDateTime;

    public NewsLetter() {
        this.content = """
               Here's a puzzle for you-
               I speak without a mouth and hear without ears. I have no body, but I come alive with the wind.
               What am I?
               Answer: ECHO.
               """;
        this.publishDateTime = LocalDateTime.parse("2024-05-22T14:15:15");
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }

    @Override
    public String toString() {
        return content + "\nPublished: " +
                publishDateTime.getDayOfMonth() + " " +
                publishDateTime.getMonth().toString().charAt(0) +
                publishDateTime.getMonth().toString().substring(1).toLowerCase() +
                " " + publishDateTime.getYear();
    }
}
