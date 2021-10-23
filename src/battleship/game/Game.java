package battleship.game;

import battleship.models.coordinate.Coordinate;
import battleship.settings.GameCondition;
import battleship.settings.input.CommandLineInputHandle;
import battleship.settings.input.ConsoleInputHandle;
import battleship.models.ship.*;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private GameCondition condition;
    private Ship[] ships;
    private Coordinate[][] coordinates;

    public Game(GameCondition condition) {
        this.condition = condition;
        this.ships = new Ship[this.condition.ShipsCount()];
        int index;

        for (index = 0; index < condition.GetSubmarines(); index++) {
            ships[index] = new Submarine();
        }
        for (; index < condition.GetSubmarines() + condition.GetDestroyers(); index++) {
            ships[index] = new Destroyer();
        }
        for (; index < condition.GetSubmarines() + condition.GetDestroyers() + condition.GetCruisers(); index++) {
            ships[index] = new Cruiser();
        }
        for (; index < ships.length - condition.GetCarriers(); index++) {
            ships[index] = new Battleship();
        }
        for (; index < ships.length; index++) {
            ships[index] = new Carrier();
        }
        coordinates = new Coordinate[condition.GetHeight()][condition.GetWidth()];
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[0].length; j++) {
                coordinates[i][j] = new Coordinate(i, j);
            }
        }

        GenerateShips();
        Print();
    }

    private void Print() {
        for (Coordinate[] coordinate : coordinates) {
            for (Coordinate value : coordinate) {
                System.out.print(value.IsShip() ? "*" : "#");
            }
            System.out.println();
        }
    }

    private void GenerateShips() {
        int MAX_ATTEMPTS = 1000000;
        int attempts = 0;
        int index = 0;
        while (index < ships.length && attempts < MAX_ATTEMPTS) {
            attempts++;
            int i = ThreadLocalRandom.current().nextInt(0, condition.GetHeight() + 1);
            int j = ThreadLocalRandom.current().nextInt(0, condition.GetWidth() + 1);
            boolean isVertical = ThreadLocalRandom.current().nextBoolean();
            if (PlaceShip(index, i, j, isVertical)) {
                index++;
            }
        }

        if (attempts == MAX_ATTEMPTS) {
            System.out.println("[!] Cannot generate battlefield.");
        }
    }

    private boolean PlaceShip(int index, int start_i, int start_j, boolean isVertical) {
        int end_i, end_j;
        if (isVertical) {
            end_i = start_i + ships[index].GetSize() - 1;
            end_j = start_j;
        } else {
            end_i = start_i;
            end_j = start_j + ships[index].GetSize() - 1;
        }

        if (ValidatePlace(start_i, end_i, start_j, end_j)) {
            ships[index].SetCoordinates(start_i, end_i, start_j, end_j);
            SetShips(start_i, end_i, start_j, end_j);
            return true;
        }
        return false;
    }

    private boolean ValidatePlace(int start_i, int end_i, int start_j, int end_j) {
        if (end_i >= condition.GetHeight() || end_j >= condition.GetWidth()) {
            return false;
        }
        start_i = Math.max(0, start_i - 1);
        end_i = Math.min(condition.GetHeight() - 1, end_i + 1);
        start_j = Math.max(0, start_j - 1);
        end_j = Math.min(condition.GetWidth() - 1, end_j + 1);

        for (int row = start_i; row <= end_i; row++)
            for (int column = start_j; column <= end_j; column++)
                if (!coordinates[row][column].IsFree())
                    return false;

        TakePlace(start_i, end_i, start_j, end_j);
        return true;
    }

    private void TakePlace(int start_i, int end_i, int start_j, int end_j) {
        for (int row = start_i; row <= end_i; row++) {
            for (int column = start_j; column <= end_j; column++) {
                coordinates[row][column].Take();
            }
        }
    }

    private void SetShips(int start_i, int end_i, int start_j, int end_j) {
        for (int i = start_i; i <= end_i; i++) {
            for (int j = start_j; j <= end_j; j++) {
                coordinates[i][j].SetShip();
            }
        }
    }


    public static Game GameCreator(String[] args) {
        if (args.length != 7) {
            return new Game(ConsoleInputHandle.GetConditions());
        }
        return new Game(CommandLineInputHandle.GetConditions(args));
    }
}
