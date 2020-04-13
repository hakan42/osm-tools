#!/bin/sh -ex

cd src/test/resources

curl --silent https://api.openstreetmap.org/api/0.6/node/25869410  -o turkey-places-city-ankara.osm 

curl --silent https://api.openstreetmap.org/api/0.6/node/904317998 -o 904317998.osm 
curl --silent https://api.openstreetmap.org/api/0.6/node/904317998 -o germany-mosque-germering.osm 

curl --silent https://api.openstreetmap.org/api/0.6/way/386990497/full  -o turkey-mosque-akkent.osm 
curl --silent https://api.openstreetmap.org/api/0.6/way/313651604/full  -o turkey-mosque-keciborlu.osm 
