CREATE TABLE customer(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    phone VARCHAR(20) UNIQUE,
    address VARCHAR(200),
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT NOW()
) ENGINE = InnoDB;