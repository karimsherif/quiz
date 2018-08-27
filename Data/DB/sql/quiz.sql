-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 26, 2018 at 10:01 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

--
-- Database: `quiz`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
CREATE TABLE IF NOT EXISTS `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_txt` mediumtext COLLATE utf8_unicode_ci,
  `answer_img_url` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_answer_question1_idx` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `answer_txt`, `answer_img_url`, `question_id`) VALUES
(1, '10-20', NULL, 4),
(2, '21-25', NULL, 4),
(3, '26-30', NULL, 4),
(4, 'Football', NULL, 5),
(5, 'Bing Bong', NULL, 5),
(9, 'V.Good', NULL, 7),
(10, 'Good', NULL, 7),
(11, 'Bad', NULL, 7),
(12, 'a1', NULL, 8),
(13, 'a2', NULL, 8),
(14, 'a1', NULL, 9),
(15, 'a2', NULL, 9),
(16, 'a1', NULL, 10),
(17, 'a2', NULL, 10);

-- --------------------------------------------------------

--
-- Table structure for table `answer_type`
--

DROP TABLE IF EXISTS `answer_type`;
CREATE TABLE IF NOT EXISTS `answer_type` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `answer_type`
--

INSERT INTO `answer_type` (`id`, `name`) VALUES
(1, 'Image & Text Answers'),
(2, 'Text Answers');

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
CREATE TABLE IF NOT EXISTS `owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`id`, `full_name`, `user_name`, `password`, `email`) VALUES
(1, 'Karim Sherif Mohamed', 'karim@Sherif.com', '1234', 'karim@Sherif.com');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `notes` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quiz_id` int(11) DEFAULT NULL,
  `question_order` int(3) DEFAULT NULL,
  `answer_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_quiz1_idx` (`quiz_id`),
  KEY `question_answer_type_id_index` (`answer_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `question`, `notes`, `quiz_id`, `question_order`, `answer_type_id`) VALUES
(4, 'What is your Age?', '', 13, 1, 2),
(5, 'What is your favorit sport??', '', 13, 2, 2),
(7, 'What is your opinion?', '', 15, 1, 2),
(8, 'q1', '', 16, 1, 2),
(9, 'q1', '', 17, 1, 2),
(10, 'q2', '', 17, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `question_answer_result`
--

DROP TABLE IF EXISTS `question_answer_result`;
CREATE TABLE IF NOT EXISTS `question_answer_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  `is_right_answer` tinyint(1) DEFAULT NULL,
  `right_answer_id` int(11) DEFAULT NULL,
  `quiz_visitor_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_answer_result_question1_idx` (`question_id`),
  KEY `fk_question_answer_result_answer1_idx` (`answer_id`),
  KEY `fk_question_answer_result_answer2_idx` (`right_answer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `question_answer_result`
--

INSERT INTO `question_answer_result` (`id`, `question_id`, `answer_id`, `is_right_answer`, `right_answer_id`, `quiz_visitor_id`) VALUES
(2, 4, 1, NULL, NULL, 1),
(3, 5, 5, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE IF NOT EXISTS `quiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `img_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image_display_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  `is_public` tinyint(1) DEFAULT NULL,
  `quiz_url` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `external_link` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_quiz_owner_idx` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`id`, `description`, `img_name`, `image_display_name`, `creation_date`, `owner_id`, `is_public`, `quiz_url`, `code`, `external_link`) VALUES
(13, 'Test Quiz 1', 'Ulraq2fCgo3DX3Ft56kQ.jpg', 'index.jpg', '2018-08-25', 1, 0, 'http://62.114.122.80:8080/Quiz/quiz/doQuiz.xhtml?q=5eKrg6y5G8FvHJqaTkBccmcgPTKxe913tk4ZZcaPFm12b3Fk3T', '5eKrg6y5G8FvHJqaTkBccmcgPTKxe913tk4ZZcaPFm12b3Fk3T', NULL),
(15, 'Test Quiz 2', '56R6Z2Ld0fn9id1c9519.png', 'index.png', '2018-08-25', 1, 0, 'http://62.114.122.80:8080/Quiz/quiz/doQuiz.xhtml?q=GquNvH5BZ8L20b10cyfRx0pRk0N0Z667kG5W7KdSF3fJJs8j9C', 'GquNvH5BZ8L20b10cyfRx0pRk0N0Z667kG5W7KdSF3fJJs8j9C', NULL),
(16, 'Test Quiz 3', 'JfDRItmRXl4eG1J00PL0.jpg', 'indexehg.jpg', '2018-08-25', 1, 0, 'http://62.114.122.80:8080/Quiz/quiz/doQuiz.xhtml?q=5DEG22Yt242MUPL9USP61h4oJC2ubz70KNb4LNgZTMFDQfA7yN', '5DEG22Yt242MUPL9USP61h4oJC2ubz70KNb4LNgZTMFDQfA7yN', 'www.google.com'),
(17, 'Test Quiz 3', 'qDQq297BTWVkweY3TKCY.jpg', 'indexehg.jpg', '2018-08-25', 1, 0, 'http://62.114.122.80:8080/Quiz/quiz/doQuiz.xhtml?q=8y2NE0R46qd62cv4yi7w5q0y9B7gQtj9yqe8plgEo78O0m2ASr', '8y2NE0R46qd62cv4yi7w5q0y9B7gQtj9yqe8plgEo78O0m2ASr', 'www.google.com');

-- --------------------------------------------------------

--
-- Table structure for table `quiz_visitor`
--

DROP TABLE IF EXISTS `quiz_visitor`;
CREATE TABLE IF NOT EXISTS `quiz_visitor` (
  `id` int(11) NOT NULL,
  `quiz_id` int(11) NOT NULL,
  `user_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_quiz_visitor_quiz1_idx` (`quiz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `visitor`
--

DROP TABLE IF EXISTS `visitor`;
CREATE TABLE IF NOT EXISTS `visitor` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `fk_question_quiz1` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `question_answer_result`
--
ALTER TABLE `question_answer_result`
  ADD CONSTRAINT `fk_question_answer_result_answer1` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_question_answer_result_answer2` FOREIGN KEY (`right_answer_id`) REFERENCES `answer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_question_answer_result_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `fk_quiz_owner` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `quiz_visitor`
--
ALTER TABLE `quiz_visitor`
  ADD CONSTRAINT `fk_quiz_visitor_quiz1` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;
