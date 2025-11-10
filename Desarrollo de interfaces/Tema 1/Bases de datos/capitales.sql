-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-01-2025 a las 08:26:23
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `poblacion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `capitales`
--

CREATE TABLE `capitales` (
  `Provincia` varchar(30) NOT NULL,
  `Autonomía` varchar(30) NOT NULL,
  `Población` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `capitales`
--

INSERT INTO `capitales` (`Provincia`, `Autonomía`, `Población`) VALUES
('Albacete', 'Castilla la Mancha', 386000),
('Alicante', 'Comunidad valenciana', 1880000),
('Almería', 'Andalucía', 731000),
('Álava', 'País Vasco', 333000),
('Asturias', 'Asturias', 1011000),
('Ávila', 'Castilla y León', 158000),
('Badajoz', 'Extremadura', 669000),
('Islas Baleares', 'Islas Baleares', 1173000),
('Barcelona', 'Cataluña', 5714000),
('Vizcaya', 'País Vasco', 1154000),
('Burgos', 'Castilla y León', 356000),
('Cáceres', 'Extremadura', 389000),
('Cádiz', 'Andalucía', 1245000),
('Cantabria', 'Cantabria', 584000),
('Castellón', 'Comunidad Valenciana', 587000),
('Ciudad Real', 'Castilla La Mancha', 492000),
('Córdoba', 'Andalucía', 776000),
('A Coruña', 'Galicia', 1120000),
('Cuenca', 'Castilla La Mancha', 195000),
('Gipuzkoa', 'País Vasco', 726000),
('Girona', 'Cataluña', 786000),
('Granada', 'Andalucía', 921000),
('Guadalajara', 'Castilla La Mancha', 265000),
('Huelva', 'Andalucía', 525000),
('Huesca', 'Aragón', 224000),
('Jaén', 'Andalucía', 627000),
('León', 'Castilla y León', 451000),
('Lérida', 'Cataluña', 439000),
('Lugo', 'Galicia', 326000),
('Madrid', 'Madrid', 6751000),
('Málaga', 'Andalucía', 1695000),
('Murcia', 'Murcia', 1518000),
('Navarra', 'Navarra', 661000),
('Orense', 'Galicia', 305000),
('Palencia', 'Castilla y León', 159000),
('Las Palmas', 'Canarias', 1128000),
('Pontevedra', 'Galicia', 944000),
('La Rioja', 'La Rioja', 319000),
('Salamanca', 'Castilla y León', 327000),
('Santa Cruz', 'Canarias', 1044000),
('Segovia', 'Castilla y León', 153000),
('Sevilla', 'Andalucía', 1947000),
('Soria', 'Castilla y León', 88000),
('Tarragona', 'Cataluña', 822000),
('Teruel', 'Aragón', 134000),
('Toledo', 'Castilla La Mancha', 709000),
('Valencia', 'Comunidad Valenciana', 2589000),
('Valladolid', 'Castilla y León', 519000),
('Zamora', 'Castilla y León', 168000),
('Zaragoza', 'Aragón', 967000),
('Ceuta', 'Ceuta', 83000),
('Melilla', 'Melilla', 86000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
