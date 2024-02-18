package se.ju23.typespeeder.service;

import java.time.LocalDateTime;
import java.time.Month;

public class Patch {
    private String patchVersion;
    public LocalDateTime releaseDateTime;

    public Patch() {
        this.patchVersion = "1.0";
        this.releaseDateTime = LocalDateTime.of(2024, Month.FEBRUARY, 23, 15, 0);
    }

    // Getter for releaseDateTime
    public LocalDateTime getReleaseDateTime() {
        return releaseDateTime;
    }

    // Setter for releaseDateTime, if needed
    public void setReleaseDateTime(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    // Getter for patchVersion
    public String getPatchVersion() {
        return patchVersion;
    }

    // Setter for patchVersion
    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }
}
