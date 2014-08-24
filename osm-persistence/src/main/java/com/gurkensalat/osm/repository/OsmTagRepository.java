package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmTag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface OsmTagRepository extends PagingAndSortingRepository<OsmTag, Long>
{
    @Modifying
    @Transactional
    @Query("update OsmTag set valid = 'f'")
    void invalidateAll();

    @Modifying
    @Transactional
    @Query("delete from OsmTag where valid = 'f'")
    void deleteAllInvalid();
}
