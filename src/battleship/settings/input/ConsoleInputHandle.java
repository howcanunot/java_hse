package battleship.settings.input;

import battleship.settings.GameCondition;

import java.util.Scanner;

public class ConsoleInputHandle {
    public static GameCondition GetConditions() {
        int rows, cells;
        int submarineCount, destroyerCount, cruiserCount, battleshipCount, carrierCount;
        System.out.println("Enter the dimensions of the battleground, separated by comma (rows count, cells count):");

        Scanner in = new Scanner(System.in);
        String[] data = in.nextLine().split(",");

        while(true) {
            try {
                if (data.length != 2) {
                    throw new NumberFormatException();
                }
                rows = Integer.parseInt(data[0].trim());
                cells = Integer.parseInt(data[1].trim());
                if (rows < 0 || cells < 0) {
                    throw new NumberFormatException();
                }
                System.out.println();
                break;
            } catch (NumberFormatException exception) {
                System.out.print("[!] Dimensions has to be 2 integers > 0. Try again:\n");
                data = in.nextLine().split(",");
            }
        }

        System.out.print("Enter the number of each ship type, separated by commas:\n");
        data = in.nextLine().split(",");
        while (true) {
            try {
                if (data.length != 5) {
                    throw new NumberFormatException();
                }
                submarineCount = Integer.parseInt(data[0].trim());
                destroyerCount = Integer.parseInt(data[1].trim());
                cruiserCount = Integer.parseInt(data[2].trim());
                battleshipCount = Integer.parseInt(data[3].trim());
                carrierCount = Integer.parseInt(data[4].trim());
                if (submarineCount < 0 || destroyerCount < 0 || cruiserCount < 0 ||
                battleshipCount < 0 || carrierCount < 0) {
                    throw new NumberFormatException();
                }
                System.out.println("\n");
                break;
            }
            catch (NumberFormatException exception) {
                System.out.print("[!] Numbers has to be integers > 0. Try again:\n");
                data = in.nextLine().split(",");
            }
        }

        return new GameCondition(rows, cells, submarineCount, destroyerCount,
                cruiserCount, battleshipCount, carrierCount);
    }

}
