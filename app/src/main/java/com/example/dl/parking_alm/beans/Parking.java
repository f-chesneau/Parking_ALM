package com.example.dl.parking_alm.beans;

/**
 * The type Parking.
 */
public class Parking
{
    private String id;
    private Position position;
    private int totalPlaces;
    private int emptyPlaces;

    /**
     * Instantiates a new Parking.
     *
     * @param id          the id
     * @param position    the position
     * @param totalPlaces the total places
     * @param emptyPlaces the empty places
     */
    public Parking(String id, Position position, int totalPlaces, int emptyPlaces) {
        this.id = id;
        this.position = position;
        this.totalPlaces = totalPlaces;
        this.emptyPlaces = emptyPlaces;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Gets empty places.
     *
     * @return the empty places
     */
    public int getEmptyPlaces() {
        return emptyPlaces;
    }

    /**
     * Sets empty places.
     *
     * @param emptyPlaces the empty places
     */
    public void setEmptyPlaces(int emptyPlaces) {
        this.emptyPlaces = emptyPlaces;
    }

    /**
     * Gets total places.
     *
     * @return the total places
     */
    public int getTotalPlaces() {
        return totalPlaces;
    }

    /**
     * Sets total places.
     *
     * @param totalPlaces the total places
     */
    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }
}
