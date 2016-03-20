package com.gurkensalat.osm.entity;

import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OSM_PLACES")
public class OsmPlace extends OsmPlaceBase
{
    private transient String mapUrl;

    protected OsmPlace()
    {
    }

    public OsmPlace(String name, PlaceType type)
    {
        super(name, type);

        if (getCreationTime() == null)
        {
            setCreationTime(DateTime.now());
        }

        if (getModificationTime() == null)
        {
            setModificationTime(DateTime.now());
        }
    }

    public OsmPlace(OsmNode node)
    {
        super(node);
    }

    public String getMapUrl()
    {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl)
    {
        this.mapUrl = mapUrl;
    }
}
