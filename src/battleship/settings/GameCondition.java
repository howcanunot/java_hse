package battleship.settings;

import battleship.models.coordinate.Coordinate;

public class GameCondition {
    private int height;
    private int width;
    private int submarineCount;
    private int destroyerCount;
    private int cruiserCount;
    private int battleshipCount;
    private int carrierCount;
    private int torpedoes;

    public GameCondition(int height, int width, int submarineCount, int destroyerCount,
                         int cruiserCount, int battleshipCount, int carrierCount, int torpedoes) {
        this.height = height;
        this.width = width;
        this.submarineCount = submarineCount;
        this.destroyerCount = destroyerCount;
        this.cruiserCount = cruiserCount;
        this.battleshipCount = battleshipCount;
        this.carrierCount = carrierCount;
        this.torpedoes = torpedoes;
    }

    public int getSubmarines() {
        return submarineCount;
    }

    public int getDestroyers()  {
        return destroyerCount;
    }

    public int getCruisers() {
        return cruiserCount;
    }

    public int getCarriers() {
        return carrierCount;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() { return width; }

    public int getTorpedoes() { return torpedoes; }

    public void decreaseTorpedo() { torpedoes--; }

    public int shipsCount() {
        return submarineCount + destroyerCount + cruiserCount + battleshipCount + carrierCount;
    }
}
