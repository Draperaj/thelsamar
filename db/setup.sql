-- Customer Table
CREATE TABLE IF NOT EXISTS `customer` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Inventory Table
CREATE TABLE IF NOT EXISTS `inventory` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` varchar(255) not null,
    `price` decimal(15,2) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Address Table, used for storing customer's address data
CREATE TABLE IF NOT EXISTS `address` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    `street` varchar(255) not null,
    `city` varchar(255) not null,
    `state` varchar(255) not null,
    `zip` varchar(255) not null,
    `country` varchar(255) not null,
    PRIMARY KEY (`id`),
    CONSTRAINT FK_customer_id FOREIGN KEY (`customer_id`) REFERENCES customer(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- customer base and addresses

INSERT INTO customer
VALUES (NULL, "draperaj", "Alex", "Draper");
INSERT INTO address
VALUES (NULL, 1, "223 Queen St.", "Charlottetown", "PE", "C1A9B2", "CA");

INSERT INTO customer
VALUES (NULL, "bobmct", "Bob", "McTest");
INSERT INTO address
VALUES (NULL, 2, "845 Avison Way", "Vancouver", "BC", "V6G3E2", "CA");

INSERT INTO customer
VALUES (NULL, "sam", "Sam", "MacTest");
INSERT INTO address
VALUES (NULL, 3, "40 Bay St.", "Toronto", "ON", "M5J2X2", "CA");

INSERT INTO customer
VALUES (NULL, "martha", "Martha", "Supertester");
INSERT INTO address
VALUES (NULL, 4, "10220 104 Ave NW", "Edmonton", "AB", "T5J0H6", "CA");



-- used for the base inventory provided for the application

INSERT INTO inventory
VALUES (NULL, "Grapes", 10.00);

INSERT INTO inventory
VALUES (NULL, "Strawberries", 12.50);

INSERT INTO inventory
VALUES (NULL, "Orange", 20.40);

INSERT INTO inventory
VALUES (NULL, "Banana", 29.99);

INSERT INTO inventory
VALUES (NULL, "Mango", 54.99);

INSERT INTO inventory
VALUES (NULL, "Apple", 100.00);