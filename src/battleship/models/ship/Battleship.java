package battleship.models.ship;

import battleship.models.Coordinate;

public class Battleship extends Ship {
    public Battleship() {
        size = 4;
        shipCoords = new Coordinate[size];
    }

    /**
     *
     * @return class name
     */
    @Override
    public String toString() {
        return "Battleship";
    }
}
