package com.gurkensalat.osm.entity;

public enum OsmEntityType
{
    NODE("N"),
    WAY("W"),
    REL("R");

    private final String name;

    private OsmEntityType(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return name;
    }
}
