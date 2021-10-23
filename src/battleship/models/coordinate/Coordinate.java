package battleship.models.coordinate;

public class Coordinate {

    private final int x;
    private final int y;
    private boolean isFree;
    private boolean isShip;

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
        isFree = true;
        coordinateStatus = status.NOT_FIRED;
    }

    public Coordinate(int x, int y, boolean isShip) {
        this.x = x;
        this.y = y;
        isFree = false;
        this.isShip = false;
        coordinateStatus = status.NOT_FIRED;
    }

    public boolean IsFree() {
        return isFree;
    }

    public boolean IsShip() {
        return isShip;
    }

    public void Take() {
        isFree = false;
    }

    public void SetShip() {
        isShip = true;
    }

}

