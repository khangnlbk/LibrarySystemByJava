-- phpMyAdmin SQL Dump
-- version 4.8.0-dev
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 192.168.30.23
-- Thời gian đã tạo: Th10 29, 2017 lúc 04:39 AM
-- Phiên bản máy phục vụ: 8.0.2-dmr
-- Phiên bản PHP: 7.0.19-1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `library-2`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `role` tinyint(4) NOT NULL,
  `user_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `book_number` varchar(10) NOT NULL,
  `title` text NOT NULL,
  `author` text,
  `publisher` text,
  `isbn` text,
  `copy_number` text NOT NULL,
  `catalog_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowed_book_order`
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
-- Cấu trúc bảng cho bảng `catalog`
--

CREATE TABLE `catalog` (
  `catalog_id` int(11) NOT NULL,
  `name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `copy`
--

CREATE TABLE `copy` (
  `copy_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `copy_number` varchar(10) NOT NULL,
  `type` text NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `HUST_student`
--

CREATE TABLE `HUST_student` (
  `user_id` int(11) NOT NULL,
  `student_id` mediumint(9) NOT NULL,
  `student_period_start` date NOT NULL,
  `student_period_end` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `librarian`
--

CREATE TABLE `librarian` (
  `librarian_id` int(11) NOT NULL,
  `full_name` varchar(30) NOT NULL,
  `contact` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
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
-- Cấu trúc bảng cho bảng `user_card`
--

CREATE TABLE `user_card` (
  `card_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `issued_date` date NOT NULL,
  `expired_date` date NOT NULL,
  `actived_code` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`);

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `book number` (`book_number`),
  ADD KEY `catalog_id` (`catalog_id`);

--
-- Chỉ mục cho bảng `borrowed_book_order`
--
ALTER TABLE `borrowed_book_order`
  ADD PRIMARY KEY (`order_id`,`card_id`,`copy_book_id`),
  ADD KEY `borrowed_book_order_ibfk_1` (`card_id`);

--
-- Chỉ mục cho bảng `catalog`
--
ALTER TABLE `catalog`
  ADD PRIMARY KEY (`catalog_id`);

--
-- Chỉ mục cho bảng `copy`
--
ALTER TABLE `copy`
  ADD PRIMARY KEY (`copy_id`),
  ADD UNIQUE KEY `copy number` (`copy_number`);

--
-- Chỉ mục cho bảng `HUST_student`
--
ALTER TABLE `HUST_student`
  ADD PRIMARY KEY (`user_id`,`student_id`);

--
-- Chỉ mục cho bảng `librarian`
--
ALTER TABLE `librarian`
  ADD PRIMARY KEY (`librarian_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Chỉ mục cho bảng `user_card`
--
ALTER TABLE `user_card`
  ADD PRIMARY KEY (`card_id`,`user_id`),
  ADD KEY `user_card_ibfk_1` (`user_id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`catalog_id`);

--
-- Các ràng buộc cho bảng `borrowed_book_order`
--
ALTER TABLE `borrowed_book_order`
  ADD CONSTRAINT `borrowed_book_order_ibfk_1` FOREIGN KEY (`card_id`) REFERENCES `user_card` (`card_id`);

--
-- Các ràng buộc cho bảng `HUST_student`
--
ALTER TABLE `HUST_student`
  ADD CONSTRAINT `HUST_student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `librarian`
--
ALTER TABLE `librarian`
  ADD CONSTRAINT `librarian_ibfk_1` FOREIGN KEY (`librarian_id`) REFERENCES `account` (`account_id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `account` (`account_id`);

--
-- Các ràng buộc cho bảng `user_card`
--
ALTER TABLE `user_card`
  ADD CONSTRAINT `user_card_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
