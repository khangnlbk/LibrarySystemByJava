-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 28, 2017 at 10:12 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `LibrarySystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `role` tinyint(4) NOT NULL,
  `user_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `borrowed_book_order`
--

CREATE TABLE `borrowed_book_order` (
  `order_id` int(11) NOT NULL,
  `card_id` int(11) NOT NULL,
  `copy_book_id` int(11) NOT NULL,
  `expired_date` date NOT NULL,
  `deposit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `HUST_student`
--

CREATE TABLE `HUST_student` (
  `user_id` int(11) NOT NULL,
  `student_id` mediumint(9) NOT NULL,
  `student_period_start` date NOT NULL,
  `student_period_end` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `librarian`
--

CREATE TABLE `librarian` (
  `librarian_id` int(11) NOT NULL,
  `full_name` varchar(30) NOT NULL,
  `contact` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  `full_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `is_HUST_student` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_card`
--

CREATE TABLE `user_card` (
  `card_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `issued_date` date NOT NULL,
  `expired_date` date NOT NULL,
  `actived_code` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `borrowed_book_order`
--
ALTER TABLE `borrowed_book_order`
  ADD PRIMARY KEY (`order_id`,`card_id`,`copy_book_id`),
  ADD KEY `borrowed_book_order_ibfk_1` (`card_id`);

--
-- Indexes for table `HUST_student`
--
ALTER TABLE `HUST_student`
  ADD PRIMARY KEY (`user_id`,`student_id`);

--
-- Indexes for table `librarian`
--
ALTER TABLE `librarian`
  ADD PRIMARY KEY (`librarian_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_card`
--
ALTER TABLE `user_card`
  ADD PRIMARY KEY (`card_id`,`user_id`),
  ADD KEY `user_card_ibfk_1` (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrowed_book_order`
--
ALTER TABLE `borrowed_book_order`
  ADD CONSTRAINT `borrowed_book_order_ibfk_1` FOREIGN KEY (`card_id`) REFERENCES `user_card` (`card_id`);

--
-- Constraints for table `HUST_student`
--
ALTER TABLE `HUST_student`
  ADD CONSTRAINT `HUST_student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `librarian`
--
ALTER TABLE `librarian`
  ADD CONSTRAINT `librarian_ibfk_1` FOREIGN KEY (`librarian_id`) REFERENCES `account` (`account_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `account` (`account_id`);

--
-- Constraints for table `user_card`
--
ALTER TABLE `user_card`
  ADD CONSTRAINT `user_card_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
