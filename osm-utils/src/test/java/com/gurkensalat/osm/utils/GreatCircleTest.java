package com.gurkensalat.osm.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GreatCircleTest
{
    private static final double STATUE_OF_LIBERTY_LAT = 40.6892;

    private static final double STATUE_OF_LIBERTY_LON = -74.0444;

    private static final double EIFFEL_TOWER_LAT = 48.8583;

    private static final double EIFFEL_TOWER_LON = 2.2945;

    private static final double DELTA_DISTANCE = 0.001;

    @Before
    public void setUp()
    {
    }

    @Test
    public void distanceStatueOfLibertyToEiffelTower()
    {
        double result = GreatCircle.distanceInKm(STATUE_OF_LIBERTY_LAT, STATUE_OF_LIBERTY_LON, EIFFEL_TOWER_LAT, EIFFEL_TOWER_LON);

        assertEquals(5837.421, result, DELTA_DISTANCE);
    }
}
