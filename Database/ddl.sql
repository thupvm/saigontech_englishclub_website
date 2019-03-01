/*
MySQL Backup
Source Server Version: 5.5.5
Source Database: sgt_englishclub
Date: 12/14/2018 10:48:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(100) NOT NULL,
  `FIRSTNAME` varchar(100) NOT NULL,
  `LASTNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `PHONE` varchar(15) NOT NULL,
  `ROLE` varchar(10) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_22cyd8s0depa03gprxm3gn6c0` (`USERNAME`)
) ENGINE=InnoDB CHARSET=utf8;

-- ----------------------------
--  Table structure for `videotype`
-- ----------------------------
DROP TABLE IF EXISTS `videotype`;
CREATE TABLE `videotype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB CHARSET=utf8;

-- ----------------------------
--  Table structure for `materialtype`
-- ----------------------------
DROP TABLE IF EXISTS `materialtype`;
CREATE TABLE `materialtype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB CHARSET=utf8;

-- ----------------------------
--  Table structure for `newstype`
-- ----------------------------
DROP TABLE IF EXISTS `newstype`;
CREATE TABLE `newstype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB CHARSET=utf8;

-- ----------------------------
--  Table structure for `tiptype`
-- ----------------------------
DROP TABLE IF EXISTS `tiptype`;
CREATE TABLE `tiptype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB CHARSET=utf8;

-- ----------------------------
--  Table structure for `material`
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(10000) DEFAULT NULL,
  `POSTDATE` date DEFAULT NULL,
  `STATUS` bit(1) NOT NULL,
  `TITLE` varchar(100) NOT NULL,
  `TITLEPICTURE` varchar(255) DEFAULT NULL,
  `ADMINID` int(11) NOT NULL,
  `MATERIALTYPEID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK8g74j658rwc38gsop3e48rwrt` (`ADMINID`),
  KEY `FK382qnnts34g98nj72l6r4r9eq` (`MATERIALTYPEID`),
  CONSTRAINT `FK382qnnts34g98nj72l6r4r9eq` FOREIGN KEY (`MATERIALTYPEID`) REFERENCES `materialtype` (`ID`),
  CONSTRAINT `FK8g74j658rwc38gsop3e48rwrt` FOREIGN KEY (`ADMINID`) REFERENCES `admin` (`ID`)
) ENGINE=InnoDB CHARSET=utf8;

-- ----------------------------
--  Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(1000) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  `MATERIALID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKhnergsu60q0k6gsh38tjkqu6m` (`MATERIALID`),
  CONSTRAINT `FKhnergsu60q0k6gsh38tjkqu6m` FOREIGN KEY (`MATERIALID`) REFERENCES `material` (`ID`)
) ENGINE=InnoDB CHARSET=utf8;

-- ----------------------------
--  Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BIGPICTURETITLE` varchar(255) NOT NULL,
  `CONTENT` longtext NOT NULL,
  `POSTDATE` date DEFAULT NULL,
  `STATUS` bit(1) NOT NULL,
  `THUMBNAILPICTURETITLE` varchar(100) DEFAULT NULL,
  `TITLE` varchar(100) NOT NULL,
  `ADMINID` int(11) NOT NULL,
  `NEWSTYPEID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKf2o7jy90wiwmggorhfklg884b` (`ADMINID`),
  KEY `FKhnitn5whxrm3lqipxjccwc3h2` (`NEWSTYPEID`),
  CONSTRAINT `FKf2o7jy90wiwmggorhfklg884b` FOREIGN KEY (`ADMINID`) REFERENCES `admin` (`ID`),
  CONSTRAINT `FKhnitn5whxrm3lqipxjccwc3h2` FOREIGN KEY (`NEWSTYPEID`) REFERENCES `newstype` (`ID`)
) ENGINE=InnoDB CHARSET=utf8;



-- ----------------------------
--  Table structure for `tip`
-- ----------------------------
DROP TABLE IF EXISTS `tip`;
CREATE TABLE `tip` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` longtext NOT NULL,
  `POSTDATE` date DEFAULT NULL,
  `STATUS` bit(1) NOT NULL,
  `TITLE` varchar(100) NOT NULL,
  `TITLEPICTURE` varchar(255) NOT NULL,
  `ADMINID` int(11) NOT NULL,
  `TIPTYPEID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKj3y54ldpl0god071r5wipr0xe` (`ADMINID`),
  KEY `FK9s1jmnoe1ybgmvw54lml5eti6` (`TIPTYPEID`),
  CONSTRAINT `FK9s1jmnoe1ybgmvw54lml5eti6` FOREIGN KEY (`TIPTYPEID`) REFERENCES `tiptype` (`ID`),
  CONSTRAINT `FKj3y54ldpl0god071r5wipr0xe` FOREIGN KEY (`ADMINID`) REFERENCES `admin` (`ID`)
) ENGINE=InnoDB CHARSET=utf8;



-- ----------------------------
--  Table structure for `video`
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(1000) NOT NULL,
  `LINK` varchar(500) NOT NULL,
  `POSTDATE` date DEFAULT NULL,
  `STATUS` bit(1) NOT NULL,
  `TITLE` varchar(100) NOT NULL,
  `ADMINID` int(11) NOT NULL,
  `CLIPTYPEID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKfhbuxw4xe8c1f5y0tfh6gjihd` (`ADMINID`),
  KEY `FKjul9x6ikquy5dd5tarvw8ekj5` (`CLIPTYPEID`),
  CONSTRAINT `FKfhbuxw4xe8c1f5y0tfh6gjihd` FOREIGN KEY (`ADMINID`) REFERENCES `admin` (`ID`),
  CONSTRAINT `FKjul9x6ikquy5dd5tarvw8ekj5` FOREIGN KEY (`CLIPTYPEID`) REFERENCES `videotype` (`ID`)
) ENGINE=InnoDB CHARSET=utf8;



-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('1','nguyenquan263@gmail.com','Quan','Nguyen','123abc','0966362138','ADMIN','','quannnm');
