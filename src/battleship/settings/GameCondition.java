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

    public GameCondition(int height, int width, int submarineCount, int destroyerCount,
                         int cruiserCount, int battleshipCount, int carrierCount) {
        this.height = height;
        this.width = width;
        this.submarineCount = submarineCount;
        this.destroyerCount = destroyerCount;
        this.cruiserCount = cruiserCount;
        this.battleshipCount = battleshipCount;
        this.carrierCount = carrierCount;
    }

    public int GetSubmarines() {
        return submarineCount;
    }

    public int GetDestroyers()  {
        return destroyerCount;
    }

    public int GetCruisers() {
        return cruiserCount;
    }

    public int GetBattleships() {
        return battleshipCount;
    }

    public int GetCarriers() {
        return carrierCount;
    }

    public int GetHeight() {
        return height;
    }

    public int GetWidth() {
        return width;
    }

    public int ShipsCount() {
        return submarineCount + destroyerCount + cruiserCount + battleshipCount + carrierCount;
    }
}
