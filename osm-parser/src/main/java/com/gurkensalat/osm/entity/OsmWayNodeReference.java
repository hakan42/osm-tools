package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OsmWayNodeReference
{
    private String ref;

    private OsmNode node;

    @JacksonXmlProperty(localName = "ref")
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
