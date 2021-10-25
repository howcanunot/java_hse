package battleship.settings.input;

import battleship.models.Pair;

import java.util.Objects;

public class Parse {
    public static boolean tryParseDimensions(String[] input, Pair<Integer, Integer> pair) {
        try {
            if (input.length != 2) {
                throw new NumberFormatException();
            }
            int height = Integer.parseInt(input[0].trim());
            int width = Integer.parseInt(input[1].trim());
            pair.first = height;
            pair.second = width;
            return true;
        } catch (NumberFormatException exception) {
            System.out.print("[!] Dimensions has to be 2 integers > 0. Try again:\n");
            return false;
        }
    }

    public static boolean tryParseShips(String[] input, int[] ships) {
        try {
            if (input.length != 6) {
                throw new NumberFormatException();
            }
            ships[0] = Integer.parseInt(input[0].trim());
            ships[1] = Integer.parseInt(input[1].trim());
            ships[2] = Integer.parseInt(input[2].trim());
            ships[3] = Integer.parseInt(input[3].trim());
            ships[4] = Integer.parseInt(input[4].trim());
            ships[5] = Integer.parseInt(input[5].trim());
            int shipsCount = 0;
            for (int i = 0; i < ships.length - 1; i++) {
                if (ships[i] < 0) {
                    throw new NumberFormatException();
                } else {
                    shipsCount += ships[i];
                }
            }
            if (ships[5] > shipsCount) {
                throw new NumberFormatException();
            }
            return true;
        }
        catch (NumberFormatException exception) {
            System.out.print("[!] Numbers has to be integers > 0 and torpedoes count " +
                    "must be < number of the Fleet ships. Try again:\n");
            return false;
        }
    }

    public static boolean tryParseHitCoordinate(String[] input, Pair<Integer, Integer> pair,
                                                int height, int width) {
        try {
            if (input.length < 2 || input.length > 3) {
                throw new NumberFormatException();
            }
            int start = 0;
            if (input.length == 3 && Objects.equals(input[0], "T")) {
                start = 1;
            }
            int row = Integer.parseInt(input[start].trim());
            int column = Integer.parseInt(input[start + 1].trim());
            if (row > height || column > width ||
                    row <= 0 || column <= 0) {
                throw new NumberFormatException();
            }
            pair.first = row - 1;
            pair.second = column - 1;
            return true;
        } catch (NumberFormatException exception) {
            System.out.println("[!] Incorrect format. Row and column must be an integer >= 1 and <= field dimensions.\n" +
                    "If you want to use torpedo input T before coordinates.");
            return false;
        }
    }
}
