package view;

import engine.enumeration.BoxPosition;
import exception.line.ReverseLineException;
import model.Dot;
import model.Line;
import model.dto.GameConfig;
import model.Player;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class ConsoleView {
    Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println();
        System.out.println();
        System.out.println("======= Dots and Box =======");
        System.out.println("Welcome to the world of Dots...");
        System.out.println("Let's make it box.");
    }

    public void displayPlayOptions() {
        System.out.println();
        System.out.println("1. Play Game");
        System.out.println("0. Exit");
    }

    public int getPlayerChoice() throws NumberFormatException {
        System.out.println();
        System.out.print("Please press any number from above: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public GameConfig getGameConfig() throws NumberFormatException {
        System.out.println();
        System.out.println("Please specify belows information,");

        System.out.print("No of rows: ");
        int rows = Integer.parseInt(scanner.nextLine());

        System.out.print("No of columns: ");
        int cols = Integer.parseInt(scanner.nextLine());

        System.out.print("No of players: ");
        int noOfPlayers = Integer.parseInt(scanner.nextLine());

        return new GameConfig(rows, cols, noOfPlayers);
    }

    public String getNewPlayerName(int i) {
        System.out.println();
        System.out.println("Please enter player " + i + " information,");

        System.out.print("Name: ");
        return scanner.nextLine();
    }

    public void displayNewPlayer(Player player) {
        System.out.println("Player added successfully..");
        System.out.println(player);
    }

    public Optional<Line> getNewLine(Player player) throws NumberFormatException, StringIndexOutOfBoundsException, ReverseLineException {
        System.out.println();
        System.out.println(player.getName() + ", Enter a line dots (ex 11 21): ");
        String dotsStr = scanner.nextLine();
        if ("menu".equals(dotsStr)) {
            return Optional.empty();
        }

        Dot startDot = new Dot(Integer.parseInt(dotsStr.substring(0, 1)),Integer.parseInt(dotsStr.substring(1, 2)));
        Dot endDot = new Dot(Integer.parseInt(dotsStr.substring(3, 4)),Integer.parseInt(dotsStr.substring(4, 5)));

        return Optional.of(new Line(startDot, endDot));
    }

    public void displayLineResult(Set<BoxPosition> lineResult, Player player) {
        System.out.println("New line added.");
        if (lineResult.isEmpty()) {
            System.out.println(player.getName() + " : " + player.getScore());
            return;
        }
        for (BoxPosition position: BoxPosition.values()) {
            if (lineResult.contains(position)) {
                System.out.println(player.getName() + " grab the " + position.name() + " position.");
            }
        }
        System.out.println(player.getName() + " : " + player.getScore());
    }

    public void displayFinalResult(Map<Integer, Player> players) {
        System.out.println();
        System.out.println("===================");
        System.out.println("This game is over!!");
        for (Integer playerId: players.keySet()) {
            System.out.println(players.get(playerId));
        }
    }
}
