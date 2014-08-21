package com.gurkensalat.osm.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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

    @XmlElement(name = "tag")
    public List<OsmTag> getTags()
    {
        return tags;
    }

    @XmlAttribute(name = "lat")
    public void setLat(double lat)
    {
        this.lat = lat;
    }

    @XmlAttribute(name = "lon")
    public void setLon(double lon)
    {
        this.lon = lon;
    }

    @XmlAttribute(name = "id")
    public void setId(long id)
    {
        this.id = id;
    }

    public void setTags(List<OsmTag> tags)
    {
        this.tags = tags;
    }
}
