package battleship.game;

import battleship.settings.GameCondition;
import battleship.settings.input.CommandLineInputHandle;
import battleship.settings.input.ConsoleInputHandle;

public class Game {
    private GameCondition condition;

    public Game(GameCondition condition) {
        this.condition = condition;
    }

    public static Game GameCreator(String[] args) {
        if (args.length != 7) {
            return new Game(ConsoleInputHandle.GetConditions());
        }
        return new Game(CommandLineInputHandle.GetConditions(args));
    }
}
