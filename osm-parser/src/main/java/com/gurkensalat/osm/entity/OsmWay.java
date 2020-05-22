package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    private List<OsmWayNodeReference> nd = new ArrayList<>();

    @JacksonXmlProperty(localName = "tag")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OsmWayTag> tags = new ArrayList<>();

    public void addTag(OsmWayTag tag)
    {
        this.tags.add(tag);
    }

    public void addNodeReference(OsmWayNodeReference osmWayNodeReference)
    {
        this.nd.add(osmWayNodeReference);
    }
}
