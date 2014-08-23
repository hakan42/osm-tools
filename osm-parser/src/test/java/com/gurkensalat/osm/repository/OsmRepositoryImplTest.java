package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmNode;
import com.gurkensalat.osm.entity.OsmNodeTag;
import com.gurkensalat.osm.entity.OsmRoot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class OsmRepositoryImplTest
{
    OsmRepositoryImpl testable;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        testable = new OsmRepositoryImpl();
    }

    @Test
    public void parseMosqueGermeringData() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("germany-mosque-germering.osm");
        assertNotNull(is);

        OsmRoot root = testable.parse(is);
        closeQuietly(is);

        assertNotNull(root);
        assertEquals(1, root.getNodes().size());
    }

    @Test
    public void parseMosqueGermeringDataFromAPI() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("904317998.osm");
        assertNotNull(is);

        OsmRoot root = testable.parse(is);
        closeQuietly(is);

        assertNotNull(root);
        assertEquals(1, root.getNodes().size());
    }

    @Test
    public void parsePlaceAnkaraData() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("turkey-places-city-ankara.osm");
        assertNotNull(is);

        OsmRoot root = testable.parse(is);
        closeQuietly(is);

        assertNotNull(root);
        assertEquals(1, root.getNodes().size());
    }

    @Test
    public void loadMosqueGermanyDataFromServer()
    {
        OsmRoot root = testable.load(904317998);

        assertNotNull(root);

        assertEquals(1, root.getNodes().size());

        OsmNode node = root.getNodes().get(0);
        assertNotEquals(0, node.getId());
        assertNotEquals(0, node.getLat());
        assertNotEquals(0, node.getLon());

        List<OsmNodeTag> tags = node.getTags();
        assertNotNull(tags);

        assertNotEquals(0, tags.size());
        for (OsmNodeTag tag : tags)
        {
            assertNotNull(tag.getKey());
            assertNotNull(tag.getValue());
        }
    }
}
