package com.gurkensalat.osm.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class OsmPlaceTest
{
    OsmNode node;

    OsmPlace testable;

    @Before
    public void setUp()
    {
    }

    @Test
    public void testCreationFromNodeGermeringMosque()
    {
        node = createGermeringMosque();
        testable = new OsmPlace(node);
        assertNotNull(testable);
    }

    @Test
    public void testCreationFromNodeAnkaraCity()
    {
        node = createAnkaraCity();
        testable = new OsmPlace(node);
        assertNotNull(testable);
    }

    private OsmNode createGermeringMosque()
    {
        OsmNode node = new OsmNode();
        node.setLat(48.1364000);
        node.setLon(11.3872928);
        node.setTags(new ArrayList<OsmTag>());
        node.getTags().add(createTag("addr:city", "Germering"));
        node.getTags().add(createTag("addr:housenumber", "13a"));
        node.getTags().add(createTag("addr:postcode", "82110"));
        node.getTags().add(createTag("addr:street", "Münchener Straße"));

        return node;
    }

    private OsmNode createAnkaraCity()
    {
        OsmNode node = new OsmNode();
        node.setLat(39.9272322);
        node.setLon(32.8519769);
        node.setTags(new ArrayList<OsmTag>());
        node.getTags().add(createTag("capital", "yes"));
        node.getTags().add(createTag("is_in", "Turkey"));
        node.getTags().add(createTag("name", "Ankara"));
        node.getTags().add(createTag("place", "city"));

        return node;
    }

    private OsmTag createTag(String key, String value)
    {
        OsmTag tag = new OsmTag();

        tag.setKey(key);
        tag.setValue(value);

        return tag;
    }
}
