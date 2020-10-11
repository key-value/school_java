CREATE TABLE `LiteAppVersion` ( `Id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Id', `VerNo` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '版本号', `FileUrl` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '升级包下载地址', `VerifyNo` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '升级包验证码', `VerContent` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '版本内容', `CreationTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间', `LastModificationTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间', `EffectTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间', PRIMARY KEY (`Id`) ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='应用版本表';


CREATE TABLE `glass` ( `Id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Id', `VerNo` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '版本号', `FileUrl` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '升级包下载地址', `VerifyNo` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '升级包验证码', `VerContent` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '版本内容', `CreationTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间', `LastModificationTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间', `EffectTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间', PRIMARY KEY (`Id`) ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='应用版本表';



CREATE TABLE `grade` (
  `Id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `GradeNum` INT NOT NULL DEFAULT 0 COMMENT '学期',
  `Sign` VARCHAR (255) NOT NULL DEFAULT '' COMMENT '年级',
  `CreationTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `LastModificationTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`Id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '年级';

