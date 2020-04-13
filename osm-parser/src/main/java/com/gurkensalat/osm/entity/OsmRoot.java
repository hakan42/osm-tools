package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "osm")
public class OsmRoot
{
    private OsmBounds bounds;

    private boolean gone;

    @JacksonXmlProperty(localName = "node")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OsmNode> nodes = new ArrayList<OsmNode>();

    @JacksonXmlProperty(localName = "way")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<OsmWay> ways = new ArrayList<OsmWay>();

    @JacksonXmlProperty(localName = "bounds")
    public OsmBounds getBounds()
    {
        return bounds;
    }

    public void setBounds(OsmBounds bounds)
    {
        this.bounds = bounds;
    }

    public boolean isGone()
    {
        return gone;
    }

    public void setGone(boolean gone)
    {
        this.gone = gone;
    }

    public void addNode(OsmNode node)
    {
        this.nodes.add(node);
    }

    public List<OsmNode> getNodes()
    {
        return nodes;
    }

    public void setNodes(List<OsmNode> nodes)
    {
        this.nodes = nodes;
    }

    public List<OsmWay> getWays()
    {
        return ways;
    }

    public void setWays(List<OsmWay> ways)
    {
        this.ways = ways;
    }

    public void addWay(OsmWay way)
    {
        this.ways.add(way);
    }

}
