package battleship.models.ship;

import battleship.models.coordinate.Coordinate;

abstract public class Ship {
    protected int size;
    protected Coordinate[] shipCoords;

    abstract public int GetSize();

    abstract public void SetCoordinates(int start_i, int end_i, int start_j, int end_j);
}
