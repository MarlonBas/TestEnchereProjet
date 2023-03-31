CREATE DATABASE  IF NOT EXISTS `projetencheres` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projetencheres`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: projetencheres
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `adresses`
--

DROP TABLE IF EXISTS `adresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adresses` (
  `id_adresse` int NOT NULL AUTO_INCREMENT,
  `rue` varchar(30) NOT NULL,
  `code_postal` int NOT NULL,
  `ville` varchar(30) NOT NULL,
  PRIMARY KEY (`id_adresse`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresses`
--

LOCK TABLES `adresses` WRITE;
/*!40000 ALTER TABLE `adresses` DISABLE KEYS */;
INSERT INTO `adresses` VALUES (1,'1 Rue du DebugGit',76000,'Village'),(2,'1 Rue du DebugGit',76000,'Village'),(3,'5 Place de la Republique',75000,'Paris'),(4,'5 Place de la Republique',75000,'Paris'),(5,'Rue de la soif',13000,'Marseille'),(6,'Rue de la soif',13000,'Marseille'),(7,'Rue de la soif',13000,'Marseille'),(8,'5 Place de la Republique',75000,'Paris');
/*!40000 ALTER TABLE `adresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articles` (
  `id_article` int NOT NULL AUTO_INCREMENT,
  `no_utilisateur` int NOT NULL,
  `id_adresse` int NOT NULL,
  `id_categorie` int NOT NULL,
  `nom_article` varchar(70) NOT NULL,
  `descr_article` varchar(200) DEFAULT NULL,
  `date_debut_encheres` date NOT NULL,
  `date_fin_encheres` date NOT NULL,
  `mise_a_prix` int NOT NULL,
  `prix_vente` int NOT NULL,
  `etat_vente` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_article`),
  KEY `fk_articles_utilisateurs` (`no_utilisateur`),
  KEY `fk_adresses_utilisateurs` (`id_adresse`),
  KEY `fk_categories_utilisateurs` (`id_categorie`),
  CONSTRAINT `fk_adresses_utilisateurs` FOREIGN KEY (`id_adresse`) REFERENCES `adresses` (`id_adresse`),
  CONSTRAINT `fk_articles_utilisateurs` FOREIGN KEY (`no_utilisateur`) REFERENCES `utilisateurs` (`no_utilisateur`),
  CONSTRAINT `fk_categories_utilisateurs` FOREIGN KEY (`id_categorie`) REFERENCES `categories` (`id_categorie`),
  CONSTRAINT `CK_etat_vente` CHECK ((`etat_vente` in (_utf8mb4'termine',_utf8mb4'en cours',_utf8mb4'pas debute')))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (1,1,2,4,'HTML pour les nuls','Inutile, jamais utilisé','2023-03-31','2023-04-02',10,20,'en cours'),(2,2,4,1,'TomChat','En trés bon état de marche, tendances gréviste','2023-03-31','2023-04-07',50,60,'en cours'),(3,3,6,4,'Le Temps Des Tempêtes','Jamais utilisé','2021-01-05','2023-04-07',10,10,'en cours'),(4,3,7,1,'Cookies pour chat','Des cookies html délicieux pour un TomChat','2023-03-31','2023-04-02',10,10,'en cours'),(5,2,8,2,'Chemise hawaïenne','Trés bonne état, mais un peu sale','2023-03-30','2023-03-31',30,30,'en cours');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id_categorie` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) NOT NULL,
  PRIMARY KEY (`id_categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Informatique'),(2,'Vêtement'),(3,'Electroménager'),(4,'Livre');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encheres`
--

DROP TABLE IF EXISTS `encheres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `encheres` (
  `id_enchere` int NOT NULL AUTO_INCREMENT,
  `no_utilisateur` int NOT NULL,
  `id_article` int NOT NULL,
  `date_enchere` date NOT NULL,
  `montant_enchere` int NOT NULL,
  PRIMARY KEY (`id_enchere`),
  KEY `fk_encheres_utilisateurs` (`no_utilisateur`),
  KEY `fk_encheres_articles` (`id_article`),
  CONSTRAINT `fk_encheres_articles` FOREIGN KEY (`id_article`) REFERENCES `articles` (`id_article`),
  CONSTRAINT `fk_encheres_utilisateurs` FOREIGN KEY (`no_utilisateur`) REFERENCES `utilisateurs` (`no_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encheres`
--

LOCK TABLES `encheres` WRITE;
/*!40000 ALTER TABLE `encheres` DISABLE KEYS */;
INSERT INTO `encheres` VALUES (1,3,1,'2023-03-31',20),(2,1,2,'2023-03-31',60);
/*!40000 ALTER TABLE `encheres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateurs` (
  `no_utilisateur` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `mot_de_passe` varchar(50) NOT NULL,
  `credit` int NOT NULL,
  `administrateur` bit(1) NOT NULL,
  `id_adresse` int NOT NULL,
  PRIMARY KEY (`no_utilisateur`),
  UNIQUE KEY `pseudo` (`pseudo`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_adresse_utilisateur` (`id_adresse`),
  CONSTRAINT `fk_adresse_utilisateur` FOREIGN KEY (`id_adresse`) REFERENCES `adresses` (`id_adresse`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateurs`
--

LOCK TABLES `utilisateurs` WRITE;
/*!40000 ALTER TABLE `utilisateurs` DISABLE KEYS */;
INSERT INTO `utilisateurs` VALUES (1,'TierX','Unautre','Thierry','tierx@gmail.com','060606075','test',40,_binary '\0',1),(2,'DarkAlex75','Petit','Alex','darkalex75@gmail.com','067506060','test',100,_binary '\0',3),(3,'Yellow13','Ricard','Lohan','lohanlemarseillais@testnet.com','06515151','test',80,_binary '\0',5);
/*!40000 ALTER TABLE `utilisateurs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-31 11:57:29
