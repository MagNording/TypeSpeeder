package se.ju23.typespeeder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int result;
    private double timeResult; //hur lång tid ett game tar
    private int amountResult; //antal rätt i procent

    @ManyToOne
    @JoinColumn(name = "playername", referencedColumnName = "id")
    private Player player;

    public Result() {}

    public Result(int result, double timeResult, int amountResult, Player player) {
        this.result = result;
        this.timeResult = timeResult;
        this.amountResult = amountResult;
        this.player = player;
    }

    public double getTimeResult() {
        return timeResult;
    }

    public void setTimeResult(double timeResult) {
        this.timeResult = timeResult;
    }

    public int getAmountResult() {
        return amountResult;
    }

    public void setAmountResult(int amountResult) {
        this.amountResult = amountResult;
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
                ", timeResult=" + timeResult +
                ", amountResult=" + amountResult +
                ", player=" + player +
                '}';
    }
}

