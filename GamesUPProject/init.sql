-- MySQL dump 10.13  Distrib 5.7.24, for osx11.1 (x86_64)
--
-- Host: localhost    Database: GamesUP
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=686 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (87,'J.K. Rowling'),(88,'J.K. Rowling'),(90,'J.K. Rowling'),(91,'J.K. Rowling'),(93,'J.K. Rowling'),(94,'J.K. Rowling'),(96,'J.K. Rowling'),(97,'J.K. Rowling'),(99,'J.K. Rowling'),(100,'J.K. Rowling'),(102,'J.K. Rowling'),(103,'J.K. Rowling'),(105,'J.K. Rowling'),(106,'J.K. Rowling'),(108,'J.K. Rowling'),(109,'J.K. Rowling');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avis`
--

DROP TABLE IF EXISTS `avis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `avis` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) NOT NULL,
  `note` int NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjm7ew4okgyb6nrqslyuivx9w7` (`user_id`),
  CONSTRAINT `FKjm7ew4okgyb6nrqslyuivx9w7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avis`
--

LOCK TABLES `avis` WRITE;
/*!40000 ALTER TABLE `avis` DISABLE KEYS */;
INSERT INTO `avis` VALUES (1,'Excellent jeu!',5,11),(2,'Très bon jeu',4,12),(3,'Super expérience!',4,13),(5,'Excellent jeu!',5,15),(6,'Très bon jeu',4,16),(7,'Super expérience!',4,17),(9,'Excellent jeu!',5,19),(10,'Super expérience!',4,20),(12,'Excellent jeu!',5,22),(13,'Super expérience!',4,23),(15,'Excellent jeu!',5,25),(16,'Super expérience!',4,26),(18,'Excellent jeu!',5,28),(19,'Super expérience!',4,29),(21,'Excellent jeu!',5,31),(22,'Super expérience!',4,32),(24,'Excellent jeu!',5,34),(25,'Super expérience!',4,35),(27,'Excellent jeu!',5,37),(28,'Super expérience!',4,38),(30,'Excellent jeu!',5,40),(31,'Super expérience!',4,41),(33,'Excellent jeu!',5,43),(34,'Super expérience!',4,44),(36,'Excellent jeu!',5,46),(37,'Super expérience!',4,47),(39,'Excellent jeu!',5,49),(40,'Super expérience!',4,50),(42,'Excellent jeu!',5,52),(43,'Super expérience!',4,53),(45,'Excellent jeu!',5,55),(46,'Super expérience!',4,56),(48,'Excellent jeu!',5,58),(49,'Super expérience!',4,59),(51,'Excellent jeu!',5,61),(52,'Super expérience!',4,62),(54,'Excellent jeu!',5,64),(55,'Super expérience!',4,65),(57,'Excellent jeu!',5,67),(58,'Super expérience!',4,68),(60,'Excellent jeu!',5,70),(61,'Super expérience!',4,71),(63,'Excellent jeu!',5,73),(64,'Super expérience!',4,74),(66,'Excellent jeu!',5,76),(67,'Super expérience!',4,77),(69,'Excellent jeu!',5,79),(70,'Super expérience!',4,80),(72,'Excellent jeu!',5,82),(73,'Super expérience!',4,83),(75,'Excellent jeu!',5,85),(76,'Super expérience!',4,86),(78,'Excellent jeu!',5,88),(79,'Super expérience!',4,89),(81,'Excellent jeu!',5,91),(82,'Super expérience!',4,92),(84,'Excellent jeu!',5,94),(85,'Super expérience!',4,95),(87,'Excellent jeu!',5,97),(88,'Super expérience!',4,98),(90,'Excellent jeu!',5,100),(91,'Super expérience!',4,101),(93,'Excellent jeu!',5,103),(94,'Super expérience!',4,104),(96,'Excellent jeu!',5,106),(97,'Super expérience!',4,107),(99,'Excellent jeu!',5,109),(100,'Super expérience!',4,110),(102,'Excellent jeu!',5,112),(103,'Super expérience!',4,113),(105,'Excellent jeu!',5,115),(106,'Super expérience!',4,116),(108,'Excellent jeu!',5,118),(109,'Super expérience!',4,119),(111,'Excellent jeu!',5,121),(112,'Super expérience!',4,122),(114,'Excellent jeu!',5,124),(115,'Super expérience!',4,125),(117,'Excellent jeu!',5,127),(118,'Super expérience!',4,128),(120,'Excellent jeu!',5,130),(121,'Super expérience!',4,131),(123,'Excellent jeu!',5,133),(124,'Super expérience!',4,134),(126,'Excellent jeu!',5,136),(127,'Super expérience!',4,137),(129,'Excellent jeu!',5,139),(130,'Super expérience!',4,140),(132,'Excellent jeu!',5,142),(133,'Super expérience!',4,143),(135,'Excellent jeu!',5,145),(136,'Super expérience!',4,146),(138,'Excellent jeu!',5,148),(139,'Super expérience!',4,149),(141,'Excellent jeu!',5,151),(142,'Super expérience!',4,152),(144,'Excellent jeu!',5,154),(145,'Super expérience!',4,155),(147,'Excellent jeu!',5,157),(148,'Super expérience!',4,158),(150,'Excellent jeu!',5,160),(151,'Super expérience!',4,161),(153,'Excellent jeu!',5,163),(154,'Super expérience!',4,164),(156,'Excellent jeu!',5,166),(157,'Super expérience!',4,167),(159,'Excellent jeu!',5,169),(160,'Super expérience!',4,170),(162,'Excellent jeu!',5,172),(163,'Super expérience!',4,173),(165,'Excellent jeu!',5,175),(166,'Super expérience!',4,176),(168,'Excellent jeu!',5,178),(169,'Super expérience!',4,179),(171,'Excellent jeu!',5,181),(172,'Super expérience!',4,182),(174,'Excellent jeu!',5,184),(175,'Super expérience!',4,185),(177,'Excellent jeu!',5,187),(178,'Super expérience!',4,188);
/*!40000 ALTER TABLE `avis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (306,'johndoe@example.com','John Doe');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_categories`
--

DROP TABLE IF EXISTS `game_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_categories` (
  `game_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`game_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `game_categories_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_categories`
--

LOCK TABLES `game_categories` WRITE;
/*!40000 ALTER TABLE `game_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `games` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text,
  `price` decimal(10,2) NOT NULL,
  `stock` int DEFAULT '0',
  `publisher` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn3iqgaiohooya6btgifkxqar9` (`author_id`),
  CONSTRAINT `FKn3iqgaiohooya6btgifkxqar9` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_item`
