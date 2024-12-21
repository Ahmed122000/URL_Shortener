CREATE DATABASE  IF NOT EXISTS `URLs`;
USE `URLs`;
--
-- Table structure for table `URL`
--

DROP TABLE IF EXISTS `url`;

CREATE TABLE `url` (
  `fullUrl` varchar(500) ,
  `shortUrl` varchar(15) UNIQUE,
  PRIMARY KEY (`fullUrl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;