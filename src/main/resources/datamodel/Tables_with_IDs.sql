-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema basex_database
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `basex_database` ;

-- -----------------------------------------------------
-- Schema basex_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `basex_database` DEFAULT CHARACTER SET latin1 ;
USE `basex_database` ;

-- -----------------------------------------------------
-- Table `basex_database`.`actor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`actor` (
  `ACTOR_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `ACTOR_NAME` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL,
  `ACTOR_DESC` VARCHAR(500) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`ACTOR_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`strategy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`strategy` (
  `STRATEGY_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `STRATEGY_NAME` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `STRATEGY_DESC` VARCHAR(350) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `VIU_CUSTOMER` NVARCHAR(550) NULL,
  `VIU_EXPERIENCE` NVARCHAR(550) NULL,
  `VIU_INTERACTIONS` NVARCHAR(550) NULL,
  `SES_CORE_SERVICES` NVARCHAR(550) NULL,
  `SES_CORE_PARTNERS` NVARCHAR(550) NULL,
  `SES_FOCAL_ORGANIZATION` NVARCHAR(550) NULL,
  `SES_ENRICHING_SERVICES` NVARCHAR(550) NULL,
  `SES_ENRICHING_PARTNERS` NVARCHAR(550) NULL,
  `CM_CORE_RELATIONSHIP` NVARCHAR(550) NULL,
  `CM_ENRICHING_RELATIONSHIPS` NVARCHAR(550) NULL,
  PRIMARY KEY (`STRATEGY_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`Company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`Company` (
  `COMPANY_NAME` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`COMPANY_NAME`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `basex_database`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`user` (
  `USER_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `EMAIL` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
  `PASSWORD` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `LASTNAME` VARCHAR(150) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `FIRSTNAME` VARCHAR(150) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `COMPANY_NAME` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`USER_ID`, `COMPANY_NAME`),
  INDEX `fk_user_Company1_idx` (`COMPANY_NAME` ASC),
  CONSTRAINT `fk_user_Company1`
    FOREIGN KEY (`COMPANY_NAME`)
    REFERENCES `basex_database`.`Company` (`COMPANY_NAME`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`business_model`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`business_model` (
  `BM_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `VALUE_IN_USE` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL,
  `DESC` VARCHAR(5000) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `STRATEGY_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `VERSION` FLOAT(2,2) NOT NULL,
  `IMAGE` BLOB NULL DEFAULT NULL,
  `CREATED_DATE` DATETIME NOT NULL,
  `LAST_EDITED_TIME` DATETIME NOT NULL,
  `CREATED_BY` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `LAST_EDITED_BY` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`BM_ID`, `STRATEGY_ID`, `CREATED_BY`, `LAST_EDITED_BY`),
  INDEX `fk_business_model_project_idx` (`STRATEGY_ID` ASC),
  INDEX `fk_BUSINESS_MODEL_USER1_idx` (`CREATED_BY` ASC),
  INDEX `fk_BUSINESS_MODEL_USER2_idx` (`LAST_EDITED_BY` ASC),
  CONSTRAINT `fk_business_model_project`
    FOREIGN KEY (`STRATEGY_ID`)
    REFERENCES `basex_database`.`strategy` (`STRATEGY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BUSINESS_MODEL_USER1`
    FOREIGN KEY (`CREATED_BY`)
    REFERENCES `basex_database`.`user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BUSINESS_MODEL_USER2`
    FOREIGN KEY (`LAST_EDITED_BY`)
    REFERENCES `basex_database`.`user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`bm_actor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`bm_actor` (
  `BM_ACTOR_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `ACTOR_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `BM_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `IS_FOCAL` TINYINT(1) NOT NULL,
  `IS_CUSTOMER` TINYINT(1) NOT NULL,
  `VALUE_PROPOSITION` VARCHAR(150) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`BM_ACTOR_ID`, `ACTOR_ID`, `BM_ID`),
  INDEX `fk_BM_ACTOR_ACTOR1_idx` (`ACTOR_ID` ASC),
  INDEX `fk_BM_ACTOR_BUSINESS_MODEL1_idx` (`BM_ID` ASC),
  CONSTRAINT `fk_BM_ACTOR_ACTOR1`
    FOREIGN KEY (`ACTOR_ID`)
    REFERENCES `basex_database`.`actor` (`ACTOR_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BM_ACTOR_BUSINESS_MODEL1`
    FOREIGN KEY (`BM_ID`)
    REFERENCES `basex_database`.`business_model` (`BM_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`cost_benefit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`cost_benefit` (
  `CB_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `NAME` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`CB_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`cost_benefit_actor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`cost_benefit_actor` (
  `CB_ACTOR_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `BM_ACTOR_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `CB_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `COST_OR_BENEFIT` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`CB_ACTOR_ID`, `BM_ACTOR_ID`, `CB_ID`),
  INDEX `fk_COST_BENEFIT_ACTOR_COST_BENEFIT1_idx` (`CB_ID` ASC),
  INDEX `fk_cost_benefit_actor_bm_actor1_idx` (`BM_ACTOR_ID` ASC),
  CONSTRAINT `fk_COST_BENEFIT_ACTOR_COST_BENEFIT1`
    FOREIGN KEY (`CB_ID`)
    REFERENCES `basex_database`.`cost_benefit` (`CB_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cost_benefit_actor_bm_actor1`
    FOREIGN KEY (`BM_ACTOR_ID`)
    REFERENCES `basex_database`.`bm_actor` (`BM_ACTOR_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`cp_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`cp_activity` (
  `CP_ACTIVITY_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `NAME` VARCHAR(159) CHARACTER SET 'utf8' NOT NULL,
  `BM_ACTOR_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`CP_ACTIVITY_ID`, `BM_ACTOR_ID`),
  INDEX `fk_COPRODUCTION_BM_ACTOR1_idx` (`BM_ACTOR_ID` ASC),
  CONSTRAINT `fk_COPRODUCTION_BM_ACTOR1`
    FOREIGN KEY (`BM_ACTOR_ID`)
    REFERENCES `basex_database`.`bm_actor` (`BM_ACTOR_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`user_has_strategy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`user_has_strategy` (
  `USER_USER_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  `STRATEGY_STRATEGY_ID` VARCHAR(40) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`USER_USER_ID`, `STRATEGY_STRATEGY_ID`),
  INDEX `fk_USER_has_STRATEGY_STRATEGY1_idx` (`STRATEGY_STRATEGY_ID` ASC),
  INDEX `fk_USER_has_STRATEGY_USER1_idx` (`USER_USER_ID` ASC),
  CONSTRAINT `fk_USER_has_STRATEGY_USER1`
    FOREIGN KEY (`USER_USER_ID`)
    REFERENCES `basex_database`.`user` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_has_STRATEGY_STRATEGY1`
    FOREIGN KEY (`STRATEGY_STRATEGY_ID`)
    REFERENCES `basex_database`.`strategy` (`STRATEGY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `basex_database`.`location_gui`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basex_database`.`location_gui` (
  `COMPONENT_ID` VARCHAR(50) NOT NULL,
  `BM_ID` VARCHAR(40) NOT NULL,
  `BM_ACTOR_ID` VARCHAR(40) NOT NULL,
  `X` DOUBLE NOT NULL,
  `Y` DOUBLE NOT NULL,
  `Degree` DOUBLE NOT NULL,
  PRIMARY KEY (`COMPONENT_ID`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
