/*
MySQL Backup
Source Server Version: 5.5.5
Source Database: sgt_englishclub
Date: 04/11/2018 20:52:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FULLNAME` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `PASSWORD` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  `ROLE` varchar(10) COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `USERNAME` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `clips`
-- ----------------------------
DROP TABLE IF EXISTS `clips`;
CREATE TABLE `clips` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(1000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `LINK` varchar(10000) COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `TITLE` varchar(100) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `UPLOAD_PERIOD` datetime NOT NULL,
  `ADMIN_ID` int(11) NOT NULL,
  `CLIP_TYPE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKl1ntwma1ggc8ul70vxqlchini` (`ADMIN_ID`),
  KEY `FK59e0hg00m8f86kunfgdcovs07` (`CLIP_TYPE_ID`),
  CONSTRAINT `FK59e0hg00m8f86kunfgdcovs07` FOREIGN KEY (`CLIP_TYPE_ID`) REFERENCES `clip_types` (`ID`),
  CONSTRAINT `FKl1ntwma1ggc8ul70vxqlchini` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admins` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `clip_types`
-- ----------------------------
DROP TABLE IF EXISTS `clip_types`;
CREATE TABLE `clip_types` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `files`
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(1000) COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `MATERIAL_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKfqqcfrkvhpdlpif3ial39380j` (`MATERIAL_ID`),
  CONSTRAINT `FKfqqcfrkvhpdlpif3ial39380j` FOREIGN KEY (`MATERIAL_ID`) REFERENCES `materials` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `materials`
-- ----------------------------
DROP TABLE IF EXISTS `materials`;
CREATE TABLE `materials` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(10000) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `STATUS` bit(1) NOT NULL,
  `TITLE` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `TITLE_PICTURE` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `UPLOAD_PERIOD` datetime NOT NULL,
  `ADMIN_ID` int(11) NOT NULL,
  `MATERIAL_TYPE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK8s6tjdtkiblb6bp1p4bje6xc0` (`ADMIN_ID`),
  KEY `FKqpon8j319ddqrdanabx7391st` (`MATERIAL_TYPE_ID`),
  CONSTRAINT `FK8s6tjdtkiblb6bp1p4bje6xc0` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admins` (`ID`),
  CONSTRAINT `FKqpon8j319ddqrdanabx7391st` FOREIGN KEY (`MATERIAL_TYPE_ID`) REFERENCES `material_types` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `material_types`
-- ----------------------------
DROP TABLE IF EXISTS `material_types`;
CREATE TABLE `material_types` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` longtext COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `TITLE` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `TITLE_PICTURE` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `UPLOAD_PERIOD` datetime NOT NULL,
  `ADMIN_ID` int(11) NOT NULL,
  `NEWS__TYPE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKf4ewrn8d7w8pgr8tta31kd93e` (`ADMIN_ID`),
  KEY `FKljx3tu9mi1ebg82l3c058x8dj` (`NEWS__TYPE_ID`),
  CONSTRAINT `FKf4ewrn8d7w8pgr8tta31kd93e` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admins` (`ID`),
  CONSTRAINT `FKljx3tu9mi1ebg82l3c058x8dj` FOREIGN KEY (`NEWS__TYPE_ID`) REFERENCES `news_types` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `news_types`
-- ----------------------------
DROP TABLE IF EXISTS `news_types`;
CREATE TABLE `news_types` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `tips`
-- ----------------------------
DROP TABLE IF EXISTS `tips`;
CREATE TABLE `tips` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` longtext COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `TITLE` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `TITLE_PICTURE` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL,
  `UPLOAD_PERIOD` datetime NOT NULL,
  `ADMIN_ID` int(11) NOT NULL,
  `TIP_TYPE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK4oea26xur59kelyg56318mi8a` (`ADMIN_ID`),
  KEY `FK5qckngdgmhfx5d367ots72c2r` (`TIP_TYPE_ID`),
  CONSTRAINT `FK4oea26xur59kelyg56318mi8a` FOREIGN KEY (`ADMIN_ID`) REFERENCES `admins` (`ID`),
  CONSTRAINT `FK5qckngdgmhfx5d367ots72c2r` FOREIGN KEY (`TIP_TYPE_ID`) REFERENCES `tip_types` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Table structure for `tip_types`
-- ----------------------------
DROP TABLE IF EXISTS `tip_types`;
CREATE TABLE `tip_types` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `admins` VALUES ('1','Phan Vu Minh Thu','123abc','ADMIN','','thupvm'), ('2','Nguyen Ngoc Minh Quan','123abc','USER','','quannnm');
