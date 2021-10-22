package battleship.settings;

import battleship.models.coordinate.Coordinate;

public class GameCondition {
    private int height;
    private int width;
    private Coordinate[][] battleGround;
    private int submarineCount;
    private int destroyerCount;
    private int cruiserCount;
    private int battleshipCount;
    private int carrierCount;

    public GameCondition(int height, int width, int submarineCount, int destroyerCount,
                         int cruiserCount, int battleshipCount, int carrierCount) {
        this.height = height;
        this.width = width;
        this.battleGround = new Coordinate[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.battleGround[i][j] = new Coordinate(i + 1, j + 1);
            }
        }

        this.submarineCount = submarineCount;
        this.destroyerCount = destroyerCount;
        this.cruiserCount = cruiserCount;
        this.battleshipCount = battleshipCount;
        this.carrierCount = carrierCount;
    }
}
