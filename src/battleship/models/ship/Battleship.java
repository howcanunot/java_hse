package battleship.models.ship;

import battleship.models.coordinate.Coordinate;

public class Battleship extends Ship {
    public Battleship() {
        size = 4;
        shipCoords = new Coordinate[size];
    }

    @Override
    public String toString() {
        return "Battleship";
    }
}
