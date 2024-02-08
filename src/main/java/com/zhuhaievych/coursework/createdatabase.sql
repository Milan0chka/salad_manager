CREATE SCHEMA chefsassistant ;

CREATE TABLE chefsassistant.ingredients (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `weight` DOUBLE NOT NULL,
    `calority` DOUBLE NOT NULL,
    `price` DOUBLE NOT NULL,
    `expTerm` DOUBLE NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

CREATE TABLE `chefsassistant`.`recipes` (
    `idrecipes` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `recipe` NVARCHAR(9000) NOT NULL,
    PRIMARY KEY (`idrecipes`),
    UNIQUE INDEX `idrecipes_UNIQUE` (`idrecipes` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);
