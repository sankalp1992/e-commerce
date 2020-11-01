-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 01, 2020 at 11:53 PM
-- Server version: 8.0.21
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e-commerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `ec_cart`
--

CREATE TABLE `ec_cart` (
  `cart_id` int NOT NULL,
  `cart_user_id` int NOT NULL,
  `cart_total_price` float NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ec_cart_products`
--

CREATE TABLE `ec_cart_products` (
  `cart_products_id` int NOT NULL,
  `cart_products_cart_id` int NOT NULL,
  `cart_products_products_id` int NOT NULL,
  `cart_products_products_quantity` int NOT NULL DEFAULT '0',
  `status` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ec_order`
--

CREATE TABLE `ec_order` (
  `order_id` int NOT NULL,
  `order_cart_id` int NOT NULL,
  `order_delivery_address` varchar(100) NOT NULL,
  `order_date_time` datetime NOT NULL,
  `order_expected_date_time` datetime NOT NULL,
  `order_status` varchar(15) NOT NULL DEFAULT 'Received',
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ec_payments`
--

CREATE TABLE `ec_payments` (
  `payments_id` int NOT NULL,
  `payments_order_id` int NOT NULL,
  `payment_status` varchar(20) NOT NULL,
  `payment_option` varchar(20) NOT NULL,
  `payment_total` float NOT NULL,
  `payment_date_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ec_products`
--

CREATE TABLE `ec_products` (
  `product_id` int NOT NULL,
  `product_seller_id` int NOT NULL,
  `product_unique_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_price` float NOT NULL,
  `product_details` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_available_quantity` int NOT NULL DEFAULT '0',
  `product_rating` float NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ec_seller`
--

CREATE TABLE `ec_seller` (
  `seller_id` int NOT NULL,
  `seller_name` varchar(100) NOT NULL,
  `seller_access_token` varchar(100) NOT NULL,
  `seller_contact_email` varchar(50) NOT NULL,
  `seller_contact_number` varchar(15) DEFAULT NULL,
  `seller_password` varchar(100) NOT NULL,
  `seller_address` varchar(100) DEFAULT NULL,
  `seller_rating` float NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ec_seller`
--

INSERT INTO `ec_seller` (`seller_id`, `seller_name`, `seller_access_token`, `seller_contact_email`, `seller_contact_number`, `seller_password`, `seller_address`, `seller_rating`, `status`) VALUES
(1, 'Test Seller', 'abc', 'testseller@mailinator.com', '9999999999', 'P@ssw0rd', 'test seller address', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ec_user`
--

CREATE TABLE `ec_user` (
  `user_id` int NOT NULL,
  `user_first_name` varchar(20) NOT NULL,
  `user_last_name` varchar(20) NOT NULL,
  `user_contact_email` varchar(50) NOT NULL,
  `user_contact_number` varchar(15) DEFAULT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_address` varchar(100) DEFAULT NULL,
  `user_access_token` varchar(100) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ec_user`
--

INSERT INTO `ec_user` (`user_id`, `user_first_name`, `user_last_name`, `user_contact_email`, `user_contact_number`, `user_password`, `user_address`, `user_access_token`, `status`) VALUES
(1, 'Test', 'User', 'testuser@mailinator.com', '9999999999', 'P@ssw0rd', 'test address', 'acb', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ec_cart`
--
ALTER TABLE `ec_cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `cart_user_id` (`cart_user_id`);

--
-- Indexes for table `ec_cart_products`
--
ALTER TABLE `ec_cart_products`
  ADD PRIMARY KEY (`cart_products_id`),
  ADD KEY `cart_products_cart_id` (`cart_products_cart_id`),
  ADD KEY `cart_products_products_id` (`cart_products_products_id`);

--
-- Indexes for table `ec_order`
--
ALTER TABLE `ec_order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `order_cart_id` (`order_cart_id`);

--
-- Indexes for table `ec_payments`
--
ALTER TABLE `ec_payments`
  ADD PRIMARY KEY (`payments_id`),
  ADD KEY `payments_order_id` (`payments_order_id`);

--
-- Indexes for table `ec_products`
--
ALTER TABLE `ec_products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `product_seller_id` (`product_seller_id`);

--
-- Indexes for table `ec_seller`
--
ALTER TABLE `ec_seller`
  ADD PRIMARY KEY (`seller_id`);

--
-- Indexes for table `ec_user`
--
ALTER TABLE `ec_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ec_cart`
--
ALTER TABLE `ec_cart`
  MODIFY `cart_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ec_cart_products`
--
ALTER TABLE `ec_cart_products`
  MODIFY `cart_products_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `ec_order`
--
ALTER TABLE `ec_order`
  MODIFY `order_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ec_payments`
--
ALTER TABLE `ec_payments`
  MODIFY `payments_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ec_products`
--
ALTER TABLE `ec_products`
  MODIFY `product_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ec_seller`
--
ALTER TABLE `ec_seller`
  MODIFY `seller_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ec_user`
--
ALTER TABLE `ec_user`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ec_cart`
--
ALTER TABLE `ec_cart`
  ADD CONSTRAINT `ec_cart_ibfk_1` FOREIGN KEY (`cart_user_id`) REFERENCES `ec_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ec_cart_products`
--
ALTER TABLE `ec_cart_products`
  ADD CONSTRAINT `ec_cart_products_ibfk_1` FOREIGN KEY (`cart_products_cart_id`) REFERENCES `ec_cart` (`cart_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `ec_cart_products_ibfk_2` FOREIGN KEY (`cart_products_products_id`) REFERENCES `ec_products` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ec_order`
--
ALTER TABLE `ec_order`
  ADD CONSTRAINT `ec_order_ibfk_1` FOREIGN KEY (`order_cart_id`) REFERENCES `ec_cart` (`cart_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ec_payments`
--
ALTER TABLE `ec_payments`
  ADD CONSTRAINT `ec_payments_ibfk_1` FOREIGN KEY (`payments_order_id`) REFERENCES `ec_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ec_products`
--
ALTER TABLE `ec_products`
  ADD CONSTRAINT `ec_products_ibfk_1` FOREIGN KEY (`product_seller_id`) REFERENCES `ec_seller` (`seller_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
