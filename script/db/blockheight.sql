CREATE TABLE `blockheight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
   `height` int NOT NULL,
   PRIMARY KEY (`id`)
  ) COMMENT='块高信息表' ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
  
insert into blockheight (height,id) values(1,1);