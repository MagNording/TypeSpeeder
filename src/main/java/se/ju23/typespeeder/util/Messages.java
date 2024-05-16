package se.ju23.typespeeder.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    private ResourceBundle resourceBundle;

    public Messages(String language) {
        Locale locale = new Locale(language);
        resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public String get(String key) {
        return resourceBundle.getString(key);
    }
}
