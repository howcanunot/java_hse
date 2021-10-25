package battleship.models.ship;

import battleship.models.Coordinate;

public class Destroyer extends Ship {
    public Destroyer() {
        size = 2;
        shipCoords = new Coordinate[size];
    }

    /**
     *
     * @return class name
     */
    @Override
    public String toString() {
        return "Destroyer";
    }
}
