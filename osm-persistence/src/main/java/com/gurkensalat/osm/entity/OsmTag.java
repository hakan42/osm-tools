package com.gurkensalat.osm.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "OSM_TAGS")
public class OsmTag extends AbstractPersistable<Long>
{
    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "VALID")
    private boolean valid;

    @Column(name = "P_TABLE", length = 80)
    private String parentTable;

    @Column(name = "P_ID")
    private Long parentId;

    @Column(name = "D_KEY", length = 30)
    private String key;

    @Column(name = "D_VAL", length = 80)
    private String value;

    public OsmTag()
    {
    }

    public void copyTo(OsmTag other)
    {
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    public boolean isValid()
    {
        return valid;
    }

    public void setValid(boolean valid)
    {
        this.valid = valid;
    }

    public String getParentTable()
    {
        return parentTable;
    }

    public void setParentTable(String parentTable)
    {
        this.parentTable = parentTable;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return (new ToStringBuilder(this))
                .append("valid", valid)
                .append("pTable", parentTable)
                .append("pId", parentId)
                .append("key", key)
                .append("value", value)
                .toString();
    }
}
