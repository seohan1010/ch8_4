CREATE TABLE `board` (
                         `bno` int NOT NULL AUTO_INCREMENT,
                         `title` varchar(100) COLLATE utf8mb3_bin NOT NULL,
                         `writer` varchar(45) COLLATE utf8mb3_bin NOT NULL,
                         `content` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
                         `write_date` date DEFAULT NULL,
                         `update_date` date DEFAULT NULL,
                         `board_like` int DEFAULT NULL,
                         `deleted_yn` char(1) COLLATE utf8mb3_bin DEFAULT 'N',
                         PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=767 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='  ';




CREATE TABLE `board_comment` (
                                 `cno` int NOT NULL AUTO_INCREMENT,
                                 `pcno` int NOT NULL,
                                 `commenter` varchar(100) NOT NULL,
                                 `comment` varchar(2000) NOT NULL,
                                 `register_date` date DEFAULT NULL,
                                 `deleted_yn` varchar(1) DEFAULT 'N',
                                 PRIMARY KEY (`cno`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;



CREATE TABLE `user_info` (
                             `email` varchar(100) NOT NULL,
                             `name` varchar(100) NOT NULL,
                             `password` varchar(100) DEFAULT NULL,
                             `birth_date` date DEFAULT NULL,
                             `reg_date` date NOT NULL,
                             `sns` varchar(100) DEFAULT NULL,
                             PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



insert into user_info(email,name,password,reg_date) values(
                                                           'aaa@aaa.com',
                                                           'test_user',
                                                           '111111',
                                                           now()
                                                          );