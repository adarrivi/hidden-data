DROP TABLE IF EXISTS`book`;
DROP TABLE IF EXISTS`author`;

CREATE TABLE `author` (
    `id` INT(10) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(250) NOT NULL,
    PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `book` (
    `id` INT(10) NOT NULL  AUTO_INCREMENT,
    `title` VARCHAR(250) NOT NULL,
    `authorId` INT(10) NOT NULL,
    `content` MEDIUMBLOB NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `book_author` (`authorId`),
    CONSTRAINT `book_author` FOREIGN KEY (`authorId`) REFERENCES `author` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
