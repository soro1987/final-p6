-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: db_escalade
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `voies`
--

DROP TABLE IF EXISTS `voies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voies` (
  `id` int NOT NULL,
  `cotations` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `longeur_id` int DEFAULT NULL,
  `nombres_relais` int DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `points` int DEFAULT NULL,
  `secteur_id` int DEFAULT NULL,
  `voies_id` int DEFAULT NULL,
  `nom_voie` varchar(50) DEFAULT NULL,
  `no` varchar(50) DEFAULT NULL,
  `nom_voies` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1yyloxna5emwaawxrrie3gcx0` (`secteur_id`),
  KEY `FK48obo805j9h7e3l3623h6hw66` (`voies_id`),
  CONSTRAINT `FK1yyloxna5emwaawxrrie3gcx0` FOREIGN KEY (`secteur_id`) REFERENCES `secteur` (`id`),
  CONSTRAINT `FK48obo805j9h7e3l3623h6hw66` FOREIGN KEY (`voies_id`) REFERENCES `secteur` (`id`),
  CONSTRAINT `FKh6jq77b2etynd9duljnrya8ed` FOREIGN KEY (`secteur_id`) REFERENCES `voies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voies`
--

LOCK TABLES `voies` WRITE;
/*!40000 ALTER TABLE `voies` DISABLE KEYS */;
INSERT INTO `voies` VALUES (96,'bwbv','vbvbvwbdwbdbw',1,1,NULL,1,NULL,22,NULL,NULL,'mhgmhgf'),(98,'hhhgfff','cg,hgfhghc',1,1,NULL,1,NULL,22,NULL,NULL,'mhgmhgf'),(99,'123','rgjtdj',4,3,NULL,4,NULL,22,NULL,NULL,'mhgmhgf'),(100,'fbbf b','fnfn  cg  ',1,1,NULL,1,NULL,22,NULL,NULL,'bgfbgfb');
/*!40000 ALTER TABLE `voies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-17 21:21:40
