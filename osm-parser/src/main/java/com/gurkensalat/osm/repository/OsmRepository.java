package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmRoot;

import java.io.File;
import java.io.InputStream;

public interface OsmRepository
{
    OsmRoot parse(File resourceFile);

    OsmRoot parse(InputStream inputStream);

    OsmRoot loadFromServer(long osmId);
}
