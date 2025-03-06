CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id VARCHAR(30) NOT NULL,
                        password VARCHAR(150) NOT NULL,
                        user_name VARCHAR(10) NOT NULL,
                        role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER'
);

ALTER TABLE member
    ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER';