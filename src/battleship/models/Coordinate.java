package battleship.models;

import battleship.models.ship.Ship;

public class Coordinate {

    private final int x;
    private final int y;
    private boolean isFree;
    private Ship ship;

    public enum status{
        NOT_FIRED,
        FIRED_MISS,
        FIRED_HIT,
        SUNK
    }

    private status coordinateStatus;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        isFree = true;
        coordinateStatus = status.NOT_FIRED;
    }

    public Coordinate(int x, int y, Ship ship) {
        this.x = x;
        this.y = y;
        isFree = false;
        this.ship = ship;
        coordinateStatus = status.NOT_FIRED;
    }

    /**
     *
     * @return true is coordinate free else false
     */
    public boolean isFree() {
        return isFree;
    }

    /**
     *
     * @return true if coordinate has ship
     */

    public boolean hasShip() { return ship == null; }

    /**
     *
     * @return ship on this coordinate
     */
    public Ship getShip() { return ship; }

    /**
     * this set coordinate not free
     */
    public void take() { isFree = false; }

    /**
     * bind ship on coordinate
     * @param ship to bind
     */
    public void setShip(Ship ship) { this.ship = ship; }

    /**
     *
     * @return status of coordinate
     */
    public status getStatus() { return coordinateStatus; }

    /**
     * set coordinate as a sunk
     */
    public void setSunk() { coordinateStatus = status.SUNK; }

    /**
     * hit coordinate handle
     * @param useTorpedo is torpedo was use
     * @return true is torpedo used else false
     */
    public boolean hit(boolean useTorpedo) {
        if (ship == null) {
            coordinateStatus = status.FIRED_MISS;
            System.out.println("[I] Miss");
            return useTorpedo;
        } else {
            System.out.println("[I] Hit");
            if (!useTorpedo) {
                coordinateStatus = status.FIRED_HIT;
            } else {
                ship.sunkShip();
                System.out.println("[I] You just have sunk a  " + ship.toString() + "!");
                return true;
            }
        }
        if (ship.isAllHit()) {
            ship.sunkShip();
            System.out.println("[I] You just have sunk a  " + ship.toString() + "!");
        }
        return false;
    }

    /**
     * Set coordinate NOT_FIRED.
     */
    public void recovery() {
        coordinateStatus = status.NOT_FIRED;
    }
}

