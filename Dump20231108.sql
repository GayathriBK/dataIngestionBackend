-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: dataingestion
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.19-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `file_category_mapping`
--

DROP TABLE IF EXISTS `file_category_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_category_mapping` (
  `file_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`file_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `file_category_mapping_ibfk_1` FOREIGN KEY (`file_id`) REFERENCES `ingested_files` (`id`),
  CONSTRAINT `file_category_mapping_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `filecategories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_category_mapping`
--

LOCK TABLES `file_category_mapping` WRITE;
/*!40000 ALTER TABLE `file_category_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_category_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filecategories`
--

DROP TABLE IF EXISTS `filecategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `filecategories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `validation_` varchar(50) DEFAULT NULL,
  `transformation` varchar(50) NOT NULL,
  `enrichment` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filecategories`
--

LOCK TABLES `filecategories` WRITE;
/*!40000 ALTER TABLE `filecategories` DISABLE KEYS */;
INSERT INTO `filecategories` VALUES (1,NULL,'CsvToJson','NPPES');
/*!40000 ALTER TABLE `filecategories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingested_data`
--

DROP TABLE IF EXISTS `ingested_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingested_data` (
  `file_id` int(11) NOT NULL,
  `provider_first_name` varchar(255) DEFAULT NULL,
  `provider_last_name` varchar(255) DEFAULT NULL,
  `ssn` varchar(11) DEFAULT NULL,
  `provider_service_location_street` varchar(255) DEFAULT NULL,
  `provider_service_location_city` varchar(255) DEFAULT NULL,
  `provider_service_location_state` varchar(255) DEFAULT NULL,
  `provider_service_location_zip` varchar(10) DEFAULT NULL,
  `nppes_taxonomy` varchar(255) DEFAULT NULL,
  `nppes_address` varchar(255) DEFAULT NULL,
  KEY `file_id` (`file_id`),
  CONSTRAINT `ingested_data_ibfk_1` FOREIGN KEY (`file_id`) REFERENCES `ingested_files` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingested_data`
--

LOCK TABLES `ingested_data` WRITE;
/*!40000 ALTER TABLE `ingested_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingested_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingested_files`
--

DROP TABLE IF EXISTS `ingested_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingested_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `uploaded_date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` varchar(20) NOT NULL,
  `ingested_date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `error_message` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingested_files`
--

LOCK TABLES `ingested_files` WRITE;
/*!40000 ALTER TABLE `ingested_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingested_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','adminLogin@123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-08  7:59:31
