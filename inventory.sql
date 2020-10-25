-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 25 oct. 2020 à 14:44
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `inventory`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`id`, `type`, `description`) VALUES
(1, 'Phone', 'Cell Phone'),
(2, 'Laptop', 'Laptop'),
(3, 'Charger', 'Mobile , Laptop Charger'),
(4, 'Accessoirs', 'Mobile and Laptop Accessoirs');

-- --------------------------------------------------------

--
-- Structure de la table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `type` enum('admin','employee') NOT NULL DEFAULT 'employee'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `employees`
--

INSERT INTO `employees` (`id`, `firstname`, `lastname`, `username`, `password`, `phone`, `address`, `type`) VALUES
(1, 'souhail', 'x', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', '0099887766', 'Berlin, Germany', 'admin'),
(2, 'Martha', 'Jones', 'user', '12dea96fec20593566ab75692c9949596833adc9', '123456789', 'Seattle', 'employee'),
(3, 'yassir', 'dev', 'yassir', '45d9fd660d7e594069a2c433e0226729f4b8c7fc', '5846846', 'dchaira', 'employee');

-- --------------------------------------------------------

--
-- Structure de la table `invoices`
--

CREATE TABLE `invoices` (
  `id` varchar(13) NOT NULL,
  `employeeId` int(11) NOT NULL,
  `total` double NOT NULL,
  `vat` double NOT NULL,
  `discount` double NOT NULL,
  `payable` double NOT NULL,
  `paid` double NOT NULL,
  `returned` double NOT NULL,
  `datetime` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `invoices`
--

INSERT INTO `invoices` (`id`, `employeeId`, `total`, `vat`, `discount`, `payable`, `paid`, `returned`, `datetime`) VALUES
('1491729973342', 2, 760, 19, 5, 774, 800, 26, '2020-01-09 15:26:13'),
('1491730560516', 2, 370, 9.25, 5, 374.25, 375, 0.75, '2020-01-09 15:36:00'),
('1492165305284', 2, 270, 6.75, 5, 271.75, 280, 8.25, '2020-01-14 16:21:45'),
('1492189349464', 2, 490, 12.25, 5, 497.25, 500, 2.75, '2020-02-14 23:02:29'),
('1492189946488', 2, 190, 4.75, 5, 189.75, 200, 10.25, '2020-02-14 23:12:26'),
('1492190099626', 2, 120, 3, 5, 118, 120, 2, '2020-04-14 23:14:59'),
('1492190341116', 2, 65, 1.625, 5, 61.625, 62, 0.375, '2020-04-14 23:19:01'),
('1492191099328', 2, 190, 4.75, 5, 189.75, 190, 0.25, '2020-04-14 23:31:39'),
('1492192975710', 2, 770, 19.25, 5, 784.25, 1000, 215.75, '2020-04-15 00:02:55'),
('1492193361407', 2, 865, 21.625, 5, 881.625, 900, 18.375, '2020-03-15 00:09:21'),
('1492313070538', 2, 275, 6.875, 5, 276.875, 300, 23.125, '2020-03-16 09:24:30'),
('1493699328760', 2, 70, 1.75, 5, 66.75, 70, 3.25, '2020-05-02 10:28:48'),
('1493699482352', 2, 190, 4.75, 5, 189.75, 190, 0.25, '2020-05-02 10:31:22'),
('1600175598948', 2, 3300, 82.5, 5, 3377.5, 5000, 1622.5, '2020-09-15 14:13:18'),
('1600183804988', 2, 200, 5, 5, 200, 300, 100, '2020-09-15 16:30:05'),
('1600958997841', 2, 100, 2.5, 5, 97.5, 100, 2.5, '2020-09-24 15:49:57'),
('1600968861315', 2, 650, 16.25, 5, 661.25, 700, 38.75, '2020-09-24 18:34:21'),
('1601479911350', 2, 100, 2.5, 5, 97.5, 100, 2.5, '2020-09-30 16:31:51');

-- --------------------------------------------------------

--
-- Structure de la table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `priceBuy` double NOT NULL,
  `priceSell` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `place` varchar(100) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `products`
--

INSERT INTO `products` (`id`, `categoryId`, `supplierId`, `name`, `priceBuy`, `priceSell`, `quantity`, `place`, `description`) VALUES
(1, 4, 1, 'Fridge', 165, 180, 47, '', 'Auto Matic Fridge'),
(2, 4, 1, 'Washing Machine', 120, 150, 144, '', 'Auto-Matic Washing Machine'),
(3, 1, 2, 'Iphone X', 590, 710, 33, '', 'Iphone X 64Go '),
(4, 2, 2, 'Iphone 11 Pro Max', 1200, 1500, 38, '', 'Iphone 11 Pro Max 256 Go'),
(5, 3, 3, 'Power Light', 70, 100, 93, '', 'Freshness Cream'),
(6, 3, 3, 'Oil Clear', 160, 200, 294, '', 'Face Wash'),
(7, 3, 6, 'Brylcreem (Red)', 300, 380, 118, '', 'Light glossy hold'),
(8, 3, 1, 'Brylcreem (Green)', 105, 140, 400, '', 'Anti-dandruff'),
(9, 1, 7, 'husa', 20, 65, 140, '', 'best shampoo'),
(10, 4, 2, 'TV 55\"', 15, 20, 10, '', 'Apple TV 55\" '),
(11, 4, 6, 'Washing machine 8Kg', 1000, 2000, 50, '', 'Washing machine 8Kg'),
(12, 4, 1, 'LG 5KG', 120, 150, 15, 'A1', 'LG 5KG automatic ');

-- --------------------------------------------------------

--
-- Structure de la table `purchases`
--

CREATE TABLE `purchases` (
  `id` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL,
  `quantity` double NOT NULL,
  `price` double NOT NULL,
  `total` double NOT NULL,
  `datetime` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `purchases`
--

INSERT INTO `purchases` (`id`, `productId`, `supplierId`, `quantity`, `price`, `total`, `datetime`) VALUES
(1, 1, 1, 5, 165, 825, '2020-03-14 00:00:00'),
(2, 2, 2, 6, 120, 720, '2020-03-09 00:00:00'),
(3, 1, 1, 1, 24, 24, '2020-05-02 10:02:47'),
(4, 1, 1, 2, 20, 40, '2017-05-02 10:10:37'),
(5, 9, 1, 10, 20, 200, '2020-09-15 14:11:28'),
(6, 9, 4, 15, 20, 300, '2020-09-15 16:46:32');

-- --------------------------------------------------------

--
-- Structure de la table `sales`
--

CREATE TABLE `sales` (
  `id` int(11) NOT NULL,
  `invoiceId` varchar(13) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` double NOT NULL,
  `price` int(11) NOT NULL,
  `total` double NOT NULL,
  `datetime` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `sales`
--

INSERT INTO `sales` (`id`, `invoiceId`, `productId`, `quantity`, `price`, `total`, `datetime`) VALUES
(1, '1491729973342', 7, 2, 300, 600, '2017-04-09 15:26:13'),
(2, '1491729973342', 6, 1, 160, 160, '2017-04-09 15:26:13'),
(3, '1491730560516', 2, 2, 120, 240, '2017-04-09 15:36:00'),
(4, '1491730560516', 4, 2, 65, 130, '2017-04-09 15:36:00'),
(5, '1492165305284', 5, 2, 70, 140, '2017-04-14 16:21:45'),
(6, '1492165305284', 4, 2, 65, 130, '2017-04-14 16:21:45'),
(7, '1492189349464', 1, 2, 165, 330, '2017-01-14 23:02:29'),
(8, '1492189349464', 6, 1, 160, 160, '2017-04-14 23:02:29'),
(9, '1492189946488', 3, 1, 190, 190, '2017-04-14 23:12:26'),
(10, '1492190099626', 2, 1, 120, 120, '2017-04-14 23:14:59'),
(11, '1492190341116', 4, 1, 65, 65, '2017-04-14 23:19:01'),
(12, '1492191099328', 3, 1, 190, 190, '2017-04-14 23:31:39'),
(13, '1492192975710', 6, 2, 160, 320, '2017-04-15 00:02:55'),
(14, '1492192975710', 2, 1, 120, 120, '2017-04-15 00:02:55'),
(15, '1492192975710', 1, 2, 165, 330, '2017-02-15 00:02:55'),
(16, '1492193361407', 3, 2, 190, 380, '2017-04-15 00:09:21'),
(17, '1492193361407', 1, 1, 165, 165, '2017-03-15 00:09:21'),
(18, '1492193361407', 6, 2, 160, 320, '2017-04-15 00:09:21'),
(19, '1492313070538', 5, 3, 70, 210, '2017-04-16 09:24:30'),
(20, '1492313070538', 4, 1, 65, 65, '2017-04-16 09:24:30'),
(21, '1493699482352', 3, 1, 190, 190, '2017-05-02 10:31:22'),
(22, '1600175598948', 9, 15, 20, 300, '2020-09-15 14:13:18'),
(23, '1600175598948', 7, 10, 300, 3000, '2020-09-15 14:13:19'),
(24, '1600183804988', 9, 10, 20, 200, '2020-09-15 16:30:05'),
(25, '1600958997841', 10, 5, 20, 100, '2020-09-24 15:49:57'),
(26, '1600968861315', 9, 10, 65, 650, '2020-09-24 18:34:21'),
(27, '1601479911350', 11, 50, 2, 100, '2020-09-30 16:31:51');

-- --------------------------------------------------------

--
-- Structure de la table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `suppliers`
--

INSERT INTO `suppliers` (`id`, `name`, `phone`, `address`) VALUES
(1, 'LG', '00000000', 'USA'),
(2, 'Apple', '1111111', 'France'),
(3, 'Samsung', '22222222', 'France'),
(4, 'Huawei', '444444', 'India'),
(5, 'Ugreen', '555555', 'China'),
(6, 'Mcdodo', '777777', 'UK'),
(7, 'Sony', '8888888', 'Canada');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoryId` (`categoryId`),
  ADD KEY `supplierId` (`supplierId`);

--
-- Index pour la table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`id`),
  ADD KEY `productId` (`productId`),
  ADD KEY `supplierId` (`supplierId`);

--
-- Index pour la table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `productId` (`productId`),
  ADD KEY `invoiceId` (`invoiceId`);

--
-- Index pour la table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`supplierId`) REFERENCES `suppliers` (`id`);

--
-- Contraintes pour la table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `purchases_ibfk_2` FOREIGN KEY (`supplierId`) REFERENCES `suppliers` (`id`);

--
-- Contraintes pour la table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`invoiceId`) REFERENCES `invoices` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
