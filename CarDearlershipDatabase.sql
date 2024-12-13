# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          CarDealership                                   #
# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS carDearlership;

CREATE DATABASE IF NOT EXISTS carDealership;

USE carDealership;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #
# ---------------------------------------------------------------------- #
# Add table "dealerships"                                               #
# ---------------------------------------------------------------------- #

CREATE TABLE `dealerships` (
    `dealershipID` INTEGER  NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(12) NOT NULL,
    CONSTRAINT `PK_dealerships` PRIMARY KEY (`dealershipID`)
    );

# Sample data for dealerships
TRUNCATE TABLE dealerships;
INSERT INTO `dealerships` (`name`, `address`, `phone`) VALUES
('Super Cars', '123 Main St, Tampa, FL', '555-1234'),
('Luxury Motors', '456 Elite Rd, Clearwater, FL', '555-5678'),
('City Motors', '789 Downtown Ave, Miami, FL', '555-6789'),
('Affordable Auto', '101 Park Blvd, Orlando, FL', '555-2345'),
('Prime Motors', '202 Prestige St, Fort Lauderdale, FL', '555-3456');


# ---------------------------------------------------------------------- #
# Add table "vehicles"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `vehicles` (
    `vin` INTEGER NOT NULL auto_increment,
    `year` INTEGER NOT NULL ,
    `make` VARCHAR(15),
    `model` VARCHAR(15),
    `type` VARCHAR(15),
    `color` VARCHAR(15),
    `odometer` INTEGER ,
    `price` DECIMAL(10,2) DEFAULT 0,
    `sold` BOOLEAN,
    CONSTRAINT `PK_vehicles` PRIMARY KEY (`vin`)
);

# Sample data for vehicles
TRUNCATE TABLE vehicles;
INSERT INTO `vehicles` (`vin`, `year`, `make`, `model`, `type`, `color`, `odometer`, `price`, `sold`) VALUES
('1', 2022, 'Honda', 'Civic', 'Sedan', 'Blue', 12000, 22000.00, TRUE),
('2', 2021, 'Ford', 'F-150', 'Truck', 'Red', 8000, 35000.00, TRUE),
('3', 2023, 'Chrysler', 'Pacifica', 'Van', 'White', 8000, 45000.00, TRUE),
('4', 2020, 'Subaru', 'Outback', 'SUV', 'Green', 20000, 27000.00, FALSE),
('5', 2021, 'Tesla', 'Model 3', 'Sedan', 'Black', 12000, 42000.00, TRUE),
('6', 2020, 'Jeep', 'Grand Cherokee', 'SUV', 'Silver', 18000, 35000.00, FALSE),
('7', 2019, 'Chevrolet', 'Camaro', 'Coupe', 'Yellow', 22000, 28000.00, TRUE),
('8', 2021, 'Lincoln', 'MKZ', 'Sedan', 'Blue', 15000, 38000.00, TRUE),
('9', 2022, 'Honda', 'Odyssey', 'Van', 'Red', 10000, 45000.00, TRUE),
('10', 2020, 'Ford', 'Mustang', 'Coupe', 'White', 3000, 37000.00, TRUE);

# ---------------------------------------------------------------------- #
# Add table "inventory"                                                  #
# ---------------------------------------------------------------------- #

CREATE TABLE `inventory` (
    `dealershipID` INTEGER NOT NULL  AUTO_INCREMENT,
    `vin` INTEGER NOT NULL,
    CONSTRAINT `PK_inventory` PRIMARY KEY (`dealershipID`, `vin`),
    CONSTRAINT `FK_inventory_dealership` FOREIGN KEY (`dealershipID`) REFERENCES `dealerships` (`dealershipID`),
    CONSTRAINT `FK_inventory_vehicle` FOREIGN KEY (`vin`) REFERENCES `vehicles` (`vin`)
);

# Sample data for inventory
INSERT INTO `inventory` (`dealershipID`, `vin`) VALUES
(1, '1'),
(2, '2'),
(3, '3'),
(4, '4'),
(5, '5'),
(1, '6'),
(2, '7'),
(3, '8'),
(4, '9'),
(5, '10');

