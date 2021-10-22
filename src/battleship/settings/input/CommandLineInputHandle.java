package battleship.settings.input;

import battleship.settings.GameCondition;

public class CommandLineInputHandle {
    public static GameCondition GetConditions(String[] args) {
        int[] data = new int[7];

        for (int i = 0; i < data.length; i++) {
            try {
                data[i] = Integer.parseInt(args[i]);
                if (data[i] < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException exception) {
                System.out.println("[!] Incorrect command-line argument: " + args[i] + "must be an integer.\n" +
                        "Change command-line arguments and run again.");
                System.exit(0);
            }
        }

        return new GameCondition(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
    }
}
