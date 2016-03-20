package com.gurkensalat.osm.repository;

import com.gurkensalat.osm.entity.OsmBounds;
import com.gurkensalat.osm.entity.OsmNode;
import com.gurkensalat.osm.entity.OsmNodeTag;
import com.gurkensalat.osm.entity.OsmRoot;
import com.gurkensalat.osm.entity.OsmWay;
import com.gurkensalat.osm.entity.OsmWayNodeReference;
import com.gurkensalat.osm.entity.OsmWayTag;
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
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
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

        calculateWayCentroids(root);
        calculateBounds(root);
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

        calculateWayCentroids(root);
        calculateBounds(root);
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

        calculateWayCentroids(root);
        calculateBounds(root);
        return root;
    }

    /* package level protection for unit testing */
    void calculateBounds(OsmRoot root)
    {
        OsmBounds bounds = root.getBounds();
        if (bounds == null)
        {
            bounds = new OsmBounds();
            root.setBounds(bounds);
        }

        if (root.getNodes() != null)
        {
            if (root.getNodes().size() > 0)
            {
                bounds.setMinlat(1000);
                bounds.setMaxlat(-999);

                bounds.setMinlon(1000);
                bounds.setMaxlon(-999);
                // Iterate over all nodes, setting minimum and maximum as necessary

                for (OsmNode node : root.getNodes())
                {
                    bounds.setMinlat(min(bounds.getMinlat(), node.getLat()));
                    bounds.setMinlon(min(bounds.getMinlon(), node.getLon()));

                    bounds.setMaxlat(max(bounds.getMaxlat(), node.getLat()));
                    bounds.setMaxlon(max(bounds.getMaxlon(), node.getLon()));
                }
            }
        }
    }

    /* package level protection for unit testing */
    void calculateWayCentroids(OsmRoot root)
    {
        Map<String, OsmNode> nodeMap = new TreeMap<String, OsmNode>();

        if (root.getNodes() != null)
        {
            for (OsmNode node : root.getNodes())
            {
                String key = Long.toString(node.getId());
                nodeMap.put(key, node);
            }
        }

        if (root.getWays() != null)
        {
            for (OsmWay way : root.getWays())
            {
                way.setCentroidValid(FALSE);

                if (way.getNd() != null)
                {
                    way.setCentroidValid(TRUE);
                    double sumOfNodeLats = 0;
                    double sumOfNodeLons = 0;
                    double centroidNodes = 0;

                    for (OsmWayNodeReference reference : way.getNd())
                    {
                        if (way.isCentroidValid())
                        {
                            String key = reference.getRef();
                            OsmNode node = nodeMap.get(key);
                            if (node != null)
                            {
                                reference.setNode(node);
                                sumOfNodeLats += node.getLat();
                                sumOfNodeLons += node.getLon();
                                centroidNodes++;
                            }
                            else
                            {
                                way.setCentroidValid(FALSE);
                            }
                        }
                    }

                    if (way.isCentroidValid())
                    {
                        if (centroidNodes > 0)
                        {
                            way.setLat(sumOfNodeLats / centroidNodes);
                            way.setLon(sumOfNodeLons / centroidNodes);
                        }
                        else
                        {
                            way.setCentroidValid(FALSE);
                        }
                    }
                }
            }
        }
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

        String path_osm_way = "osm/way";
        digester.addObjectCreate(path_osm_way, OsmWay.class);
        digester.addSetProperties(path_osm_way);
        digester.addSetNext(path_osm_way, "addWay");

        String path_osm_way_tag = "osm/way/tag";
        digester.addObjectCreate(path_osm_way_tag, OsmWayTag.class);
        digester.addSetProperties(path_osm_way_tag);
        digester.addSetNext(path_osm_way_tag, "addTag");

        String path_osm_way_node_ref = "osm/way/nd";
        digester.addObjectCreate(path_osm_way_node_ref, OsmWayNodeReference.class);
        digester.addSetProperties(path_osm_way_node_ref);
        digester.addSetNext(path_osm_way_node_ref, "addNodeReference");

        return digester;
    }
}
