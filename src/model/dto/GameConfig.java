package model.dto;

public class GameConfig {

    private final int rows;
    private final int cols;
    private final int noOfPlayers;

    public GameConfig(int rows, int cols, int noOfPlayers) {
        this.rows = rows;
        this.cols = cols;
        this.noOfPlayers = noOfPlayers;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }
}
