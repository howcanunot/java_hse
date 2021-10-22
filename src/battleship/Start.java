package battleship;

import battleship.game.Game;

public class Start {
    public static void main(String[] argc) {
        Game game = Game.GameCreator(argc);
    }
}
