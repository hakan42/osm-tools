package com.gurkensalat.osm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OsmNodeTag
{
    @JacksonXmlProperty(localName = "k")
    private String key;

    @JacksonXmlProperty(localName = "v")
    private String value;

    @JsonIgnore
    public void setKey(String key)
    {
        this.key = key;
    }

    @JsonIgnore
    public void setValue(String value)
    {
        this.value = value;
    }
}
