package se.ju23.typespeeder.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;


public class NewsLetter {
    private String content;
    public LocalDateTime publishDateTime;

    public NewsLetter() {
        // Example content between 100 and 200 characters long
        this.content = "This is a sample content for the NewsLetter class. It is specifically" +
                " designed to meet the test requirements of having 100 to 200 characters.";
        this.publishDateTime = LocalDateTime.of(2024, Month.FEBRUARY, 23, 15, 0);
    }

    // Constructor with validation for content length
    public NewsLetter(String content, LocalDateTime publishDateTime) {
        setContent(content); // Use the setter to leverage the validation logic
        this.publishDateTime = publishDateTime;
    }

    // Setter for content with validation
    public void setContent(String content) {
        if (content == null || content.length() < 100 || content.length() > 200) {
            throw new IllegalArgumentException("Content must be between 100 and 200 characters.");
        }
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPublishDateTime(LocalDateTime publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }
}
