package battleship.models.ship;

import battleship.models.Coordinate;

public class Submarine extends Ship {
    public Submarine() {
        size = 1;
        shipCoords = new Coordinate[size];
    }

    /**
     *
     * @return class name
     */
    @Override
    public String toString() {
        return "Submarine";
    }
}
