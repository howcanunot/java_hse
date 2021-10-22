package battleship.models.coordinate;

public class Coordinate {

    private final int x;
    private final int y;
    private final boolean hasShip;

    private enum status{
        NOT_FIRED,
        FIRED_MISS,
        FIRED_HIN,
        SUNK
    }

    private final status coordinateStatus;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasShip = false;
        this.coordinateStatus = status.NOT_FIRED;
    }

}

