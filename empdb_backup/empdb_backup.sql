-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: empdb
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `compliance`
--

DROP TABLE IF EXISTS `compliance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compliance` (
  `complianceid` int NOT NULL AUTO_INCREMENT,
  `rlType` varchar(15) DEFAULT (NULL),
  `details` varchar(250) DEFAULT (NULL),
  `createDate` date DEFAULT (NULL),
  `department_id` int DEFAULT (NULL),
  PRIMARY KEY (`complianceid`),
  KEY `FK_DEPART_ID` (`department_id`),
  CONSTRAINT `FK_DEPART_ID` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compliance`
--

LOCK TABLES `compliance` WRITE;
/*!40000 ALTER TABLE `compliance` DISABLE KEYS */;
/*!40000 ALTER TABLE `compliance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_nm` varchar(25) NOT NULL,
  PRIMARY KEY (`department_id`),
  UNIQUE KEY `department_nm` (`department_nm`)
) ENGINE=InnoDB AUTO_INCREMENT=1158 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1157,'Account'),(1144,'Finance'),(1111,'HR'),(1141,'Marketing'),(1145,'Operation'),(1155,'security'),(1149,'Testing');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `empid` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT (NULL),
  `lastname` varchar(45) DEFAULT (NULL),
  `dob` date DEFAULT (NULL),
  `email` varchar(100) DEFAULT (NULL),
  `department_id` int DEFAULT (NULL),
  `role` varchar(15) NOT NULL,
  `pass` varchar(30) NOT NULL,
  PRIMARY KEY (`empid`),
  KEY `FK_DEPARTMENT_ID` (`department_id`),
  CONSTRAINT `FK_DEPARTMENT_ID` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22224451 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (22224449,'Anurag','Sharma','1990-03-09','anuragboss01@gmail.com',1111,'admin','sahib'),(22224450,'raju','paji','1990-01-09','a@in.ca',1157,'user','bhalle');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_emp_insert` AFTER INSERT ON `employees` FOR EACH ROW BEGIN
        INSERT INTO login_master(userid, password,  role)
        VALUES(new.empid, new.role, new.pass);
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_emp_delete` BEFORE DELETE ON `employees` FOR EACH ROW BEGIN
    delete from login_master where userid = OLD.empid;
  
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `login_master`
--

DROP TABLE IF EXISTS `login_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_master` (
  `userid` int NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `FK_EMPID` FOREIGN KEY (`userid`) REFERENCES `employees` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_master`
--

LOCK TABLES `login_master` WRITE;
/*!40000 ALTER TABLE `login_master` DISABLE KEYS */;
INSERT INTO `login_master` VALUES (22224449,'admin','sahib'),(22224450,'user','bhalle');
/*!40000 ALTER TABLE `login_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statusreport`
--

DROP TABLE IF EXISTS `statusreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statusreport` (
  `complianceid` int NOT NULL,
  `statusrptid` int NOT NULL AUTO_INCREMENT,
  `empid` int NOT NULL,
  `comments` varchar(15) DEFAULT (NULL),
  `createDate` date DEFAULT (NULL),
  `department_id` int DEFAULT (NULL),
  PRIMARY KEY (`statusrptid`),
  KEY `statusreport_ibfk_1` (`department_id`),
  KEY `statusreport_ibfk_2` (`empid`),
  KEY `statusreport_ibfk_3` (`complianceid`),
  CONSTRAINT `statusreport_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `statusreport_ibfk_2` FOREIGN KEY (`empid`) REFERENCES `employees` (`empid`),
  CONSTRAINT `statusreport_ibfk_3` FOREIGN KEY (`complianceid`) REFERENCES `compliance` (`complianceid`)
) ENGINE=InnoDB AUTO_INCREMENT=99991111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statusreport`
--

LOCK TABLES `statusreport` WRITE;
/*!40000 ALTER TABLE `statusreport` DISABLE KEYS */;
/*!40000 ALTER TABLE `statusreport` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-09 19:46:04
