CREATE TABLE `OSM_PLACES`
(
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,

  PRIMARY KEY(`ID`)
)
DEFAULT CHARSET=utf8;


ALTER TABLE `OSM_PLACES` add `LAT` double DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `LON` double DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `NAME` varchar(80) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `TYPE` varchar(5) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `D_KEY` varchar(80) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `D_CODE` varchar(80) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `PLACE_TYPE` varchar(20) DEFAULT NULL;
ALTER TABLE `OSM_PLACES` add `VALID` bit(1) DEFAULT NULL;