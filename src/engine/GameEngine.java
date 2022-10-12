package engine;

import engine.enumeration.BoxPosition;
import engine.helper.BoxBuilder;
import exception.*;
import exception.line.DuplicateLineException;
import exception.line.InvalidLineException;
import model.Box;
import model.Line;
import model.Player;
import model.dto.GameConfig;

import java.util.*;

public class GameEngine {

    private int emptyBox;

    private final List<Line> lines;
    private final GameConfig config;
    private final Map<Integer, Player> players;

    public GameEngine(GameConfig gameConfig) {
        if (!isValidConfig(gameConfig)) {
            throw new InvalidGameConfigException("Game config is not valid!! You pass the wrong parameter.");
        }
        this.config = gameConfig;
        this.lines = new ArrayList<>();
        this.emptyBox = (gameConfig.getRows()-1) * (gameConfig.getCols()-1);
        this.players = new LinkedHashMap<>(gameConfig.getNoOfPlayers());
    }

    public List<Line> getLines() {
        return lines;
    }

    public Player getPlayer(int playerId) {
        return players.containsKey(playerId) ? players.get(playerId) : null;
    }

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public GameConfig getConfig() {
        return config;
    }

    public boolean isGameOver() {
        return emptyBox <= 0;
    }

    public void addPlayer(final Player player) {
        if (config.getNoOfPlayers() <= players.size()) {
            throw new PlayersFullException("New player can't be add. This board is full!!");
        }
        players.put(player.getPlayerId(), player);
    }


    public Set<BoxPosition> createNewLine(final Line line) {
        if (emptyBox < 1) {
            throw new GameOverException("No more space for new box. This board is complete!!");
        }
        if (lines.contains(line)) {
            throw new DuplicateLineException("Entered Line already in the board!!");
        }
        if (!isValidLine(line)) {
            throw new InvalidLineException("Line is not valid!!");
        }

        final Set<BoxPosition> responseList = new HashSet<>();
        lines.add(line);    // adding line to games

        BoxBuilder boxBuilder = new BoxBuilder();
        Map<BoxPosition, Box> tempBoxes = boxBuilder.buildBoxes(line, config.getRows(), config.getCols());
        if (isBoxable(tempBoxes)) {
            addLineIfBoxComplete(responseList, tempBoxes);
        }

        return responseList;
    }

    public Set<BoxPosition> createNewLine(final Line line, final Player player) {
        Set<BoxPosition> responseList = createNewLine(line);
        if (!responseList.isEmpty()) {
            player.setScore(player.getScore() + responseList.size());
        }

        return responseList;
    }


    private void addLineIfBoxComplete(final Set<BoxPosition> responseList, final Map<BoxPosition, Box> tempBoxes) {
        for (BoxPosition position: BoxPosition.values()) {
            if (tempBoxes.containsKey(position) && isBoxComplete(tempBoxes.get(position))) {
                Line newLine = tempBoxes.get(position).getLine(position);
                lines.add(newLine);
                responseList.add(position);
                decreaseEmptyBox();
            }
        }
    }

    private void decreaseEmptyBox() {
        emptyBox--;
    }


    private boolean isBoxable(final Map<BoxPosition, Box> boxes) {
        return boxes.size() > 0;
    }

    private boolean isBoxComplete(final Box box) {
        return this.lines.containsAll(box.getLines());
    }

    private boolean isValidConfig(final GameConfig gameConfig) {
        return gameConfig != null && gameConfig.getRows() > 1 && gameConfig.getCols() > 1 && gameConfig.getNoOfPlayers() > 1;
    }

    private boolean isValidLine(final Line line) {
        return line.isDotInsideBoard(config.getRows(), config.getCols());
    }
}
