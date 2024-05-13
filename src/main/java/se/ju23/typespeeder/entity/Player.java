package se.ju23.typespeeder.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String playername;
    private int XP;
    private int level; //++ för varje 100e XP

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Result> results;

    public Player() {}

    public Player(String username, String password, String playername, int XP, int level, List<Result> results) {
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
        return "\n\033[1mPlayerid:\033[0m " + id + " | " +
                "\033[1mUsername:\033[0m " + username + " | " +
                "\033[1mPassword:\033[0m " + password + " | " +
                "\033[1mGamertag: \033[0m " + playername + " | ";
    }
}

