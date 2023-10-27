SET MODE PostgresSQL;

CREATE TABLE IF NOT EXISTS inventory(
    itemId int PRIMARY KEY auto_increment,
    itemName VARCHAR,
    itemSerial VARCHAR,
    itemManufacturer VARCHAR,
    partnerId INT,
);

CREATE TABLE IF NOT EXISTS partnerisps (
   partnerId int PRIMARY KEY auto_increment,
   partnerName VARCHAR,
   partnerEmail VARCHAR,
   description VARCHAR
);