package battleship.models.ship;

import battleship.models.Coordinate;

public class Carrier extends Ship{
    public Carrier() {
        size = 5;
        shipCoords = new Coordinate[size];
    }

    /**
     *
     * @return class name
     */
    @Override
    public String toString() {
        return "Carrier";
    }
}
