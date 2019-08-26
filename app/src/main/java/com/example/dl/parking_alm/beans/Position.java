package com.example.dl.parking_alm.beans;

/**
 * The type Position.
 */
public class Position
{
    private double latitude;
    private double longitude;

    /**
     * Instantiates a new Position.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
//Constructor
    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
//Getters/Setters
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
