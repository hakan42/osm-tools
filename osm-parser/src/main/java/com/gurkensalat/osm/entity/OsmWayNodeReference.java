package com.gurkensalat.osm.entity;

import javax.xml.bind.annotation.XmlAttribute;

public class OsmWayNodeReference
{
    private String ref;

    private OsmNode node;

    @XmlAttribute(name = "ref")
    public String getRef()
    {
        return ref;
    }

    public void setRef(String ref)
    {
        this.ref = ref;
    }

    public OsmNode getNode()
    {
        return node;
    }

    public void setNode(OsmNode node)
    {
        this.node = node;
    }
}
