package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Player implements Comparable<Player> {
    private static final AtomicInteger count = new AtomicInteger(0);

    private final int playerId;
    private String name;
    private int score;

    public Player(String name) {
        this.playerId = count.incrementAndGet();
        this.name = name;
        this.score = 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public int compareTo(Player player) {
        return this.playerId - player.getPlayerId();
    }

    @Override
    public String toString() {
        return "Player ID is " + playerId + " and Name " + name + " with score " + score;
    }
}
