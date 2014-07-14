package com.gurkensalat.osm.entity;

import java.util.ArrayList;
import java.util.List;

public class OsmNode
{
    private long id;

    private double lat;

    private double lon;

    private List<OsmTag> tags = new ArrayList<OsmTag>();

    public void addTag(OsmTag tag)
    {
        this.tags.add(tag);
    }

    public double getLat()
    {
        return lat;
    }

    public double getLon()
    {
        return lon;
    }

    public long getId()
    {
        return id;
    }

    public List<OsmTag> getTags()
    {
        return tags;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setTags(List<OsmTag> tags)
    {
        this.tags = tags;
    }
}
