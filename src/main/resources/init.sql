-- Create Database
CREATE DATABASE IF NOT EXISTS `your_db`;
USE `your_db`;

-- Table: product_type
CREATE TABLE `product_type` (
                                `id` INT AUTO_INCREMENT PRIMARY KEY,
                                `description` VARCHAR(255) NOT NULL
);

-- Table: product
CREATE TABLE `product` (
                           `id` INT AUTO_INCREMENT PRIMARY KEY,
                           `product_type_id` INT NOT NULL,
                           `description` VARCHAR(255) NOT NULL,
                           `value` DECIMAL(10, 2) NOT NULL,
                           FOREIGN KEY (`product_type_id`) REFERENCES `product_type`(`id`)
);

-- Table: sale
CREATE TABLE `sale` (
                        `id` INT AUTO_INCREMENT PRIMARY KEY,
                        `insert_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: sale_item
CREATE TABLE `sale_item` (
                             `id` INT AUTO_INCREMENT PRIMARY KEY,
                             `product_id` INT NOT NULL,
                             `quantity` INT NOT NULL,
                             `percentual_discount` DECIMAL(5, 2) NOT NULL,
                             `sale_id` INT NOT NULL,
                             FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
                             FOREIGN KEY (`sale_id`) REFERENCES `sale`(`id`)
);

-- Insert Sample Data

-- Inserting into product_type
INSERT INTO `product_type` (`description`) VALUES ('Electronics'), ('Furniture'), ('Clothing');

-- Inserting into product
INSERT INTO `product` (`product_type_id`, `description`, `value`)
VALUES
    (1, 'Laptop', 1200.00),
    (1, 'Smartphone', 800.00),
    (2, 'Sofa', 450.00),
    (3, 'Jeans', 40.00);

-- Inserting into sale
INSERT INTO `sale` (`insert_at`) VALUES (NOW()), (NOW());

-- Inserting into sale_item
INSERT INTO `sale_item` (`product_id`, `quantity`, `percentual_discount`, `sale_id`)
VALUES
    (1, 1, 10.00, 1),
    (2, 2, 5.00, 1),
    (3, 1, 0.00, 2),
    (4, 3, 20.00, 2);
