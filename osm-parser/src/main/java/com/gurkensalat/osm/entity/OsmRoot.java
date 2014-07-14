package com.gurkensalat.osm.entity;

import java.util.ArrayList;
import java.util.List;

public class OsmRoot
{
    private List<OsmNode> nodes = new ArrayList<OsmNode>();

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

}
