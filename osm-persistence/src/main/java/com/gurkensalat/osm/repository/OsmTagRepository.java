package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmTag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface OsmTagRepository extends PagingAndSortingRepository<OsmTag, Long>
{
}