--

DROP TABLE IF EXISTS `inventory_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `game_id` bigint DEFAULT NULL,
  `inventory_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk5dyq25q0q8qyxr17iw854bqm` (`inventory_id`),
  KEY `FK_game` (`game_id`),
  CONSTRAINT `FK_game` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`),
  CONSTRAINT `FKk5dyq25q0q8qyxr17iw854bqm` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_item`
--

LOCK TABLES `inventory_item` WRITE;
/*!40000 ALTER TABLE `inventory_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jeux`
--

DROP TABLE IF EXISTS `jeux`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jeux` (
  `id` int NOT NULL AUTO_INCREMENT,
  `auteur` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `num_edition` int NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `publisher_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk67mo2rd9pyhmcl4nyvjtnw5a` (`category_id`),
  KEY `FKagrfkiefy8wm39ujenlaxpv59` (`publisher_id`),
  CONSTRAINT `FKagrfkiefy8wm39ujenlaxpv59` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`),
  CONSTRAINT `FKk67mo2rd9pyhmcl4nyvjtnw5a` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jeux`
--

LOCK TABLES `jeux` WRITE;
/*!40000 ALTER TABLE `jeux` DISABLE KEYS */;
/*!40000 ALTER TABLE `jeux` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `game_id` bigint DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `game_id` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `client_id` bigint NOT NULL,
  `game_id` bigint NOT NULL,
  `order_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `FK17yo6gry2nuwg2erwhbaxqbs9` (`client_id`),
  CONSTRAINT `FK17yo6gry2nuwg2erwhbaxqbs9` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publishers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `archived` bit(1) NOT NULL,
  `date` datetime(6) NOT NULL,
  `delivered` bit(1) NOT NULL,
  `paid` bit(1) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (2,_binary '\0','2024-11-10 23:33:52.884000',_binary '',_binary '',1),(3,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(4,_binary '\0','2024-11-10 23:33:52.936000',_binary '',_binary '',1),(5,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(7,_binary '\0','2024-11-10 23:36:06.174000',_binary '',_binary '',1),(8,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(9,_binary '\0','2024-11-10 23:36:06.205000',_binary '',_binary '',1),(10,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(12,_binary '\0','2024-11-10 23:37:32.568000',_binary '',_binary '',1),(13,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(14,_binary '\0','2024-11-10 23:37:32.601000',_binary '',_binary '',1),(15,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(17,_binary '\0','2024-11-10 23:38:37.418000',_binary '',_binary '',1),(18,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(19,_binary '\0','2024-11-10 23:38:37.471000',_binary '',_binary '',1),(20,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(22,_binary '\0','2024-11-10 23:39:46.508000',_binary '',_binary '',1),(23,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(24,_binary '\0','2024-11-10 23:39:46.616000',_binary '',_binary '',1),(25,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(27,_binary '\0','2024-11-10 23:41:22.751000',_binary '',_binary '',1),(28,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(29,_binary '\0','2024-11-10 23:41:22.787000',_binary '',_binary '',1),(30,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(32,_binary '\0','2024-11-10 23:42:28.479000',_binary '',_binary '',1),(33,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(34,_binary '\0','2024-11-10 23:42:28.511000',_binary '',_binary '',1),(35,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(37,_binary '\0','2024-11-10 23:45:29.250000',_binary '',_binary '',1),(38,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(39,_binary '\0','2024-11-10 23:45:29.312000',_binary '',_binary '',1),(40,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(42,_binary '\0','2024-11-10 23:47:17.758000',_binary '',_binary '',1),(43,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(44,_binary '\0','2024-11-10 23:47:17.818000',_binary '',_binary '',1),(45,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(47,_binary '\0','2024-11-10 23:49:06.976000',_binary '',_binary '',1),(48,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(49,_binary '\0','2024-11-10 23:49:07.010000',_binary '',_binary '',1),(50,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(52,_binary '\0','2024-11-10 23:50:45.672000',_binary '',_binary '',1),(53,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(54,_binary '\0','2024-11-10 23:50:45.708000',_binary '',_binary '',1),(55,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(57,_binary '\0','2024-11-10 23:52:42.798000',_binary '',_binary '',1),(58,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(59,_binary '\0','2024-11-10 23:52:42.839000',_binary '',_binary '',1),(60,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(62,_binary '\0','2024-11-10 23:57:40.021000',_binary '',_binary '',1),(63,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(64,_binary '\0','2024-11-10 23:57:40.060000',_binary '',_binary '',1),(65,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(67,_binary '\0','2024-11-10 23:59:50.766000',_binary '',_binary '',1),(68,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(69,_binary '\0','2024-11-10 23:59:50.799000',_binary '',_binary '',1),(70,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(72,_binary '\0','2024-11-11 00:07:25.691000',_binary '',_binary '',1),(73,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(74,_binary '\0','2024-11-11 00:07:25.724000',_binary '',_binary '',1),(75,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(77,_binary '\0','2024-11-11 00:08:29.601000',_binary '',_binary '',1),(78,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(79,_binary '\0','2024-11-11 00:08:29.630000',_binary '',_binary '',1),(80,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(82,_binary '\0','2024-11-11 00:14:36.400000',_binary '',_binary '',1),(83,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(84,_binary '\0','2024-11-11 00:14:36.428000',_binary '',_binary '',1),(85,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(87,_binary '\0','2024-11-11 00:16:52.441000',_binary '',_binary '',1),(88,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(89,_binary '\0','2024-11-11 00:16:52.479000',_binary '',_binary '',1),(90,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(92,_binary '\0','2024-11-11 00:17:38.963000',_binary '',_binary '',1),(93,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(94,_binary '\0','2024-11-11 00:17:39.027000',_binary '',_binary '',1),(95,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(97,_binary '\0','2024-11-11 00:21:55.405000',_binary '',_binary '',1),(98,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(99,_binary '\0','2024-11-11 00:21:55.483000',_binary '',_binary '',1),(100,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(102,_binary '\0','2024-11-11 00:26:10.029000',_binary '',_binary '',1),(103,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(104,_binary '\0','2024-11-11 00:26:10.118000',_binary '',_binary '',1),(105,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(107,_binary '\0','2024-11-11 00:41:50.610000',_binary '',_binary '',1),(108,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(109,_binary '\0','2024-11-11 00:41:50.646000',_binary '',_binary '',1),(110,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(112,_binary '\0','2024-11-11 00:43:24.886000',_binary '',_binary '',1),(113,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(114,_binary '\0','2024-11-11 00:43:24.946000',_binary '',_binary '',1),(115,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(117,_binary '\0','2024-11-11 00:46:26.869000',_binary '',_binary '',1),(118,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(119,_binary '\0','2024-11-11 00:46:26.989000',_binary '',_binary '',1),(120,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(122,_binary '\0','2024-11-11 00:47:17.228000',_binary '',_binary '',1),(123,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(124,_binary '\0','2024-11-11 00:47:17.265000',_binary '',_binary '',1),(125,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(127,_binary '\0','2024-11-11 00:50:16.479000',_binary '',_binary '',1),(128,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(129,_binary '\0','2024-11-11 00:50:16.602000',_binary '',_binary '',1),(130,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(132,_binary '\0','2024-11-11 01:57:49.164000',_binary '',_binary '',1),(133,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(134,_binary '\0','2024-11-11 01:57:49.202000',_binary '',_binary '',1),(135,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1),(137,_binary '\0','2024-11-11 02:05:55.335000',_binary '',_binary '',1),(138,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '',1),(139,_binary '\0','2024-11-11 02:05:55.412000',_binary '',_binary '',1),(140,_binary '\0','2024-11-10 13:00:00.000000',_binary '',_binary '\0',1);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_line`
--

DROP TABLE IF EXISTS `purchase_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_line` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jeu_id` int NOT NULL,
  `prix` double NOT NULL,
  `utilisateur_id` int NOT NULL,
  `purchase_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf13kjx8dac73h8k13urbl613i` (`purchase_id`),
  CONSTRAINT `FKf13kjx8dac73h8k13urbl613i` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_line`
--

LOCK TABLES `purchase_line` WRITE;
/*!40000 ALTER TABLE `purchase_line` DISABLE KEYS */;
INSERT INTO `purchase_line` VALUES (1,2,99.99,1,NULL);
/*!40000 ALTER TABLE `purchase_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommendation`
--

DROP TABLE IF EXISTS `recommendation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommendation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `recommendation_details` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommendation`
--

LOCK TABLES `recommendation` WRITE;
/*!40000 ALTER TABLE `recommendation` DISABLE KEYS */;
/*!40000 ALTER TABLE `recommendation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommendations`
--

DROP TABLE IF EXISTS `recommendations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommendations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `game_id` int NOT NULL,
  `score` decimal(5,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `game_id` (`game_id`),
  KEY `recommendations_ibfk_1` (`user_id`),
  CONSTRAINT `recommendations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommendations`
--

LOCK TABLES `recommendations` WRITE;
/*!40000 ALTER TABLE `recommendations` DISABLE KEYS */;
/*!40000 ALTER TABLE `recommendations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(4,'client'),(2,'temporaryName'),(10,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin',NULL,NULL,NULL),(2,'user',NULL,NULL,NULL),(3,'admin','admin',NULL,NULL),(4,'user','user',NULL,NULL),(5,'testuser','testuser','testpassword',NULL),(6,'John Doe','john_doe','$2a$10$Rg30v2nPA9/nZLhzOJZ.s.BXwP3Jc4mKu5zL6UCPQJ7Dov6u/3WJG',NULL),(7,NULL,'newuser','$2a$10$m5qwOaQazsV9Oi2jGe78UuZA9G0RKSXxpfOO4eQIH.BgG/SxCF6mu',NULL),(8,NULL,NULL,'$2a$10$vvyQR8UQfd7vyMtzMVr7mupJk3SYGhB2B2rJSOp40jlY82hoIOOR6',NULL),(9,NULL,NULL,'$2a$10$.YHFJGsRLQs2./x5kgnabOal5Q5C3dj5S1EzaDG.6rB7WmCa94V86',NULL),(10,'Test User','testuser@gamesup.com','$2a$10$Rk49/9yInjWacTG1Orvzk.Rzy7TiGXRVI3gVhAv7EzCluQzxiUmuq',NULL),(11,'Test User','testuser','password','testuser@example.com'),(12,'Test User','testuser','password','testuser@example.com'),(13,'Test User','testuser','password','testuser@example.com'),(14,'Test User','testuser','password','testuser@example.com'),(15,'Test User','testuser','password','testuser@example.com'),(16,'Test User','testuser','password','testuser@example.com'),(17,'Test User','testuser','password','testuser@example.com'),(18,'Test User','testuser','password','testuser@example.com'),(19,'Test User','testuser','password','testuser@example.com'),(20,'Test User','testuser','password','testuser@example.com'),(21,'Test User','testuser','password','testuser@example.com'),(22,'Test User','testuser','password','testuser@example.com'),(23,'Test User','testuser','password','testuser@example.com'),(24,'Test User','testuser','password','testuser@example.com'),(25,'Test User','testuser','password','testuser@example.com'),(26,'Test User','testuser','password','testuser@example.com'),(27,'Test User','testuser','password','testuser@example.com'),(28,'Test User','testuser','password','testuser@example.com'),(29,'Test User','testuser','password','testuser@example.com'),(30,'Test User','testuser','password','testuser@example.com'),(31,'Test User','testuser','password','testuser@example.com'),(32,'Test User','testuser','password','testuser@example.com'),(33,'Test User','testuser','password','testuser@example.com'),(34,'Test User','testuser','password','testuser@example.com'),(35,'Test User','testuser','password','testuser@example.com'),(36,'Test User','testuser','password','testuser@example.com'),(37,'Test User','testuser','password','testuser@example.com'),(38,'Test User','testuser','password','testuser@example.com'),(39,'Test User','testuser','password','testuser@example.com'),(40,'Test User','testuser','password','testuser@example.com'),(41,'Test User','testuser','password','testuser@example.com'),(42,'Test User','testuser','password','testuser@example.com'),(43,'Test User','testuser','password','testuser@example.com'),(44,'Test User','testuser','password','testuser@example.com'),(45,'Test User','testuser','password','testuser@example.com'),(46,'Test User','testuser','password','testuser@example.com'),(47,'Test User','testuser','password','testuser@example.com'),(48,'Test User','testuser','password','testuser@example.com'),(49,'Test User','testuser','password','testuser@example.com'),(50,'Test User','testuser','password','testuser@example.com'),(51,'Test User','testuser','password','testuser@example.com'),(52,'Test User','testuser','password','testuser@example.com'),(53,'Test User','testuser','password','testuser@example.com'),(54,'Test User','testuser','password','testuser@example.com'),(55,'Test User','testuser','password','testuser@example.com'),(56,'Test User','testuser','password','testuser@example.com'),(57,'Test User','testuser','password','testuser@example.com'),(58,'Test User','testuser','password','testuser@example.com'),(59,'Test User','testuser','password','testuser@example.com'),(60,'Test User','testuser','password','testuser@example.com'),(61,'Test User','testuser','password','testuser@example.com'),(62,'Test User','testuser','password','testuser@example.com'),(63,'Test User','testuser','password','testuser@example.com'),(64,'Test User','testuser','password','testuser@example.com'),(65,'Test User','testuser','password','testuser@example.com'),(66,'Test User','testuser','password','testuser@example.com'),(67,'Test User','testuser','password','testuser@example.com'),(68,'Test User','testuser','password','testuser@example.com'),(69,'Test User','testuser','password','testuser@example.com'),(70,'Test User','testuser','password','testuser@example.com'),(71,'Test User','testuser','password','testuser@example.com'),(72,'Test User','testuser','password','testuser@example.com'),(73,'Test User','testuser','password','testuser@example.com'),(74,'Test User','testuser','password','testuser@example.com'),(75,'Test User','testuser','password','testuser@example.com'),(76,'Test User','testuser','password','testuser@example.com'),(77,'Test User','testuser','password','testuser@example.com'),(78,'Test User','testuser','password','testuser@example.com'),(79,'Test User','testuser','password','testuser@example.com'),(80,'Test User','testuser','password','testuser@example.com'),(81,'Test User','testuser','password','testuser@example.com'),(82,'Test User','testuser','password','testuser@example.com'),(83,'Test User','testuser','password','testuser@example.com'),(84,'Test User','testuser','password','testuser@example.com'),(85,'Test User','testuser','password','testuser@example.com'),(86,'Test User','testuser','password','testuser@example.com'),(87,'Test User','testuser','password','testuser@example.com'),(88,'Test User','testuser','password','testuser@example.com'),(89,'Test User','testuser','password','testuser@example.com'),(90,'Test User','testuser','password','testuser@example.com'),(91,'Test User','testuser','password','testuser@example.com'),(92,'Test User','testuser','password','testuser@example.com'),(93,'Test User','testuser','password','testuser@example.com'),(94,'Test User','testuser','password','testuser@example.com'),(95,'Test User','testuser','password','testuser@example.com'),(96,'Test User','testuser','password','testuser@example.com'),(97,'Test User','testuser','password','testuser@example.com'),(98,'Test User','testuser','password','testuser@example.com'),(99,'Test User','testuser','password','testuser@example.com'),(100,'Test User','testuser','password','testuser@example.com'),(101,'Test User','testuser','password','testuser@example.com'),(102,'Test User','testuser','password','testuser@example.com'),(103,'Test User','testuser','password','testuser@example.com'),(104,'Test User','testuser','password','testuser@example.com'),(105,'Test User','testuser','password','testuser@example.com'),(106,'Test User','testuser','password','testuser@example.com'),(107,'Test User','testuser','password','testuser@example.com'),(108,'Test User','testuser','password','testuser@example.com'),(109,'Test User','testuser','password','testuser@example.com'),(110,'Test User','testuser','password','testuser@example.com'),(111,'Test User','testuser','password','testuser@example.com'),(112,'Test User','testuser','password','testuser@example.com'),(113,'Test User','testuser','password','testuser@example.com'),(114,'Test User','testuser','password','testuser@example.com'),(115,'Test User','testuser','password','testuser@example.com'),(116,'Test User','testuser','password','testuser@example.com'),(117,'Test User','testuser','password','testuser@example.com'),(118,'Test User','testuser','password','testuser@example.com'),(119,'Test User','testuser','password','testuser@example.com'),(120,'Test User','testuser','password','testuser@example.com'),(121,'Test User','testuser','password','testuser@example.com'),(122,'Test User','testuser','password','testuser@example.com'),(123,'Test User','testuser','password','testuser@example.com'),(124,'Test User','testuser','password','testuser@example.com'),(125,'Test User','testuser','password','testuser@example.com'),(126,'Test User','testuser','password','testuser@example.com'),(127,'Test User','testuser','password','testuser@example.com'),(128,'Test User','testuser','password','testuser@example.com'),(129,'Test User','testuser','password','testuser@example.com'),(130,'Test User','testuser','password','testuser@example.com'),(131,'Test User','testuser','password','testuser@example.com'),(132,'Test User','testuser','password','testuser@example.com'),(133,'Test User','testuser','password','testuser@example.com'),(134,'Test User','testuser','password','testuser@example.com'),(135,'Test User','testuser','password','testuser@example.com'),(136,'Test User','testuser','password','testuser@example.com'),(137,'Test User','testuser','password','testuser@example.com'),(138,'Test User','testuser','password','testuser@example.com'),(139,'Test User','testuser','password','testuser@example.com'),(140,'Test User','testuser','password','testuser@example.com'),(141,'Test User','testuser','password','testuser@example.com'),(142,'Test User','testuser','password','testuser@example.com'),(143,'Test User','testuser','password','testuser@example.com'),(144,'Test User','testuser','password','testuser@example.com'),(145,'Test User','testuser','password','testuser@example.com'),(146,'Test User','testuser','password','testuser@example.com'),(147,'Test User','testuser','password','testuser@example.com'),(148,'Test User','testuser','password','testuser@example.com'),(149,'Test User','testuser','password','testuser@example.com'),(150,'Test User','testuser','password','testuser@example.com'),(151,'Test User','testuser','password','testuser@example.com'),(152,'Test User','testuser','password','testuser@example.com'),(153,'Test User','testuser','password','testuser@example.com'),(154,'Test User','testuser','password','testuser@example.com'),(155,'Test User','testuser','password','testuser@example.com'),(156,'Test User','testuser','password','testuser@example.com'),(157,'Test User','testuser','password','testuser@example.com'),(158,'Test User','testuser','password','testuser@example.com'),(159,'Test User','testuser','password','testuser@example.com'),(160,'Test User','testuser','password','testuser@example.com'),(161,'Test User','testuser','password','testuser@example.com'),(162,'Test User','testuser','password','testuser@example.com'),(163,'Test User','testuser','password','testuser@example.com'),(164,'Test User','testuser','password','testuser@example.com'),(165,'Test User','testuser','password','testuser@example.com'),(166,'Test User','testuser','password','testuser@example.com'),(167,'Test User','testuser','password','testuser@example.com'),(168,'Test User','testuser','password','testuser@example.com'),(169,'Test User','testuser','password','testuser@example.com'),(170,'Test User','testuser','password','testuser@example.com'),(171,'Test User','testuser','password','testuser@example.com'),(172,'Test User','testuser','password','testuser@example.com'),(173,'Test User','testuser','password','testuser@example.com'),(174,'Test User','testuser','password','testuser@example.com'),(175,'Test User','testuser','password','testuser@example.com'),(176,'Test User','testuser','password','testuser@example.com'),(177,'Test User','testuser','password','testuser@example.com'),(178,'Test User','testuser','password','testuser@example.com'),(179,'Test User','testuser','password','testuser@example.com'),(180,'Test User','testuser','password','testuser@example.com'),(181,'Test User','testuser','password','testuser@example.com'),(182,'Test User','testuser','password','testuser@example.com'),(183,'Test User','testuser','password','testuser@example.com'),(184,'Test User','testuser','password','testuser@example.com'),(185,'Test User','testuser','password','testuser@example.com'),(186,'Test User','testuser','password','testuser@example.com'),(187,'Test User','testuser','password','testuser@example.com'),(188,'Test User','testuser','password','testuser@example.com'),(189,'Test User','testuser','password','testuser@example.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_purchase`
--

DROP TABLE IF EXISTS `user_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_purchase` (
  `id` int NOT NULL,
  `game_id` int NOT NULL,
  `rating` float NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_purchase`
--

LOCK TABLES `user_purchase` WRITE;
/*!40000 ALTER TABLE `user_purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (10,1),(9,4);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('client','admin') NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wishlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd4r80jm8s41fgoa0xv9yy5lo8` (`user_id`),
  CONSTRAINT `FKd4r80jm8s41fgoa0xv9yy5lo8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist_game`
--

DROP TABLE IF EXISTS `wishlist_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wishlist_game` (
  `wishlist_id` bigint NOT NULL,
  `game_id` bigint DEFAULT NULL,
  KEY `FKgn43ncxc1mnl86eonvhj4nh8s` (`wishlist_id`),
  KEY `FKe6rg64ukshk91gymi9d5g3yr9` (`game_id`),
  CONSTRAINT `FKe6rg64ukshk91gymi9d5g3yr9` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`),
  CONSTRAINT `FKgn43ncxc1mnl86eonvhj4nh8s` FOREIGN KEY (`wishlist_id`) REFERENCES `wishlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist_game`
--

LOCK TABLES `wishlist_game` WRITE;
/*!40000 ALTER TABLE `wishlist_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `wishlist_game` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-11  5:05:49
