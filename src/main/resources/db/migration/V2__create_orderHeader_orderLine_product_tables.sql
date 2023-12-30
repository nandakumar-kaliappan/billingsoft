CREATE TABLE IF NOT EXISTS  product
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    name               VARCHAR(50) NOT NULL,
    rate               DECIMAL(10, 2),
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT NOW()
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS order_header
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    count              INTEGER,
    amount             DECIMAL(10, 2),
    customer_id        BIGINT NOT NULL,
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT NOW(),
    CONSTRAINT `order_customer_fk` FOREIGN KEY (`customer_id`)
        REFERENCES `customer` (`id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS order_line
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity           INTEGER,
    order_header_id    BIGINT NOT NULL,
    product_id         BIGINT NOT NULL,
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT NOW(),
    CONSTRAINT `order_header_fk` FOREIGN KEY (`order_header_id`)
        REFERENCES `order_header` (`id`),
    CONSTRAINT `prodct_fk` FOREIGN KEY (`product_id`)
        REFERENCES `product` (`id`)
) ENGINE = InnoDB;