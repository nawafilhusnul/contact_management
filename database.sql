CREATE TABLE users(
    username VARCHAR(100) NOT NULL ,
    password TEXT NOT NULL ,
    name VARCHAR(100) NOT NULL ,
    token VARCHAR(255) ,
    token_expired_at BIGINT,
    PRIMARY KEY (username),
    UNIQUE (token)
) ENGINE=InnoDB;

DESC users;

CREATE TABLE contacts (
    id VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    phone VARCHAR(100),
    email VARCHAR(100),
    PRIMARY KEY (id),
    FOREIGN KEY fk_users_contacts (username) REFERENCES users (username) ON DELETE CASCADE
) ENGINE=InnoDB;

DESC contacts;

CREATE TABLE addresses(
    id VARCHAR(100) NOT NULL ,
    contact_id VARCHAR(100) NOT NULL ,
    street VARCHAR(100),
    city VARCHAR(100),
    province VARCHAR(100),
    country VARCHAR(100) NOT NULL ,
    zip_code VARCHAR(10),

    PRIMARY KEY (id),
    FOREIGN KEY fk_addresses (contact_id) REFERENCES contacts (id)
) ENGINE=InnoDB;

DESC addresses;
