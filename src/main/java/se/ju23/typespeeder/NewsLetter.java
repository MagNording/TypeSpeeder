package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.Messages;

import java.time.LocalDateTime;

@Component
public class NewsLetter {
    private final Menu menu;
    private String content;
    public LocalDateTime publishDateTime;

    @Autowired
    public NewsLetter(Menu menu) {
        this.menu = menu;
        updateContent();
        this.publishDateTime = LocalDateTime.parse("2024-05-22T14:15:15");
    }

    public void updateContent() {
        Messages messages = new Messages(menu.getLanguage());
        this.content = messages.get("newsletter.content");
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }

    @Override
    public String toString() {
        return String.format("%s\n\nPublished: %d %s %d",
                content,
                publishDateTime.getDayOfMonth(),
                publishDateTime.getMonth().toString().charAt(0) +
                publishDateTime.getMonth().toString().substring(1).toLowerCase(),
                publishDateTime.getYear());
    }
}
