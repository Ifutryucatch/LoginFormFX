DROP SCHEMA IF EXISTS user_sheme;
CREATE SCHEMA user_sheme DEFAULT CHARACTER SET utf8;
USE user_sheme;

CREATE TABLE user_info(
	user_id INT auto_increment unique,
    user_username varchar(500) unique not null,
    user_password varchar(400) not null,
    
    primary key(user_id)
);

insert into user_info(user_id, user_username, user_password) values(1, 'Ivkonymax', 'Pringles98');
insert into user_info(user_username, user_password) values('mamamia', '123456');