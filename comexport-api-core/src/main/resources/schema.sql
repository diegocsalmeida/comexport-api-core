DROP TABLE IF EXISTS PRODUCTS;
DROP TABLE IF EXISTS ORDERS;
  
CREATE TABLE PRODUCTS (
  ID BIGINT AUTO_INCREMENT  PRIMARY KEY,
  DESCRIPTION VARCHAR(250) NOT NULL,
  PRICE DECIMAL(19,2) NOT NULL,
  CREATED_AT TIMESTAMP,
  UPDATED_AT TIMESTAMP
);

CREATE TABLE ORDERS (
	ID BIGINT NOT NULL,
	CREATED_AT TIMESTAMP,
	ID_PRODUCT BIGINT NOT NULL,
	ID_USER BIGINT NOT NULL,
	PRICE DECIMAL(19,2) NOT NULL,
	SALES_CHANNEL VARCHAR(255) NOT NULL,
	STATUS VARCHAR(255),
	UPDATED_AT TIMESTAMP
);