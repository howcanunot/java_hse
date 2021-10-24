package battleship.models.ship;

import battleship.models.coordinate.Coordinate;

public class Cruiser extends Ship {
    public Cruiser() {
        size = 3;
        shipCoords = new Coordinate[size];
    }

    @Override
    public String toString() {
        return "Cruiser";
    }
}
