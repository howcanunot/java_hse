package battleship.settings.input;

import battleship.settings.GameCondition;

public class CommandLineInputHandle {
    /**
     * handle command-line input
     * @param args command-line arguments
     * @return GameCondition object with all game settings
     */
    public static GameCondition getConditions(String[] args) {
        int[] data = new int[8];

        for (int i = 0; i < data.length; i++) {
            try {
                data[i] = Integer.parseInt(args[i]);
                if (data[i] < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException exception) {
                System.out.println("[!] Incorrect command-line argument: " + args[i] + " must be an integer.\n" +
                        "Set configuration using console.");
                return ConsoleInputHandle.getConditions();
            }
        }

        return new GameCondition(data[0], data[1], data[6], data[5], data[4], data[3], data[2], data[7]);
    }
}
