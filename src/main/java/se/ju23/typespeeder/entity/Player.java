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

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Result> results;

    public Player() {}

    public Player(String username, String password, String playername) {
        this.username = username;
        this.password = password;
        this.playername = playername;
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

