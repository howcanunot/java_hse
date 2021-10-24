package battleship.models.ship;

import battleship.models.coordinate.Coordinate;

abstract public class Ship {
    protected int size;
    protected Coordinate[] shipCoords;

    public int GetSize() {
        return size;
    }

    public void setCoordinates(int start_i, int end_i, int start_j, int end_j, Coordinate[][] coordinates) {
        int index = 0;
        for (int i = start_i; i <= end_i; i++) {
            for (int j = start_j; j <= end_j; j++) {
                shipCoords[index++] = coordinates[i][j];
            }
        }
    }

    public boolean isAllHit() {
        int hitCount = 0;
        for (var coord : shipCoords) {
            if (coord.getStatus() == Coordinate.status.FIRED_HIT) {
                hitCount++;
            }
        }

        return hitCount == shipCoords.length;
    }

    public boolean isAllSunk() {
        int sunkCount = 0;
        for (var coord : shipCoords) {
            if (coord.getStatus() == Coordinate.status.SUNK) {
                sunkCount++;
            }
        }

        return sunkCount == shipCoords.length;
    }

    public void sunkShip() {
        for (var coord : shipCoords) {
            coord.setSunk();
        }
    }

}
