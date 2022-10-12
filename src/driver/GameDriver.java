package driver;

import engine.GameEngine;
import engine.enumeration.BoxPosition;
import exception.*;
import exception.line.LineException;
import model.Line;
import model.Player;
import model.dto.GameConfig;
import view.ConsoleView;

import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class GameDriver {

    private static GameEngine engine;
    private static ConsoleView view;
    private static int choice;      // 0=exit, 1=play, 9=backToMenu

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        view = new ConsoleView();
        view.displayWelcomeMessage();
        view.displayPlayOptions();

        choice = view.getPlayerChoice();
        while (choice != 0) {
            if (choice == 1) {
                try {
                    // initialize engine
                    GameConfig gameConfig = view.getGameConfig();
                    engine = new GameEngine(gameConfig);

                    // add players to engine
                    for (int i = 1; i <= gameConfig.getNoOfPlayers(); i++) {
                        String playerName = view.getNewPlayerName(i);
                        Player player = new Player(playerName);

                        engine.addPlayer(player);
                        view.displayNewPlayer(player);
                    }

                    play();
                    choice = 9;
                } catch (InvalidGameConfigException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (choice == 9){
                view.displayWelcomeMessage();
                view.displayPlayOptions();
                choice = view.getPlayerChoice();
            }
        }
    }

    private static void play() {
        boolean gameOver = false;
        while (!gameOver) {
            Map<Integer, Player> players = engine.getPlayers();
            for (Integer playerId: players.keySet()) {
                while (true) {
                    int interruptCode = processMove(players.get(playerId));
                    if (interruptCode == 9) {
                        choice = 9;
                        return;     // back to caller as it interrupt to main menu
                    } else if (interruptCode == 1) {
                        break;   // change player
                    }

                    if (engine.isGameOver()) {
                        view.displayFinalResult(engine.getPlayers());
                        gameOver = true;
                        break;
                    }
                }
            }
        }
    }

    private static int processMove(final Player player) {
        while (true) {
            try {
                Optional<Line> line = view.getNewLine(player);
                if (line.isEmpty()) {
                    return 9;
                }

                Set<BoxPosition> lineResult = engine.createNewLine(line.get(), player);
                view.displayLineResult(lineResult, player);

                if (lineResult.isEmpty()) {
                    return 1;
                }

                return 0;
            } catch (LineException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println(e);
            }
        }
    }
}
