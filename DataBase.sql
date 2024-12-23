CREATE DATABASE  IF NOT EXISTS `URLs`;
USE `URLs`;
--
-- Table structure for table `URL`
--

DROP TABLE IF EXISTS `url`;

CREATE TABLE `url` (
	`url_key` varchar(32) NOT NULL,
    `full_url` VARCHAR(500) UNIQUE NOT NULL,
    `short_url` VARCHAR(50) UNIQUE NOT NULL,
    PRIMARY KEY (`url_key`)
)  ENGINE=INNODB DEFAULT CHARSET=utf8mb4;