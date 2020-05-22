package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "osm")
@Getter
@Setter
public class OsmRoot
{
    private OsmBounds bounds;

    private boolean gone;

    @JacksonXmlProperty(localName = "node")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OsmNode> nodes = new ArrayList<>();

    @JacksonXmlProperty(localName = "way")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OsmWay> ways = new ArrayList<>();

    @JacksonXmlProperty(localName = "bounds")
    public OsmBounds getBounds()
    {
        return bounds;
    }

    public void addNode(OsmNode node)
    {
        this.nodes.add(node);
    }

    public void addWay(OsmWay way)
    {
        this.ways.add(way);
    }
}
