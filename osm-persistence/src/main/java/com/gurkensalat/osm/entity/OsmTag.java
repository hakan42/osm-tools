package com.gurkensalat.osm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "OSM_TAGS")
@Data
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
}
