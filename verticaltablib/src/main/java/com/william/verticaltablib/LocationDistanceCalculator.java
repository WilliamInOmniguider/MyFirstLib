package com.william.verticaltablib;

/**
 * Created by wiliiamwang on 27/10/2017.
 */

public class LocationDistanceCalculator {

    private static final int EARTH_RADIUS_IN_KILOMETERS = 6371;

    public static float calculateDistance(LocationPoint pointA, LocationPoint pointB) {

        double dLat = Math.toRadians(pointB.getLat() - pointA.getLat());
        double dLng = Math.toRadians(pointB.getLng() - pointA.getLng());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(pointA.getLat())) *
                        Math.cos(Math.toRadians(pointB.getLat())) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (EARTH_RADIUS_IN_KILOMETERS * c);
    }

}
