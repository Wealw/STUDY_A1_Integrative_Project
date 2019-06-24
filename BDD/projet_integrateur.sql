-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 24 juin 2019 à 13:18
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_integrateur`
--

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `getPoints`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getPoints` (IN `p_id_map` INT)  NO SQL
SELECT points.Point, points.PosX, points.PosY
FROM points
WHERE points.ID_Map = p_id_map$$

DROP PROCEDURE IF EXISTS `getVectors`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getVectors` (IN `p_id_map` INT)  NO SQL
SELECT vecteurs.Point, vecteurs.Directions
FROM vecteurs
WHERE vecteurs.ID_Map = p_id_map$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `map`
--

DROP TABLE IF EXISTS `map`;
CREATE TABLE IF NOT EXISTS `map` (
  `ID_Map` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Longueur` int(11) NOT NULL,
  `Hauteur` int(11) NOT NULL,
  PRIMARY KEY (`ID_Map`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `map`
--

INSERT INTO `map` (`ID_Map`, `Nom`, `Longueur`, `Hauteur`) VALUES
(1, 'SmartCity', 110, 60);

-- --------------------------------------------------------

--
-- Structure de la table `points`
--

DROP TABLE IF EXISTS `points`;
CREATE TABLE IF NOT EXISTS `points` (
  `ID_Point` int(11) NOT NULL,
  `ID_Map` int(11) NOT NULL,
  `Point` text,
  `PosX` int(11) DEFAULT NULL,
  `PosY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Point`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `points`
--

INSERT INTO `points` (`ID_Point`, `ID_Map`, `Point`, `PosX`, `PosY`) VALUES
(1, 1, 'A', 0, 54),
(2, 1, 'B', 19, 54),
(3, 1, 'C', 59, 54),
(4, 1, 'D', 78, 54),
(5, 1, 'E', 90, 54),
(6, 1, 'F', 0, 40),
(7, 1, 'G', 19, 40),
(8, 1, 'H', 59, 40),
(9, 1, 'I', 0, 22),
(10, 1, 'J', 78, 22),
(11, 1, 'K', 90, 22),
(12, 1, 'L', 19, 12),
(13, 1, 'M', 78, 12),
(14, 1, 'N', 0, 0),
(15, 1, 'O', 19, 0),
(16, 1, 'P', 90, 0);

-- --------------------------------------------------------

--
-- Structure de la table `vecteurs`
--

DROP TABLE IF EXISTS `vecteurs`;
CREATE TABLE IF NOT EXISTS `vecteurs` (
  `Identifiant` int(11) NOT NULL,
  `ID_Map` int(11) NOT NULL,
  `Point` text,
  `Directions` text,
  PRIMARY KEY (`Identifiant`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vecteurs`
--

INSERT INTO `vecteurs` (`Identifiant`, `ID_Map`, `Point`, `Directions`) VALUES
(1, 1, 'A', 'B'),
(2, 1, 'A', 'F'),
(3, 1, 'B', 'C'),
(4, 1, 'B', 'G'),
(5, 1, 'B', 'A'),
(6, 1, 'C', 'D'),
(7, 1, 'C', 'H'),
(8, 1, 'C', 'B'),
(9, 1, 'D', 'C'),
(10, 1, 'D', 'E'),
(11, 1, 'D', 'J'),
(12, 1, 'E', 'D'),
(13, 1, 'E', 'K'),
(14, 1, 'F', 'A'),
(15, 1, 'F', 'G'),
(16, 1, 'F', 'I'),
(17, 1, 'G', 'F'),
(18, 1, 'G', 'B'),
(19, 1, 'G', 'H'),
(20, 1, 'H', 'C'),
(21, 1, 'H', 'G'),
(22, 1, 'I', 'F'),
(23, 1, 'I', 'J'),
(24, 1, 'I', 'N'),
(25, 1, 'J', 'D'),
(26, 0, 'J', 'K'),
(27, 0, 'J', 'M'),
(28, 0, 'J', 'I'),
(29, 0, 'K', 'E'),
(30, 0, 'K', 'J'),
(31, 0, 'K', 'P'),
(32, 0, 'L', 'M'),
(33, 0, 'L', 'O'),
(34, 0, 'M', 'J'),
(35, 0, 'M', 'L'),
(36, 0, 'N', 'I'),
(37, 0, 'N', 'O'),
(38, 0, 'O', 'N'),
(39, 0, 'O', 'L'),
(40, 0, 'O', 'P'),
(41, 0, 'P', 'O'),
(42, 0, 'P', 'K');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
