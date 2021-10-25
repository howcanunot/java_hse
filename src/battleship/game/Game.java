package battleship.game;

import battleship.models.Coordinate;
import battleship.settings.GameCondition;
import battleship.settings.input.CommandLineInputHandle;
import battleship.settings.input.ConsoleInputHandle;
import battleship.models.ship.*;
import battleship.models.Pair;
import battleship.settings.input.Parse;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private GameCondition condition;
    private Ship[] ships;
    private Coordinate[][] coordinates;
    private boolean recovery;
    private Ship lastHitSip;

    private Game(GameCondition condition) {
        this.condition = condition;
        this.ships = new Ship[this.condition.shipsCount()];
        int index;

        for (index = 0; index < condition.getSubmarines(); index++) {
            ships[index] = new Submarine();
        }
        for (; index < condition.getSubmarines() + condition.getDestroyers(); index++) {
            ships[index] = new Destroyer();
        }
        for (; index < condition.getSubmarines() + condition.getDestroyers() + condition.getCruisers(); index++) {
            ships[index] = new Cruiser();
        }
        for (; index < ships.length - condition.getCarriers(); index++) {
            ships[index] = new Battleship();
        }
        for (; index < ships.length; index++) {
            ships[index] = new Carrier();
        }
        coordinates = new Coordinate[condition.getHeight()][condition.getWidth()];
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[0].length; j++) {
                coordinates[i][j] = new Coordinate(i, j);
            }
        }
    }

    /**
     * method starts game.
     * every turn user take row and column he wants to hit.
     * game ends when all ships sunk.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        Pair<Integer, Integer> pair = new Pair<>(0, 0);
        while (true) {
            System.out.println("[>] Input prefix T (optional), row and column number you want to hit, separated by comma:");
            String[] userInput = in.nextLine().split(",");
            if (Parse.tryParseHitCoordinate(userInput, pair, condition.getHeight(), condition.getWidth()) &&
                    handleHit(pair, userInput.length == 3)) {
                print();
                int sunkCount = 0;
                for (var ship : ships) {
                    if (ship.isAllSunk()) {
                        sunkCount++;
                    }
                }
                if (sunkCount == ships.length) {
                    break;
                }
            }
        }

        System.out.println("[I] All ships destroyed. You won!");
        System.exit(0);
    }

    /**
     * this method decides how create game conditions.
     * if command-line not correct user have to use console input.
     * @param args command-line arguments
     * @return game object with generated ships
     */
    public static Game gameCreator(String[] args) {
        Game game = null;

        do {
            if (game != null) {
                System.out.println("[!] Cannot generate battlefield. Try another configuration using console input\n");
            }
            if (args.length != 8 || game != null) {
                game = new Game(ConsoleInputHandle.getConditions());
            } else {
                game = new Game(CommandLineInputHandle.getConditions(args));
            }
        } while(game.generateShips());

        boolean isRecovery = isRecoveryMode();
        game.setRecovery(isRecovery);
        System.out.println("[I] Generation complete! Let's play!");
        System.out.println("[I] " + (isRecovery ? "recovery mode enable\n" : "recovery mode disable\n"));
        return game;
    }

    private static boolean isRecoveryMode() {
        System.out.println("[>] Do you want to set recovery mode? [Y/another string]:");
        var in = new Scanner(System.in);
        String result = in.nextLine().trim();

        return result.equals("Y") || result.equals("y");
    }

    private void setRecovery(boolean result) { recovery = result; }

    private void print() {
        boolean first;
        for (Coordinate[] coordinate : coordinates) {
            first = true;
            for (Coordinate value : coordinate) {
                if (first) {
                    System.out.print("|");
                    first = false;
                }
                System.out.print(value.getStatus() == Coordinate.status.NOT_FIRED ? " " :
                        (value.getStatus() == Coordinate.status.FIRED_MISS ? "X" :
                                (value.getStatus() == Coordinate.status.FIRED_HIT ? "#" : "*")));
                System.out.print("|");
            }
            System.out.println();
        }

        System.out.println();
    }

    private boolean generateShips() {
        int ATTEMPTS = 1000000;
        int attempts = 0;
        int index = 0;
        while (index < ships.length && attempts < ATTEMPTS) {
            attempts++;
            int i = ThreadLocalRandom.current().nextInt(0, condition.getHeight());
            int j = ThreadLocalRandom.current().nextInt(0, condition.getWidth());
            boolean isVertical = ThreadLocalRandom.current().nextBoolean();
            if (placeShip(index, i, j, isVertical)) {
                index++;
            }

            if (attempts % 1000 == 0 && index != ships.length) {
                index = 0;
                for (int row = 0; row < coordinates.length; row++) {
                    for (int column = 0; column < coordinates[0].length; column++) {
                        coordinates[row][column] = new Coordinate(row, column);
                    }
                }
            }
        }

        return attempts == ATTEMPTS;
    }

    private boolean placeShip(int index, int start_i, int start_j, boolean isVertical) {
        int end_i, end_j;
        if (isVertical) {
            end_i = start_i + ships[index].getSize() - 1;
            end_j = start_j;
        } else {
            end_i = start_i;
            end_j = start_j + ships[index].getSize() - 1;
        }

        if (validatePlace(start_i, end_i, start_j, end_j)) {
            ships[index].setCoordinates(start_i, end_i, start_j, end_j, coordinates);
            setShips(start_i, end_i, start_j, end_j, index);
            return true;
        }
        return false;
    }

    private boolean validatePlace(int start_i, int end_i, int start_j, int end_j) {
        if (end_i >= condition.getHeight() || end_j >= condition.getWidth()) {
            return false;
        }

        for (int row = start_i; row <= end_i; row++)
            for (int column = start_j; column <= end_j; column++)
                if (!coordinates[row][column].isFree())
                    return false;

        start_i = Math.max(0, start_i - 1);
        end_i = Math.min(condition.getHeight() - 1, end_i + 1);
        start_j = Math.max(0, start_j - 1);
        end_j = Math.min(condition.getWidth() - 1, end_j + 1);
        takePlace(start_i, end_i, start_j, end_j);
        return true;
    }

    private void takePlace(int start_i, int end_i, int start_j, int end_j) {
        for (int row = start_i; row <= end_i; row++) {
            for (int column = start_j; column <= end_j; column++) {
                coordinates[row][column].take();
            }
        }
    }

    private void setShips(int start_i, int end_i, int start_j, int end_j, int index) {
        for (int i = start_i; i <= end_i; i++) {
            for (int j = start_j; j <= end_j; j++) {
                coordinates[i][j].setShip(ships[index]);
            }
        }
    }

    private boolean handleHit(Pair<Integer, Integer> pair, boolean useTorpedo) {
        if ((condition.getTorpedoes() == 0 && useTorpedo)) {
            System.out.println("[!] No torpedoes available");
            return false;
        }
        int row = pair.first, column = pair.second;
        if (coordinates[row][column].getStatus() != Coordinate.status.NOT_FIRED) {
            System.out.println("[I] This coordinate was already hit");
            return false;
        }
        if (recovery && lastHitSip != coordinates[row][column].getShip() && lastHitSip != null) {
            lastHitSip.hide();
            System.out.println("[I] You missed last ship. Ship was recover");
        }
        lastHitSip = coordinates[row][column].getShip();
        boolean isTorpedoUsed = coordinates[row][column].hit(useTorpedo);
        if (isTorpedoUsed) {
            condition.decreaseTorpedo();
            System.out.println("[I] You used torpedo, " + condition.getTorpedoes() + " left");
        }
        if (coordinates[row][column].getStatus() == Coordinate.status.SUNK) {
            lastHitSip = null;
        }
        return true;
    }
}
