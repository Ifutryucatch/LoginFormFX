DROP SCHEMA IF EXISTS user_sheme;
CREATE SCHEMA user_sheme DEFAULT CHARACTER SET utf8;
USE user_sheme;

CREATE TABLE user_info(
	user_id INT AUTO_INCREMENT UNIQUE,
    user_username VARCHAR(100) UNIQUE NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    user_role VARCHAR(100) NOT NULL,
    PRIMARY KEY(user_id)
);

INSERT INTO user_info(user_id, user_username, user_password, user_role) VALUES (1, 'Ivkonymax', 'Pringles98', 'admin');
INSERT INTO user_info(user_username, user_password, user_role) VALUES ('mamamia', '12345678', 'user');