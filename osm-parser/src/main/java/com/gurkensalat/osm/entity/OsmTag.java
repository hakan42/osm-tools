package com.gurkensalat.osm.entity;

import javax.xml.bind.annotation.XmlAttribute;

public class OsmTag
{
    private String key;

    private String value;

    @XmlAttribute(name = "k")
    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public void setK(String key)
    {
        this.key = key;
    }

    @XmlAttribute(name = "v")
    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public void setV(String value)
    {
        this.value = value;
    }
}
