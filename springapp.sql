CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) 
ENGINE=InnoDB
AUTO_INCREMENT=3
DEFAULT CHARSET=utf8;

CREATE TABLE `permissions_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `permissions_map_role_id_x` (`role_id`),
  KEY `permissions_map_resource_id_x` (`resource_id`) USING BTREE
)
ENGINE = INNODB
AUTO_INCREMENT = 8
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE roles(
  id INT(11) NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(32) NOT NULL,
  role_disable TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE INDEX role_name (role_name)
)
ENGINE = INNODB
AUTO_INCREMENT = 8
AVG_ROW_LENGTH = 8192
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE users(
  id INT(11) NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(32) NOT NULL,
  email VARCHAR(128) NOT NULL,
  `password` VARCHAR(512) NOT NULL,
  full_name VARCHAR(128) NOT NULL DEFAULT '',
  enabled TINYINT(1) NOT NULL DEFAULT 1,
  non_locked TINYINT(1) NOT NULL DEFAULT 1,
  non_expired TINYINT(1) NOT NULL DEFAULT 1,
  credentials_non_expired TINYINT(1) NOT NULL DEFAULT 1,
  role_id INT(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX users_email_u_x (email),
  INDEX users_role_id_x (role_id),
  UNIQUE INDEX users_user_name_u_x (user_name)
)
ENGINE = INNODB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 16384
CHARACTER SET utf8
COLLATE utf8_general_ci;