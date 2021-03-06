package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmBounds;
import com.gurkensalat.osm.entity.OsmNode;
import com.gurkensalat.osm.entity.OsmNodeTag;
import com.gurkensalat.osm.entity.OsmRoot;
import com.gurkensalat.osm.entity.OsmWay;
import com.gurkensalat.osm.entity.OsmWayTag;
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
import static org.junit.Assert.assertTrue;

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

        OsmBounds bounds = root.getBounds();
        assertNotNull(bounds);

        assertEquals(11.38729, bounds.getMinlon(), 0.001);
        assertEquals(48.13640, bounds.getMinlat(), 0.001);

        assertEquals(11.38789, bounds.getMaxlon(), 0.001);
        assertEquals(48.13640, bounds.getMaxlat(), 0.001);
    }

    @Test
    public void parseMosqueGermeringDataFromAPICached() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("904317998.osm");
        assertNotNull(is);

        OsmRoot root = testable.parse(is);
        closeQuietly(is);

        assertNotNull(root);
        assertEquals(1, root.getNodes().size());

        OsmBounds bounds = root.getBounds();
        assertNotNull(bounds);

        assertEquals(11.38729, bounds.getMinlon(), 0.001);
        assertEquals(48.13640, bounds.getMinlat(), 0.001);

        assertEquals(11.38789, bounds.getMaxlon(), 0.001);
        assertEquals(48.13640, bounds.getMaxlat(), 0.001);
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

        OsmBounds bounds = root.getBounds();
        assertNotNull(bounds);

        assertEquals(32.85406, bounds.getMinlon(), 0.001);
        assertEquals(39.92077, bounds.getMinlat(), 0.001);

        assertEquals(32.85406, bounds.getMaxlon(), 0.001);
        assertEquals(39.92077, bounds.getMaxlat(), 0.001);
    }

    @Test
    public void loadOnlineMosqueDataNodeGermeringFromServer()
    {
        OsmRoot root = testable.loadNodeFromServer(904317998);

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

        OsmBounds bounds = root.getBounds();
        assertNotNull(bounds);

        assertEquals(11.38729, bounds.getMinlon(), 0.001);
        assertEquals(48.13640, bounds.getMinlat(), 0.001);

        assertEquals(11.38789, bounds.getMaxlon(), 0.001);
        assertEquals(48.13640, bounds.getMaxlat(), 0.001);
    }

    @Test
    public void loadOnlineWayMosqueDataEmreguelFromServer()
    {
        OsmRoot root = testable.loadWayFromServer(790360319);

        assertNotNull(root);

        assertEquals(1, root.getWays().size());

        OsmWay way = root.getWays().get(0);
        assertNotEquals(0, way.getId());
        assertNotEquals(0, way.getLat());
        assertNotEquals(0, way.getLon());

        List<OsmWayTag> tags = way.getTags();
        assertNotNull(tags);

        assertNotEquals(0, tags.size());
        for (OsmWayTag tag : tags)
        {
            assertNotNull(tag.getKey());
            assertNotNull(tag.getValue());
        }

        OsmBounds bounds = root.getBounds();
        assertNotNull(bounds);

        // lat 37.751149579999996
        // lon 30.55327546

        // minlon 30.5531455
        // minlat 37.7510304
        assertEquals(30.55315, bounds.getMinlon(), 0.001);
        assertEquals(37.75103, bounds.getMinlat(), 0.001);

        // maxlon 30.5534677
        // maxlat 37.7512671
        assertEquals(30.55347, bounds.getMaxlon(), 0.001);
        assertEquals(37.75127, bounds.getMaxlat(), 0.001);
    }

    @Test
    public void parsePlaceIspartaAkkentCamiiData() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("turkey-mosque-akkent.osm");
        assertNotNull(is);

        OsmRoot root = testable.parse(is);
        closeQuietly(is);

        assertNotNull(root);
        assertEquals(4, root.getNodes().size());
        assertEquals(1, root.getWays().size());

        OsmWay way = root.getWays().get(0);
        assertNotNull(way);

        assertNotNull(way.getTags());
        assertEquals(8, way.getTags().size());

        assertNotNull(way.getNd());
        assertEquals(5, way.getNd().size());

        assertTrue(way.isCentroidValid());
        assertEquals(30.57980, way.getLon(), 0.001);
        assertEquals(37.82062, way.getLat(), 0.001);

        OsmBounds bounds = root.getBounds();
        assertNotNull(bounds);

        assertEquals(30.57966, bounds.getMinlon(), 0.001);
        assertEquals(37.82048, bounds.getMinlat(), 0.001);

        assertEquals(30.58002, bounds.getMaxlon(), 0.001);
        assertEquals(37.82075, bounds.getMaxlat(), 0.001);
    }

    @Test
    public void parsePlaceIspartaKeciborluUluCamiData() throws IOException
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream("turkey-mosque-keciborlu.osm");
        assertNotNull(is);

        OsmRoot root = testable.parse(is);
        closeQuietly(is);

        assertNotNull(root);
        assertTrue(root.getNodes().size() >= 4);
        assertEquals(1, root.getWays().size());

        OsmWay way = root.getWays().get(0);
        assertNotNull(way);

        assertNotNull(way.getTags());
        assertEquals(5, way.getTags().size());

        assertNotNull(way.getNd());
        assertEquals(5, way.getNd().size());

        assertTrue(way.isCentroidValid());
        assertEquals(30.30288, way.getLon(), 0.001);
        assertEquals(37.94384, way.getLat(), 0.001);

        OsmBounds bounds = root.getBounds();
        assertNotNull(bounds);

        assertEquals(30.30278, bounds.getMinlon(), 0.001);
        assertEquals(37.94374, bounds.getMinlat(), 0.001);

        assertEquals(30.30308, bounds.getMaxlon(), 0.001);
        assertEquals(37.94395, bounds.getMaxlat(), 0.001);
    }

}
