package com.gurkensalat.osm.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlaceTypeTest
{
    @Before
    public void setUp()
    {

    }

    @Test
    public void testToString()
    {
        PlaceType testable = PlaceType.OSM_CITY;

        String result = testable.toString();
        assertEquals("OSM_CITY", result);
    }
}
