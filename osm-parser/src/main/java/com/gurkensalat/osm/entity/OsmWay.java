package com.gurkensalat.osm.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class OsmWay
{
    private long id;

    private double lat;

    private double lon;

    private boolean centroidValid;

    private List<OsmWayNodeReference> nd = new ArrayList<OsmWayNodeReference>();

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

    @XmlAttribute(name = "lat")
    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    @XmlAttribute(name = "lon")
    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public long getId()
    {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(long id)
    {
        this.id = id;
    }

    @XmlElement(name = "nd")
    public List<OsmWayNodeReference> getNd()
    {
        return nd;
    }

    public void setNd(List<OsmWayNodeReference> nd)
    {
        this.nd = nd;
    }

    @XmlElement(name = "tag")
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
