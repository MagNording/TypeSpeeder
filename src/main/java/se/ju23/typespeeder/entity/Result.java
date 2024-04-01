package se.ju23.typespeeder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int result;

    @ManyToOne
    @JoinColumn(name = "playername", referencedColumnName = "id")
    private Player player;

    public Result() {}

    public Result(int result, Player player) {
        this.result = result;
        this.player = player;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Player getUser() {
        return player;
    }

    public void setUser(Player player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", result=" + result +
                ", user=" + player +
                '}';
    }


}

