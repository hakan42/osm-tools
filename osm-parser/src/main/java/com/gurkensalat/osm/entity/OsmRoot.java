package com.gurkensalat.osm.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "osm")
public class OsmRoot
{
    private List<OsmNode> nodes = new ArrayList<OsmNode>();

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

}
