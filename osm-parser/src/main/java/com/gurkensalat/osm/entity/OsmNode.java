package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class OsmNode
{
    @JacksonXmlProperty(localName = "id")
    private long id;

    @JacksonXmlProperty(localName = "lat")
    private double lat;

    @JacksonXmlProperty(localName = "lon")
    private double lon;

    @JacksonXmlProperty(localName = "tag")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OsmNodeTag> tags = new ArrayList<OsmNodeTag>();

    public void addTag(OsmNodeTag tag)
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

    public List<OsmNodeTag> getTags()
    {
        return tags;
    }

    // @JacksonXmlProperty(localName = "lat")
    public void setLat(double lat)
    {
        this.lat = lat;
    }

    // @JacksonXmlProperty(localName = "lon")
    public void setLon(double lon)
    {
        this.lon = lon;
    }

    // @JacksonXmlProperty(localName = "id")
    public void setId(long id)
    {
        this.id = id;
    }

    public void setTags(List<OsmNodeTag> tags)
    {
        this.tags = tags;
    }
}
