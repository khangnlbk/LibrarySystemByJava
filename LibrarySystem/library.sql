-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 26, 2017 at 03:27 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library.kien`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `book_number` varchar(6) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `discription` varchar(500) NOT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`book_id`, `book_number`, `title`, `author`, `publisher`, `isbn`, `discription`, `price`) VALUES
(2, 'dr0002', 'hannibal', 'thomas harris', 'Nha Nam', '123', 'a man and his obssesion with an other man', 10000),
(3, 'fi0001', 'Metro 2035', 'Dmitry Glukhovsky', 'Gollancz', '1234', 'b', 10000),
(5, 'fa0001', 'Dark Tower: The Ginslinger', 'Stephen King', 'Grant', '978-0-937986-50-9', 'c', 10000),
(8, 'ho0002', 'It', 'Stephen King', 'Viking', '0-670-81302-8', 'd', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `borrowcard`
--

CREATE TABLE `borrowcard` (
  `card_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `expired_date` date DEFAULT NULL,
  `deposit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `borrowcard`
--

INSERT INTO `borrowcard` (`card_id`, `user_id`, `expired_date`, `deposit`) VALUES
(1, 3, '2016-12-31', 100000);

-- --------------------------------------------------------

--
-- Table structure for table `borrowingregistrationitem`
--

CREATE TABLE `borrowingregistrationitem` (
  `item_id` int(11) NOT NULL,
  `copy_id` int(11) NOT NULL,
  `card_id` int(11) NOT NULL,
  `registration_id` int(11) NOT NULL,
  `borrow_date` date DEFAULT NULL,
  `lent_date` date DEFAULT NULL,
  `expected_return_date` date DEFAULT NULL,
  `returned` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `borrowingregistrationitem`
--

INSERT INTO `borrowingregistrationitem` (`item_id`, `copy_id`, `card_id`, `registration_id`, `borrow_date`, `lent_date`, `expected_return_date`, `returned`) VALUES
(1, 1, 1, 4, '2016-10-27', '2016-10-27', '2016-11-11', 0),
(2, 5, 1, 5, '2016-10-28', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `borrowregistration`
--

CREATE TABLE `borrowregistration` (
  `registration_id` int(11) NOT NULL,
  `card_id` int(11) NOT NULL,
  `status` smallint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `borrowregistration`
--

INSERT INTO `borrowregistration` (`registration_id`, `card_id`, `status`) VALUES
(4, 1, 1),
(5, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `encode` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `name`, `encode`) VALUES
(1, 'science', 'sc'),
(2, 'fiction', 'fi'),
(3, 'drama', 'dr'),
(4, 'action', 'ac'),
(5, 'romance', 'rm'),
(6, 'adventure', 'ad'),
(7, 'mistery', 'mi'),
(8, 'horror', 'ho'),
(9, 'self-help', 'sh'),
(10, 'travel', 'tr'),
(11, 'religion', 're'),
(12, 'history', 'hi'),
(13, 'education', 'ed'),
(14, 'comic', 'co'),
(15, 'fantasy', 'fa');

-- --------------------------------------------------------

--
-- Table structure for table `categorybook`
--

CREATE TABLE `categorybook` (
  `book_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categorybook`
--

INSERT INTO `categorybook` (`book_id`, `category_id`) VALUES
(2, 3),
(3, 2),
(3, 4),
(3, 6),
(5, 4),
(5, 6),
(5, 15),
(8, 8);

-- --------------------------------------------------------

--
-- Table structure for table `copy`
--

CREATE TABLE `copy` (
  `copy_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `copy_number` varchar(10) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `copy_condition` varchar(100) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `copy_type` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `copy`
--

INSERT INTO `copy` (`copy_id`, `book_id`, `copy_number`, `status`, `price`, `copy_condition`, `copy_type`) VALUES
(1, 2, 'dr0002-1', 'borrowed', 30000, 'good', 'borrowable'),
(2, 3, 'fi0001-1', 'available', 40000, 'good', 'reference'),
(3, 8, 'ho0001-2', 'available', 40000, 'good', 'borrowable'),
(5, 5, 'fa0001-1', 'borrowed', 45000, 'great, almost 99% new', 'borrowable'),
(6, 5, 'fa0001-2', 'available', 45000, 'great, almost 99% new', 'borrowable');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `role` int(2) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `full_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `deposit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `role`, `username`, `password`, `full_name`, `email`, `age`, `address`, `gender`, `student_id`, `deposit`) VALUES
(1, 0, 'admin', '1', 'adminisme', 'admin@gmail.com', 21, 'earth', 1, 123, 50000),
(2, 1, 'anhdep', '1', 'anhtrai', 'anhdeptrai1995@gmail.com', 21, 'hanoi', 1, 20132134, 60000),
(3, 2, 'anh', '1', 'anhdeptrai', 'example@gmail.com', 21, 'hanoi', 2, 20130000, 60000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `borrowcard`
--
ALTER TABLE `borrowcard`
  ADD PRIMARY KEY (`card_id`,`user_id`),
  ADD KEY `fk_BorrowCard_user_idx` (`user_id`);

--
-- Indexes for table `borrowingregistrationitem`
--
ALTER TABLE `borrowingregistrationitem`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `fk_BorrowingRegistrationItem_BorrowRegistration1_idx` (`registration_id`),
  ADD KEY `fk_item_card_id` (`card_id`);

--
-- Indexes for table `borrowregistration`
--
ALTER TABLE `borrowregistration`
  ADD PRIMARY KEY (`registration_id`,`card_id`),
  ADD KEY `fk_BorrowRegistration_BorrowCard1_idx` (`card_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `categorybook`
--
ALTER TABLE `categorybook`
  ADD PRIMARY KEY (`book_id`,`category_id`),
  ADD KEY `fk_CategoryBook_Category1_idx` (`category_id`);

--
-- Indexes for table `copy`
--
ALTER TABLE `copy`
  ADD PRIMARY KEY (`copy_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `borrowcard`
--
ALTER TABLE `borrowcard`
  MODIFY `card_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `borrowingregistrationitem`
--
ALTER TABLE `borrowingregistrationitem`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `borrowregistration`
--
ALTER TABLE `borrowregistration`
  MODIFY `registration_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `copy`
--
ALTER TABLE `copy`
  MODIFY `copy_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrowcard`
--
ALTER TABLE `borrowcard`
  ADD CONSTRAINT `fk_BorrowCard_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `borrowingregistrationitem`
--
ALTER TABLE `borrowingregistrationitem`
  ADD CONSTRAINT `fk_item_card_id` FOREIGN KEY (`card_id`) REFERENCES `borrowcard` (`card_id`),
  ADD CONSTRAINT `fk_registration_id` FOREIGN KEY (`registration_id`) REFERENCES `borrowregistration` (`registration_id`);

--
-- Constraints for table `borrowregistration`
--
ALTER TABLE `borrowregistration`
  ADD CONSTRAINT `fk_card_id` FOREIGN KEY (`card_id`) REFERENCES `borrowcard` (`card_id`);

--
-- Constraints for table `categorybook`
--
ALTER TABLE `categorybook`
  ADD CONSTRAINT `fk_CategoryBook_Book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_CategoryBook_Category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
