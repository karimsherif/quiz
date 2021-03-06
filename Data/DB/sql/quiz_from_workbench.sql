-- MySQL Script generated by MySQL Workbench
-- 08/27/18 00:02:45
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema quiz
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quiz
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quiz` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `quiz` ;

-- -----------------------------------------------------
-- Table `quiz`.`owner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz`.`owner` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `full_name` VARCHAR(100) NULL COMMENT '',
  `user_name` VARCHAR(50) NULL COMMENT '',
  `password` VARCHAR(50) NULL COMMENT '',
  `email` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quiz`.`quiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz`.`quiz` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `description` VARCHAR(500) NULL COMMENT '',
  `img_name` VARCHAR(50) NULL COMMENT '',
  `image_display_name` VARCHAR(50) NULL COMMENT '',
  `creation_date` DATE NULL COMMENT '',
  `owner_id` INT NOT NULL COMMENT '',
  `is_public` TINYINT(1) NULL COMMENT '',
  `quiz_url` VARCHAR(2000) NULL COMMENT '',
  `code` VARCHAR(100) NULL COMMENT '',
  `external_link` VARCHAR(2000) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_quiz_owner_idx` (`owner_id` ASC)  COMMENT '',
  CONSTRAINT `fk_quiz_owner`
    FOREIGN KEY (`owner_id`)
    REFERENCES `quiz`.`owner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quiz`.`answer_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz`.`answer_type` (
  `id` INT NOT NULL COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');


-- -----------------------------------------------------
-- Table `quiz`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `question` MEDIUMTEXT NOT NULL COMMENT '',
  `notes` VARCHAR(45) NULL COMMENT '',
  `question_order` INT NULL COMMENT '',
  `quiz_id` INT NOT NULL COMMENT '',
  `answer_type_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_question_quiz1_idx` (`quiz_id` ASC)  COMMENT '',
  INDEX `fk_question_answer_type1_idx` (`answer_type_id` ASC)  COMMENT '',
  CONSTRAINT `fk_question_quiz1`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quiz`.`quiz` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_answer_type1`
    FOREIGN KEY (`answer_type_id`)
    REFERENCES `quiz`.`answer_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quiz`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `answer_txt` MEDIUMTEXT NULL COMMENT '',
  `answer_img_url` VARCHAR(45) NULL COMMENT '',
  `question_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_answer_question1_idx` (`question_id` ASC)  COMMENT '',
  CONSTRAINT `fk_answer_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `quiz`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quiz`.`question_answer_result`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz`.`question_answer_result` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `question_id` INT NOT NULL COMMENT '',
  `answer_id` INT NOT NULL COMMENT '',
  `is_right_answer` TINYINT(1) NULL COMMENT '',
  `right_answer_id` INT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_question_answer_result_question1_idx` (`question_id` ASC)  COMMENT '',
  INDEX `fk_question_answer_result_answer1_idx` (`answer_id` ASC)  COMMENT '',
  INDEX `fk_question_answer_result_answer2_idx` (`right_answer_id` ASC)  COMMENT '',
  CONSTRAINT `fk_question_answer_result_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `quiz`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_answer_result_answer1`
    FOREIGN KEY (`answer_id`)
    REFERENCES `quiz`.`answer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_answer_result_answer2`
    FOREIGN KEY (`right_answer_id`)
    REFERENCES `quiz`.`answer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quiz`.`visitor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz`.`visitor` (
  `id` INT NOT NULL COMMENT '',
  `user_name` VARCHAR(200) NULL COMMENT '',
  `password` VARCHAR(200) NULL COMMENT '',
  `email` VARCHAR(200) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quiz`.`quiz_visitor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz`.`quiz_visitor` (
  `id` INT NOT NULL COMMENT '',
  `quiz_id` INT NOT NULL COMMENT '',
  `visitor_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_quiz_visitor_quiz1_idx` (`quiz_id` ASC)  COMMENT '',
  INDEX `fk_quiz_visitor_visitor1_idx` (`visitor_id` ASC)  COMMENT '',
  CONSTRAINT `fk_quiz_visitor_quiz1`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `quiz`.`quiz` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_quiz_visitor_visitor1`
    FOREIGN KEY (`visitor_id`)
    REFERENCES `quiz`.`visitor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
