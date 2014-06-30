

CREATE TABLE `member` (
  `id` varchar(15) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `lvl` int(1) unsigned DEFAULT '0',
  `nickname` varchar(8) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `profileimg` char(40) DEFAULT NULL,
  `memo` varchar(1000) DEFAULT NULL,
  `joindate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `article` (
  `idx` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `content` varchar(5000) NOT NULL,
  `writedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `acount` int(10) unsigned DEFAULT '0',
  `alike` int(10) unsigned DEFAULT '0',
  `report` int(10) unsigned DEFAULT '0',
  `secret` int(10) unsigned DEFAULT '0',
  `thumbnail` varchar(40) NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `id` (`id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;


CREATE TABLE `image` (
  `image_idx` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idx` int(10) unsigned NOT NULL,
  `ref` int(10) unsigned DEFAULT '0',
  `ori_name` varchar(40) NOT NULL,
  `file_name` varchar(40) NOT NULL,
  `file_size` int(10) unsigned NOT NULL,
  `tag` varchar(200) DEFAULT NULL,
  `subject` varchar(100) NOT NULL,
  `content` varchar(1000) NOT NULL,
  PRIMARY KEY (`image_idx`),
  KEY `image_ibfk_1` (`idx`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`idx`) REFERENCES `article` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

CREATE TABLE `reply` (
  `reply_idx` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idx` int(10) unsigned NOT NULL,
  `id` varchar(20) NOT NULL,
  `content` varchar(5000) NOT NULL,
  `writedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `report` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`reply_idx`),
  KEY `reply_ibfk_1` (`id`),
  KEY `reply_ibfk_2` (`idx`),
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`idx`) REFERENCES `article` (`idx`) ON DELETE CASCADE,
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

CREATE TABLE `notice` (
  `id` varchar(15) NOT NULL,
  `idx` int(10) unsigned NOT NULL,
  `stat` int(10) unsigned NOT NULL,
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `chk` int(10) unsigned DEFAULT '0',
  KEY `id` (`id`),
  KEY `idx` (`idx`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
  CONSTRAINT `notice_ibfk_2` FOREIGN KEY (`idx`) REFERENCES `article` (`idx`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `interest` (
  `id` varchar(15) NOT NULL,
  `interest` varchar(15) NOT NULL,
  KEY `id` (`id`),
  KEY `interest` (`interest`),
  CONSTRAINT `interest_ibfk_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
  CONSTRAINT `interest_ibfk_2` FOREIGN KEY (`interest`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `creator` (
  `id` varchar(15) NOT NULL,
  `homepage` varchar(20) DEFAULT NULL,
  `cmemo` varchar(2000) DEFAULT NULL,
  `history` varchar(2000) DEFAULT NULL,
  KEY `id` (`id`),
  CONSTRAINT `creator_ibfk_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `emblem` (
  `emblem_no` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `emblem` varchar(50) NOT NULL,
  `eimg` varchar(40) NOT NULL,
  PRIMARY KEY (`emblem_no`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `challenge` (
  `chall_index` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `emblem_no` int(10) unsigned NOT NULL,
  `subject` varchar(50) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `writedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `startdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `enddate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `activation` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`chall_index`),
  KEY `emblem_no` (`emblem_no`),
  CONSTRAINT `challenge_ibfk_1` FOREIGN KEY (`emblem_no`) REFERENCES `emblem` (`emblem_no`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

CREATE TABLE `emblem_storage` (
  `id` varchar(15) NOT NULL,
  `emblem_no` int(10) unsigned NOT NULL,
  `idx` int(10) unsigned NOT NULL,
  `winner` int(10) unsigned DEFAULT '0',
  KEY `id` (`id`),
  KEY `emblem_no` (`emblem_no`),
  KEY `idx` (`idx`),
  CONSTRAINT `emblem_storage_ibfk_1` FOREIGN KEY (`id`) REFERENCES `member` (`id`),
  CONSTRAINT `emblem_storage_ibfk_2` FOREIGN KEY (`emblem_no`) REFERENCES `emblem` (`emblem_no`),
  CONSTRAINT `emblem_storage_ibfk_3` FOREIGN KEY (`idx`) REFERENCES `article` (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;