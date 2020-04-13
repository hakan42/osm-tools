package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class OsmWay
{
    @JacksonXmlProperty(localName = "id")
    private long id;

    @JacksonXmlProperty(localName = "lat")
    private double lat;

    @JacksonXmlProperty(localName = "lon")
    private double lon;

    private boolean centroidValid;

    @JacksonXmlProperty(localName = "nd")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OsmWayNodeReference> nd = new ArrayList<OsmWayNodeReference>();

    @JacksonXmlProperty(localName = "tag")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OsmWayTag> tags = new ArrayList<OsmWayTag>();

    public boolean isCentroidValid()
    {
        return centroidValid;
    }

    public void setCentroidValid(boolean centroidValid)
    {
        this.centroidValid = centroidValid;
    }

    public void addTag(OsmWayTag tag)
    {
        this.tags.add(tag);
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public List<OsmWayNodeReference> getNd()
    {
        return nd;
    }

    public void setNd(List<OsmWayNodeReference> nd)
    {
        this.nd = nd;
    }

    public List<OsmWayTag> getTags()
    {
        return tags;
    }

    public void setTags(List<OsmWayTag> tags)
    {
        this.tags = tags;
    }

    public void addNodeReference(OsmWayNodeReference osmWayNodeReference)
    {
        this.nd.add(osmWayNodeReference);
    }
}
