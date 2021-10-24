package battleship.models.ship;

import battleship.models.coordinate.Coordinate;

public class Submarine extends Ship {
    public Submarine() {
        size = 1;
        shipCoords = new Coordinate[size];
    }

    @Override
    public String toString() {
        return "Submarine";
    }
}
