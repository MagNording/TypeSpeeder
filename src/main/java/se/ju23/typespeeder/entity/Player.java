package se.ju23.typespeeder.entity;

import jakarta.persistence.*;
import se.ju23.typespeeder.util.Messages;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String playername;
    private int XP; // får xp från result
    private int level; //++ för varje 100e XP

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Result> results;

    public Player() {}

    public Player(String username, String password, String playername, int XP, int level,
                  List<Result> results) {
        this.username = username;
        this.password = password;
        this.playername = playername;
        this.XP = XP;
        this.level = level;
        this.results = results;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return String.format("Playername: %s | XP: %d | Level: %d", playername, XP, level);
    }

    public String generateResultsTable(Messages messages) {
        StringBuilder sb = new StringBuilder();
        sb.append(messages.get("player.name")).append(": ").append(playername).append(" | ")
                .append(messages.get("player.xp")).append(": ").append(XP).append(" | ")
                .append(messages.get("player.level")).append(": ").append(level).append("\n");
        sb.append(messages.get("results.latest")).append("\n");

        if (results != null && !results.isEmpty()) {
            // Sort results by ID in descending order to get the latest ones
            results.sort(Comparator.comparingInt(Result::getId).reversed());

            // Get the latest 10 results
            List<Result> latestResults = results.stream().limit(10).toList();

            for (Result result : latestResults) {
                sb.append(result.toLocalizedString(messages)).append("\n");
            }
        } else {
            sb.append(messages.get("results.noResults")).append("\n");
        }

        return sb.toString();
    }

}

