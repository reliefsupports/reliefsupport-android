-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2017 at 05:04 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flood`
--

-- --------------------------------------------------------

--
-- Table structure for table `device_token_details`
--

CREATE TABLE `device_token_details` (
  `device_token_id` int(11) NOT NULL,
  `device_token_text` longtext,
  `device_token_user_id` int(11) DEFAULT NULL,
  `device_token_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = active, 0 = inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `disaster_area_details`
--

CREATE TABLE `disaster_area_details` (
  `disaster_area_id` int(11) NOT NULL,
  `disaster_area_name` varchar(255) DEFAULT NULL,
  `disaster_area_lat` varchar(255) NOT NULL,
  `disaster_area_lng` varchar(255) NOT NULL,
  `disaster_area_reason` longtext,
  `disaster_area_special_note` longtext,
  `disaster_area_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = active, 0 = inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `donations`
--

CREATE TABLE `donations` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `telephone` varchar(255) NOT NULL DEFAULT '',
  `address` text NOT NULL,
  `city` varchar(255) NOT NULL,
  `donation` text NOT NULL,
  `information` text,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `identifier` varchar(2) DEFAULT 'W' COMMENT 'a- android,i - IOS, b- BOT, W - Web'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `needs`
--

CREATE TABLE `needs` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `telephone` varchar(255) NOT NULL DEFAULT '',
  `address` text NOT NULL,
  `city` varchar(255) NOT NULL,
  `needs` text NOT NULL,
  `heads` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `identifier` varchar(1) DEFAULT 'W' COMMENT 'a- android,i - IOS, b- BOT, W - Web'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `notification_details`
--

CREATE TABLE `notification_details` (
  `notification_id` int(11) NOT NULL,
  `notification_main_value` varchar(255) DEFAULT NULL,
  `notification_sub_value_1` varchar(255) DEFAULT NULL,
  `notification_sub_value_2` varchar(255) DEFAULT NULL,
  `notification_sub_value_3` varchar(255) DEFAULT NULL,
  `notification_sub_value_4` varchar(255) DEFAULT NULL,
  `notification_date_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `notification_active` int(1) NOT NULL DEFAULT '1' COMMENT '1 = active,  0 = inactive'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `device_token_details`
--
ALTER TABLE `device_token_details`
  ADD PRIMARY KEY (`device_token_id`);

--
-- Indexes for table `disaster_area_details`
--
ALTER TABLE `disaster_area_details`
  ADD PRIMARY KEY (`disaster_area_id`);

--
-- Indexes for table `donations`
--
ALTER TABLE `donations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `needs`
--
ALTER TABLE `needs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification_details`
--
ALTER TABLE `notification_details`
  ADD PRIMARY KEY (`notification_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `device_token_details`
--
ALTER TABLE `device_token_details`
  MODIFY `device_token_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `disaster_area_details`
--
ALTER TABLE `disaster_area_details`
  MODIFY `disaster_area_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `donations`
--
ALTER TABLE `donations`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `needs`
--
ALTER TABLE `needs`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `notification_details`
--
ALTER TABLE `notification_details`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
