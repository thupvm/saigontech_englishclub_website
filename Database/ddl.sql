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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `materialtype`
-- ----------------------------
DROP TABLE IF EXISTS `materialtype`;
CREATE TABLE `materialtype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `newstype`
-- ----------------------------
DROP TABLE IF EXISTS `newstype`;
CREATE TABLE `newstype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `tiptype`
-- ----------------------------
DROP TABLE IF EXISTS `tiptype`;
CREATE TABLE `tiptype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `videotype`
-- ----------------------------
DROP TABLE IF EXISTS `videotype`;
CREATE TABLE `videotype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `STATUS` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('1','nguyenquan263@gmail.com','Quan','Nguyen','123abc','0966362138','ADMIN','','quannnm');
INSERT INTO `file` VALUES ('5','50c81-Mau-bieu-05-DK-TCT.docx','','3'), ('8','armbian_1.txt','','4'), ('9','50c81-Mau-bieu-05-DK-TCT_2.docx','','5'), ('11','50c81-Mau-bieu-05-DK-TCT_3.docx','','6'), ('12','armbian_2.txt','','6'), ('13','50c81-Mau-bieu-05-DK-TCT_4.docx','','4');
INSERT INTO `material` VALUES ('3','save Minh Thu','2018-01-12','','Minh Thu stupid','news-main_1.PNG','1','2'), ('4','','2018-01-14','','Minh Thu stupid','news-main_2.PNG','1','2'), ('5','','2018-01-14','\0','hihiohoho','Jellyfish.jpg','1','2'), ('6','','2018-01-14','\0','Minh Thu stupid','news-main.PNG','1','2');
INSERT INTO `materialtype` VALUES ('1','SPEAKING',''), ('2','LISTENING','');
INSERT INTO `tip` VALUES ('14','<ul><li>dsadsadsad</li><li>sadsadsa</li></ul>','2018-01-07','','sadsads','Penguins.jpg','1','1'), ('15','<h5><a href=\"https://www.ielts-exam.net/ielts-speaking-test/\" target=\"_blank\" title=\"IELTS Speaking Part 1\">&nbsp;IELTS Speaking Part 1</a>&nbsp;</h5><p><strong>Introduction and interview</strong> (4-5 minutes)<br>In the first part, the examiner will ask you a number of <a href=\"https://www.ielts-exam.net/ielts-speaking/ielts-speaking-part-one.html\">&nbsp;general questions</a>. Be prepared to introduce yourself and talk about things which are personal to you, for example, your country and home town, your family, your studies or work, what you like doing in your free time and what you might do in the future.<br><a href=\"https://www.ielts-exam.net/ielts-speaking-test/\" target=\"_blank\">&nbsp;Click here for samples</a>&nbsp;</p><h5><a href=\"https://www.ielts-exam.net/ielts_real_speaking_test/\" target=\"_blank\" title=\"IELTS Speaking Part 2\">&nbsp;IELTS Speaking Part 2</a>&nbsp;</h5><p><strong>Individual long turn</strong> (3-4 minutes)<br>In this part, the examiner will give you a <a href=\"https://www.ielts-exam.net/ielts-speaking/ielts-speaking-part-two-and-three.html\">&nbsp;card</a> that asks you to talk about a person, place, event or object. You will have 1 minute to prepare to speak, and then you will talk for 1-2 minutes, during which the examiner will not speak. The examiner will then ask one or two rounding-off questions.<br><a href=\"https://www.ielts-exam.net/ielts_real_speaking_test/\" target=\"_blank\">&nbsp;Click here for samples</a>&nbsp;</p><h5><a href=\"https://www.ielts-exam.net/ielts_interviews/\" target=\"_blank\" title=\"IELTS Speaking Part 3\">&nbsp;IELTS Speaking Part 3</a>&nbsp;</h5><p><strong>Two-way discussion</strong> (4-5 minutes)<br>In the last part, you will talk with the examiner about issues related to the topic on the <a href=\"https://www.ielts-exam.net/ielts-speaking/ielts-speaking-part-two-and-three.html\">&nbsp;card</a>. However, the discussion will be on less personal topics. For example, in Part 2 you may talk about a teacher you had at school, but in Part 3 you might talk about education in your country.<br><a href=\"https://www.ielts-exam.net/ielts_interviews/\" target=\"_blank\">&nbsp;Click here for samples</a>&nbsp;</p>','2018-01-13','','IELTS Tips 1','Penguins_1.jpg','1','1');
INSERT INTO `tiptype` VALUES ('1','IELTS',''), ('2','TOEIC','');
INSERT INTO `video` VALUES ('1','Donald Trump vs Hillary Clinton Oabama','https://www.youtube.com/watch?v=zWGMk74iRN0','2018-11-15','','Presidents speaking','1','2'), ('2','Arvenger hihihi','https://www.youtube.com/watch?v=VCYJckDc_fw','2018-12-13','','Phe Phim: Ronin','1','1');
INSERT INTO `videotype` VALUES ('1','PRONUNCIATION',''), ('2','GRAMMAR',''), ('3','SPEAKING','');
