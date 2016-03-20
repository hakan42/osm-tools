package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmBounds;
import com.gurkensalat.osm.entity.OsmNode;
import com.gurkensalat.osm.entity.OsmNodeTag;
import com.gurkensalat.osm.entity.OsmRoot;
import org.apache.commons.digester3.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import static org.apache.commons.io.IOUtils.closeQuietly;

@Component
public class OsmParserRepositoryImpl implements OsmParserRepository
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OsmParserRepositoryImpl.class);

    private final static String API_NODE_HOST = "api.openstreetmap.org";

    private final static String API_NODE_PATH = "/api/0.6/node";

    public OsmRoot parse(File resourceFile)
    {
        OsmRoot root = new OsmRoot();

        try
        {
            FileInputStream fis = new FileInputStream(resourceFile);
            try
            {
                root = parse(fis);
            }
            finally
            {
                closeQuietly(fis);
            }

            LOGGER.info("Parsed Root: {}", root);
        }
        catch (IOException e)
        {
            LOGGER.error("While parsing OSM XML", e);
            LOGGER.info("  were trying to read {}", resourceFile);
        }

        return root;
    }

    public OsmRoot parse(InputStream inputStream)
    {
        OsmRoot root = new OsmRoot();
        try
        {
            Digester digester = createOsmPlanetDigester();

            root = digester.parse(inputStream);
        }
        catch (IOException e)
        {
            LOGGER.error("While parsing OSM XML", e);
        }
        catch (SAXException e)
        {
            LOGGER.error("While parsing OSM XML", e);
        }

        return root;
    }

    public OsmRoot loadFromServer(long osmId)
    {
        OsmRoot root = new OsmRoot();
        try
        {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://" + API_NODE_HOST + API_NODE_PATH + "/" + Long.toString(osmId));

            URI uri = builder.build().toUri();

            LOGGER.debug("Loading from {}", uri.toString());

            RestTemplate restTemplate = new RestTemplate();
            root = restTemplate.getForObject(uri, OsmRoot.class);
        }
        catch (Exception e)
        {
            LOGGER.error("While fetching OSM node from API", e);
        }

        return root;
    }

    private static Digester createOsmPlanetDigester()
    {
        Digester digester = new Digester();

        digester.addObjectCreate("osm", OsmRoot.class);

        String path_osm_bounds = "osm/bounds";
        digester.addObjectCreate(path_osm_bounds, OsmBounds.class);
        digester.addSetProperties(path_osm_bounds);
        digester.addSetNext(path_osm_bounds, "setBounds");

        String path_osm_node = "osm/node";
        digester.addObjectCreate(path_osm_node, OsmNode.class);
        digester.addSetProperties(path_osm_node);
        digester.addSetNext(path_osm_node, "addNode");

        String path_osm_node_tag = "osm/node/tag";
        digester.addObjectCreate(path_osm_node_tag, OsmNodeTag.class);
        digester.addSetProperties(path_osm_node_tag);
        digester.addSetNext(path_osm_node_tag, "addTag");

        return digester;
    }
}
