package battleship.models.ship;

import battleship.models.coordinate.Coordinate;

public class Battleship extends Ship {
    protected int size;

    public Battleship() {
        size = 4;
        shipCoords = new Coordinate[size];
    }

    @Override
    public int GetSize() {
        return size;
    }

    @Override
    public void SetCoordinates(int start_i, int end_i, int start_j, int end_j) {
        int index = 0;
        for (int i = start_i; i <= end_i; i++) {
            for (int j = start_j; j <= end_j; j++) {
                shipCoords[index++] = new Coordinate(i, j, true);
            }
        }
    }
}
