package battleship.models.ship;

import battleship.models.coordinate.Coordinate;

public class Carrier extends Ship{
    public Carrier() {
        size = 5;
        shipCoords = new Coordinate[size];
    }

    @Override
    public String toString() {
        return "Carrier";
    }
}
