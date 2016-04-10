package com.gurkensalat.osm.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "osm")
public class OsmRoot
{
    private OsmBounds bounds;

    private boolean gone;

    private List<OsmNode> nodes = new ArrayList<OsmNode>();

    private List<OsmWay> ways = new ArrayList<OsmWay>();

    @XmlElement(name = "bounds")
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

    @XmlElement(name = "node")
    public List<OsmNode> getNodes()
    {
        return nodes;
    }

    public void setNodes(List<OsmNode> nodes)
    {
        this.nodes = nodes;
    }

    @XmlElement(name = "way")
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