# ---------------------------------------------------------------------- #
# Add table "contract"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `contract` (
	`contractID` INTEGER NOT NULL  AUTO_INCREMENT,
    `date` DATE NOT NULL,
    `name` VARCHAR(30),  
    `email` VARCHAR(30),  
    `vin` INTEGER NOT NULL, 
    CONSTRAINT `PK_contract` PRIMARY KEY (`contractID`),
    CONSTRAINT `FK_contract_vin` FOREIGN KEY (`vin`) REFERENCES `vehicles` (`vin`)  
);

# Sample data for contract
TRUNCATE TABLE contract;
INSERT INTO `contract` (`contractID`, `date`, `name`, `email`, `vin`) VALUES
(1, '2024-11-21', 'John Doe', 'johndoe@email.com', '1'),
(2, '2024-11-22', 'Jane Smith', 'janesmith@email.com', '2'),
(5, '2024-11-25', 'David Williams', 'davidw@email.com', '5'),
(7, '2024-11-27', 'James Wilson', 'jamesw@email.com', '7'),
(8, '2024-11-28', 'Olivia Moore', 'oliviam@email.com', '8'),
(9, '2024-11-29', 'Sophia Taylor', 'sophiat@email.com', '9'),
(10, '2024-11-30', 'Liam Anderson', 'liama@email.com', '10');

# ---------------------------------------------------------------------- #
# Add table "sales_contract"                                             #
# ---------------------------------------------------------------------- #

CREATE TABLE `sales_contracts` (
    `contractID` INTEGER NOT NULL ,                                     
    `sales_tax` DECIMAL(10, 2) NOT NULL ,                       
    `recording_fee` DECIMAL(10, 2),                                      
    `processing_fee` DECIMAL(10, 2),                                     
    `is_finance` BOOLEAN,
    CONSTRAINT `PK_sales_contract` PRIMARY KEY (`contractID`),         
    CONSTRAINT `FK_sales_contracts_contractID` FOREIGN KEY (`contractID`) REFERENCES `contract` (`contractID`)  
);

# Sample data for sales_contracts
TRUNCATE TABLE sales_contracts;
INSERT INTO `sales_contracts` (`contractID`, `sales_tax`, `recording_fee`, `processing_fee`, `is_finance`) VALUES
(1, 1500.00, 100.00, 50.00, TRUE),
(5, 2000.00, 130.00, 65.00, TRUE),
(8, 1900.00, 160.00, 90.00, FALSE),
(9, 2300.00, 180.00, 100.00, FALSE);

# ---------------------------------------------------------------------- #
# Add table "lease_contracts"                                            #
# ---------------------------------------------------------------------- #

CREATE TABLE `lease_contracts` (
    `contractID` INTEGER NOT NULL ,                                     
    `expected_ending_value_percentage` DECIMAL(10, 2) NOT NULL ,  
    `lease_fee` DECIMAL(10, 2),                                        
    CONSTRAINT `PK_lease_contract` PRIMARY KEY (`contractID`),         
    CONSTRAINT `FK_lease_contracts_contractID` FOREIGN KEY (`contractID`) REFERENCES `contract` (`contractID`)  
);

# Sample data for lease_contracts
TRUNCATE TABLE lease_contracts;
INSERT INTO `lease_contracts` (`contractID`, `expected_ending_value_percentage`, `lease_fee`) VALUES

(2, 10.00, 500.00),
(7, 22.00, 550.00),
(9, 13.00, 650.00);


# ---------------------------------------------------------------------- #
# Indexes for performance                                                #
# ---------------------------------------------------------------------- #

# Index on foreign key `vin` in `sales_contracts`, `lease_contracts`, and `contract`
CREATE INDEX idx_contract_vin ON `contract` (`vin`);

# Index on `customerEmail` in `contract` for faster lookups
CREATE INDEX idx_contract_email ON `contract` (`email`);

# Index on `customerName` in `contract` for faster lookups
CREATE INDEX idx_contract_name ON `contract` (`name`);

# Index on `year` in `vehicles` for faster lookups
CREATE INDEX idx_vehicles_year ON `vehicles` (`year`);
