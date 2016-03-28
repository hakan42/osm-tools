package com.gurkensalat.osm.entity;

public enum OsmEntityType
{
    NODE("Node"),
    WAY("Way"),
    REL("Relation");

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
