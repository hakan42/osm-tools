package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmTag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface OsmTagRepository extends PagingAndSortingRepository<OsmTag, Long>
{
    Iterable<OsmTag> findByParentTableAndParentId(@Param("parentTable") String parentTable, @Param("parentId") Long parentId);

    @Modifying
    @Transactional
    @Query("update OsmTag set valid = false")
    void invalidateAll();

    @Modifying
    @Transactional
    @Query("delete from OsmTag where valid = false")
    void deleteAllInvalid();
}
