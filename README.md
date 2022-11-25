# Spring-Message-Board
<br><br><br>

## Query - Create Tables <br>

-- 게시물<br>
CREATE TABLE `board` (<br>
  `seq` int NOT NULL,<br>
  `subject` varchar(200) DEFAULT NULL,<br>
  `content` varchar(1000) DEFAULT NULL,<br>
  `name` varchar(20) DEFAULT NULL,<br>
  `reg_date` varchar(100) DEFAULT NULL,<br>
  `readCount` int DEFAULT NULL,<br>
  `file` varchar(2000) DEFAULT NULL,<br>
  PRIMARY KEY (`seq`)<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;<br>
<br>
<br>
-- 댓글<br>
CREATE TABLE `board_reply` (<br>
  `rseq` int NOT NULL,<br>
  `bseq` int DEFAULT NULL,<br>
  `content` varchar(1000) NOT NULL,<br>
  `name` varchar(20) NOT NULL,<br>
  `reg_date` varchar(100) DEFAULT NULL,<br>
  `rep` int DEFAULT '0',<br>
  `re_level` int DEFAULT '0',<br>
  `re_step` int DEFAULT '0',<br>
  `parent_rseq` int DEFAULT '0',<br>
  `child` int DEFAULT '0',<br>
  PRIMARY KEY (`rseq`),<br>
  KEY `board_reply_ibfk_1` (`bseq`),<br>
  CONSTRAINT `board_reply_ibfk_1` FOREIGN KEY (`bseq`) REFERENCES `board` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;<br>
<br>
<br>
-- 회원<br>
CREATE TABLE `user` (<br>
  `id` varchar(20) NOT NULL,<br>
  `pw` varchar(20) NOT NULL,<br>
  `name` varchar(20) NOT NULL,<br>
  `admin` int DEFAULT '0',<br>
  PRIMARY KEY (`id`)<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;<br>
