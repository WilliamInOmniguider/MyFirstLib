package com.william.verticaltablib;

/**
 * Created by wiliiamwang on 27/10/2017.
 */

public class LocationPoint {

    float lat;
    float lng;

    public LocationPoint(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}
