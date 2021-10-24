package battleship.settings.input;

import battleship.models.Pair;
import battleship.settings.GameCondition;

import java.util.Scanner;

public class ConsoleInputHandle {
    public static GameCondition getConditions() {
        System.out.println("[>] Enter the dimensions of the battleground, separated by comma (rows count, cells count):");
        Scanner in = new Scanner(System.in);

        String[] data = in.nextLine().split(",");
        Pair<Integer, Integer> dim = new Pair<>(0, 0);

        while (!Parse.tryParseDimensions(data, dim)) {
            data = in.nextLine().split(",");
        }
        int rows = dim.first, cells = dim.second;

        System.out.print("[>] Enter the number of each ship type and torpedoes, separated by commas:\n");
        data = in.nextLine().split(",");
        int[] ships = new int[6];

        while (!Parse.tryParseShips(data, ships)) {
            data = in.nextLine().split(",");
        }

        return new GameCondition(rows, cells, ships[4], ships[3],
                ships[2], ships[1], ships[0], ships[5]);
    }

}
