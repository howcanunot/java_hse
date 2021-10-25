package battleship;

import battleship.game.Game;

public class Main {
    public static void main(String[] argc) {
        Game game = Game.gameCreator(argc);
        game.run();
    }
}
