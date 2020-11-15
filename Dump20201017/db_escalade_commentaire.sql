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
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentaire` (
  `id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `site_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `topo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7poewyx0vupiexwj3o3v5r746` (`site_id`),
  KEY `FKmru48t4nk57igcva25tfyl5v6` (`user_id`),
  KEY `FKaa6a80ne3ip3kms28ujmr6hbt` (`topo_id`),
  CONSTRAINT `FK7poewyx0vupiexwj3o3v5r746` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKaa6a80ne3ip3kms28ujmr6hbt` FOREIGN KEY (`topo_id`) REFERENCES `topo` (`id`),
  CONSTRAINT `FKmru48t4nk57igcva25tfyl5v6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentaire`
--

LOCK TABLES `commentaire` WRITE;
/*!40000 ALTER TABLE `commentaire` DISABLE KEYS */;
INSERT INTO `commentaire` VALUES (50,'2020-10-13 10:11:57.204000','Je suis Soro qui commente et je l\'ai modifié encore de nouveau',10,1,NULL),(51,'2020-10-11 21:34:32.573000','Je suis Soro qui commente',10,1,NULL),(55,'2020-10-12 15:41:20.800000','Je suis un autre commentaire par Camara',12,7,NULL),(56,'2020-10-12 15:41:33.091000','Je suis un autre commentaire par Camara',12,7,NULL),(68,'2020-10-15 16:22:01.245000','Je suisun commentaire modifier',10,7,NULL),(69,'2020-10-13 19:23:41.676000','Un autre commentaire dans ce site new encore',13,1,NULL),(70,'2020-10-13 19:24:19.813000','Un autre commentaire dans ce site new encore',13,1,NULL),(71,'2020-10-13 19:28:19.938000','Un autre commentaire dans ce site new encore 12',17,1,NULL),(72,'2020-10-13 19:30:40.441000','Un autre commentaire dans ce site new encore 145',17,1,NULL),(86,'2020-10-15 07:53:35.218000','Vs pourriez mettre une description',NULL,1,1);
/*!40000 ALTER TABLE `commentaire` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-17 21:21:36
