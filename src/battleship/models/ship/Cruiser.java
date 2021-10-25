package battleship.models.ship;

import battleship.models.Coordinate;

public class Cruiser extends Ship {
    public Cruiser() {
        size = 3;
        shipCoords = new Coordinate[size];
    }

    /**
     *
     * @return class name
     */
    @Override
    public String toString() {
        return "Cruiser";
    }
}
