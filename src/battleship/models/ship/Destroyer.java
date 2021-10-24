package battleship.models.ship;

import battleship.models.coordinate.Coordinate;

public class Destroyer extends Ship {
    public Destroyer() {
        size = 2;
        shipCoords = new Coordinate[size];
    }

    @Override
    public String toString() {
        return "Destroyer";
    }
}
