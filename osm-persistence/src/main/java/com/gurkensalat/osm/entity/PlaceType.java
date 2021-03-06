package com.gurkensalat.osm.entity;

public enum PlaceType
{
    OSM_CITY("OSM_CITY"),
    OSM_PLACE_OF_WORSHIP("OSM_PLACE_OF_WORSHIP"),
    IL_ILCE_DUMP_CITY("IL_ILCE_DUMP_CITY");

    private final String name;

    private PlaceType(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return name;
    }
}
