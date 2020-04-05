package com.gurkensalat.osm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "OSM_TAGS")
@Getter
@Setter
@NoArgsConstructor
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

    public void copyTo(OsmTag other)
    {
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
