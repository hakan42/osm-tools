package com.gurkensalat.osm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OSM_PLACES")
public class OsmPlace extends OsmPlaceBase
{
    protected OsmPlace()
    {
    }

    public OsmPlace(String name, PlaceType type)
    {
        super(name, type);
    }

    public OsmPlace(OsmNode node)
    {
        super(node);
    }
}
