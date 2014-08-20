package com.gurkensalat.osm.utils;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class GreatCircle
{
    public static double distanceInKm(double startLat, double startLon, double endLat, double endLon)
    {
        LatLng startPoint = new LatLng(startLat, startLon);
        LatLng endPoint = new LatLng(endLat, endLon);
        return LatLngTool.distance(startPoint, endPoint, LengthUnit.KILOMETER);
    }
}
