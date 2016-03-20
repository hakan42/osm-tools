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
import static org.junit.Assert.assertNull;

public class OsmParserRepositoryImplTest
{
    OsmParserRepositoryImpl testable;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        testable = new OsmParserRepositoryImpl();
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

        assertNotNull(root.getBounds());

        assertEquals(11.38729, root.getBounds().getMinlon(), 0.001);
        assertEquals(48.13640, root.getBounds().getMinlat(), 0.001);

        assertEquals(11.38789, root.getBounds().getMaxlon(), 0.001);
        assertEquals(48.13640, root.getBounds().getMaxlat(), 0.001);
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

        assertNotNull(root.getBounds());

        assertEquals(11.38729, root.getBounds().getMinlon(), 0.001);
        assertEquals(48.13640, root.getBounds().getMinlat(), 0.001);

        assertEquals(11.38789, root.getBounds().getMaxlon(), 0.001);
        assertEquals(48.13640, root.getBounds().getMaxlat(), 0.001);
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

        assertNotNull(root.getBounds());

        assertEquals(32.85197, root.getBounds().getMinlon(), 0.001);
        assertEquals(39.92723, root.getBounds().getMinlat(), 0.001);

        assertEquals(32.85197, root.getBounds().getMaxlon(), 0.001);
        assertEquals(39.92723, root.getBounds().getMaxlat(), 0.001);
    }

    @Test
    public void loadMosqueGermanyDataFromServer()
    {
        OsmRoot root = testable.loadFromServer(904317998);

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

        assertNotNull(root.getBounds());

        assertEquals(11.38729, root.getBounds().getMinlon(), 0.001);
        assertEquals(48.13640, root.getBounds().getMinlat(), 0.001);

        assertEquals(11.38789, root.getBounds().getMaxlon(), 0.001);
        assertEquals(48.13640, root.getBounds().getMaxlat(), 0.001);
    }
}
