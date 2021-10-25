package battleship.settings;

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

    /**
     *
     * @return submarines count
     */
    public int getSubmarines() {
        return submarineCount;
    }

    /**
     *
     * @return destroyers count
     */
    public int getDestroyers()  {
        return destroyerCount;
    }

    /**
     *
     * @return cruisers count
     */
    public int getCruisers() {
        return cruiserCount;
    }

    /**
     *
     * @return carriers count
     */
    public int getCarriers() {
        return carrierCount;
    }

    /**
     *
     * @return height size
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return width size
     */
    public int getWidth() { return width; }

    /**
     *
     * @return torpedoes count
     */
    public int getTorpedoes() { return torpedoes; }

    /**
     * decrease torpedoes count
     */
    public void decreaseTorpedo() { torpedoes--; }

    /**
     *
     * @return sum of all ships
     */
    public int shipsCount() {
        return submarineCount + destroyerCount + cruiserCount + battleshipCount + carrierCount;
    }
}
