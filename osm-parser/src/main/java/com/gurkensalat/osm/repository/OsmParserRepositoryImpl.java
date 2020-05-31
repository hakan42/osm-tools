package com.gurkensalat.osm.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gurkensalat.osm.entity.OsmBounds;
import com.gurkensalat.osm.entity.OsmNode;
import com.gurkensalat.osm.entity.OsmRoot;
import com.gurkensalat.osm.entity.OsmWay;
import com.gurkensalat.osm.entity.OsmWayNodeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Math.max;
import static java.lang.Math.min;

@Component
@Slf4j
public class OsmParserRepositoryImpl implements OsmParserRepository
{
    private final static String API_PROTOCOL = "https";

    private final static String API_HOST = "api.openstreetmap.org";

    private final static String API_NODE_PATH = "api/0.6/node";

    private final static String API_WAY_PATH = "api/0.6/way";

    public OsmRoot parse(File resourceFile)
    {
        OsmRoot root = new OsmRoot();

        try (FileInputStream fis = new FileInputStream(resourceFile))
        {
            root = parse(fis);
            log.info("Parsed Root: {}", root);
        }
        catch (IOException e)
        {
            log.error("While parsing OSM XML", e);
            log.info("  were trying to read {}", resourceFile);
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
            ObjectMapper mapper = new XmlMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer);
            String rawData = writer.toString();

            root = mapper.readValue(rawData, OsmRoot.class);
        }
        catch (IOException e)
        {
            log.error("While parsing OSM XML", e);
        }

        calculateWayCentroids(root);
        calculateBounds(root);
        return root;
    }

    public OsmRoot loadNodeFromServer(long osmId)
    {
        OsmRoot root = new OsmRoot();
        try
        {
            UriComponents components = UriComponentsBuilder.newInstance()
                .scheme(API_PROTOCOL)
                .host(API_HOST)
                .pathSegment(API_NODE_PATH, Long.toString(osmId))
                .build();

            URI uri = components.toUri();

            log.debug("Loading from {}", uri);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);

            if (entity.getStatusCode() == HttpStatus.OK)
            {
                String rawData = entity.getBody();
                ObjectMapper mapper = new XmlMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                root = mapper.readValue(rawData, OsmRoot.class);
            }
        }
        catch (HttpClientErrorException hcee)
        {
            if (HttpStatus.GONE == hcee.getStatusCode())
            {
                log.info("Object is gone...");
                root.setGone(true);
            }
        }
        catch (Exception e)
        {
            log.error("While fetching OSM node from API", e);
        }

        calculateWayCentroids(root);
        calculateBounds(root);
        return root;
    }

    public OsmRoot loadWayFromServer(long osmId)
    {
        OsmRoot root = new OsmRoot();
        try
        {
            UriComponents components = UriComponentsBuilder.newInstance()
                .scheme(API_PROTOCOL)
                .host(API_HOST)
                .pathSegment(API_WAY_PATH, Long.toString(osmId), "full")
                .build();

            URI uri = components.toUri();

            log.debug("Loading from {}", uri);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);

            if (entity.getStatusCode() == HttpStatus.OK)
            {
                String rawData = entity.getBody();
                ObjectMapper mapper = new XmlMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                root = mapper.readValue(rawData, OsmRoot.class);
            }
        }
        catch (HttpClientErrorException hcee)
        {
            if (HttpStatus.GONE == hcee.getStatusCode())
            {
                log.info("Object is gone...");
                root.setGone(true);
            }
        }
        catch (Exception e)
        {
            log.error("While fetching OSM node from API", e);
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
}
