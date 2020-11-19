DROP SCHEMA IF EXISTS ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NOT NULL,
    `surname` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `price` DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `customerID` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customerID`) REFERENCES customers(id)
);
CREATE TABLE IF NOT EXISTS `ims`.`order_items` (
    `itemID` INT NOT NULL,
    `orderID` INT NOT NULL,
    `quantity` INT(11) NOT NULL,
    FOREIGN KEY (`itemID`) REFERENCES items(id),
    FOREIGN KEY (`orderID`) REFERENCES orders(id)
);
