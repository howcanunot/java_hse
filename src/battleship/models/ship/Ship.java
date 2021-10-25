package battleship.models.ship;

import battleship.models.Coordinate;

abstract public class Ship {
    protected int size;
    protected Coordinate[] shipCoords;

    /**
     *
     * @return ship size
     */
    public int GetSize() {
        return size;
    }

    /**
     * bind ships coordinate
     * @param start_i left-up row corner
     * @param end_i right-down row corner
     * @param start_j left-up column corner
     * @param end_j right-down column corner
     * @param coordinates field
     */
    public void setCoordinates(int start_i, int end_i, int start_j, int end_j, Coordinate[][] coordinates) {
        int index = 0;
        for (int i = start_i; i <= end_i; i++) {
            for (int j = start_j; j <= end_j; j++) {
                shipCoords[index++] = coordinates[i][j];
            }
        }
    }

    /**
     *
     * @return true if all ship's coordinates was hit
     */
    public boolean isAllHit() {
        int hitCount = 0;
        for (var coord : shipCoords) {
            if (coord.getStatus() == Coordinate.status.FIRED_HIT) {
                hitCount++;
            }
        }

        return hitCount == shipCoords.length;
    }

    /**
     *
     * @return true if all ship's coordinate already sunk
     */
    public boolean isAllSunk() {
        int sunkCount = 0;
        for (var coord : shipCoords) {
            if (coord.getStatus() == Coordinate.status.SUNK) {
                sunkCount++;
            }
        }

        return sunkCount == shipCoords.length;
    }

    /**
     * make all ship sunk
     */
    public void sunkShip() {
        for (var coord : shipCoords) {
            coord.setSunk();
        }
    }

    /**
     * set all ship's coordinate NOT_FIRED
     */
    public void hide() {
        for (var coord : shipCoords) {
            coord.recovery();
        }
    }

}
