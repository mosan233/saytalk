CREATE DATABASE skytalk;
USE skytalk;

-- 创建用户表

CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `account_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `token` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `gmt_create` bigint DEFAULT NULL,
    `gmt_modified` bigint DEFAULT NULL,
    `bio` varchar(255) DEFAULT NULL,
    `avatar_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- 创建问题表
CREATE TABLE `question` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `gmt_create` bigint NOT NULL,
    `gmt_modified` bigint DEFAULT NULL,
    `creator` int NOT NULL,
    `comment_count` int DEFAULT '0',
    `view_count` int DEFAULT '0',
    `like_count` int DEFAULT '0',
    `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
