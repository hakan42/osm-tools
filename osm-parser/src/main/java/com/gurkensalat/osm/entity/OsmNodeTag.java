package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OsmNodeTag
{
    @JacksonXmlProperty(localName = "k")
    private String key;

    @JacksonXmlProperty(localName = "v")
    private String value;

    public String getKey()
    {
        return key;
    }

    @JsonIgnore
    public void setKey(String key)
    {
        this.key = key;
    }

    public void setK(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    @JsonIgnore
    public void setValue(String value)
    {
        this.value = value;
    }

    public void setV(String value)
    {
        this.value = value;
    }
}
