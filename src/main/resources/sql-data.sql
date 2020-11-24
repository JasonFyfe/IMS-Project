INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jason', 'fyfe');
INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('steven', 'smith');
INSERT INTO `ims`.`items` (`name`, `price`) VALUES ('xbox', '299.99');
INSERT INTO `ims`.`items` (`name`, `price`) VALUES ('ps5', '499.99');
INSERT INTO `ims`.`orders` (`customerID`) VALUE (1);
INSERT INTO `ims`.`order_items` (`itemID`, `orderID`, `quantity`) VALUES (1, 1, 5);
INSERT INTO `ims`.`order_items` (`itemID`, `orderID`, `quantity`) VALUES (2, 1, 3);