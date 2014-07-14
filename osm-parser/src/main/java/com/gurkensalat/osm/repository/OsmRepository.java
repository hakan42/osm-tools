package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmRoot;

import java.io.File;

public interface OsmRepository
{
    OsmRoot parse(File resourceFile);
}
