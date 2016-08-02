DROP TABLE IF EXISTS tao_config;  
CREATE TABLE tao_config (  
  config_id integer NOT NULL AUTO_INCREMENT,  
  tao_code varchar(20),
  wish_cata_code varchar(15),
  PRIMARY KEY (config_id)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;
