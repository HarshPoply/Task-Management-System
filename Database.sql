-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2018 at 08:42 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `taskmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `taskdetails`
--

CREATE TABLE `taskdetails` (
  `tid` varchar(20) NOT NULL,
  `uid` varchar(20) NOT NULL,
  `taskname` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `date` varchar(2) NOT NULL,
  `month` varchar(12) NOT NULL,
  `year` varchar(5) NOT NULL,
  `shour` varchar(5) NOT NULL,
  `sminute` varchar(5) NOT NULL,
  `sm` varchar(5) NOT NULL,
  `ehour` varchar(5) NOT NULL,
  `eminute` varchar(5) NOT NULL,
  `em` varchar(5) NOT NULL,
  `submit` int(11) NOT NULL,
  `active` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userpass`
--

CREATE TABLE `userpass` (
  `uid` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(70) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` varchar(7) NOT NULL,
  `date` varchar(10) NOT NULL,
  `month` varchar(12) NOT NULL,
  `year` varchar(10) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `address` varchar(150) NOT NULL,
  `city` varchar(50) NOT NULL,
  `contact` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `active` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userpass`
--

INSERT INTO `userpass` (`uid`, `username`, `password`, `name`, `gender`, `date`, `month`, `year`, `fname`, `address`, `city`, `contact`, `email`, `active`) VALUES
('UID100', 'admin', 'admin', 'A', 'M', '0', 'J', '9', 'D', 'N', 'L', '9', 'H', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `taskdetails`
--
ALTER TABLE `taskdetails`
  ADD PRIMARY KEY (`tid`),
  ADD KEY `uid` (`uid`);

--
-- Indexes for table `userpass`
--
ALTER TABLE `userpass`
  ADD PRIMARY KEY (`uid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
