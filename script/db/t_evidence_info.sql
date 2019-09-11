CREATE TABLE t_evidence_info (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  block_height int NOT NULL COMMENT '交易所在块高',
  event varchar(128) NOT NULL COMMENT 'event',
  evidence_id varchar(128) NOT NULL COMMENT '证据ID',
  evidence_hash varchar(256) NOT NULL COMMENT '证据hash',
  sign_data varchar(256) DEFAULT '' COMMENT '签名数据',
  factory_address varchar(256) DEFAULT '' COMMENT '工厂合约地址',
  PRIMARY KEY (id)
) COMMENT='证据信息表' ENGINE=InnoDB DEFAULT CHARSET=utf8;